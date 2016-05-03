package vn.edu.namnguyen;

import vn.edu.namnguyen.controller.EnemyController;
import vn.edu.namnguyen.controller.PlaneController;
import vn.edu.namnguyen.controller.PlaneDirection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by MyComputer on 4/27/2016.
 */
public class GameWindow extends Frame implements Runnable {

    Image background;
    Image backbufferImage;
    Thread thread;
    PlaneController planeController1;
    PlaneController planeController2;
    Vector<EnemyController> enemyControllerVector = new Vector<EnemyController>();
    int miliSec = 0;


    public GameWindow(){
        this.setVisible(true);
        this.setSize(400, 600);
        this.planeController1 = PlaneController.getPlaneController();
        this.planeController2 = PlaneController.getPlaneController1();
        enemyControllerVector.add(EnemyController.getEnemyController1());


        try {
            background = ImageIO.read(new File("resources/background.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
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
                PlaneDirection planeDirection = PlaneDirection.NONE;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        planeDirection = PlaneDirection.UP;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        planeDirection = PlaneDirection.DOWN;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        planeDirection = PlaneDirection.LEFT;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        planeDirection = PlaneDirection.RIGHT;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        planeController1.shot();
                        break;
                    }
                }

                planeController1.move(planeDirection);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                PlaneDirection planeDirection = PlaneDirection.NONE;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                    }
                    case KeyEvent.VK_DOWN: {
                        planeDirection = PlaneDirection.STOP_Y;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                    }
                    case KeyEvent.VK_RIGHT: {
                        planeDirection = PlaneDirection.STOP_X;
                        break;
                    }
                }

                planeController1.move(planeDirection);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                planeController2.shot();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
        Graphics backbufferImageGraphics = backbufferImage.getGraphics();
        backbufferImageGraphics.drawImage(background, 0, 0, null);
        planeController1.paint(backbufferImageGraphics);
        planeController2.paint(backbufferImageGraphics);
        for (int i = 0; i < enemyControllerVector.size(); i++) enemyControllerVector.get(i).paint(backbufferImageGraphics);
        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while(true) {
            try {
                PlaneDirection planeDirection1 = PlaneDirection.NONE;
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                if(mousePoint.x - 40 > planeController2.getGameObject().getX()) {
                    planeDirection1 = PlaneDirection.RIGHT;
                } else if(mousePoint.x -30 < planeController2.getGameObject().getX()) {
                    planeDirection1 = PlaneDirection.LEFT;
                } else {
                    planeDirection1 = PlaneDirection.STOP_X;
                }
                planeController2.move(planeDirection1);

                if(mousePoint.y - 35 > planeController2.getGameObject().getY()) {
                    planeDirection1 = PlaneDirection.DOWN;
                } else if(mousePoint.y - 25 < planeController2.getGameObject().getY()) {
                    planeDirection1 = PlaneDirection.UP;
                } else {
                    planeDirection1 = PlaneDirection.STOP_Y;
                }
                planeController2.move(planeDirection1);
                planeController2.run();
                planeController1.run();
                planeController1.checkBullet(enemyControllerVector);
                planeController2.checkBullet(enemyControllerVector);
                for (int i = 0; i < enemyControllerVector.size(); i++){
                    enemyControllerVector.get(i).run();
                }

                repaint();
                Thread.sleep(17);

                miliSec += 17;
//                if(miliSec == 1003) {
//                    EnemyController.setNull();
//                    enemyControllerVector.add(EnemyController.getEnemyController1());
//                }
                if(miliSec >= 2000){
                    miliSec = 0;
                    for (int i = 0; i< enemyControllerVector.size(); i++ ) enemyControllerVector.get(i).shot();
//                    EnemyController.setNull();
//                    enemyControllerVector.add(EnemyController.getEnemyController1());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
