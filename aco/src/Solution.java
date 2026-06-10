import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> tour;
    private double cost;

    public Solution(List<Integer> tour, double cost) {
        this.tour = new ArrayList<Integer>(tour);
        this.cost = cost;
    }

    public List<Integer> getTour() {
        return tour;
    }

    public double getCost() {
        return cost;
    }
}
