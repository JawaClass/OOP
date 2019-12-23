package PEngine;

import processing.core.PApplet;
import processing.core.PImage;


public abstract class PEngine extends PApplet {


    private int lastTime = 0;
    private int delta = 0;


    public void setup() {
        frameRate(18);
        GameManager.g = g;
        GameManager.a = this;
        initGame();
    }




    public void settings() {
        super.size(Consts.WIDTH, Consts.HEIGHT);
    }

    public void setBackground(String path) {
        GameManager.backgroundImage = loadImage(path);
        surface.setSize(getBackground().width, getBackground().height);
    }
    PImage getBackground() { return GameManager.backgroundImage; }

    public abstract void initGame();

    @Override
    public void keyPressed() {
        for (PPlayer p : GameManager.pPlayers) if (p.controllerAttached()) p.pController.pressButton(keyCode);
    }

    @Override
    public void keyReleased() { for (PPlayer p : GameManager.pPlayers) if (p.controllerAttached()) p.pController.releaseButton(keyCode); }

    @Override
    public void draw() {

        GameManager.g.clear();
        // at first draw background
        if (getBackground() != null) background(GameManager.backgroundImage);
        else background(GameManager.backgroundColor);

//
        for (PPlayer p : GameManager.pPlayers) p.update();
//

        for (int i = 0; i < GameManager.pObjects.size(); i++) {
            GameManager.pObjects.get(i).update();
            GameManager.pObjects.get(i).display();
//            PLog.i("OBJECT::: " + GameManager.pObjects.get(i).getClass().toString());
        }

        //
        delta = millis() - lastTime;
        lastTime = millis();
        GameManager.deltaTime = delta;
    }
}
