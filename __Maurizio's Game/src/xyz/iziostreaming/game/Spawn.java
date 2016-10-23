package xyz.iziostreaming.game;

import java.util.Random;

import javax.swing.Timer;

public class Spawn {
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.scoreKeep = 0;
		this.game = game;
	}

	/**
	 * Tiene traccia dello score che permette di passare al livello successivo.
	 */
	public void tick() {
		this.scoreKeep++;

		// AVANZA DI LIVELLO DOPO OGNI NEXT_LEVEL_SCORE
		if (this.scoreKeep >= NEXT_LEVEL_SCORE) {
			this.scoreKeep = 0;
			this.hud.setLevel(this.hud.getLevel() + 1);

			// AGGIUNGE UN NEMICO BASE AD OGNI LIVELLO PARI
			if (this.hud.getLevel() % 2 == 0) {
				this.handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH - BasicEnemy.WIDTH),
						r.nextInt(Game.HEIGHT - BasicEnemy.HEIGHT), ID.BasicEnemy, this.handler));
			}

			// AGGIUNGE UN NEMICO INTELLIGENTE AD OGNI LIVELLO MULTIPLO DI 3
			if (this.hud.getLevel() % 3 == 0) {
				this.handler.object.add(new SmartEnemy(r.nextInt(Game.WIDTH - FastEnemy.WIDTH),
						r.nextInt(Game.HEIGHT - SmartEnemy.HEIGHT), ID.SmartEnemy, this.handler));
			}

			// AGGIUNGE UN NEMICO BOSS AD OGNI LIVELLO MULTIPLO DI 5
			if (this.hud.getLevel() == 5) {
				this.handler.clearEnemys();
				EnemyBoss enemyBoss = new EnemyBoss((Game.WIDTH / 2 - EnemyBoss.WIDTH), 0, ID.EnemyBoss, this.handler, this.game);
				this.handler.object.add(enemyBoss);
				// AZIONA L'ACTION PERFORMED DI enemyBoss
				t = new Timer(5000, enemyBoss);
				t.start();
			}

			// RIPRISTINA LA SALUTE AD OGNI LIVELLO MULTIPLO DI 10
			if (this.hud.getLevel() % 10 == 0) {
				HUD.HEALTH = 100;
			}
		}
	}

	private Handler handler;
	private HUD hud;
	private int scoreKeep;
	private Random r = new Random();
	private static final int NEXT_LEVEL_SCORE = 1000;
	private Game game;
	static Timer t;
}
