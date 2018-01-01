package sample;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends Applet implements Runnable, MouseListener {
    private static boolean mouseClicked = false;
    private Butterfly butterfly;
    private int lastState = 0;
    Thread animator;

    public void paint(Graphics g) {
        butterfly = new Butterfly(g, 400, 400, lastState);
        butterfly.draw();
    }

    public void init() {
        addMouseListener(this);
    }

    public void start() {
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        if (mouseClicked) {
            while (Thread.currentThread() == animator) {
                lastState = (lastState + 1) % 2;
                repaint();
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String... args) {
        new Main();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        this.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
