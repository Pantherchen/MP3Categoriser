/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.kanwas.audio.commons.MP3Content;
import de.kanwas.audio.commons.MP3File;
import de.kanwas.audio.commons.MP3Folder;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3FileHandler {
  /** version number */
  public static final String VER = "$Revision$";

  private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
    .getLog(MP3FileHandler.class);

  private static final String DEFAULT_CATEGORY_FILENAME = "C:/mp3";

  private String mp3Dir;

  private List<MP3Content> mp3Content;

  public enum MUSICFILES {
    MP3, OGG, MP4, WAV;

    public static boolean isMusicFile(String filename) {
      if (filename == null) {
        return false;
      }
      if (filename.toUpperCase().endsWith("MP3")) {
        return true;
      }
      if (filename.toUpperCase().endsWith("OGG")) {
        return true;
      }
      if (filename.toUpperCase().endsWith("MP4")) {
        return true;
      }
      if (filename.toUpperCase().endsWith("WAV")) {
        return true;
      }
      return false;
    }
  }

  public MP3FileHandler(String musicPath) {
    this.mp3Dir = musicPath;
    this.mp3Content = new ArrayList<MP3Content>();

  }

  public void readFiles() {
    if (this.mp3Dir == null) {
      this.mp3Dir = DEFAULT_CATEGORY_FILENAME;
    }
    File catFile = new File(this.mp3Dir);
    if (catFile.exists() && catFile.canRead()) {
      readAllFiles(catFile);
    }
  }

  /**
   * 
   */
  private void readAllFiles(File file) {
    File[] files = file.listFiles(new FileFilter(){

      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
          readAllFiles(pathname);
          return false;
        }
        return MUSICFILES.isMusicFile(pathname.getName());
      }
    });
    MP3File mp3 = null;
    MP3Folder folder = new MP3Folder(file);
    for (File f : files) {
      mp3 = new MP3File(f, folder);
      folder.addMP3File(mp3);
    }
    if (folder != null && !mp3Content.contains(folder)) {
      this.mp3Content.add(folder);
    }
  }

  public List<MP3Content> getMP3Files() {
    return this.mp3Content;
  }

  public void createPlaylist(List<MP3File> files2Export, File outputFolder) {
    File outputFile = null;
    for (MP3File mp3file : files2Export) {
      outputFile = new File(outputFolder + "/" + mp3file.getFile().getName());
      try {
        FileUtils.copyFile(mp3file.getFile(), outputFile);
      } catch (IOException e) {
        logger.error("Could not write file " + mp3file + " to playlistfolder: " + outputFile);
        e.printStackTrace();
      }
    }
  }
}
