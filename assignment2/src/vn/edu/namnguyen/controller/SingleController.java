package vn.edu.namnguyen.controller;

import vn.edu.namnguyen.model.GameObject;
import vn.edu.namnguyen.model.GameVector;
import vn.edu.namnguyen.view.GameDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 4/29/2016.
 */
public class SingleController implements Controller {
    protected GameObject gameObject;
    protected GameDrawer gameDrawer;
    protected GameVector gameVector;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer){
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = new GameVector();
    }

    public GameObject getGameObject(){
        return this.gameObject;
    }

    @Override
    public void run() {
        gameObject.move(gameVector);
    }

    @Override
    public void paint(Graphics g) {
        gameDrawer.paint(this.gameObject, g);
    }
}
