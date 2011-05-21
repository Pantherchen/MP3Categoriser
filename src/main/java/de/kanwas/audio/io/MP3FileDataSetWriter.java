/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.kanwas.audio.commons.MP3File;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class MP3FileDataSetWriter {
  /** version number */
  public static final String VER = "$Revision$";

  private File dbPath = null;

  public MP3FileDataSetWriter(String path) {
    this.dbPath = new File(path);
  }

  public void write2DB(MP3File[] files) {
    StringBuilder builder = new StringBuilder();

    for (MP3File mp3File : files) {
      builder.append(mp3File.formatAsCSV());
      builder.append("\n");
    }

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(dbPath));
      writer.write(builder.toString());
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
