package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		Point p = new Point(mouseX, mouseY);

		// MENU STATE
		if (this.game.getGameState() == STATE.MENU) {

			// CLICK ON PLAY BUTTON
			if (PLAY_BUTTON.contains(p)) {
				this.game.setGameState(STATE.GAME);

				// AGGIUNGE UN GIOCATORE
				this.handler.addGameObject(new Player(r.nextInt(Game.WIDTH - Player.WIDTH),
						r.nextInt(Game.HEIGHT - Player.HEIGHT), ID.Player, this.handler));
				// AGGIUNGE UN NEMICO
				this.handler.addGameObject(new BasicEnemy(r.nextInt(Game.WIDTH - BasicEnemy.WIDTH),
						r.nextInt(Game.HEIGHT - BasicEnemy.HEIGHT), ID.BasicEnemy, this.handler));
			}

			// CLICK ON HELP BUTTON
			if (HELP_BUTTON.contains(p)) {
				this.game.setGameState(STATE.HELP);
			}

			// CLICK ON QUIT BUTTON
			if (QUIT_BUTTON.contains(p)) {
				System.exit(0);
			}
		} // END MENU STATE

		// HELP STATE
		else if (this.game.getGameState() == STATE.HELP) {
			// CLICK ON BACK BUTTON FOR HELP
			if (BACK_BUTTON.contains(p)) {
				this.game.setGameState(STATE.MENU);
			}
		} // END HELP STATE

		// GAME OVER STATE
		else if (this.game.getGameState() == STATE.GAME_OVER) {
			// CLICK ON TRY AGAIN BUTTON
			if (TRY_AGAIN_BUTTON.contains(p)) {
				this.game.setGameState(STATE.GAME);
				this.handler.clearEnemys();
				// AGGIUNGE UN NEMICO BASE
				this.handler.addGameObject(new BasicEnemy(r.nextInt(Game.WIDTH - BasicEnemy.WIDTH),
						r.nextInt(Game.HEIGHT - BasicEnemy.HEIGHT), ID.BasicEnemy, this.handler));

				this.hud.setLevel(1);
				this.hud.setScore(0);
			}

			// CLICK ON QUIT BUTTON
			if (QUIT_BUTTON.contains(p)) {
				System.exit(0);
			}
		}
		// PAUSE STATE
		else if (this.game.getGameState() == STATE.PAUSE) {
			if (RESUME_BUTTON.contains(p)) {
				this.game.setGameState(STATE.GAME);
			}

			// CLICK ON QUIT BUTTON
			if (QUIT_BUTTON.contains(p)) {
				System.exit(0);
			}
		}
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (this.game.getGameState() == STATE.MENU) {
			Font font1 = new Font("Arial", 1, 100);
			g2.setFont(font1);
			g2.setColor(Color.BLUE);
			g2.drawString("MENU", Game.WIDTH / 2 - Menu.WIDTH / 2, Game.HEIGHT / 5 - Menu.HEIGHT / 2);

			Font font2 = new Font("Arial", 1, 60);
			g2.setFont(font2);

			// PLAY BUTTON
			g2.setColor(Color.RED);
			g2.draw(PLAY_BUTTON);
			g2.drawString("PLAY", Game.WIDTH / 2 - Menu.WIDTH / 4, Game.HEIGHT / 3 - Menu.HEIGHT / 3);
			// HELP BUTTON
			g2.setColor(Color.GREEN);
			g2.draw(HELP_BUTTON);
			g2.drawString("HELP", Game.WIDTH / 2 - Menu.WIDTH / 4, Game.HEIGHT / 2 + Menu.HEIGHT / 4);
			// QUIT BUTTON
			g2.setColor(Color.ORANGE);
			g2.draw(QUIT_BUTTON);
			g2.drawString("QUIT", Game.WIDTH / 2 - Menu.WIDTH / 4, Game.HEIGHT / 2 + 14 / 5 * Menu.HEIGHT + 30);
		} else if (this.game.getGameState() == STATE.HELP) {
			Font font1 = new Font("Arial", 1, 100);
			g2.setFont(font1);
			g2.setColor(Color.GREEN);
			g2.drawString("HELP", Game.WIDTH / 2 - Menu.WIDTH / 2, Game.HEIGHT / 5 - Menu.HEIGHT / 2);
			// BACK BUTTON
			Font font2 = new Font("Arial", 1, 40);
			g2.setFont(font2);
			g2.drawString("USE ARROWS TO MOVE THE PLAYER AND AVOID ENEMIES,", 60, 300);
			g2.drawString("ESC KEY TO PAUSE(while playing) / EXIT.", 60, 400);

			Font font3 = new Font("Arial", 1, 50);
			g2.setFont(font3);
			g2.draw(BACK_BUTTON);
			g2.drawString("BACK", Game.WIDTH / 2 - Menu.WIDTH / 3, Game.HEIGHT / 2 + 2 * Menu.HEIGHT + 25);
		} else if (this.game.getGameState() == STATE.GAME_OVER) {
			Font font1 = new Font("Arial", 1, 150);
			g2.setFont(font1);
			g2.setColor(Color.RED);
			g2.drawString("GAME OVER", Game.WIDTH / 3 - Menu.WIDTH + 25, Game.HEIGHT / 3 - Menu.HEIGHT / 2);
			// TRY AGAIN BUTTON
			Font font2 = new Font("Arial", 1, 40);
			g2.setFont(font2);
			g2.drawString("You lost with a score of: " + this.hud.getScore(), 60, 300);

			Font font3 = new Font("Arial", 1, 50);
			g2.setFont(font3);
			g2.draw(TRY_AGAIN_BUTTON);
			g2.drawString("TRY AGAIN", Game.WIDTH / 2 - Menu.WIDTH * 3 / 7 - 8, Game.HEIGHT / 2 + 25);

			// QUIT BUTTON
			g2.setColor(Color.ORANGE);
			g2.draw(QUIT_BUTTON);
			g2.drawString("QUIT", Game.WIDTH / 2 - Menu.WIDTH / 4 + 10, Game.HEIGHT / 2 + 2 * Menu.HEIGHT + 27);

		} else if (this.game.getGameState() == STATE.PAUSE) {
			Font font1 = new Font("Arial", 1, 150);
			g2.setFont(font1);
			g2.setColor(Color.RED);
			g2.drawString("PAUSE", Game.WIDTH / 3 - 50, Game.HEIGHT / 3 - Menu.HEIGHT / 2);

			// RESUME BUTTON
			Font font2 = new Font("Arial", 1, 60);
			g2.setFont(font2);
			g2.setColor(Color.ORANGE);

			g2.draw(RESUME_BUTTON);
			g2.drawString("RESUME", Game.WIDTH / 2 - Game.WIDTH / 10, Game.HEIGHT / 2 + 30);

			// QUIT BUTTON
			g2.draw(QUIT_BUTTON);
			g2.drawString("QUIT", Game.WIDTH / 2 - Menu.WIDTH / 4 - 3, Game.HEIGHT / 2 + 2 * Menu.HEIGHT + 30);

		}

	}

	private static final int WIDTH = 300, HEIGHT = 100;
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;

	private static final Rectangle PLAY_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT / 4 - Menu.HEIGHT / 2, Menu.WIDTH, Menu.HEIGHT);
	private static final Rectangle HELP_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT / 2 - Menu.HEIGHT / 2, Menu.WIDTH, Menu.HEIGHT);
	private static final Rectangle QUIT_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT - 2 * Menu.HEIGHT, Menu.WIDTH, Menu.HEIGHT);
	private static final Rectangle TRY_AGAIN_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT - 4 * Menu.HEIGHT, Menu.WIDTH, Menu.HEIGHT);
	private static final Rectangle BACK_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT - 2 * Menu.HEIGHT, Menu.WIDTH * 5 / 6, Menu.HEIGHT);
	private static final Rectangle RESUME_BUTTON = new Rectangle(Game.WIDTH / 2 - Menu.WIDTH / 2,
			Game.HEIGHT - 4 * Menu.HEIGHT, Menu.WIDTH, Menu.HEIGHT);
}
