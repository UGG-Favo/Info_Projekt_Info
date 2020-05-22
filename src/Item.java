public class Item {

    private String name;
    private String imgPath;
    private boolean stackable;

    public Item(String givenName, String imagePath, boolean stackAble){
        name=givenName;
        imgPath=imagePath;
        stackable=stackAble;
    }
    public Item(){


    }

    public Item(int none){
        this.setName("Leer");
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
