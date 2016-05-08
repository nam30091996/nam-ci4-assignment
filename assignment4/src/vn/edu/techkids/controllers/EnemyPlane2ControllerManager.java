package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by MyComputer on 5/8/2016.
 */
public class EnemyPlane2ControllerManager extends ControllerManager {
    private int count = 0;

    private EnemyPlane2ControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if(GameConfig.getInst().durationInSeconds(count) > 4) {
            count = 0;
            for (int x = -300; x < GameConfig.getInst().getScreenWidth() - 40; x += 100) {
                EnemyPlane enemyPlane= new EnemyPlane(x, 0, 32, 32);
                ImageDrawer imageDrawer =
                        new ImageDrawer("resources/enemy_plane_white_1.png");
                EnemyPlane2Controller enemyPlane2Controller = new EnemyPlane2Controller(
                        enemyPlane,
                        imageDrawer
                );
                this.singleControllerVector.add(enemyPlane2Controller);
            }
        }
    }

    private static EnemyPlane2ControllerManager inst;
    public static EnemyPlane2ControllerManager getInst() {
        if(inst == null) {
            inst = new EnemyPlane2ControllerManager();
        }

        return inst;
    }
}
