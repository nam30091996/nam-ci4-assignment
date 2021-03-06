package vn.edu.namnguyen.view;

import vn.edu.namnguyen.model.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by MyComputer on 4/29/2016.
 */
public class ImageDrawer implements GameDrawer {
    private Image image;

    public ImageDrawer(String url){
        try {
            image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(GameObject gameObject, Graphics g) {
        g.drawImage(this.image, gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight(), null);
    }
}
