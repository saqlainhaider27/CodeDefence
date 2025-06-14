package Core.Scene.Entity.Component;

public class Health extends BaseComponent {
    public int maxHealth;
    public int health;
    private boolean dead;
    @Override
    public void start() {
        super.start();
        dead = false;
        health = maxHealth;
    }

    @Override
    public void update() {
    }
    public void hit(int damage) {
        if (dead) return;

        health -= damage;
        if (health <= 0) {
            die();
        }
    }


    private void die() {
        health = 0;
        dead = true;
    }
    public boolean isDead(){
        return dead;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health){
        if (health > maxHealth){
            health = maxHealth;
        }
        this.health = health;
    }
}
