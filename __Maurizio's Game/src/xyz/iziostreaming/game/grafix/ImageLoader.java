package xyz.iziostreaming.game.grafix;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	/**
	 * Loads an Image.
	 * @param path The path of the image.
	 * @return The loaded image.
	 */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
