package xyz.iziostreaming.game.music.javaFX;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class FolderContentsLoader {
	
	/**
	 * Creates a FolderContentsLoader
	 * @param directory The folder which you want to load the contents.
	 */
	public FolderContentsLoader(File directory) {
		this.directory = directory;
	}

	
	/**
	 * Gets all
	 * @param filter
	 * @return
	 */
	public ArrayList<String> getFolderFiles(FilenameFilter filter) {

		if(!this.directory.exists()){
			System.err.println(this.directory + " does not exist!!!");
			System.exit(1);
		}
		URL url;
		try {
			url = this.directory.toURI().toURL();
			if (url != null) {
				try {
					final File apps = new File(url.toURI());
					for (File app : apps.listFiles(filter)) {
						// System.out.println(app.toURI());
						this.folderFiles.add(app.toURI().toString());
					}

					return this.folderFiles;
				} catch (URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return this.folderFiles;
	}
	
	
	private ArrayList<String> folderFiles = new ArrayList<>();
	private File directory;
}
