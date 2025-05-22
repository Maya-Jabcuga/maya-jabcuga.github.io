package etchASketch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EtchASketch extends JPanel implements KeyListener {

    private int x = 250;
    private int y = 250;
    private final int STEP = 5;

    private final BufferedImage canvas;
    private final SketchControls controls = new SketchControls();

    public EtchASketch() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = canvas.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 500, 500);
        g2.setColor(Color.BLACK);
        g2.fillOval(x, y, 5, 5); // initial dot
        g2.dispose();

        Timer timer = new Timer(16, e -> updateAndRepaint());
        timer.start();
    }

    private void updateAndRepaint() {
        int dx = controls.getMoveDirectionX();
        int dy = controls.getMoveDirectionY();
        if (dx != 0 || dy != 0) {
            x += dx * STEP;
            y += dy * STEP;
            Graphics2D g2 = canvas.createGraphics();
            g2.setColor(Color.BLACK);
            g2.fillOval(x, y, 5, 5);
            g2.dispose();
            repaint();
            System.out.println("drawing at " + x + ", " + y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Painting image...");
        g.drawImage(canvas, 0, 0, null);
    }

    @Override public void keyPressed(KeyEvent e) {
        controls.handleKeyPressed(e);
    }

    @Override public void keyReleased(KeyEvent e) {
        controls.handleKeyReleased(e);
    }

    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Etch A Sketch");
        EtchASketch panel = new EtchASketch();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        panel.requestFocusInWindow();
        frame.repaint(); // <-- force first paint
    }
}
