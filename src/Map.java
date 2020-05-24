public class Map {
  private int xpos = 0;
  private int ypos = 0;
  private int mapWidth;
  private int mapHeight;
  private Room[][] rooms;
  
  public Map(int width, int height) {
    mapWidth = width; 
    mapHeight = height;
    
    rooms = new Room[mapWidth][mapHeight];
    
    for (int x=0; x<=mapWidth-1; x++) {
      for (int y=0; y<=mapHeight-1; y++) {
        rooms[x][y] = new Room(true,true,true,true, false);
      }
    }
  }

  public int getXpos() {
    return xpos;
  }

  public void setXpos(int xpos) {
    this.xpos = xpos;
  }

  public int getYpos() {
    return ypos;
  }

  public void setYpos(int ypos) {
    this.ypos = ypos;
  }

  public int getMapWidth() {
    return mapWidth;
  }

  public void setMapWidth(int mapWidth) {
    this.mapWidth = mapWidth;
  }

  public int getMapHeight() {
    return mapHeight;
  }

  public void setMapHeight(int mapHeight) {
    this.mapHeight = mapHeight;
  }

  public Room[][] getRooms() {
    return rooms;
  }

  public void setRooms(Room[][] rooms) {
    this.rooms = rooms;
  }
}
