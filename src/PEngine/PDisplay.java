package PEngine;

public abstract class PDisplay<E> implements Drawable{
    PosRot posRot;
    public boolean isVisible = true;
    public E element;

    public PDisplay() {
        this(new PosRot(50f,50f));
    }

    public PDisplay(PosRot posRot) {
        this.posRot = posRot;
    }

}
