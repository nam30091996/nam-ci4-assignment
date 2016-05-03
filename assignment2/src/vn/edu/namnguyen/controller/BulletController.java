package vn.edu.namnguyen.controller;

import vn.edu.namnguyen.model.Bullet;
import vn.edu.namnguyen.model.GameObject;
import vn.edu.namnguyen.view.GameDrawer;
import vn.edu.namnguyen.view.ImageDrawer;

/**
 * Created by MyComputer on 4/29/2016.
 */
public class BulletController extends SingleController {
    private final int SPEED = 15;

    public BulletController(Bullet bullet, ImageDrawer gameDrawer) {
        super(bullet, gameDrawer);
        gameVector.dy = -SPEED;
    }

    public boolean checkEnemy(EnemyController enemyController){
        if (this.gameObject.getX() > enemyController.gameObject.getX() && this.gameObject.getX() - enemyController.gameObject.getX() < enemyController.gameObject.getWidth() && this.gameObject.getY() > enemyController.gameObject.getY() && this.gameObject.getY() - enemyController.gameObject.getY() < enemyController.gameObject.getHeight()) return true;
        return false;
    }
}
