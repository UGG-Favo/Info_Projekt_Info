public class Entities {
    private String Name;
    private boolean friendly;
    private String imagePath;

    public Entities(String name, boolean friendly, String imagePath) {
        Name = name;
        this.friendly = friendly;
        this.imagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
