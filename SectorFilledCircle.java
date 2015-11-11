import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SectorFilledCircle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3020400109026502819L;

	@Override
	protected void paintComponent(Graphics g) {
		draw((Graphics2D) g);
	}

	private void draw(Graphics2D g2) {
		int w = getWidth();
		int h = getHeight();
		int w2 = w / 2;
		int h2 = h / 2;
		int radiusStep = Math.min(w, h) / ((DataModel.NUM_CIRCLES + 1) * 2);
		Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN,
				Color.YELLOW, Color.ORANGE };
		for (int i = DataModel.NUM_CIRCLES - 1; i >= 0; i--) {
			int radius = radiusStep * (i + 2);
			// If it is the first circle, fill in the oval.

			int x = w2 - radius;
			int y = h2 - radius;
			g2.setColor(Color.LIGHT_GRAY);
			g2.drawOval(x, y, 2 * radius, 2 * radius);

			for (int j = 0; j < DataModel.NUM_SECTORS; j++) {
				Arc2D arc = new Arc2D.Double(x, y, 2 * radius, 2 * radius, j * 45,
						45, Arc2D.PIE);
				g2.setColor(colors[(int)(Math.random() * colors.length)]);
				g2.fill(arc);
				g2.setColor(Color.MAGENTA);
				g2.draw(arc);
			}
	
		}
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame("2048 Circle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SectorFilledCircle sect = new SectorFilledCircle();
		sect.setPreferredSize(new Dimension(400, 400));

		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(sect, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

	}
}
