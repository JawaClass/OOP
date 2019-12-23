package PEngine;

public class PosRot {
    public float x;
    public float y;
    float rot;
    public PosRot(float x, float y) { this(x, y, 0f); }
    public PosRot(float x, float y, float rot) { this.x = x; this.y = y; this.rot = rot; }
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof PosRot)) return false;
        PosRot position = (PosRot) other;
        return this.x == position.x && this.y == position.y; }
    public float dist(PosRot other) { return (float) Math.sqrt((x-other.x)*(x-other.x)+(y-other.y)*(y-other.y)); }
    public String toString() { return x + " . " + y + " |" + rot; }
    public PosRot copy() { return new PosRot(x, y, rot);}
}
