import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Map;

public class GraphFrame extends JFrame {
    private Map<Integer, Long> avgAddTimes;
    private Map<Integer, Long> avgRemoveTimes;
    private Map<Integer, Long> totalAddTimes;
    private Map<Integer, Long> totalRemoveTimes;
    private String title;

    public GraphFrame(Map<Integer, Long> avgAddTimes, Map<Integer, Long> avgRemoveTimes,
                      Map<Integer, Long> totalAddTimes, Map<Integer, Long> totalRemoveTimes, String title) {
        this.avgAddTimes = avgAddTimes;
        this.avgRemoveTimes = avgRemoveTimes;
        this.totalAddTimes = totalAddTimes;
        this.totalRemoveTimes = totalRemoveTimes;
        this.title = title;

        setTitle(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid
        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 50; i < getWidth() - 50; i += 100) {
            g2.drawLine(i, 50, i, getHeight() - 50);
        }
        for (int i = 50; i < getHeight() - 50; i += 50) {
            g2.drawLine(50, i, getWidth() - 50, i);
        }

        // Draw axis
        g2.setColor(Color.BLACK);
        g2.drawLine(50, getHeight() - 50, getWidth() - 50, getHeight() - 50); // X-axis
        g2.drawLine(50, getHeight() - 50, 50, 50); // Y-axis

        // Draw labels for axes
        g2.drawString("Element Count", getWidth() / 2 - 40, getHeight() - 20);
        g2.drawString("Time (log scale)", 10, getHeight() / 2);

        // Draw graphs with logarithmic scaling and numeric values
        if (!avgAddTimes.isEmpty() && !avgRemoveTimes.isEmpty()) {
            drawGraph(g2, avgAddTimes, Color.BLUE, "Average Add");
            drawGraph(g2, avgRemoveTimes, Color.RED, "Average Remove");
        }
        if (!totalAddTimes.isEmpty() && !totalRemoveTimes.isEmpty()) {
            drawGraph(g2, totalAddTimes, Color.GREEN, "Total Add");
            drawGraph(g2, totalRemoveTimes, Color.ORANGE, "Total Remove");
        }

        // Draw legend based on the graph type
        drawLegend(g2);
    }

    private void drawGraph(Graphics2D g2, Map<Integer, Long> times, Color color, String label) {
        g2.setColor(color);
        int prevX = 50, prevY = getHeight() - 50;
        int i = 0;

        for (Map.Entry<Integer, Long> entry : times.entrySet()) {
            int x = 50 + (i * 100);  // Adjust x-spacing
            int y = getHeight() - 50 - (int) (Math.log10(entry.getValue()) * 50);  // Logarithmic scaling for Y-axis

            // Draw line between points
            if (i > 0) {
                g2.draw(new Line2D.Double(prevX, prevY, x, y));
            }
            prevX = x;
            prevY = y;

            // Draw numeric value near each point
            g2.drawString(String.valueOf(entry.getValue()), x, y - 5);

            i++;
        }
    }

    private void drawLegend(Graphics2D g2) {
        int legendX = getWidth() - 150;
        int legendY = 60;
        g2.setColor(Color.BLACK);
        g2.drawString("Legend:", legendX, legendY);

        if (!avgAddTimes.isEmpty() && !avgRemoveTimes.isEmpty()) {
            g2.setColor(Color.BLUE);
            g2.drawString("Average Add", legendX, legendY + 20);

            g2.setColor(Color.RED);
            g2.drawString("Average Remove", legendX, legendY + 40);
        }

        if (!totalAddTimes.isEmpty() && !totalRemoveTimes.isEmpty()) {
            g2.setColor(Color.GREEN);
            g2.drawString("Total Add", legendX, legendY + 20);

            g2.setColor(Color.ORANGE);
            g2.drawString("Total Remove", legendX, legendY + 40);
        }
    }
}
