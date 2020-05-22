public class Armor extends Item {


    private int damageBlocked;
    private int durability;

    public Armor(int damageblocked, int wDurability, String imagePath, String givenName, boolean stackAble){
        super(givenName, imagePath, stackAble);
        durability=wDurability;
        damageBlocked=damageblocked;


    }

    public int getDamageBlocked() {
        return damageBlocked;
    }

    public void setDamageBlocked(int damageBlocked) {
        this.damageBlocked = damageBlocked;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
