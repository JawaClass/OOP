package PEngine;

import processing.core.PShape;

public class PDisplayShape extends PDisplay<PShape> {

    public PDisplayShape(PShape shape) { this.element = shape; }
    @Override
    public void rotate(float alpha) {
        element.rotate(alpha);
    }

    @Override
    public void display() {
        GameManager.g.shape(element, posRot.x, posRot.y);
    }

    @Override
    public void changeColor(int color) {
element.setFill(color);    }

}


