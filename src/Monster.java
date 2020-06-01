public class Monster extends Entities {
    private int damage;
    private int health;

    public Monster(String name, boolean friendly, String imagePath, int damage, int health) {
        super(name, friendly, imagePath);
        this.damage = damage;
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
