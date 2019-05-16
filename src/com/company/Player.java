package com.company;

import java.awt.*;

public class Player implements Updatable, Drawable{
    double hp = 100;
    Circle shape ;
    V2 speed = new V2(50, 80);

    boolean lose = false;

    public Player(Circle shape) {
        this.shape = shape;
    }

    @Override
    public void update(double dt) {
        if(!lose && hp < 0){
            lose = true;
            Main.drawables.add(g -> {
                g.setColor(Color.RED);
                g.drawString("LOSE", 700, 200);
            });
        }
        //V2 newPos = shape.pos.add(speed.scale(dt));
        Circle newCircle = new Circle(shape.pos.add(speed.scale(dt)), shape.r);
        boolean collide = false;
        for(Collidable c : Main.bounds){
            if(c.collides(newCircle)){
                collide = true;
                break;
            }
        }
        if(!collide){
            shape.pos = newCircle.pos;
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawString((int)hp +"", shape.pos.getXInt(), shape.pos.getYInt());
    }
}
