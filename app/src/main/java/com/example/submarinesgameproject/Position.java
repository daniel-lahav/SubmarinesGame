package com.example.submarinesgameproject;

public class Position
{
    private int x,y; // קורדינטות המיקום
    private boolean vertical;// האם הכיוון הוא אנכי?

    public Position (int x, int y, boolean vertical)
    {
        this.x=x;
        this.y=y;
        this.vertical=vertical;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
