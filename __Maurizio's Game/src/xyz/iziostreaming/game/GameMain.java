package xyz.iziostreaming.game;

import java.io.IOException;

import xyz.iziostreaming.game.music.javaFX.MP3PlayerJavaFX;

public class GameMain {

	public static void main(String[] args) throws IOException {
		new Game();

		final Thread t = new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(MP3PlayerJavaFX.class, Game.MUSIC_DIRECTORY.getAbsolutePath());
			}
		};
		
		t.start();
		
		/*
		 * try { AudioPlayer.player.start(new
		 * FileInputStream("Spektrem-Shine.wav")); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}
}
