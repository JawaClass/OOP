package Frogger;


import PEngine.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class FroggerGame extends PEngine {
    PlayerFrog frog;
    static String DEAD_ZONE = "DEAD_ZONE";
    static String LIFE_ZONE = "LIFE_ZONE";
    static String RESSOURCE_PATH = "C:\\Users\\18mas\\AndroidStudioProjects\\OOP\\src\\Frogger\\";
    public static void main(String[] args) {
        PApplet.main(FroggerGame.class);
    }

    LaneCar lane1Car;
    LaneCar lane2Car;
    LaneTreeTrunk lane3TreeTrunk;
    LaneTreeTrunk lane4TreeTrunk;
    //TreeTrunk[] treeTrunks = new TreeTrunk[3];
    UiLifePoints uiLifePoints;


    @Override
    public void initGame() {
        setBackground("C:\\Users\\18mas\\AndroidStudioProjects\\OOP\\src\\Frogger\\bg.jpg");


//        for (int i = 0; i < treeTrunks.length; i++) {
//            String imgPath = RESSOURCE_PATH + "treeTrunk_" + (int)(Math.random() * 2) + ".png";
//            float x = -800f-(i)*500f;
//            float y = ((float)Math.random()*(i+1)*5f + (i+1)*60f) % 180f;
//            treeTrunks[i] = new TreeTrunk(imgPath, x, y);
//        }

//        new PWall(300f,200f, 240f, 200f);
//        new PLine(5f,5f, 400f,400f);
         // Interactive Objects
//        new PWall(300f,200f, 240f, 200f);0
//        new PLine(5f,5f, 400f,400f);





        new Fly("C:\\Users\\18mas\\AndroidStudioProjects\\OOP\\src\\Frogger\\fly.png", (float) Math.random()*GameManager.g.width, (float) Math.random()*GameManager.g.height);


        PWall deadZoneWater = new PWall(0f, 30f, width, 140f);
        deadZoneWater.tag = FroggerGame.DEAD_ZONE;
        deadZoneWater.getpDisplay().isVisible = false;

        lane1Car = new LaneCar(25f, 1, -1999, GameManager.a.height - 140f);
        lane2Car = new LaneCar(15f, -1, 1999, GameManager.a.height - 250f);

        lane3TreeTrunk = new LaneTreeTrunk(3f, 1, -1999, 30);
        lane4TreeTrunk = new LaneTreeTrunk(6f, -1, 1999, 130);

        frog = new PlayerFrog();
        uiLifePoints = new UiLifePoints(frog.JUMP_COOLDOWN);

        pSprite = new PSprite(new PImage[] { GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog0_N.png") });





//        deadZoneWater.pDisplay.isVisible = false;
        // other
//        size(900,492);
//        size(900,492);

        ;
     }
    int diaomdCnt = 0;
    PSprite pSprite;

    @Override
    public void draw() {
        super.draw();
        text(frog.frogState.toString(), width-50f,height-50f);
        frog.update();
        uiLifePoints.cnt = frog.JUMP_COOLDOWN-frog.jumpCoolDownCnt;

        if (frameCount % 150 == 0f) {
            diaomdCnt++;
            new Diamond(FroggerGame.RESSOURCE_PATH+"diamond.png",(float)(Math.random()*GameManager.a.width), (float)(Math.random()*GameManager.a.height));

        }

        text("Diamonds: " + (diaomdCnt-(int)GameManager.pObjects.stream().filter(x->x instanceof Diamond).count()), 20f,20f);
        pSprite.rotate(0.001f);

        pSprite.display();
    }
}


enum FROG_VIEW_DIRECTION { S, W, N, E }
enum FROG_STATE { DEAD, IDLE, JUMP, WALK }

class PlayerFrog extends PPlayer implements PCollissionListener {

    // Images

    static Map<String, PImage> frogImageMap;
    static {
        frogImageMap = new HashMap<String, PImage>();
        frogImageMap.put("0N", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog0_N.png"));
        frogImageMap.put("0E", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog0_E.png"));
        frogImageMap.put("0S", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog0_S.png"));
        frogImageMap.put("0W", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog0_W.png"));
        //
        frogImageMap.put("1N", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog1_N.png"));
        frogImageMap.put("1E", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog1_E.png"));
        frogImageMap.put("1S", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog1_S.png"));
        frogImageMap.put("1W", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog1_W.png"));
        //
        frogImageMap.put("2N", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog2_N.png"));
        frogImageMap.put("2E", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog2_E.png"));
        frogImageMap.put("2S", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog2_S.png"));
        frogImageMap.put("2W", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog2_W.png"));
        //
        frogImageMap.put("3N", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog3_N.png"));
        frogImageMap.put("3E", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog3_E.png"));
        frogImageMap.put("3S", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog3_S.png"));
        frogImageMap.put("3W", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog3_W.png"));
        //
        frogImageMap.put("4N", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog4_N.png"));
        frogImageMap.put("4E", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog4_E.png"));
        frogImageMap.put("4S", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog4_S.png"));
        frogImageMap.put("4W", GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog4_W.png"));


    }
    PImage frogDeathSign = GameManager.a.loadImage(FroggerGame.RESSOURCE_PATH+"frog_Dead.png");
    //
    FROG_VIEW_DIRECTION viewDirection;
    FROG_STATE frogState = FROG_STATE.IDLE;
    float JUMP_COOLDOWN = 5;
    float jumpCoolDownCnt = 0;

    void changefrogState(FROG_STATE state) {
        if (frogState == FROG_STATE.DEAD)
            return;
        if (state == FROG_STATE.WALK && frogState == FROG_STATE.JUMP)
            return;
        frogState = state;
    }
    void update() {
        if (GameManager.a.frameCount % 2 != 0) return;
        if (frogState == FROG_STATE.DEAD) return;
        else if (frogState == FROG_STATE.IDLE) pObject.getpDisplay().element = frogImageMap.get(0+""+viewDirection);
        else if (frogState == FROG_STATE.JUMP) pObject.getpDisplay().element = frogImageMap.get(3+""+viewDirection);
        else {
//            if (GameManager.a.frameCount % 2f ==0)
                pObject.getpDisplay().element = frogImageMap.get(GameManager.a.frameCount%4+""+viewDirection);
        }

    }

    @Override
    public void onAllHit(ArrayList<PObject> allOther) {
        int deadZoneCnt = (int)allOther.stream().filter(o->o.tag.equals(FroggerGame.DEAD_ZONE)).count();
        int lifeZoneCnt = (int)allOther.stream().filter(o->o.tag.equals(FroggerGame.LIFE_ZONE)).count();

        if (deadZoneCnt > lifeZoneCnt) {
            if (frogState != FROG_STATE.JUMP) {
                changefrogState(FROG_STATE.DEAD);
                pObject.getpDisplay().element = frogDeathSign;
            }
        }

    }

    @Override
    public void onHit(PObject other) {
        if (other instanceof Diamond) {
            GameManager.pObjects.remove(other);
        }

    }


    void changeViewDirection(FROG_VIEW_DIRECTION view) {
        viewDirection = view;
        pObject.getpDisplay().element = frogImageMap.get(GameManager.a.frameCount%4+""+viewDirection);
    }


    void refillJumps() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                changefrogState(FROG_STATE.IDLE);
                if (jumpCoolDownCnt > 0) jumpCoolDownCnt--;
                refillJumps();
            }
        }, 350);
    }
PlayerFrog() {
    super();
    pObject = new PEmpty(GameManager.a.width-400f, GameManager.a.height - 50f);
    pObject.PCollissionListener = this;
    pObject.setpDisplay(new PDisplayImage(frogImageMap.get("0N")));
    pObject.PCollider = new PRectanglePCollider(pObject.posRot, ((PImage)pObject.getpDisplay().element).width, ((PImage)pObject.getpDisplay().element).height);
    viewDirection = FROG_VIEW_DIRECTION.N;
    refillJumps();

//    PImage imgDisplay = (PImage)pObject.pDisplay.element;

    pController = new PController(new PControllerEventListener() {
        @Override
        public void keyPressed(KEY key) {

        }

        @Override
        public void keyReleased(KEY key) {

        }

        @Override
        public void keyHold(Set<KEY> keys) {
            if (frogState == FROG_STATE.DEAD)
                return;
            if (frogState == FROG_STATE.JUMP)
                return;
            float unit = 8f;
            for (KEY key: keys) {
                switch (key) {
                    case RIGHT:
                        if (viewDirection != FROG_VIEW_DIRECTION.E) {
                            changeViewDirection(FROG_VIEW_DIRECTION.E);
                        } else {
                            pObject.posRot.x+=unit; changefrogState(FROG_STATE.WALK);
                        }
                        break;
                    case LEFT:
                        if (viewDirection != FROG_VIEW_DIRECTION.W) {
                            changeViewDirection(FROG_VIEW_DIRECTION.W);
                        } else {
                            pObject.posRot.x-=unit; changefrogState(FROG_STATE.WALK);
                        }
                        break;
                    case UP:
                        if (viewDirection != FROG_VIEW_DIRECTION.N) {
                            changeViewDirection(FROG_VIEW_DIRECTION.N);
                        } else {
                            pObject.posRot.y-=unit; changefrogState(FROG_STATE.WALK);
                        }
                        break;
                    case DOWN:
                        if (viewDirection != FROG_VIEW_DIRECTION.S) {
                            changeViewDirection(FROG_VIEW_DIRECTION.S);
                        } else {
                            pObject.posRot.y+=unit; changefrogState(FROG_STATE.WALK);
                        }
                        break;
                    case SPACE:
                        if (jumpCoolDownCnt > 0)
                            break;
                        if (viewDirection == FROG_VIEW_DIRECTION.S)
                            pObject.posRot.y+=unit*10;
                        if (viewDirection == FROG_VIEW_DIRECTION.N)
                            pObject.posRot.y-=unit*10;
                        if (viewDirection == FROG_VIEW_DIRECTION.E)
                            pObject.posRot.x+=unit*10;
                        if (viewDirection == FROG_VIEW_DIRECTION.W)
                            pObject.posRot.x-=unit*10;
                        changefrogState(FROG_STATE.JUMP);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                changefrogState(FROG_STATE.WALK);
                                jumpCoolDownCnt = JUMP_COOLDOWN;
                            }
                        }, 400);
                        break;
                }
            }
        }
    });



//
//    GameManager.g.image(img, 100,100);
//
//    GameManager.g.rotate(0.11f);
////    PImage img = GameManager.a.loadImage("C:\\Users\\18mas\\AndroidStudioProjects\\OOP\\src\\Frogger\\frog1.png");
//    GameManager.g.image(img, 100,100);
//    PGraphics pg = GameManager.a.createGraphics(img.width, img.height, PConstants.JAVA2D);
//
//    PLog.i("pg", pg);
//    PLog.i("image", img);

//    pg.beginShape();
//    pg.image(img, 200f, 200f);
//    pg.endShape();

//
//    PShape shape =  GameManager.newRectangle(img.width,img.height);
//
//
//
//    pObject.shape.beginShape();
//    pObject.shape.texture(img);
//    pObject.shape.vertex(10, 20);
//    pObject.shape.vertex(80, 5);
//    pObject.shape.vertex(95, 90);
//    pObject.shape.vertex(40, 95);
//    pObject.shape.endShape();
//
//
//
//    GameManager.a.beginShape();
//    GameManager.a.texture(img);
//    GameManager.a.endShape();
////
//    shape.beginShape();
//    shape.(img);
//    shape.endShape();
//    pObject.shape = shape;

//pObject.shape = GameManager.newRectangle(img.width, img.height);
//pObject.shape.beginShape();;
//pObject.shape.width = 200f;
//pObject.shape.height = 200f;
//pObject.shape.tint(20);
//pObject.shape.endShape(PConstants.CLOSE);



    //pObject.shape = pg.createShape(PConstants.RECT,100, 100, 100,100);

}


//
//        @Override
//        public void keyPressed(KEY key) {
//            //PLog.i("keyPressed", key);
//        }
//
//        @Override
//        public void keyReleased(KEY key) {
//            //PLog.i("keyReleased", key);
//        }
//
//        @Override
//        public void keyHold(Set<KEY> keys) {
//            float unit = 25f;
//            for (KEY key: keys) {
//                switch (key) {
//                    case RIGHT:
//                        pObject.posRot.x+=unit;
//                        break;
//                    case LEFT:
//                        pObject.posRot.x-=unit;
//                        break;
//                    case UP:
//                        pObject.posRot.y-=unit;
//                        break;
//                    case DOWN:
//                        pObject.posRot.y+=unit;
//                        break;
//                }
//            }
//        }

}


//



class UiLifePoints extends PObject {
    UiLifePoints(float grid) {super(new PosRot(GameManager.g.width - 40f, 10f)); this.grid =grid; this.cnt = grid; }
    float cnt;
    float grid;
    float width = 30f;
    float height = 120f;

    @Override
    public void display() {
        super.display();
        for (int i = 0; i < cnt; i++) {
            GameManager.g.rect(posRot.x, posRot.y+(i*height/cnt), width, height/grid);
        }
    }
}


class Diamond extends PObject {
    Diamond(String imgPath, float x, float y) {
        super(new PosRot(x,y));
        PImage img = GameManager.a.loadImage(imgPath);
        setpDisplay(new PDisplayImage(img));
        PCollider = new PRectanglePCollider(posRot, img.width, img.height);
    }
}

class Fly extends PObject {
    Fly(String imgPath, float x, float y) {
        super(new PosRot(x,y));
        PImage img = GameManager.a.loadImage(imgPath);
        setpDisplay(new PDisplayImage(img));
        PCollider = new PRectanglePCollider(posRot, img.width, img.height);
        tag = FroggerGame.DEAD_ZONE;
    }

    float speed = 2f;

}

class LaneCar {
    Car[] cars = new Car[GameManager.a.width*4/600];
    float speed;
    int direction;
    public LaneCar(float speed, int direction, float startX, float startY) {
        this.speed = speed;
        this.direction = direction;
        float x = startX;
        for (int i = 0; i < cars.length; i++) {
            String imgPath = FroggerGame.RESSOURCE_PATH + "car_" + (int)(Math.random() * 5) + ".png";
            cars[i] = new Car(imgPath, x+=600f*direction, startY);
            cars[i].speed = speed * direction;
        }

    }
}


class LaneTreeTrunk {
    TreeTrunk[] trunks = new TreeTrunk[GameManager.a.width*3/1000];
    float speed;
    int direction;
    public LaneTreeTrunk(float speed, int direction, float startX, float startY) {
        this.speed = speed;
        this.direction = direction;
        float x = startX;
        for (int i = 0; i < trunks.length; i++) {
            String imgPath = FroggerGame.RESSOURCE_PATH + "treeTrunk_" + (int)(Math.random() * 3) + ".png";
            trunks[i] = new TreeTrunk(imgPath, x+=1000*direction, startY);
            trunks[i].speed = speed * direction;
        }

    }
}


class TreeTrunk extends PObject {
    TreeTrunk(String imgPath, float x, float y) {
        super(new PosRot(x, y));
        PImage img = GameManager.a.loadImage(imgPath);
        setpDisplay(new PDisplayImage(img));
        PCollider = new PRectanglePCollider(posRot, img.width, img.height);
        tag = FroggerGame.LIFE_ZONE;
    }

    float speed = 4f ;
    @Override
    public void display() {
        super.display();
        if (posRot.x  > GameManager.a.width+2000)
            posRot.x = -1999;
        if (posRot.x  < -2000)
            posRot.x = GameManager.a.width+1999;
        posRot.x+=speed;
    }
}
class Car extends PObject {
    Car(String imgPath, float x, float y) {
        super(new PosRot(x, y));
        PImage img = GameManager.a.loadImage(imgPath);
        setpDisplay(new PDisplayImage(img));
        PCollider = new PRectanglePCollider(posRot, img.width, img.height);
        tag = FroggerGame.DEAD_ZONE;
    }
    float speed = 3f;

    @Override
    public void display() {
        super.display();
        if (posRot.x  > GameManager.a.width+2000)
            posRot.x = -1999;
        if (posRot.x  < -2000)
            posRot.x = GameManager.a.width+1999;
        posRot.x+=speed;
    }
}
