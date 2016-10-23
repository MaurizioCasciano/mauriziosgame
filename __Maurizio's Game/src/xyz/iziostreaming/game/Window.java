package xyz.iziostreaming.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends Canvas{
	
	/**
	 * Creates a new Window
	 * @param width The width of the Window.
	 * @param height The height of the Window.
	 * @param title The title of the Window.
	 * @param game The game to display on the Window.
	 */
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		/*If the component is null, the window is placed in the center of the screen.*/
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}
