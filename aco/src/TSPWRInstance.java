public class TSPWRInstance {
    private double[][] distanceMatrix;
    private double fuelCap;

    public TSPWRInstance(double[][] distanceMatrix, double fuelCap) {
        this.distanceMatrix = distanceMatrix;
        this.fuelCap = fuelCap;
    }

    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public double getFuelCap() {
        return fuelCap;
    }

    public int size() {
        return distanceMatrix.length;
    }
}
