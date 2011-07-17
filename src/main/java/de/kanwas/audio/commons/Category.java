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

  private boolean dirty;

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

  /**
   * @return the dirty
   */
  public boolean isDirty() {
    return this.dirty;
  }

  /**
   * @param dirty the dirty to set
   */
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }

  @Override
  public String toString() {
    return "name: " + this.name + ", index: " + index + ", mapped: " + mapped + ", dirty: " + dirty;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.dirty ? 1231 : 1237);
    result = prime * result + this.index;
    result = prime * result + (this.mapped ? 1231 : 1237);
    result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Category other = (Category)obj;
    if (this.dirty != other.dirty) return false;
    if (this.index != other.index) return false;
    if (this.mapped != other.mapped) return false;
    if (this.name == null) {
      if (other.name != null) return false;
    } else if (!this.name.equals(other.name)) return false;
    return true;
  }

}
