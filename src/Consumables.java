public class Consumables extends Item{
    private int healing;
    public Consumables(int heal, String name, String imgPath, boolean stackable){
        super(name, imgPath, stackable);
        healing=heal;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }
}
