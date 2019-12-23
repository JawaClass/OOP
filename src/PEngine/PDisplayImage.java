package PEngine;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class PDisplayImage extends PDisplay<PImage> {

    float rotValue = 0f;
    float rotTotal = 0f;
    float oldX = Float.NEGATIVE_INFINITY, oldY=Float.POSITIVE_INFINITY;
    boolean need2Rot = false;

    public PDisplayImage(PImage img) {
        super(new PosRot(50f,50f));
        this.element = img; }
    @Override
    public void rotate(float alpha) {
        rotValue = alpha;
        rotTotal += alpha;
//        PLog.i("ROTATE");

        GameManager.a.pushMatrix();
//           GameManager.a.translate(element.width, element.height);
           GameManager.a.rotate(rotTotal);

       GameManager.a.popMatrix();

    }

    @Override
    public void display() {

//        PLog.i("DISPLAY");
//        translate(width/2, height/2);
//        rotate(counter*TWO_PI/360);
//        translate(-img.width/2, -img.height/2);


//
//        float sine = (float)Math.sin(rotTotal ); // % (2*Math.PI)
//        float cosine = (float)Math.cos(rotTotal ); // % (2*Math.PI)


        GameManager.a.image(element,   posRot.x, posRot.y);

//        if (need2Rot) {

//            float x1 = x*sine-element.width/2f;
//            float y1 = y*cosine-element.height/2f;
//        PVector v = new PVector(x1, y1).sub(x1, y1);

//            GameManager.a.pushMatrix();
//            GameManager.a.translate(element.width, element.height);
//            GameManager.a.rotate(rotTotal);
//            GameManager.a.image(element, x, y);
////        GameManager.a.image(element,   x, y);
//
//        GameManager.a.popMatrix();




//
//        }
//
//        else
//            GameManager.a.image(element,   x, y);
//    need2Rot = false;
        //            GameManager.a.image(element,   x*sine-element.width/2f, y*cosine-element.height/2f);


//        }
        //else if(need2Move){

//            oldX = x;
//            oldY = y;
        //}










//        GameManager.a.translate(element.width/2f, element.height/2f);
//        GameManager.a.rotate(rotTotal);
//        GameManager.a.translate(-element.width/2f, -element.height/2f);
//        float c = (float) Math.cos(rotTotal);
//        GameManager.a.translate(GameManager.a.width/2f, GameManager.a.height/2f);
//        GameManager.a.rotate(c);
//        GameManager.a.translate(-element.width, -element.height);

//        float sine = (float)Math.sin(rotTotal);
//        float cosine = (float)Math.cos(rotTotal);
//        GameManager.a.rotate(rotTotal);
//        GameManager.a.image(element,   x/2f*sine +element.width/2f*cosine, y/2f*cosine+element.height/2f*sine);
//        GameManager.a.popMatrix();


//        GameManager.a.pushMatrix();
//        // move the origin to the pivot point
//        GameManager.a.translate(40, 40);
//
//        // then pivot the grid
//        GameManager.a.rotate(PApplet.radians(45));
//
//        // and draw the square at the origin
//        GameManager.a.fill(0);
//        GameManager.a.rect(0, 0, 40, 20);
//        GameManager.a.popMatrix();



    }

    @Override
    public void changeColor(int color) {
//            element.
    }

}
