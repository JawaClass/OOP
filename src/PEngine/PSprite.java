package PEngine;

import processing.core.PImage;

import java.util.Scanner;

public class PSprite extends PDisplay<PImage[]> implements Drawable{


    public PSprite(PImage[] imageSequence) {
        this(imageSequence, new PosRot(220f,200f));
    }

    public PSprite(PImage[] imageSequence, PosRot posRot) {
        super(posRot);
        this.element = imageSequence;
    }
float rotCnt=0f;
    public void rotate(float rotValue) {
        posRot.rot+=rotValue;

        rotCnt+=0.001f;
        float sine = (float)Math.sin(rotCnt) % (float) (2*Math.PI);
        float cosine = (float)Math.cos(rotCnt) % (float) (2*Math.PI);

        for (int i = 0; i < element.length; i++) {
            element[i].loadPixels();

//            GameManager.a.pushMatrix();

            GameManager.a.rotate(rotCnt);
//            element[i].updatePixels();

//            GameManager.a.popMatrix();
            element[i].updatePixels();

            posRot.x = posRot.x*sine-element[i].width/2f;
            posRot.y = posRot.y*cosine-element[i].height/2f;

            PLog.i("ROTATEPSPRITE ....................");

        }
    }

    @Override
    public void display() {
        for (int i = 0; i < element.length; i++) {
            GameManager.g.image(element[i], posRot.x, posRot.y);
        }
    }

    @Override
    public void changeColor(int rgb) {

    }

}
