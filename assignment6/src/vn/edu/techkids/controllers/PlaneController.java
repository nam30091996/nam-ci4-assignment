package vn.edu.techkids.controllers;


import vn.edu.techkids.Utils;
import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;
import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneController;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class PlaneController extends SingleControllerWithHP implements Colliable {

    public final int SPEED = 4;
    public final int MAX_BULLET_COUNT = 3;
    private int speed = SPEED;
    private int count = 0;

    private BulletControllerManager bulletControllerManager;

    private PlaneController(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        bulletControllerManager = new BulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    public void move(PlaneDirection planeDirection) {
        switch (planeDirection) {
            case NONE:
                break;
            case UP:
                this.gameVector.dy = -speed;
                break;
            case DOWN:
                this.gameVector.dy = speed;
                break;
            case LEFT:
                this.gameVector.dx = -speed;
                break;
            case RIGHT:
                this.gameVector.dx = speed;
                break;
            case STOP_X:
                this.gameVector.dx = 0;
                break;
            case STOP_Y:
                this.gameVector.dy = 0;
                break;
        }

    }

    public void shot() {
        if (bulletControllerManager.size() < MAX_BULLET_COUNT) {
            Bullet bullet = new Bullet(
                    gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                    gameObject.getY(),
                    Bullet.DEFAULT_WIDTH,
                    Bullet.DEFAULT_HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
            BulletController bulletController = new BulletController(
                    bullet,
                    imageDrawer
            );

            this.bulletControllerManager.add(bulletController);
        }
    }

    private static PlaneController planeController1;

    public static PlaneController getPlaneController1() {
        if (planeController1 == null) {
            Plane plane = new Plane(100, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane4.png");
            planeController1 = new PlaneController(plane, planeDrawer);
        }
        return planeController1;
    }

    @Override
    public void run() {
        if (this.gameObject.isAlive()) {
            Rectangle rectangle=this.gameObject.getNextRect(this.gameVector);
            if(GameConfig.getInst().isInScreen(rectangle)) {
                super.run();
            }
            if(this.speed != SPEED) count++;
            if(GameConfig.getInst().durationInSeconds(count) == 3) {
                count = 0;
                this.speed = SPEED;
            }
            bulletControllerManager.run();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.gameObject.isAlive()) {
            super.paint(g);
            bulletControllerManager.paint(g);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyPlaneController) {
            EnemyPlane enemyPlane = (EnemyPlane) c.getGameObject();
            enemyPlane.setAlive(false);
        } else if (c instanceof EnemyBulletController) {
            EnemyBullet enemyBullet = (EnemyBullet) c.getGameObject();
            enemyBullet.setAlive(false);
        }
    }

    public void reduceSpeed(int x) {
        speed /= x;
    }

    /* TODO: Work on the second plane */
}
