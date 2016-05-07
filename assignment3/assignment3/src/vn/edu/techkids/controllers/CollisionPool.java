package vn.edu.techkids.controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by MyComputer on 5/6/2016.
 */
public class CollisionPool {
    private Vector<Colliable> coliableVector;

    public CollisionPool() {
        coliableVector = new Vector<Colliable>();
    }

    public void add(Colliable c) {
        this.coliableVector.add(c);
    }

    public void run() {
        Iterator<Colliable> iterator = coliableVector.iterator();
        while(iterator.hasNext()) {
            Colliable c = iterator.next();
            if(!c.getGameObject().isAlive()) {
                iterator.remove();
            }
        }

        for(int i = 0; i < coliableVector.size() - 1; i++) {
            for(int j = i + 1; j<coliableVector.size(); j++) {
                Colliable ci = coliableVector.get(i);
                Colliable cj = coliableVector.get(j);
                Rectangle ri = ci.getGameObject().getRect();
                Rectangle rj = cj.getGameObject().getRect();
                if(ri.intersects(rj)) {
                    ci.onColide(cj);
                    cj.onColide(ci);
                }
            }
        }
    }

    private static CollisionPool collisionPool;
    public static CollisionPool getCollisionPool() {
        if(collisionPool == null) {
            collisionPool = new CollisionPool();
        }
        return collisionPool;
    }
}
