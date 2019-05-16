package com.company;

import java.awt.*;

public class KillerSquare implements Updatable, Drawable {

    Rect shape;
    boolean active = false;
    double dhp = 50;

    public KillerSquare(Rect shape) {
        Main.updatables.add(this);
        Main.drawables.add(this);
        this.shape = shape;
    }

    @Override
    public void update(double dt) {
        active = System.currentTimeMillis() % 2000 < 1000;
        if (active) {
            if (shape.collides(Main.player.shape)) {
                Main.player.hp -= dhp * dt;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (active) {
            shape.draw(g);
        }
    }
}
