package PEngine;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.Stack;

import static processing.core.PConstants.*;

public class GameManager {
    // Interactive Objects
    public static PGraphics g;
    public static PApplet a;
    public static ArrayList<PObject> pObjects = new ArrayList<PObject>();
    public static ArrayList<PPlayer> pPlayers = new ArrayList<PPlayer>();
    public static Stack<PLevel> pLevels = new Stack<PLevel>();
    public static PShape newCircle(float r) { return g.createShape(ELLIPSE, 0, 0, r, r); }
    public static PShape newEllipse(float rHori, float rVert) { return g.createShape(ELLIPSE, 0, 0, rHori, rVert); }
    public static PShape newPoint() { return g.createShape(POINT, 0, 0); }
    public static PShape newRectangle(float width, float height) { return g.createShape(RECT, 0, 0, width, height); }
    public static PShape newLine(PosRot p1, PosRot p2) { return g.createShape(LINE, p1.x, p1.y, p2.x, p2.y); }

    // other
    public static PImage backgroundImage = null;
    public static int backgroundColor = Consts.C_YELLOW;
    public static float deltaTime = 0f;

}
