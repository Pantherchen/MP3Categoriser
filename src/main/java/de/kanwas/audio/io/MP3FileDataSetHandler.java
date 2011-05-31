/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.kanwas.audio.commons.Category;
import de.kanwas.audio.commons.MP3File;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3FileDataSetHandler {

  /** version number */
  public static final String VER = "$Revision$";

  /**   */
  public static final String CATEGORY_DELIMITER = "°";

  private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
    .getLog(MP3FileDataSetHandler.class);

  private File dbPath = null;

  private List<Category> allCategories;

  public MP3FileDataSetHandler(String path, List<Category> categories) {
    this.dbPath = new File(path);
    allCategories = categories;
  }

  public boolean write2DB(List<MP3File> files) {
    List<String> csvList = new ArrayList<String>();

    for (MP3File mp3File : files) {
      csvList.add(writeMP3FileAsCSV(mp3File));

    }

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(dbPath));
      for (String s : csvList) {
        writer.write(s);
        writer.write("\n");
      }
      writer.close();
      return true;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }

  /**
   * @return {@link MP3File} in CSV format
   */
  private String writeMP3FileAsCSV(MP3File file) {
    StringBuilder builder = new StringBuilder();
    builder.append(file.getFile().getName());
    builder.append(";");
    builder.append(file.getFile().getPath());
    Category mp3Cat = null;
    for (Category cat : this.allCategories) {
      builder.append(";");
      builder.append(cat.getIndex());
      builder.append(MP3FileDataSetHandler.CATEGORY_DELIMITER);
      mp3Cat = file.getCategory(cat.getName());
      if (mp3Cat != null && mp3Cat.isMapped()) {
        builder.append(1);
      } else {
        builder.append(0);
      }
    }

    return builder.toString();
  }

  public List<MP3File> readFilesFromDB() {
    if (allCategories == null || allCategories.size() == 0) {
      return null;
    }
    BufferedReader reader = null;
    String line = null;
    String[] data = null;
    MP3File mp3File = null;
    List<Category> mp3Categories = null;
    try {
      List<MP3File> files = new ArrayList<MP3File>();
      reader = new BufferedReader(new FileReader(dbPath));
      while ((line = reader.readLine()) != null) {
        data = line.split(";");
        if (data != null && data.length > 1) {
          mp3Categories = retrieveCategory(data);
          mp3File = new MP3File(data[1], mp3Categories);
          files.add(mp3File);
        } else {
          logger.error("Could not create new MP3File Object because the data read was empty: "+line);
        }
      }
      return files;
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @param data
   * @param categories
   * @return
   */
  private List<Category> retrieveCategory(String[] data) {
    if (data == null || data.length < 1) {
      return Collections.emptyList();
    }
    String[] catData = null;
    List<Category> mp3Categories = new ArrayList<Category>();
    Category mp3Category = null;
    for (int i = 2; i < data.length; i++) {
      catData = data[i].split(CATEGORY_DELIMITER);
      mp3Category = getCategory(catData);
      if (mp3Category != null && !mp3Categories.contains(mp3Category)) {
        mp3Categories.add(mp3Category);
      }
    }
    return mp3Categories;
  }

  /**
   * @param catData
   * @param categories
   * @return
   */
  private Category getCategory(String[] catData) {
    Category mp3Category = null;
    try {
      int index = Integer.parseInt(catData[0]);
      for (Category c : allCategories) {
        if (c.getIndex() == index) {
          mp3Category = new Category(c.getName(), c.getIndex());
          if ("1".equals(catData[1])) {
            mp3Category.setMapped(true);
          } else {
            mp3Category.setMapped(false);
          }
          return mp3Category;
        }
      }
    } catch (NumberFormatException nfe) {
      nfe.printStackTrace();
    }
    return null;
  }
}
