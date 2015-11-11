import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

class GameBoard extends JPanel {

	protected final int[] currentCircle = { 1 };
	private static final long serialVersionUID = 1553697124437310276L;

	private final DataModel model;

	public GameBoard(DataModel model) {
		this.model = model;
	}

	@Override
	protected void paintComponent(Graphics g) {
		draw((Graphics2D) g);
		if (model.gameIsOver()) {
			displayGameOverScreen((Graphics2D) g);
		}
	}

	protected void displayGameOverScreen(Graphics2D g2) {
		int w = getWidth();
		int h = getHeight();
		int w2 = w / 2;
		int h2 = h / 2;
		int radiusStep = Math.min(w, h) / ((DataModel.NUM_CIRCLES + 1) * 2);

		// Anti-Aliasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Font
		Font font = new Font("Impact", Font.BOLD, 16);
		g2.setFont(font);

		// Clip output to largest circle.
		Ellipse2D clipOval = new Ellipse2D.Float();
		// clipOval.setFrame(0, 0, w, h);
		clipOval.setFrame(w2 - radiusStep + 1, h2 - radiusStep + 1,
				2 * radiusStep + 1, 2 * radiusStep + 1);
		g2.clip(clipOval);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(w2 - radiusStep + 1, h2 - radiusStep + 1,
				2 * radiusStep + 1, 2 * radiusStep + 1);

		// Display max score in the middle of the circle
		String gameOv = "Game Over";
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(gameOv, g2);
		g2.setColor(Color.RED);
		AffineTransform orig = g2.getTransform();
		g2.translate(w2, h2);
		g2.drawChars(gameOv.toCharArray(), 0, gameOv.toCharArray().length,
				(int) (-rect.getWidth() / 2) + 1, (int) (rect.getHeight() / 2));
		g2.setTransform(orig);
	}

	private void draw(Graphics2D g2) {
		int w = getWidth();
		int h = getHeight();
		int w2 = w / 2;
		int h2 = h / 2;
		int radiusStep = Math.min(w, h) / ((DataModel.NUM_CIRCLES + 1) * 2);
		int radiusStep2 = radiusStep / 2;
		double offsetAngle = Math.PI / (DataModel.NUM_SECTORS);

		// Anti-Aliasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(2));

		// Draw rings, leaving space for a smaller, inner circle.
		for (int i = DataModel.NUM_CIRCLES - 1; i >= 0; i--) {
			int radius = radiusStep * (i + 2);
			// If it is the first circle, fill in the oval.

			g2.setColor(Color.GRAY);
			g2.drawOval(w2 - radius, h2 - radius, 2 * radius, 2 * radius);

			// Draw segments.
			for (int j = 0; j < DataModel.NUM_SECTORS; j++) {
				char[] chars = model.getCellText(i, j);
				int val = model.getCellValue(i, j);
				if (val == 0) {
					g2.setColor(Color.LIGHT_GRAY);
				} else {
					if (i == currentCircle[0] - 1) {
						g2.setColor(getHighlightedSectorColor(val));
					} else {
						g2.setColor(getSectorColor(val));
					}
				}
				Arc2D arc = new Arc2D.Double(w2 - radius, h2 - radius,
						2 * radius, 2 * radius, (j - 1) * 45, 45, Arc2D.PIE);
				g2.fill(arc);
				g2.setColor(getBorderColor(val));
				g2.draw(arc);

				// Set Font
				Font font = new Font("Impact", Font.BOLD, 20);
				g2.setFont(font);

				FontMetrics fm = g2.getFontMetrics();
				Rectangle2D rect = fm.getStringBounds(chars.toString(), g2);
				g2.setColor(getFontColor(val));
				AffineTransform orig = g2.getTransform();
				// Bring origin to the center
				// Rotate to proper sector angle
				// Translate into proper ring
				// Rotate 90 degrees
				g2.translate(w2, h2);
				g2.rotate(-Math.PI * j / DataModel.NUM_CIRCLES + offsetAngle);
				g2.translate(radius - radiusStep2, 0);
				// rotate number to be facing bottom-into center of circle
				g2.rotate(Math.PI / 2);
				g2.drawChars(chars, 0, chars.length,
						(int) (-rect.getHeight() / 2),
						(int) (rect.getHeight() / 2));
				g2.setTransform(orig);
			}
		}

		// Clip output to largest circle.
		int radius = radiusStep * (DataModel.NUM_CIRCLES + 1);
		Ellipse2D clipOval = new Ellipse2D.Float();
		// clipOval.setFrame(0, 0, w, h);
		clipOval.setFrame(w2 - radius, h2 - radius, 2 * radius, 2 * radius);
		g2.clip(clipOval);

		g2.setColor(Color.GRAY);
		g2.drawLine(0, h2, w, h2); // Horizontal line.
		g2.drawLine(w2, 0, w2, h); // Vertical line.
		// g2.drawLine(0, 0, w, h); // Left diagonal.
		int radius45 = (int) (radius * Math.sqrt(2));
		g2.drawLine(w2 - radius45, h2 - radius45, w2 + radius45, h2 + radius45); // Left
																					// diagonal.
		g2.drawLine(w2 - radius45, h2 + radius45, w2 + radius45, h2 - radius45); // Right
																					// diagonal.
		// Draw the center circle, unused for data.
		g2.drawOval(w2 - radiusStep + 1, h2 - radiusStep + 1,
				2 * radiusStep + 1, 2 * radiusStep + 1);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(w2 - radiusStep + 1, h2 - radiusStep + 1,
				2 * radiusStep + 1, 2 * radiusStep + 1);

		// Draw yellow borders to highlight the current circle.
		radius = radiusStep * (currentCircle[0] + 1);
		if (currentCircle[0] == 4) {
			g2.setStroke(new BasicStroke(6));
		} else {
			g2.setStroke(new BasicStroke(3));
		}

		g2.setColor(new Color(0xEDAB16));
		g2.drawOval(w2 - radius, h2 - radius, 2 * radius, 2 * radius);
		radius = radiusStep * currentCircle[0];
		g2.setStroke(new BasicStroke(3));
		g2.drawOval(w2 - radius, h2 - radius, 2 * radius, 2 * radius);

	}

