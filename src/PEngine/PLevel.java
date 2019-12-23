package PEngine;

public abstract class PLevel {

    private final int levelId = (int) (Math.random() * Integer.MAX_VALUE);

    public PLevel() { GameManager.pLevels.push(this); }

}
