package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;
import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by qhuydtvt on 5/9/2016.
 */
public class EnemyDirectShotBehavior implements EnemyShotBehavior {

    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.creat(EnemyBulletType.DIRECT, x, y);
    }
}
