package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;
import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by MyComputer on 5/14/2016.
 */
public class EnemyDiagonalLeftShotBehavior implements EnemyShotBehavior {
    @Override
    public EnemyBulletController doShot(int x, int y) {
        EnemyBullet enemyBullet =
                new EnemyBullet(x, y,
                        EnemyBullet.WIDTH, EnemyBullet.HEIGHT, 2, 2);
        ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");

        EnemyBulletController enemyBulletController = new EnemyBulletController(
                enemyBullet,
                imageDrawer,
                new GameVector(3, 3)
        );
        return enemyBulletController;
    }
}
