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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.folder == null) ? 0 : this.folder.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MP3Folder other = (MP3Folder)obj;
    if (this.folder == null) {
      if (other.folder != null) return false;
    } else if (!this.folder.equals(other.folder)) return false;
    return true;
  }

  
}
