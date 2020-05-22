public class Weapon extends Item {
    private int damage;

    private int durability;

    public Weapon(int wDamage, int wDurability, String imagePath, String givenName, boolean stackAble){
        super(givenName, imagePath, stackAble);
        damage=wDamage;
        durability=wDurability;


    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
