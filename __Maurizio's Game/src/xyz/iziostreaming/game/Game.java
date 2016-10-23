package xyz.iziostreaming.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import xyz.iziostreaming.game.grafix.Assets;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public Game() {
		Game.maxScore = 0;

		if (!Game.MAIN_DIRECTORY.exists()) {
			try {
				Game.MAIN_DIRECTORY.mkdirs();
				Game.readMeFile.createNewFile();
				PrintWriter pw = new PrintWriter(Game.readMeFile);
				pw.println(Game.GAME_NAME);
				pw.println(Game.readMe);
				pw.println("\n" + Game.enjoy);
				pw.close();
				Game.MUSIC_DIRECTORY.mkdirs();
				Game.SCORES_DIRECTORY.mkdirs();
				Game.maxScoreFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (SecurityException s) {
				s.printStackTrace();
				System.exit(1);
			}
		}

		if (!MUSIC_DIRECTORY.exists()) {
			try {
				Game.MUSIC_DIRECTORY.mkdirs();
			} catch (SecurityException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		if (!SCORES_DIRECTORY.exists()) {

			try {
				Game.SCORES_DIRECTORY.mkdirs();
				maxScoreFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (SecurityException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		if (!Game.readMeFile.exists()) {
			try {
				Game.readMeFile.createNewFile();
				PrintWriter pw = new PrintWriter(Game.readMeFile);
				pw.println(Game.GAME_NAME);
				pw.println(Game.readMe);
				pw.println("\n" + Game.enjoy);
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		if (Game.maxScoreFile.exists()) {
			try {
				Scanner lettore = new Scanner(maxScoreFile);
				if (lettore.hasNextLine()) {
					Game.maxScore = Integer.parseInt(lettore.nextLine());
					lettore.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}else{
			try {
				maxScoreFile.createNewFile();
				Scanner lettore = new Scanner(maxScoreFile);
				if (lettore.hasNextLine()) {
					Game.maxScore = Integer.parseInt(lettore.nextLine());
					lettore.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		/*
		 * File testFile = new File(""); String currentPath =
		 * testFile.getAbsolutePath(); System.out.println("current path is: " +
		 * currentPath);
		 */
		Assets.init();
		this.gameState = STATE.MENU;
		this.handler = new Handler();
		this.hud = new HUD(this);
		this.menu = new Menu(this, this.handler, this.hud);
		// controllo tasti premuti da tastiera
		this.addKeyListener(new KeyInput(this, handler));
		// controllo tasti premuti da mouse
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "Maurizio's GAME", this);

		this.spawn = new Spawn(this.handler, this.hud, this);
		this.r = new Random();

		if (gameState == STATE.GAME) {
			// AGGIUNGE UN GIOCATORE
			this.handler.addGameObject(new Player(r.nextInt(Game.WIDTH - Player.WIDTH),
					r.nextInt(Game.HEIGHT - Player.HEIGHT), ID.Player, this.handler));
			// AGGIUNGE UN NEMICO BASE
			this.handler.addGameObject(new BasicEnemy(r.nextInt(Game.WIDTH - BasicEnemy.WIDTH),
					r.nextInt(Game.HEIGHT - BasicEnemy.HEIGHT), ID.BasicEnemy, this.handler));
		}
	}

	public synchronized void start() {
		this.thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// the next line allows to control the player without having to click on
		// the frame first.
		this.requestFocus();

		// START OF THE GAME LOOP
		/*
		 * frames per second: the number of times that we want to call the tick
		 * and the render method every second
		 */
		final int fps = 60;
		final int nanoSecondsInASecond = 1000000000;
		/*
		 * timePerTick is the maximum amount of time in nanoseconds that we have
		 * to execute the tick and the render methods so that we could be able
		 * to achieve our 60 fps target.
		 */
		double timePerTick = nanoSecondsInASecond / fps;
		/*
		 * delta is the amount of time we have until we have to call tick and
		 * render methods again.
		 */
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (this.running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				// UPDATES
				this.tick();
				// DRAWING
				this.render();
				ticks++;
				delta--;
			}

			/*
			 * Checks if our timer has exceeded one second. After one second is
			 * going to tell us how many ticks has occurred in the last second.
			 */
			if (timer >= nanoSecondsInASecond) {
				System.out.println("Ticks and Frames: " + ticks);
				this.FPS = ticks;
				ticks = 0;
				timer = 0;
			}

		}

	}

	private void tick() {

		if (this.gameState == STATE.GAME) {
			this.hud.tick();
			this.spawn.tick();
			this.handler.tick();

			if (HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;

				this.handler.clearEnemys();
				this.gameState = STATE.GAME_OVER;
			}

		} else if (this.gameState == STATE.MENU || this.gameState == STATE.GAME_OVER) {
			this.menu.tick();

			if (this.gameState == STATE.GAME_OVER) {
				PrintWriter maxScoreWriter;
				try {
					if (this.hud.getScore() > Game.maxScore) {
						Game.maxScore = this.hud.getScore();
						maxScoreWriter = new PrintWriter(maxScoreFile);
						maxScoreWriter.println(Game.maxScore);
						// VERY IMPORTANT: Otherwise COULD NOT SAVE the maxScore
						// into the file.
						maxScoreWriter.close();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// COLORA IL BACKGROUND DELLA FINESTRA
		// g.fillRect(0, 0, WIDTH, HEIGHT);

		if (gameState == STATE.GAME) {
			g.drawImage(Assets.skullBackground, 0, 0, null);
			handler.render(g);
			hud.render(g);
		} else if (gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.GAME_OVER
				|| gameState == STATE.PAUSE) {
			g.drawImage(Assets.cubeBackground, 0, 0, null);
			this.menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	// morsetto
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else
			return var;
	}

	public STATE getGameState() {
		return this.gameState;
	}

	public void setGameState(STATE gameState) {
		this.gameState = gameState;
	}

	public static int getMaxScore() {
		return Game.maxScore;
	}

	public void setMaxScore(int maxScore) {
		Game.maxScore = maxScore;
	}

	public int getFPS() {
		return this.FPS;
	}

	/********************************************************************************************************/
	public static final int WIDTH = 1280, HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private STATE gameState;
	private int FPS = 0;

	private static int maxScore;

	private static final File MAIN_DIRECTORY = new File("Maurizio's Game");
	private static final File readMeFile = new File(MAIN_DIRECTORY, "READ ME.txt");
	public static final File MUSIC_DIRECTORY = new File(MAIN_DIRECTORY, "Music");
	private static final File SCORES_DIRECTORY = new File(MAIN_DIRECTORY, "Scores");
	private static final File maxScoreFile = new File(SCORES_DIRECTORY, "Scores.txt");

	private static final String GAME_NAME = "MAURIZIO's GAME";
	private static final String readMe = "If you like to listen to your favorite music while playing, "
			+ "you can insert your favorite songs inside the Music folder. Only MP3 and WAV are supported.";
	private static final String enjoy = "Hope you enjoy.";
}
