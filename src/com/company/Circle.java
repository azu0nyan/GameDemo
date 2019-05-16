package com.company;

import java.awt.*;

public class Circle implements Drawable,Collidable{
    V2 pos;
    double r;
    Color c;

    public Circle(V2 pos, double r) {
        this.pos = pos;
        this.r = r;
    }

    public Circle(V2 pos, double r, Color c) {
        this.pos = pos;
        this.r = r;
        this.c = c;
    }

    boolean contains(V2 point) {
        return pos.distance(point) <= r;
    }

    public void draw(Graphics2D g) {
        g.setColor(c);
        g.fillOval((int) (pos.getXInt() - r), (int) (pos.getYInt() -  r), (int) (2 * r), (int) (2 * r));
    }

    @Override
    public boolean collides(Circle c) {
        return pos.distance(c.pos) <= c.r + r;
    }
}
