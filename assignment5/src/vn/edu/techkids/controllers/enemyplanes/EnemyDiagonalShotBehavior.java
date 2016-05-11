package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;
import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class EnemyDiagonalShotBehavior implements EnemyShotBehavior {
    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.creat(EnemyBulletType.DIAGONAL, x, y);
    }
}
