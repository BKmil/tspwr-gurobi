import java.util.*;

public class Main {

    public static void main(String[] args) {

        int n = 15;
        int MAP_SIZE = 200;

        Random random = new Random(42);

        List<City> cities = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = random.nextInt(MAP_SIZE + 1);
            int y = random.nextInt(MAP_SIZE + 1);
            cities.add(new City(x, y));
        }

        System.out.println("\ncities = [");
        for (City c : cities) {
            System.out.println("  (" + c.x + ", " + c.y + "),");
        }
        System.out.println("]\n");

        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i != j) {
                    City c1 = cities.get(i);
                    City c2 = cities.get(j);

                    double dx = c1.x - c2.x;
                    double dy = c1.y - c2.y;

                    double d = Math.sqrt(dx * dx + dy * dy);

                    dist[i][j] = Math.round(d * 100.0) / 100.0;
                } else {
                    dist[i][j] = 0.0;
                }
            }
        }

        double FUEL_CAPACITY = 2000;

        TSPWRInstance instance = new TSPWRInstance(dist, FUEL_CAPACITY);

        System.out.println("Distance matrix ready.\n");

        Optimizer opt = new Optimizer(
                instance, 100, 50, 0.5, 4.0, 0.2, 50.0
        );

        long start = System.nanoTime();
        Solution best = opt.solve();
        long end = System.nanoTime();

        System.out.println("aco_result = {");
        System.out.println("  \"n\": " + n + ",");
        System.out.println("  \"cost\": " + best.getCost() + ",");
        System.out.println("  \"time_ns\": " + (end - start));
        System.out.println("}");

        System.out.println("\nBest cost (Java ACO): " + best.getCost());
        System.out.println("Execution time (s): " + (end - start) / 1_000_000_000.0);
    }
}