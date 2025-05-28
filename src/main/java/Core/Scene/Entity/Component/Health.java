package Core.Scene.Entity.Component;

public class Health extends BaseComponent {
    public int health;
    private boolean dead;
    @Override
    public void start() {
        super.start();
        dead = false;
    }

    @Override
    public void update() {
    }
    public void hit(int damage){
        if (health - damage <= 0 && !dead){
            die();
            return;
        }
        health -= damage;
        System.out.println(health);
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
}
