package xyz.iziostreaming.game.music.javaFX;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * Filters all the Songs which ends with .mp3 or .wav
 */
public class SongFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		for (String extension : SUPPORTED_FILE_EXTENSIONS) {
			if (name.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
	
	private static final List<String> SUPPORTED_FILE_EXTENSIONS = Arrays.asList(".mp3", ".wav");

}
