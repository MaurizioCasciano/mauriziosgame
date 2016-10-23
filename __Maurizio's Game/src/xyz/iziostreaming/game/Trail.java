package xyz.iziostreaming.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//Trail = Percorso
public class Trail extends GameObject{

	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if(alpha > life){
			this.alpha -= life - 0.0001f;
		}
		else{
			this.handler.removeGameObgect(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setComposite(this.makeTransparent(alpha));
		g.setColor(this.color);
		g.fillRect((int)x, (int)y, width, height);
		g2D.setComposite(this.makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private float alpha = 1, life;
	private Handler handler;
	private Color color;
	private int width, height;
}
