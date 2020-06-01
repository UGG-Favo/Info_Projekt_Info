public class Room {
  boolean isNallowed;
  boolean isSallowed;
  boolean isEallowed;
  boolean isWallowed;
  boolean hasItem;
  Consumables cons;
  Weapon weapon;
  Armor armor;
  
  public Room (boolean n, boolean s, boolean e, boolean w, boolean hasitem) {
    isNallowed = n;
    isSallowed = s;
    isEallowed = e;
    isWallowed = w;
    hasItem = hasitem;
  }
  public Room (boolean n, boolean s, boolean e, boolean w, boolean hasitem,Consumables con) {
    isNallowed = n;
    isSallowed = s;
    isEallowed = e;
    isWallowed = w;
    hasItem = hasitem;
    cons = con;
  }
  public Room (boolean n, boolean s, boolean e, boolean w, boolean hasitem, Weapon wea) {
    isNallowed = n;
    isSallowed = s;
    isEallowed = e;
    isWallowed = w;
    hasItem = hasitem;
    weapon = wea;
  }
  public Room (boolean n, boolean s, boolean e, boolean w, boolean hasitem, Armor arm) {
    isNallowed = n;
    isSallowed = s;
    isEallowed = e;
    isWallowed = w;
    hasItem = hasitem;
    armor = arm;
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
