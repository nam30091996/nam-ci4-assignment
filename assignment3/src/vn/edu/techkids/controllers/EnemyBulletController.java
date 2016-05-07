package vn.edu.techkids.controllers;


import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {

    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        CollisionPool.getCollisionPool().add(this);
    }

    @Override
    public void run() {
        super.run();
        if(!GameConfig.getInst().isInScreen(this.gameObject)){
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onColide(Colliable c) {
        if(c instanceof PlaneController) {
            if (c instanceof PlaneController) {
                c.getGameObject().setHp(c.getGameObject().getHp() - 1);
                gameObject.setAlive(false);
                if (c.getGameObject().getHp() == 0) {
                    c.getGameObject().setAlive(false);
                }

            }
        }
    }
}
