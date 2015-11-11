import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame {

	public static void main(String[] args) {
		final JFrame frame = new JFrame("2048 Circle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final DataModel model = new DataModel();
		final GameBoard board = new GameBoard(model);
		board.setPreferredSize(new Dimension(400, 400));

		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(board, BorderLayout.CENTER);

		// final JPanel controlPanel = new JPanel(new GridBagLayout());
		// c.add(controlPanel, BorderLayout.SOUTH);
		// initializeControlPanel(frame, model, c, currentCircle, controlPanel);

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				return;
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					board.currentCircle[0] = 1;
					frame.repaint();
					break;
				case KeyEvent.VK_2:
					board.currentCircle[0] = 2;
					frame.repaint();
					break;
				case KeyEvent.VK_3:
					board.currentCircle[0] = 3;
					frame.repaint();
					break;
				case KeyEvent.VK_4:
					board.currentCircle[0] = 4;
					frame.repaint();
					break;
				// In
				case KeyEvent.VK_UP:
					model.combineOut();
					model.randomStep();
					frame.repaint();
					break;
				// Out
				case KeyEvent.VK_DOWN:
					model.combineIn();
					model.randomStep();
					frame.repaint();
					break;
				// CCW
				case KeyEvent.VK_LEFT:
					if (board.currentCircle[0] == 0) {
						break;
					}
					model.rotateData(board.currentCircle[0] - 1, 1);
					frame.repaint();
					break;
				// CW
				case KeyEvent.VK_RIGHT:
					if (board.currentCircle[0] == 0) {
						break;
					}
					model.rotateData(board.currentCircle[0] - 1, -1);
					frame.repaint();
					break;

				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		frame.pack();
		frame.setVisible(true);
	}
	

}
