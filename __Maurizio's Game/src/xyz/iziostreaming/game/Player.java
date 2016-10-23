package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import xyz.iziostreaming.game.grafix.Assets;;

public class Player extends GameObject {

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

	/**
	 * Aggiorna la posizione (x, y) del Player.
	 * Controlla se vi è una collisione con un nemico.
	 */
	@Override
	public void tick() {
		this.x += this.velocityX;
		this.y += this.velocityY;
		//Limita la x e la y nelle dimensioni massime della finsestra.
		this.x = Game.clamp((int)x, 0, Game.WIDTH - Player.WIDTH - 8);
		this.y = Game.clamp((int)y, 0, Game.HEIGHT - Player.HEIGHT - 30);

		this.collision();
	}

	/**
	 * Controlla se vi è una collisione con un nemico all'interno della lista della classe Handler.
	 */
	private void collision() {
		for (int i = 0; i < this.handler.object.size(); i++) {
			GameObject tempGameObject = this.handler.object.get(i);
			ID GameObjectID = tempGameObject.getId();
			
			if (GameObjectID == ID.BasicEnemy || GameObjectID == ID.FastEnemy 
					|| GameObjectID == ID.SmartEnemy) {
				
				if (this.getBounds().intersects(tempGameObject.getBounds())) {
					// Colision code
					HUD.HEALTH--;
				}
			}
			else if(GameObjectID == ID.EnemyBoss){
				
				if (this.getBounds().intersects(tempGameObject.getBounds())) {
					// Colision code
					HUD.HEALTH -= HUD.HEALTH;
				}
			}
		}
	}

	/**
	 * Draws a WHITE Rectangle on the screen.
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		//g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
		g.drawImage(PLAYER_IMAGE, (int)x, (int)y, null);

	}

	private Handler handler;
	private static final BufferedImage PLAYER_IMAGE = Assets.skullPlayer;
	public static final int WIDTH = PLAYER_IMAGE.getWidth(), HEIGHT = PLAYER_IMAGE.getHeight();
	
	//public static final int WIDTH = 20, HEIGHT = 20;
}
