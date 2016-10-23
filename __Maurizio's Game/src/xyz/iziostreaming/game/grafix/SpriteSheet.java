package xyz.iziostreaming.game.grafix;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	public SpriteSheet(BufferedImage sheet) {
		this.image = sheet;
	}

	public BufferedImage crop(int x, int y, int width, int height){
		return this.image.getSubimage(x, y, width, height);
	}
	
	private BufferedImage image;
}
