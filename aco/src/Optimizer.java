import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Optimizer {
    private double[][] pheromone;

    private TSPWRInstance instance;

    private int numAnts;
    private int maxIterations;

    private double alpha;
    private double beta;

    private double rho;
    private double Q;

    private Random random = new Random();

    public Optimizer(TSPWRInstance instance,
                     int numAnts,
                     int maxIterations,
                     double alpha,
                     double beta,
                     double rho,
                     double Q) {

        this.instance = instance;
        this.numAnts = numAnts;
        this.maxIterations = maxIterations;

        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        this.Q = Q;
    }

    private void initializePhermones() {
        int n = instance.size();

        pheromone = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheromone[i][j] = 1.0;
            }
        }
    }

    private Ant constructSolution() {
        int n = instance.size();

        Ant ant = new Ant(
                n,
                instance.getFuelCap()
        );

        double[][] d = instance.getDistanceMatrix();

        int current = 0;

        ant.tour.add(0);
        ant.visited[0] = true;

        while (ant.tour.size() < n) {
            int next = chooseNextCity(ant, current);

            if (next == -1) {
                ant.cost = Double.MAX_VALUE;
                return ant;
            }

            ant.cost += d[current][next];
            ant.fuel -= d[current][next];

            ant.tour.add(next);
            ant.visited[next] = true;
            current = next;
        }

        ant.cost += d[current][0];
        ant.fuel -= d[current][0];

        ant.tour.add(0);

        if (ant.fuel < 0) {
            ant.cost = Double.MAX_VALUE;
        }

        return ant;
    }

    private int chooseNextCity(Ant ant, int current) {
        double[][] d = instance.getDistanceMatrix();

        List<Integer> feasible = new ArrayList<>();

        for (int j = 1; j < instance.size(); j++) {
            if (ant.visited[j]) {
                continue;
            }

            double fuelNeeded = d[current][j] + d[j][0];

            if (ant.fuel >= fuelNeeded) {
                feasible.add(j);
            }
        }

        if (feasible.isEmpty()) {
            return -1;
        }

        double denom = 0.0;

        for (int j : feasible) {
            double tau = Math.pow(pheromone[current][j], alpha);

            double eta = Math.pow(1.0 / (d[current][j] + 0.0001), beta);

            denom += tau * eta;
        }

        double r = random.nextDouble() * denom;

        double cumulative = 0.0;

        for (int j : feasible) {
            double tau = Math.pow(pheromone[current][j], alpha);

            double eta = Math.pow(1.0 / (d[current][j] + 0.0001), beta);

            cumulative += tau * eta;

            if (cumulative >= r) {
                return j;
            }
        }

        return feasible.getLast();
    }

    private void evaporate() {
        int n = instance.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheromone[i][j] *= (1.0 - rho);

                if (pheromone[i][j] < 0.0001) {
                    pheromone[i][j] = 0.0001;
                }
            }
        }
    }

    private void depositPheromones(Solution best) {
        if (best == null){
            return;
        }

        double delta = Q / best.getCost();

        List<Integer> tour = best.getTour();

        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i);
            int to = tour.get(i + 1);

            pheromone[from][to] += delta;
            pheromone[to][from] += delta;
        }
    }

    public Solution solve() {
        initializePhermones();

        Solution globalBest = null;

        for(int iter=0; iter<maxIterations; iter++) {
            Ant iterationBest = null;

            for (int k = 0; k < numAnts; k++) {
                Ant ant = constructSolution();

                if (ant.cost == Double.MAX_VALUE) {
                    continue;
                }

                if (iterationBest == null || ant.cost < iterationBest.cost) {
                    iterationBest = ant;
                }
            }

            if (iterationBest != null) {
                if (globalBest == null || iterationBest.cost < globalBest.getCost()) {
                    globalBest = new Solution(iterationBest.tour, iterationBest.cost);
                }
            }

            evaporate();

            depositPheromones(globalBest);

            System.out.println("Iteration: " + iter + " best: " + (globalBest == null ? "-" : globalBest.getCost()));
        }

        return globalBest;
    }
}
