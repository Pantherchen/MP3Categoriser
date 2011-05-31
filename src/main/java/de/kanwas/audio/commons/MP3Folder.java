/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3Folder extends MP3Content {
  /** version number */
  @SuppressWarnings("hiding")
  public static final String VER = "$Revision$";

  private File folder;

  private List<MP3File> mp3Files;

  public MP3Folder(File folder) {
    this.folder = folder;
    this.mp3Files = new ArrayList<MP3File>();
  }

  public File getFolder() {
    return this.folder;
  }

  public List<MP3File> getMp3Files() {
    return this.mp3Files;
  }

  @Override
  public String toString() {
    return this.folder.getPath();
  }

  /**
   * @param mp3
   */
  public void addMP3File(MP3File mp3) {
    if (!this.mp3Files.contains(mp3)) {
      this.mp3Files.add(mp3);
    }

  }

}
