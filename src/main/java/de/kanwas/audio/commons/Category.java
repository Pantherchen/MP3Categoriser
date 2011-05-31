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

  private int index;

  private boolean mapped;

  public Category(String name, int index) {
    this.name = name;
    this.index = index;
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

  public int getIndex() {
    return this.index;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.index;
    result = prime * result + (this.mapped ? 1231 : 1237);
    result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Category other = (Category)obj;
    if (this.index != other.index) return false;
    if (this.mapped != other.mapped) return false;
    if (this.name == null) {
      if (other.name != null) return false;
    } else if (!this.name.equals(other.name)) return false;
    return true;
  }


}
