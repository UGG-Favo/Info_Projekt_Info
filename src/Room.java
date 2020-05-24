public class Room {
  boolean isNallowed;
  boolean isSallowed;
  boolean isEallowed;
  boolean isWallowed;
  boolean hasItem;
  
  public Room (boolean n, boolean s, boolean e, boolean w, boolean hasitem) {
    isNallowed = n;
    isSallowed = s;
    isEallowed = e;
    isWallowed = w;
    hasItem = hasitem;
  }

  public boolean isNallowed() {
    return isNallowed;
  }

  public void setNallowed(boolean nallowed) {
    isNallowed = nallowed;
  }

  public boolean isSallowed() {
    return isSallowed;
  }

  public void setSallowed(boolean sallowed) {
    isSallowed = sallowed;
  }

  public boolean isEallowed() {
    return isEallowed;
  }

  public void setEallowed(boolean eallowed) {
    isEallowed = eallowed;
  }

  public boolean isWallowed() {
    return isWallowed;
  }

  public void setWallowed(boolean wallowed) {
    isWallowed = wallowed;
  }

  public boolean isHasItem() {
    return hasItem;
  }

  public void setHasItem(boolean hasItem) {
    this.hasItem = hasItem;
  }
}
