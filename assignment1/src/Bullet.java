import java.awt.*;

/**
 * Created by MyComputer on 4/26/2016.
 */
public class Bullet {
    private int x;
    private int y;
    private Image bulletImage;

    public static final int WIDTH = 12;
    public static final int HEIGHT = 33;

    public Bullet(int x, int y, Image bulletImage) {
        this.x = x;
        this.bulletImage = bulletImage;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public void run(){
        this.y -= 5;
    }

    public void paint(Graphics g){
        g.drawImage(this.bulletImage, x, y, null);
    }
}
