/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package de.kanwas.audio.commons;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class Category {
  /** version number */
  public static final String VER = "$Revision$";

  private String name;

  private boolean mapped;

  public Category(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public boolean isMapped() {
    return this.mapped;
  }

  public void setMapped(boolean mapped) {
    this.mapped = mapped;
  }

}
