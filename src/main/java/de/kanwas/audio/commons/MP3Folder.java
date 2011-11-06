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

  private List<MP3Content> mp3Content;

  private final MP3Folder parentFolder;

  public MP3Folder(File folder, MP3Folder parentFolder) {
    this.folder = folder;
    this.parentFolder = parentFolder;
    this.mp3Content = new ArrayList<MP3Content>();
  }

  public File getFolder() {
    return this.folder;
  }

  public List<MP3File> getMp3Files() {
    List<MP3File> mp3Files = new ArrayList<MP3File>();
    for(MP3Content content : this.mp3Content) {
      if(content instanceof MP3File) {
        mp3Files.add((MP3File)content);
      }
    }
    return mp3Files;
  }

  @Override
  public String toString() {
    return this.folder.getPath();
  }

  /**
   * @param mp3
   */
  public void addMP3Content(MP3Content mp3) {
    if (!this.mp3Content.contains(mp3)) {
      this.mp3Content.add(mp3);
    }
  }

  /**
   * @return the mp3Content
   */
  public List<MP3Content> getMp3Content() {
    return this.mp3Content;
  }

  /**
   * @return the parentFolder
   */
  public MP3Folder getParentFolder() {
    return this.parentFolder;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.folder == null) ? 0 : this.folder.hashCode());
    result = prime * result + ((this.mp3Content == null) ? 0 : this.mp3Content.hashCode());
    result = prime * result + ((this.parentFolder == null) ? 0 : this.parentFolder.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MP3Folder other = (MP3Folder)obj;
    if (this.folder == null) {
      if (other.folder != null) return false;
    } else if (!this.folder.equals(other.folder)) return false;
//    if (this.mp3Content == null) {
//      if (other.mp3Content != null) return false;
//    } else if (!this.mp3Content.equals(other.mp3Content)) return false;
    if (this.parentFolder == null) {
      if (other.parentFolder != null) return false;
    } else if (!this.parentFolder.equals(other.parentFolder)) return false;
    return true;
  }


}
