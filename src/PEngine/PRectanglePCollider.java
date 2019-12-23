package PEngine;

import static processing.core.PConstants.PI;

public class PRectanglePCollider extends PCollider {
    float width;
    float height;

    public PRectanglePCollider(PosRot posRot, float width, float height) {
        super(posRot); this.width = width; this.height = height;
    PLog.i("posRot " + posRot);}

    void debugCollider() {
        PosRot[] points = getPoints();
        for (PosRot posRot : points)

        for (PosRot p : points) GameManager.g.ellipse(p.x, p.y, 10f, 10f);
        for (int i = 0; i < points.length; i++) {
            GameManager.g.line(points[i].x,points[i].y,points[(i+1)%(points.length)].x,points[(i+1)%(points.length)].y);
        }
    }


    @Override
    PosRot[] getPoints() {
        // returns array of 4 points of rotated rectangle in following order
        // UpperLeft, UpperRight, LowerRight, LowerLeft
        PosRot[] sortedPoints = new PosRot[4];
        PosRot[] unsortedPoints;

        if (posRot.rot % PI <= PI*.5f)
            unsortedPoints = new PosRot[]{new PosRot(posRot.x - width * (float) Math.sin(posRot.rot), posRot.y),
                    new PosRot(posRot.x + width * (float) Math.cos(posRot.rot), posRot.y),
                    new PosRot(posRot.x + width * (float) Math.cos(posRot.rot), posRot.y + height * (float) Math.sin(posRot.rot) + height * (float) Math.cos(posRot.rot)),
                    new PosRot(posRot.x - width * (float) Math.sin(posRot.rot), posRot.y + height * (float) Math.sin(posRot.rot) + height * (float) Math.cos(posRot.rot)),
            };
        else
            unsortedPoints = new PosRot[] {
                    new PosRot(posRot.x-width*(float) Math.sin(posRot.rot)+width*(float) Math.cos(posRot.rot), posRot.y+height*(float) Math.cos(posRot.rot)),
                    new PosRot(posRot.x, posRot.y+height*(float) Math.cos(posRot.rot)),
                    new PosRot(posRot.x, posRot.y+height*(float) Math.sin(posRot.rot)),
                    new PosRot(posRot.x-width*(float) Math.sin(posRot.rot)+width*(float) Math.cos(posRot.rot), posRot.y+height*(float) Math.sin(posRot.rot)),

            };

        for(PosRot p : unsortedPoints) {
                if (sortedPoints[0] == null || p.x <= sortedPoints[0].x && p.y <= sortedPoints[0].y)
                    sortedPoints[0] = p;
                if (sortedPoints[1] == null || p.x >= sortedPoints[1].x && p.y <= sortedPoints[1].y)
                    sortedPoints[1] = p;
                if (sortedPoints[2] == null || p.x >= sortedPoints[2].x && p.y >= sortedPoints[2].y)
                    sortedPoints[2] = p;
                if (sortedPoints[3] == null || p.x <= sortedPoints[3].x && p.y >= sortedPoints[3].y)
                    sortedPoints[3] = p;
            }

        return sortedPoints;
    }

}
