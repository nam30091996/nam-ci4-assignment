package vn.edu.namnguyen.controller;

import vn.edu.namnguyen.model.Bullet;
import vn.edu.namnguyen.model.Plane;
import vn.edu.namnguyen.view.GameDrawer;
import vn.edu.namnguyen.view.ImageDrawer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by MyComputer on 4/29/2016.
 */
public class PlaneController extends SingleController {
    public final int SPEED = 10;
    private Vector<BulletController> bulletControllerVector;

    private PlaneController(Plane plane, GameDrawer gameDrawer) {
        super(plane, gameDrawer);
        bulletControllerVector = new Vector<BulletController>();
    }

    public void move(PlaneDirection planeDirection) {
        switch (planeDirection) {
            case NONE: {
                break;
            }
            case UP:{
                this.gameVector.dy = -SPEED;
                break;
            }
            case DOWN: {
                this.gameVector.dy = SPEED;
                break;
            }
            case LEFT: {
                this.gameVector.dx = -SPEED;
                break;
            }
            case RIGHT: {
                this.gameVector.dx = SPEED;
                break;
            }
            case STOP_Y: {
                this.gameVector.dy = 0;
                break;
            }
            case STOP_X: {
                this.gameVector.dx = 0;
                break;
            }
        }
    }

    public void shot() {
        Bullet bullet = new Bullet(gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2, gameObject.getY(), Bullet.DEFAULT_WIDTH, Bullet.DEFAULT_HEIGHT);
        ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
        BulletController bulletController = new BulletController(bullet, imageDrawer);
        this.bulletControllerVector.add(bulletController);
    }

    private static PlaneController planeController1;

    public static PlaneController getPlaneController() {
        if(planeController1 == null) {
            Plane plane = new Plane(100, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane4.png");
            planeController1 = new PlaneController(plane, planeDrawer);
        }
        return planeController1;
    }

    private static PlaneController planeController2;

    public static PlaneController getPlaneController1() {
        if(planeController2 == null) {
            Plane plane = new Plane(300, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane3.png");
            planeController2 = new PlaneController(plane, planeDrawer);
        }
        return planeController2;
    }



    @Override
    public void run() {
        super.run();
        for(BulletController bulletController : this.bulletControllerVector) {
            bulletController.run();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(BulletController bulletController : this.bulletControllerVector) {
            bulletController.paint(g);
        }
    }

    public void checkBullet(Vector<EnemyController> enemyController) {
        if (enemyController.isEmpty() == false && bulletControllerVector.isEmpty() == false) {
            for(int i = 0; i < bulletControllerVector.size(); i++) {
                if(bulletControllerVector.get(i).gameObject.getY() < 0) bulletControllerVector.remove(i);
                else {
                    for (int j = 0; j < enemyController.size(); j++) {
                        if (bulletControllerVector.get(i).gameObject.getX() > enemyController.get(j).gameObject.getX() && bulletControllerVector.get(i).gameObject.getX() - enemyController.get(j).gameObject.getX() < enemyController.get(j).gameObject.getWidth() && bulletControllerVector.get(i).gameObject.getY() > enemyController.get(j).gameObject.getY() && bulletControllerVector.get(i).gameObject.getY() - enemyController.get(j).gameObject.getY() < enemyController.get(j).gameObject.getHeight()) {
                            bulletControllerVector.remove(i);
                            enemyController.remove(j);
                        }
                    }
                }
            }
        }

    }

    //TODO: work on the second plane
}
