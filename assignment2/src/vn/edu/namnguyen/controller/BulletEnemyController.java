package vn.edu.namnguyen.controller;

import vn.edu.namnguyen.model.GameObject;
import vn.edu.namnguyen.view.GameDrawer;
import vn.edu.namnguyen.model.GameObject;

/**
 * Created by MyComputer on 5/3/2016.
 */
public class BulletEnemyController extends SingleController {
    private final int SPEED = 7;

    public BulletEnemyController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVector.dy = SPEED;
    }
}
