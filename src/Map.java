public class Map {
  private int xpos = 0;
  private int ypos = 0;
  private int mapWidth;
  private int mapHeight;
  private Room[][] rooms;
  
  public void createMapWithRooms(int width, int height) {  
    mapWidth = width; 
    mapHeight = height;
    
    rooms = new Room[mapWidth][mapHeight];
    
    for (int x=0; x<=mapWidth-1; x++) {
      for (int y=0; y<=mapHeight-1; y++) {
        rooms[x][y] = new Room(false,false,false,false);
      }
    }
  }
}
