package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by MyComputer on 5/8/2016.
 */
public class EnemyBullet2Controller extends SingleController implements Colliable {
    public EnemyBullet2Controller(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        this.gameVector.dx = -5;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            Plane plane = (Plane) c.getGameObject();
            EnemyBullet e = (EnemyBullet) this.getGameObject();
            plane.decreaseHP(e.getDamage());
            if (plane.getHp() <= 0) {
                plane.setAlive(false);
            }
        }
    }
}
