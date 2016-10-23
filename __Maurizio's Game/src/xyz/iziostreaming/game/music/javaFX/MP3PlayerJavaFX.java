package xyz.iziostreaming.game.music.javaFX;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MP3PlayerJavaFX extends Application {

	private ArrayList<MediaPlayer> mediPlayers;
	private java.util.List<String> parameters;
	private File directory;
	private ArrayList<String> songsCollection;

	@Override
	public void init() throws Exception {
		super.init();
		this.mediPlayers = new ArrayList<>();
		this.songsCollection = new ArrayList<>();
		
		if(this.getParameters().getRaw().size() == 1){
			this.parameters = this.getParameters().getRaw();
			this.directory = new File(this.parameters.get(0));
			if(this.directory.exists()){
				this.songsCollection = new FolderContentsLoader(this.directory).getFolderFiles(new SongFilter());
			}
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		if (this.songsCollection.size() > 0) {
			for (String file : this.songsCollection) {
				//System.out.println(file);
				Media media = new Media(file);
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				this.mediPlayers.add(mediaPlayer);
			}
		}else{
			Media m1 = new Media(MP3PlayerJavaFX.class.getResource("/music/Krewella - Come And Get It.mp3").toURI().toString());
			MediaPlayer mP1 = new MediaPlayer(m1);
			this.mediPlayers.add(mP1);
			
			Media m2 = new Media(MP3PlayerJavaFX.class.getResource("/music/Spektrem - Shine.mp3").toURI().toString());
			MediaPlayer mP2 = new MediaPlayer(m2);
			this.mediPlayers.add(mP2);
			
			Media m3 = new Media(MP3PlayerJavaFX.class.getResource("/music/Tobu - Life.mp3").toURI().toString());
			MediaPlayer mP3 = new MediaPlayer(m3);
			this.mediPlayers.add(mP3);
			
			Media m4 = new Media(MP3PlayerJavaFX.class.getResource("/music/Tobu - Mesmerize.mp3").toURI().toString());
			MediaPlayer mP4 = new MediaPlayer(m4);
			this.mediPlayers.add(mP4);
			
		}

		for (int i = 0; i < this.mediPlayers.size(); i++) {
			final MediaPlayer player = mediPlayers.get(i);
			final MediaPlayer nextPlayer = mediPlayers.get((i + 1) % mediPlayers.size());
			player.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					player.stop();
					System.out.println(getSongTitle(player.getMedia()) + " ENDED");
					nextPlayer.play();
					System.out.println(getSongTitle(nextPlayer.getMedia()) + " STARTED");
				}
			});
		}

		mediPlayers.get(0).play();
		System.out.println(getSongTitle(mediPlayers.get(0).getMedia()) + " STARTED");
	}

	private static String getSongTitle(Media media) {

		String songTitle = media.getSource().substring(media.getSource().lastIndexOf("/") + 1).replaceAll("%20", " ");

		return songTitle;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
