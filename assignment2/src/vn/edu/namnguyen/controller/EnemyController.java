package vn.edu.namnguyen.controller;

import vn.edu.namnguyen.model.Bullet;
import vn.edu.namnguyen.model.Plane;
import vn.edu.namnguyen.view.GameDrawer;
import vn.edu.namnguyen.view.ImageDrawer;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by MyComputer on 5/3/2016.
 */
public class EnemyController extends SingleController {
    public final int SPEED = 1;
    private Vector<BulletEnemyController> bulletEnemyControllerVector;

    public EnemyController(Plane enemyPlane, GameDrawer gameDrawer) {
        super(enemyPlane, gameDrawer);
        this.gameVector.dx = 0;
        this.gameVector.dy = SPEED;
        bulletEnemyControllerVector = new Vector<BulletEnemyController>();
    }

    public void shot() {
        Bullet bullet = new Bullet(gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 4, gameObject.getY() + gameObject.getWidth(), Bullet.DEFAULT_WIDTH / 2, Bullet.DEFAULT_HEIGHT / 2);
        ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
        BulletEnemyController bulletEnemyController = new BulletEnemyController(bullet, imageDrawer);
        this.bulletEnemyControllerVector.add(bulletEnemyController);
    }

    private static EnemyController enemyController1;

    public static void setNull(){
        enemyController1 = null;
    }

    public static EnemyController getEnemyController1() {
        if(enemyController1 == null) {
            Random rd = new Random();
            int x = rd.nextInt(350);
            Plane plane = new Plane(x, 0, 50, 50);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane1.png");
            enemyController1 = new EnemyController(plane, planeDrawer);
        }
        return enemyController1;
    }

    public void run() {
        super.run();
        for(BulletEnemyController bulletEnemyController : this.bulletEnemyControllerVector) {
            bulletEnemyController.run();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(BulletEnemyController bulletEnemyController : this.bulletEnemyControllerVector) {
            bulletEnemyController.paint(g);
        }
    }


}
