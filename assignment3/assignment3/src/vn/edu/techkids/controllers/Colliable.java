package vn.edu.techkids.controllers;

import vn.edu.techkids.models.GameObject;

/**
 * Created by MyComputer on 5/6/2016.
 */
public interface Colliable {
    void onColide(Colliable c);
    GameObject getGameObject();
}
