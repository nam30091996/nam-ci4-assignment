import java.awt.*;

/**
 * Created by MyComputer on 4/26/2016.
 */
public class EnemyPlane {
    private int x;
    private int y;
    private Image planeImage;

    public EnemyPlane(int x, int y, Image planeImage) {
        this.x = x;
        this.y = y;
        this.planeImage = planeImage;
    }

    public void paint(Graphics g) {
        g.drawImage(planeImage, x, y, 40, 35, null);
    }

    public void move(){
        this.y += 2;
    }
}