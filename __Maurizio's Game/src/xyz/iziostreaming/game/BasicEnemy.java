package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		this.velocityX = 5;
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
		
		if(this.y <=0 || this.y >= Game.HEIGHT - 2 * BasicEnemy.HEIGHT){
			this.velocityY *= -1;
		}
		
		if(this.x <=0 || this.x >= Game.WIDTH - BasicEnemy.WIDTH){
			this.velocityX *= -1;
		}
		
		//AGGIUNGE UN TRAIL = PERCORSO/SCIA
		this.handler.addGameObject(new Trail((int)this.x, (int)this.y, ID.Trail, Color.RED, BasicEnemy.WIDTH, BasicEnemy.HEIGHT, 0.02F, this.handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	
	protected static final int WIDTH = 15, HEIGHT = 15;
	private Handler handler;
}
