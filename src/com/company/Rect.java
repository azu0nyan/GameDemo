package com.company;

import java.awt.*;

public class Rect implements Drawable, Collidable{

    V2 min;
    V2 size;
    Color c = Color.BLACK;

    public Rect(V2 min, V2 sizel) {
        this.min = min;
        this.size = sizel;
    }

    public Rect(V2 min, V2 size, Color c) {
        this.min = min;
        this.size = size;
        this.c = c;
    }

    boolean contains(V2 point){
        return point.x >= min.x && point.x < (min.x + size.x) &&
                point.y >= min.y && point.y <= (min.y + size.y);
    }

    public boolean collides(Circle c){
        //TODO нормальная проверка
        return contains(c.pos);
    }

    public void draw(Graphics2D g){
        g.setColor(c);
        g.fillRect(min.getXInt(), min.getYInt(), size.getXInt(), size.getYInt());
    }
}
