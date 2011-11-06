/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.commons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3File extends MP3Content {
  /** version number */
  @SuppressWarnings("hiding")
  public static final String VER = "$Revision$";

  private File file;

  private Map<String, Category> categories;

  private MP3Folder parent;

  private Tag mp3Tag;

  public MP3File(File file, MP3Folder parent) {
    this.file = file;
    this.parent = parent;
    this.categories = new HashMap<String, Category>();
    createTag();
  }

  /**
   * @return
   */
  private void createTag() {
    AudioFile f;
    try {
      f = AudioFileIO.read(file);
      this.mp3Tag = f.getTag();
    } catch (CannotReadException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TagException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ReadOnlyFileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidAudioFrameException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @param data
   */
  public MP3File(String data, List<Category> categories) {
    this.file = new File(data);
    if (this.file != null && this.file.canRead()) {
      this.parent = new MP3Folder(new File(this.file.getParent()), null);
    }
    this.categories = new HashMap<String, Category>();
    for (Category category : categories) {
      this.categories.put(category.getName(), category);
    }
  }

  public void setCategory(Category category) {
    this.categories.put(category.getName(), category);
  }

  public void removeCategory(Category category) {
    if (this.categories.containsKey(category.getName())) {
      this.categories.remove(category);
    }
  }

  public List<Category> getCategories() {
    List<Category> categoryList = new ArrayList<Category>();
    for (Category cat : this.categories.values()) {
      categoryList.add(cat);
    }
    return categoryList;
  }

  public Category getCategory(String name) {
    if (this.categories.containsKey(name)) {
      return this.categories.get(name);
    }
    return null;
  }

  public Category getCategoryByIndex(int index) {
    for (Category c : this.categories.values()) {
      if (c.getIndex() == index) {
        return c;
      }
    }
    return null;
  }

  public void setFilefromDB(MP3File file) {
    for (Category c : file.getCategories()) {
      this.categories.put(c.getName(), c);
    }
    this.file = file.getFile();
    this.parent = file.getParent();

  }

  public File getFile() {
    return this.file;
  }

  public MP3Folder getParent() {
    return this.parent;
  }

  @Override
  public String toString() {
    return this.mp3Tag.getFirstField(FieldKey.TITLE).toString();
  }

  /**
   * @return the dirty
   */
  public boolean isDirty() {
    for (Category c : getCategories()) {
      if (c.isDirty()) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the mp3Tag
   */
  public Tag getMp3Tag() {
    return this.mp3Tag;
  }

  /**
   * @param dbFile
   * @return
   */
  public boolean isEqualTo(MP3File dbFile) {
    if (this.getFile().getAbsolutePath().equals(dbFile.getFile().getAbsolutePath())
        && this.getParent().getFolder().getAbsolutePath().equals(dbFile.getParent().getFolder().getAbsolutePath())) {
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.categories == null) ? 0 : this.categories.hashCode());
    result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
    result = prime * result + ((this.mp3Tag == null) ? 0 : this.mp3Tag.hashCode());
    result = prime * result + ((this.parent == null) ? 0 : this.parent.hashCode());
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
    MP3File other = (MP3File)obj;
    if (this.categories == null) {
      if (other.categories != null) return false;
    } else if (!this.categories.equals(other.categories)) return false;
    if (this.file == null) {
      if (other.file != null) return false;
    } else if (!this.file.equals(other.file)) return false;
    if (this.mp3Tag == null) {
      if (other.mp3Tag != null) return false;
    } else if (!this.mp3Tag.equals(other.mp3Tag)) return false;
    if (this.parent == null) {
      if (other.parent != null) return false;
    } else if (!this.parent.equals(other.parent)) return false;
    return true;
  }

  
}
