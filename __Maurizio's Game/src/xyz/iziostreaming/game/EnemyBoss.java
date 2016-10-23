package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class EnemyBoss extends GameObject implements ActionListener{

	Random r = new Random();

	public EnemyBoss(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;

		this.velocityX = 10;
		this.velocityY = 0;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.game.getGameState() == STATE.GAME){
			this.handler.addGameObject(new EnemyBossBullet(r.nextInt(Game.WIDTH), r.nextInt(EnemyBoss.HEIGHT), ID.BasicEnemy, this.handler));
		}
	}

	@Override
	public void tick() {
		this.x += this.velocityX;
		this.y += this.velocityY;

		if (this.y <= 0 || this.y >= Game.HEIGHT - EnemyBoss.HEIGHT){
			this.velocityY *= -1;
		}

		if (this.x <= 0 || this.x >= Game.WIDTH - EnemyBoss.WIDTH){
			this.velocityX *= -1;
		}

		// AGGIUNGE UN TRAIL = PERCORSO/SCIA
		this.handler.addGameObject(new Trail((int) this.x, (int) this.y, ID.Trail, Color.BLUE, EnemyBoss.WIDTH,
				EnemyBoss.HEIGHT, 0.008F, this.handler));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
	}

	protected static final int WIDTH = 90, HEIGHT = 90;
	private Handler handler;
	private Game game;
}
