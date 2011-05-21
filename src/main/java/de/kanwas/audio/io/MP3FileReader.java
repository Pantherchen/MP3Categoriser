/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import de.kanwas.audio.commons.MP3File;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3FileReader {
  /** version number */
  public static final String VER = "$Revision$";

  private static final String DEFAULT_CATEGORY_FILENAME = "C:/mp3";

  private String mp3Dir;

  private List<MP3File> mp3Files;

  public enum MUSICFILES {
    MP3, OGG, MP4, WAV;

    public static boolean isMusicFile(String filename) {
      if (filename.contains("MP3")) {
        return true;
      }
      if (filename.contains("OGG")) {
        return true;
      }
      if (filename.contains("MP4")) {
        return true;
      }
      if (filename.contains("WAV")) {
        return true;
      }
      return false;
    }
  }

  public MP3FileReader(String musicPath) {
    this.mp3Dir = musicPath;
    this.mp3Files = new ArrayList<MP3File>();

  }

  public void readFiles() {
    if (this.mp3Dir == null) {
      this.mp3Dir = DEFAULT_CATEGORY_FILENAME;
    }
    File catFile = new File(this.mp3Dir);
    if (catFile.exists() && catFile.canRead()) {
      File[] files = catFile.listFiles(new FilenameFilter(){

        @Override
        public boolean accept(File dir, String name) {
          return MUSICFILES.isMusicFile(name);
        }
      });
      MP3File mp3File = null;
      for (File f : files) {
        mp3File = new MP3File(f);
        if (!this.mp3Files.contains(mp3File)) {
          this.mp3Files.add(mp3File);
        }
      }
    }
  }

  public List<MP3File> getMP3Files() {
    return this.mp3Files;
  }

  public MP3File[] getMP3FilesArray() {
    return this.mp3Files.toArray(new MP3File[this.mp3Files.size()]);
  }
}
