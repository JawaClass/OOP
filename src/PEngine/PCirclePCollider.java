package PEngine;

public class PCirclePCollider extends PCollider {
    float r;

    PCirclePCollider(PosRot posRot, float r) {
        super(posRot);
        this.r = r;
    }

    @Override
    PosRot[] getPoints() {
        return new PosRot[0];
    }
}
