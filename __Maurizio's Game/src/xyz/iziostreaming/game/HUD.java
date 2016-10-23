package xyz.iziostreaming.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Head-up display.
 */
public class HUD {

	public HUD(Game game) {
		this.game = game;
	}

	/**
	 * Aggiorna HEALTH, greenValue e score.
	 */
	public void tick() {
		HUD.HEALTH = (int) Game.clamp((float)HUD.HEALTH, (float) 0.0f, 100.0f);

		this.greenValue = (int) Game.clamp(this.greenValue, 0.0f, 255.0f);
		this.greenValue = (int) (HUD.HEALTH * 2);
		
		this.score++;
	}

	/**
	 * Disegna i rettangoli della salute.
	 * @param g The Graphics parameter.
	 */
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(5, 5, 200, 40);

		g.setColor(new Color((int)(++this.redValue) % 255, (int)this.greenValue, 0));
		g.fillRect(5, 5, (int) (HEALTH * 2), 40);

		g.setColor(Color.WHITE);
		// DISEGNA I LATI DEL RETTANGOLO SALUTE
		g.drawRect(5, 5, 200, 40);
		g.drawString("HEALTH: " + HEALTH + "%", 30, 30);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("LEVEL:  " + this.level, 5, 60);
		g.drawString("SCORE:  " + this.score, 5, 80);
		g.drawString("MAX SCORE: " + Game.getMaxScore(), 5, 100);
		g.setColor(Color.BLUE);
		g.drawString("FPS: " + this.game.getFPS(), 5, 120);
		
	}
	
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}





	public static float HEALTH = 100;
	private float greenValue = 255, redValue = 0;
	private int score = 0, level = 1;
	private Game game;
}
