package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/8/2016.
 */
public class EnemyPlane2Controller extends SingleControllerWithHP implements Colliable {
    private EnemyBulletControllerManager enemyBulletControllerManager;
    private int count = 0;

    public EnemyPlane2Controller(EnemyPlane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 2;
        this.gameVector.dx = 2;
        enemyBulletControllerManager = new EnemyBulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    /* TODO override run */

    @Override
    public void run() {
        super.run();
        this.enemyBulletControllerManager.run();

        count++;
        if (GameConfig.getInst().durationInSeconds(count) >= 2) {
            count = 0;
            EnemyBullet enemyBullet = new EnemyBullet(
                    gameObject.getX() + gameObject.getWidth() / 2 - EnemyBullet.WIDTH / 2,
                    gameObject.getY() + gameObject.getHeight(),
                    EnemyBullet.WIDTH,
                    EnemyBullet.HEIGHT,
                    3
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");
            EnemyBullet2Controller enemyBullet2Controller = new EnemyBullet2Controller(
                    enemyBullet,
                    imageDrawer
            );
            this.enemyBulletControllerManager.add(enemyBullet2Controller);
        }

        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.enemyBulletControllerManager.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            Plane plane=(Plane)c.getGameObject();
            plane.decreaseHP(2);
        }
    }
}
