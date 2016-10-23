package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		this.velocityX = r.nextInt(10) + -5;
		this.velocityY = 5;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

	@Override
	public void tick() {
		this.x += this.velocityX;
		this.y += this.velocityY;
		
		if(this.y >= Game.HEIGHT){
			this.handler.removeGameObgect(this);
		}
		
		
		
		//AGGIUNGE UN TRAIL = PERCORSO/SCIA
		this.handler.addGameObject(new Trail((int)this.x, (int)this.y, ID.Trail, EnemyBossBullet.VIOLA, EnemyBossBullet.WIDTH, EnemyBossBullet.HEIGHT, 0.008F, this.handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	
	protected static final int WIDTH = 15, HEIGHT = 15;
	private Handler handler;
	private Random r = new Random();
	private static final Color VIOLA = new Color(143, 0, 255);;
}
