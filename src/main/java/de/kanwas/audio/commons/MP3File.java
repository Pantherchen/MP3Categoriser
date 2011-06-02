/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public MP3File(File file, MP3Folder parent) {
    this.file = file;
    this.parent = parent;
    this.categories = new HashMap<String, Category>();
  }

  /**
   * @param data
   */
  public MP3File(String data, List<Category> categories) {
    this.file = new File(data);
    if (this.file != null && this.file.canRead()) {
      this.parent = new MP3Folder(new File(this.file.getPath()));
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

  public File getFile() {
    return this.file;
  }

  public MP3Folder getParent() {
    return this.parent;
  }

  @Override
  public String toString() {
    return this.file.getName();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.categories == null) ? 0 : this.categories.hashCode());
    result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
    result = prime * result + ((this.parent == null) ? 0 : this.parent.hashCode());
    return result;
  }

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
    if (this.parent == null) {
      if (other.parent != null) return false;
    } else if (!this.parent.equals(other.parent)) return false;
    return true;
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
}
