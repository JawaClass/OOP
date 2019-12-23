package PEngine;

public class PointPCollider extends PCollider {
    PointPCollider(PosRot posRot) {
        super(posRot);
    }

    @Override
    PosRot[] getPoints() {
        return new PosRot[0];
    }
}
