import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by MyComputer on 4/26/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Image background;
    Plane plane;
    Plane plane2;
    Image backbufferImage;
    EnemyPlane enemyPlane;

    Thread thread;

    public GameWindow(){
        this.setVisible(true);
        this.setSize(400, 600);

        try {
            background = ImageIO.read(new File("resources/background.png"));
            plane = new Plane(100, 500, ImageIO.read(new File("resources/plane4.png")));
            plane2 = new Plane(200, 500, ImageIO.read(new File("resources/plane2.png")));
            enemyPlane = new EnemyPlane(150, -50, ImageIO.read(new File("resources/plane1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                plane.run(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.stop(e);
            }
        });

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(400, 600, 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(background, 0, 0, null);
        plane.paint(backbufferGraphics);
        plane2.paint(backbufferGraphics);
        enemyPlane.paint(backbufferGraphics);
        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while(true){
            try {
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                if(mousePoint.x - 5 > plane2.x) {
                    plane2.dx = 5;
                } else if(mousePoint.x + 5 < plane2.x) {
                    plane2.dx = -5;
                } else {
                    plane2.dx = 0;
                }

                if(mousePoint.y - 5 > plane2.y) {
                    plane2.dy = 5;
                } else if(mousePoint.y + 5 < plane2.y) {
                    plane2.dy = -5;
                } else {
                    plane2.dy = 0;
                }

                plane.move();
                plane2.move();
                enemyPlane.move();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
