package com.game1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by MyComputer on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {

    Image background;
    Plane plane1;
    Plane plane2;

    Thread thread;
    Image backBufferImage;


    public GameWindow() {
        setVisible(true);
        setSize(400,600);

        try {
            background = ImageIO.read(new File("resources/background.png"));
            plane1 = new Plane(100, 500, ImageIO.read(new File("resources/plane4.png")));
            plane2 = new Plane(300, 200, ImageIO.read(new File("resources/plane1.png")));
            //bullet = new Bullet(200, 600, ImageIO.read(new File("resources/bullet.png")));
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
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:{
                        plane1.dy = -5;
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        plane1.dy = 5;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        plane1.dx = -5;
                        break
                    }
                    case KeyEvent.VK_RIGHT:{
                        plane1.dx = 5;
                        break;
                    }
                    case KeyEvent.VK_SPACE:{
                        plane1.shot();
                        break;
                    }
                    case KeyEvent.VK_W:{
                        plane2.dy = -5;
                        break;
                    }
                    case KeyEvent.VK_S:{
                        plane2.dy = 5;
                        break;
                    }
                    case KeyEvent.VK_A:{
                        plane2.dx = -5;
                        break;
                    }
                    case KeyEvent.VK_D:{
                        plane2.dx = 5;
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:{
                        plane1.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        plane1.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        plane1.dx = 0;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        plane1.dx = 0;
                        break;
                    }
                    case KeyEvent.VK_W:{
                        plane2.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_S:{
                        plane2.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_A:{
                        plane2.dx = 0;
                        break;
                    }
                    case KeyEvent.VK_D:{
                        plane2.dx = 0;
                        break;
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if(backBufferImage == null){
            backBufferImage = new BufferedImage(400, 600, 1);
        }
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        backBufferGraphics.drawImage(background, 0, 0, null);
        plane1.paint(backBufferGraphics);
        plane2.paint(backBufferGraphics);
        //bullet.paint(backBufferGraphics);
        g.drawImage(backBufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while(true){
            try {
                plane1.run();
                plane2.run();
                //bullet.run();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
