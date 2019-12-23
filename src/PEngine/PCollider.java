package PEngine;

public abstract class PCollider {
    final PosRot posRot;

    PCollider(PosRot posRot) { this.posRot = posRot; }
    void debugCollider() {}
    abstract PosRot[] getPoints();

    private boolean line2line(PLinePCollider other) {
        PLinePCollider me = (PLinePCollider)this;
        return this.posRot.equals(other.posRot);
    }

    private boolean point2point(PointPCollider other) {
        return this.posRot.equals(other.posRot);
    }

    private boolean point2Circle(PCirclePCollider other) {
        return this.posRot.dist(other.posRot) <= other.r;
    }

    private boolean point2Rectangle(PRectanglePCollider other) {
        return this.posRot.x >= other.posRot.x &&
                this.posRot.x <= other.posRot.x+other.width &&
                this.posRot.y >= other.posRot.y &&
                this.posRot.y <= other.posRot.y+other.height;
    }

    private boolean circle2Circle(PCirclePCollider other) {
        PCirclePCollider me = (PCirclePCollider)this;
        return this.posRot.dist(other.posRot) <= other.r+me.r;
    }

    private boolean point2Line(PLinePCollider other) {
        float buffer = 50f;
        float d1 = posRot.dist(other.posRot);
        float d2 = posRot.dist(other.p2);
        return d1+d2 >= other.length-buffer && d1+d2 <= other.length+buffer;
    }

    private boolean circle2Rectangle(PRectanglePCollider other) {
        PCirclePCollider me = (PCirclePCollider)this;
        return me.posRot.x+me.r >= other.posRot.x &&
                me.posRot.x-me.r <= other.posRot.x+other.width &&
                me.posRot.y+me.r >= other.posRot.y &&
                me.posRot.y-me.r <= other.posRot.y+other.height;
    }

    private boolean rectangle2Rectangle(PRectanglePCollider other) {
        if (posRot.rot == 0f && other.posRot.rot == 0f) {
            PRectanglePCollider me = (PRectanglePCollider)this;
            return me.posRot.x+me.width >= other.posRot.x &&
                    me.posRot.x <= other.posRot.x+other.width &&
                    me.posRot.y+me.height >= other.posRot.y &&
                    me.posRot.y <= other.posRot.y+other.height;
        } else {
            PosRot[] me = getPoints();
            PosRot[] o = other.getPoints();
            boolean hasPotetial4Collission = me[1].x >= o[0].x &&
                    me[0].x <= o[1].x &&
                    me[2].y >= o[1].y &&
                    me[1].y <= o[2].y;
            // Seperating Axis Theorem Implementation

            //
            return hasPotetial4Collission;
        }
    }

    private boolean circle2RectangleVer2(PRectanglePCollider other) {
        //Arrays.stream(this.points).map(x -> x + " ").forEach(System.out::print);
        System.out.println();

        // 300.0 . 200.0 540.0 . 200.0 300.0 . 400.0 540.0 . 400.0

        return false;
    }

    boolean collides(PCollider other) {

        if (this instanceof PointPCollider) {
//            PLog.i("collides.is.PointCollider "  + (other instanceof RectangleCollider));
            if (other instanceof PointPCollider)
                return point2point((PointPCollider) other);
            if (other instanceof PCirclePCollider)
                return point2Circle((PCirclePCollider) other);
            if (other instanceof PRectanglePCollider)
                return point2Rectangle((PRectanglePCollider) other);
            if (other instanceof PLinePCollider)
                return point2Line((PLinePCollider) other);
        }

        if (this instanceof PCirclePCollider) {
//            PLog.i("collides.is.CircleCollider");
            if (other instanceof PointPCollider)
                return other.point2Circle((PCirclePCollider)this);
            if (other instanceof PCirclePCollider)
                return circle2Circle((PCirclePCollider) other);
            if (other instanceof PRectanglePCollider)
               return circle2Rectangle((PRectanglePCollider) other);

        }

        if (this instanceof PRectanglePCollider) {
//            PLog.i("collides.is.RectangleCollider");
            if (other instanceof PointPCollider)
                return other.point2Rectangle((PRectanglePCollider)this);
            if (other instanceof PCirclePCollider)
                return other.circle2Rectangle((PRectanglePCollider)this);
            if (other instanceof PRectanglePCollider)
                return rectangle2Rectangle((PRectanglePCollider) other);
        }
        return false;
    }

}
