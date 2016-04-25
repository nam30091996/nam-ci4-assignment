import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by MyComputer on 4/26/2016.
 */
public class Plane {
    public int x;
    public int y;
    public int dx;
    public int dy;
    private Image planeImage;
    Bullet bullet;

    public final int WIDTH = 70;
    public final int HEIGHT = 60;

    public Plane(int x, int y, Image planeImage) {
        this.x = x;
        this.y = y;
        this.planeImage = planeImage;
    }

    public void paint(Graphics g){
        g.drawImage(planeImage, x, y, null);
        if(bullet != null) bullet.paint(g);
    }

    public void run(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:{
                dy = -5;
                break;
            }
            case KeyEvent.VK_DOWN:{
                dy = 5;
                break;
            }
            case KeyEvent.VK_LEFT:{
                dx = -5;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                dx = 5;
                break;
            }
            case KeyEvent.VK_SPACE:{
                shot();
                break;
            }
        }
    }

    public void stop(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:{
                dy = 0;
                break;
            }
            case KeyEvent.VK_DOWN:{
                dy = 0;
                break;
            }
            case KeyEvent.VK_LEFT:{
                dx = 0;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                dx = 0;
                break;
            }
        }
    }

    public void move(){
        if((x + dx) <= 359 && (x + dx) >= -29) x += dx;
        if((y + dy) >= 31 && (y + dy) <= 538) y += dy;
        if(bullet != null) bullet.run();
    }

    public void shot(){
        try {
            this.bullet = new Bullet(this.x + WIDTH/2 - Bullet.WIDTH/2, this.y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