	private Color getSectorColor(int value) {
		switch (value) {
		case 2:
			return new Color(0xCEECF5);

		case 4:
			return new Color(0xA9E2F3);

		case 8:
			return new Color(0x81BEF7);

		case 16:
			return new Color(0x5882fa);

		case 32:
			return new Color(0x1d41db);

		case 64:
			return new Color(0x12288a);

		case 128:
			return new Color(0x6822cf);

		case 256:
			return new Color(0xb71acf);

		case 512:
			return new Color(0xe124f3);

		case 1024:
			return new Color(0xcf1577);

		case 2048:
			return new Color(0xcc1040ff);

		default:
			return Color.WHITE;
		}
	}

	private Color getHighlightedSectorColor(int value) {
		switch (value) {
		case 2:
			return new Color(0xD5EFD0);

		case 4:
			return new Color(0xB6E6CF);

		case 8:
			return new Color(0x94C8D2);

		case 16:
			return new Color(0x7195D4);

		case 32:
			return new Color(0x3F5EBA);

		case 64:
			return new Color(0x364875);

		case 128:
			return new Color(0x7F43B0);

		case 256:
			return new Color(0xC23CB0);

		case 512:
			return new Color(0xE645CF);

		case 1024:
			return new Color(0xD63865);

		case 2048:
			return new Color(0xD43436);

		default:
			return Color.WHITE;
		}
	}

	private Color getFontColor(int value) {
		switch (value) {
		case 2:
		case 4:
		case 8:
			return new Color(0x12288a);
		default:
			return Color.WHITE;
		}
	}

	private Color getBorderColor(int value) {
		switch (value) {
		case 2:
			return new Color(0xb5edfd);

		case 4:
			return new Color(0x84c2fc);

		case 8:
			return new Color(0x5eaffa);

		case 16:
			return new Color(0x264de8);

		case 32:
			return new Color(0x223690);

		case 64:
			return Color.BLACK;

		case 128:
			return new Color(0x6a22d2);

		case 256:
			return new Color(0x7d37ab);

		case 512:
			return new Color(0x7a254f);

		case 1024:
			return new Color(0xb50d5b);

		case 2048:
			return new Color(0x660000);

		default:
			return Color.GRAY;
		}
	}

}
