package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < this.handler.object.size(); i++){
			if(this.handler.object.get(i).getId() == ID.Player){
				this.player = this.handler.object.get(i);
			}
		}
		
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
		
		float diffX = this.x - this.player.getX() - SmartEnemy.WIDTH/2;
		float diffY = this.y - this.player.getY() - SmartEnemy.HEIGHT/2;
		float distance = (float) Math.sqrt((this.x - this.player.getX()) * (this.x - this.player.getX()) + (this.y - this.player.getY()) * (this.y - this.player.getY()));
		
		this.velocityX = (float) ((-1.0/distance) * diffX);
		this.velocityY = (float) ((-1.0/distance) * diffY);
		
		if(this.y <=0 || this.y >= Game.HEIGHT - SmartEnemy.HEIGHT){
			this.velocityY *= -1;
		}
		
		if(this.x <=0 || this.x >= Game.WIDTH - SmartEnemy.WIDTH){
			this.velocityX *= -1;
		}
		
		//AGGIUNGE UN TRAIL = PERCORSO/SCIA
		this.handler.addGameObject(new Trail((int)this.x, (int)this.y, ID.Trail, Color.GREEN, SmartEnemy.WIDTH, SmartEnemy.HEIGHT, 0.02F, this.handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	
	protected static final int WIDTH = 15, HEIGHT = 15;
	private Handler handler;
	private GameObject player;
}
