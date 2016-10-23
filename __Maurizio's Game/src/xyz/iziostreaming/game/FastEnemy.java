package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		this.velocityX = 2;
		this.velocityY = 9;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

	@Override
	public void tick() {
		this.x += this.velocityX;
		this.y += this.velocityY;
		
		if(this.y <=0 || this.y >= Game.HEIGHT - 2 * FastEnemy.HEIGHT){
			this.velocityY *= -1;
		}
		
		if(this.x <=0 || this.x >= Game.WIDTH - FastEnemy.WIDTH){
			this.velocityX *= -1;
		}
		
		//AGGIUNGE UN TRAIL = PERCORSO/SCIA
		this.handler.addGameObject(new Trail((int)this.x, (int)this.y, ID.Trail, Color.CYAN, FastEnemy.WIDTH, FastEnemy.HEIGHT, 0.02F, this.handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	
	protected static final int WIDTH = 15, HEIGHT = 15;
	private Handler handler;
}
