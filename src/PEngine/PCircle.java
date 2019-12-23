package PEngine;

import processing.core.PShape;

import static processing.core.PConstants.GROUP;

public class PCircle extends PObject {
    float r;
    PCircle(float ... params) {
        super(new PosRot(params[0], params[1]));
        r = params[2];
        PCollider = new PCirclePCollider(posRot, r);
        setpDisplay(new PDisplayShape(new PShape(GROUP)));
        ((PShape) getpDisplay().element).addChild(GameManager.newCircle(r*2));//

        // getElement().// el(GameManager.newCircle(r*2));
//        shape.addChild(GameManager.newCircle(r*2));
        //shape.addChild(GameManager.newRectangle(100f, 10f));
    }
}
