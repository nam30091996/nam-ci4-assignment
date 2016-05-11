package vn.edu.techkids.controllers;

import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.views.GameDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class GiftController extends SingleController implements Colliable {
    public static final int INCREASE_DEFAULT = 2;

    public GiftController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(this.gameObject.isAlive())
        super.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            BulletController.increase(INCREASE_DEFAULT);
        }
    }
}
