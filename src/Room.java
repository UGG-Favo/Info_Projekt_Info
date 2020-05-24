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
}
