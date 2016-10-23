package xyz.iziostreaming.game.grafix;

import java.awt.image.BufferedImage;

public class Assets {

public static void init(){
		
		SpriteSheet skull = new SpriteSheet(ImageLoader.loadImage("/images/skull-smoker-1280x720.jpg"));
		SpriteSheet cube = new SpriteSheet(ImageLoader.loadImage("/images/black&blueCubes1280x720.jpg"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/images/youkai-eyeball.gif"));
		
		cubeBackground = cube.crop(0, 0, 1280, 720);
		skullBackground = skull.crop(0, 0, 1280, 720);
		skullPlayer = player.crop(0, 0, 45, 48);
	}
	
	
	public static BufferedImage skullBackground, cubeBackground, skullPlayer;
}
