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
public class CategoryReader {
  /** version number */
  public static final String VER = "$Revision$";

  private static final String DEFAULT_CATEGORY_FILENAME = "C:/category.txt";

  private String filename;

  private List<Category> categories;

  public CategoryReader(String filename) {
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
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(catFile));
      String categoryLine = null;
      String catName = null;
      String[] catLine = null;
      int index = 0;
      while ((categoryLine = reader.readLine()) != null) {
        catLine = categoryLine.split(",");
        if (catLine != null && catLine[0] != null && catLine[1] != null) {
          catName = catLine[0];
          try {
            index = Integer.parseInt(catLine[1]);
          } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            index = -1;
          }
          Category category = new Category(catName, index);
          if (category != null && !categories.contains(category)) {
            this.categories.add(category);
          }
        }
      }
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally{
      try {
        reader.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
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
