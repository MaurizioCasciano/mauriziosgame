package xyz.iziostreaming.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Maurizio
 * 
 *         Controlla i tasti premuti sulla tastiera
 */

public class KeyInput extends KeyAdapter {

	public KeyInput(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		this.keyDown = new boolean[4];

		for (int i = 0; i < this.keyDown.length; i++) {
			this.keyDown[i] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempGameObject = handler.object.get(i);

			if (tempGameObject.getId() == ID.Player) {

				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					tempGameObject.setVelocityY(-SPEED);
					this.keyDown[0] = true;
				}

				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					tempGameObject.setVelocityY(SPEED);
					this.keyDown[1] = true;
				}

				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempGameObject.setVelocityX(-SPEED);
					this.keyDown[2] = true;
				}

				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempGameObject.setVelocityX(SPEED);
					this.keyDown[3] = true;
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			if (this.game.getGameState() != STATE.GAME) {
				System.exit(0);
			} else {
				this.game.setGameState(STATE.PAUSE);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempGameObject = handler.object.get(i);

			if (tempGameObject.getId() == ID.Player) {

				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					// tempGameObject.setVelocityY(0);
					this.keyDown[0] = false;
				}

				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					// tempGameObject.setVelocityY(0);
					this.keyDown[1] = false;
				}

				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					// tempGameObject.setVelocityX(0);
					this.keyDown[2] = false;
				}

				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					// tempGameObject.setVelocityX(0);
					this.keyDown[3] = false;
				}
				if (key == KeyEvent.VK_SPACE) {
					tempGameObject.setVelocityY(0);
				}

				// VERTICAL MOVEMENT
				if (!this.keyDown[0] && !this.keyDown[1]) {
					tempGameObject.setVelocityY(0);
				}

				// HORIZONTAL MOVEMENT
				if (!this.keyDown[2] && !this.keyDown[3]) {
					tempGameObject.setVelocityX(0);
				}
			}
		}
	}

	/**************************************************************************************/
	private Handler handler;
	private boolean[] keyDown;
	private Game game;
	private static int SPEED = 7;
}
