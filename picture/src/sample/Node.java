package sample;

public class Node {
    private double x = 0;
    private double y = 0;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}
