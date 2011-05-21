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
public class MP3File {
  /** version number */
  public static final String VER = "$Revision$";

  private File file;

  private List<Category> categories;

  public MP3File(File file) {
    this.file = file;
    this.categories = new ArrayList<Category>();
  }

  public void addCategory(Category category) {
    if (!this.categories.contains(category)) {
      this.categories.add(category);
    }
  }

  public void removeCategory(Category category) {
    if (this.categories.contains(category)) {
      this.categories.remove(category);
    }
  }

  public List<Category> getCategories() {
    return this.categories;
  }

  public File getFile() {
    return this.file;
  }

  /**
   * @return {@link MP3File} in CSV format
   */
  public String formatAsCSV() {
    StringBuilder builder = new StringBuilder();
    builder.append(file.getName());
    builder.append(";");
    builder.append(file.getPath());
    for (Category cat : this.categories) {
      builder.append(";");
      builder.append(cat.isMapped());
    }
    return builder.toString();
  }
}
