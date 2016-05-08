package vn.edu.techkids.models;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBullet extends GameObject {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    private static final int DEFAULT_DAMAGE = 1;

    private int damage;

    public EnemyBullet(int x, int y, int width, int height, int damage) {
        super(x, y, width, height);
        this.damage = damage;
    }

    public EnemyBullet(int x, int y, int width, int height){
        this(x, y, width, height, DEFAULT_DAMAGE);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
