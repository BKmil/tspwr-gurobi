import java.util.*;

public class Ant {
    List<Integer> tour;
    boolean[] visited;

    double fuel;
    double cost;

    public Ant(int n, double fuelCap) {
        tour = new ArrayList<>();
        visited = new boolean[n];

        fuel = fuelCap;
        cost = 0;
    }
}
