package sample;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Butterfly {
    private Graphics graphics;
    private int width;
    private int height;
    private int state = 0;

    public void setState(int state) {
        this.state = state;
    }

    private ArrayList<Node[]> lines = new ArrayList<>();
    private HashMap<String, Node> points = new HashMap<>();

    private Node startNode = new Node(0, 0);

    public Butterfly(Graphics graphics, int width, int height, int state) {
        this.graphics = graphics;
        this.width = width;
        this.height = height;
        this.state = state;
    }

    public void draw() {
        fillInPoints();
        defineLines();
        drawLines();
    }

    public void drawAt(int x, int y) {
        startNode = new Node(x, y);
        draw();
    }

    private void drawLines() {
        for (Node[] line : lines) {
            drawLine(line[0], line[1]);
        }
    }

    private void drawLine(Node start, Node end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        graphics.drawLine(x1, y1, x2, y2);
    }

    private void fillInPoints() {
        points.put("A", startNode);
        points.put("B", new Node(startNode.getX() + width, startNode.getY()));
        points.put("C", defineDownNode(points.get("A")));
        points.put("D", defineDownNode(points.get("B")));
        points.put("E", getXPoint());

        Node APoint = points.get("A");
        Node BPoint = points.get("B");
        if (state == 1) {
            points.put("Ad", new Node(APoint.getX() + width * 0.15, APoint.getY() + height * 0.47));
            points.put("Bd", new Node(BPoint.getX() - width * 0.15, BPoint.getY() + height * 0.47));
        } else if (state == 0) {
            points.put("Ad", new Node(APoint.getX(), APoint.getY() + height * 0.5));
            points.put("Bd", new Node(BPoint.getX(), BPoint.getY() + height * 0.5));
        }

        Node CPoint = points.get("C");
        points.put("Cs", new Node(CPoint.getX() - width * 0.125, CPoint.getY()));

        Node DPoint = points.get("D");
        points.put("Ds", new Node(DPoint.getX() + width * 0.125, DPoint.getY()));
    }

    private Node getXPoint() {
        double h = width / (2 * Math.tan(Math.asin((0.75 * width) / (Math.sqrt(Math.pow((0.75 * width), 2) + Math.pow(height, 2))))));
        double w = width / 2;
        return new Node(w, h);
    }

    private void defineLines() {
        lines.add(new Node[]{points.get("A"), points.get("C")});
        lines.add(new Node[]{points.get("B"), points.get("D")});
        lines.add(new Node[]{points.get("A"), points.get("Ad")});
        lines.add(new Node[]{points.get("B"), points.get("Bd")});
        lines.add(new Node[]{points.get("C"), points.get("Cs")});
        lines.add(new Node[]{points.get("D"), points.get("Ds")});
        lines.add(new Node[]{points.get("Ad"), points.get("E")});
        lines.add(new Node[]{points.get("Bd"), points.get("E")});
        lines.add(new Node[]{points.get("Cs"), points.get("E")});
        lines.add(new Node[]{points.get("Ds"), points.get("E")});
    }

    private Node defineDownNode(Node fromNode) {
        if (fromNode.getX() < width / 2) {
            return new Node(width * 0.75 + startNode.getX(), height);
        } else if (fromNode.getX() > width / 2) {
            return new Node(width * 0.25 + startNode.getX(), height);
        }
        return null;
    }
}
