/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.kanwas.audio.commons.Category;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class CategoryReader2 {
  /** version number */
  public static final String VER = "$Revision$";

  private static final String DEFAULT_CATEGORY_FILENAME = "C:/category";

  private String filename;

  private List<Category> categories;

  public CategoryReader2(String filename) {
    this.filename = filename;
    this.categories = new ArrayList<Category>();

  }

  public void readCategories() {
    if (this.filename == null) {
      this.filename = DEFAULT_CATEGORY_FILENAME;
    }
    File catFile = new File(this.filename);
    if (catFile.exists() && catFile.canRead()) {
      this.read(catFile);
    }
  }

  /**
   * @param catFile
   */
  private void read(File catFile) {
    List<String> cats = new ArrayList<String>();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(catFile));
      String category = null;
      while (reader.read() > 0) {
        category = reader.readLine();
        if (category != null && !cats.contains(category)) {
          cats.add(category);
        }
      }
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (cats != null) {
      Category cat = null;
      for (String c : cats) {
        cat = new Category(c);
        if (!this.categories.contains(cat)) {
          this.categories.add(cat);
        }
      }
    }
  }

  public List<Category> getCategories() {
    return this.categories;
  }

  public Category[] getCategoriesArray() {
    return this.categories.toArray(new Category[this.categories.size()]);
  }
}
