package PEngine;


import processing.core.PVector;

import java.util.ArrayList;

public abstract class PObject implements PObjectActions {
    //public PShape shape;
    private PDisplay pDisplay;
    public PCollider PCollider;
    public PPhysicsBody pPhysicsBody;
    public PosRot posRot;
    private final PosRot creationPosRot;
    public String tag = "";


    public void move(PVector v) {
        pPhysicsBody.pVelocity.add(v);
    }
    public void moveX(float x) {
        move(new PVector(x,0));
    }
    public void moveY(float y) {
        move(new PVector(0,y));
    }
    public void update() {
        if (pPhysicsBody == null) return;
        pPhysicsBody.update();
        posRot.x += pPhysicsBody.pVelocity.x;
        posRot.y += pPhysicsBody.pVelocity.y;

    }
    public PCollissionListener PCollissionListener;


    public PDisplay getpDisplay() { return pDisplay; }
    public void setpDisplay(PDisplay pDisplay) { this.pDisplay = pDisplay; if (this.pDisplay != null) pDisplay.posRot = posRot; }

    public void rotateInDegree(float degree) {
        float rad = (float) Math.toRadians(degree);
        pDisplay.rotate(rad);
        posRot.rot += rad;
//        PLog.i("rot::: " + posRot.rot);
    }

    public void rotateInRad(float rad) {
        pDisplay.rotate(rad);
        posRot.rot += rad;
//        PLog.i("rot::: " + posRot.rot);
    }

    public PObject(PosRot posRot) { GameManager.pObjects.add(this); this.posRot = posRot; creationPosRot = posRot.copy(); }

    @Override
    public void display() {

        if (pDisplay == null)
            return;

        if (PCollider != null) {
            pDisplay.changeColor(200);
            ArrayList<PObject> collidingPObjects = new ArrayList<PObject>();
            for (int i = 0; i < GameManager.pObjects.size(); i++) {
                if (GameManager.pObjects.get(i) == this) continue;
                PObject other = GameManager.pObjects.get(i);
                if (other.PCollider != null) {
                    boolean result = PCollider.collides(other.PCollider);
                    if (result) {
                        collidingPObjects.add(other);
                        pDisplay.changeColor(40);
                        if (PCollissionListener != null)
                            PCollissionListener.onHit(other);
                    }
                }


            }
            if (PCollissionListener != null)
                PCollissionListener.onAllHit(collidingPObjects);

        }

        if (pDisplay.isVisible) pDisplay.display();

    }
}
