package PEngine;

import processing.core.PVector;

public abstract class PPhysicsBody {
    public float mass;
    public float pFriction = 1f;
    public PVector pVelocity = new PVector();
    public PVector pGravity = new PVector(0f,9.8f,0f);

    void update() {
        pVelocity.mult((float)Math.pow(pFriction,  GameManager.deltaTime)).add(pGravity);
    }
}
