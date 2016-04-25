package com.game1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by MyComputer on 4/25/2016.
 */
public class Plane {
    public int x;
    public int y;
    public int dx;
    public int dy;
    private Image planeImage;
    Bullet bullet;

    public Plane(int x, int y, Image planeImage){
        this.x = x;
        this.y = y;
        this.planeImage = planeImage;
    }

    public void setPlaneImage(Image planeImage){
        if(planeImage != null && this.planeImage == null) this.planeImage = planeImage;
    }

    public Image getPlaneImage(){
        return this.planeImage;
    }

    public void paint(Graphics g){
        g.drawImage(planeImage, x, y, null);
        if(bullet != null) bullet.paint(g);
    }

    public void run(){
        if((x + dx) <= 359 && (x + dx) >= -29) x += dx;
        if((y + dy) >= 31 && (y + dy) <= 538) y += dy;
        if(bullet != null) bullet.run();
    }

//    public void move(Movement movement){
//        if(movement.dx > 0) dx = 5;
//        else if(movement.dx < 0) dx = -5;
//        else dx = 0;
//        if(movement.dy > 0) dy = 5;
//        else if(movement.dy < 0) dy = -5;
//        else dy = 0;
//    }

    public void shot(){
        try {
            this.bullet = new Bullet(this.x, this.y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
