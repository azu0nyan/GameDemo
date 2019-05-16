package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Main implements MouseListener, KeyListener {
    static final int screenX = 1024;
    static final int screenY = 768;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(screenX, screenY);
        jf.setUndecorated(false);
        jf.setVisible(true);
        jf.createBufferStrategy(2);
        Main m = new Main();
        jf.addMouseListener(m);
        jf.addKeyListener(m);

        long lastFrame = System.currentTimeMillis();
        while (true) {
            BufferStrategy bs = jf.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, screenX, screenY);

            long now = System.currentTimeMillis();
            double dt = (now - lastFrame) / 1000.0;
            draw(g);
            update(dt);
            lastFrame = now;

            bs.show();
            g.dispose();
        }
    }

    //ВСЕ ДАННЫЕ ИГРЫ ТУТ
    static ArrayList<Updatable> updatables = new ArrayList<>();
    static ArrayList<Collidable> bounds = new ArrayList<>();
    static ArrayList<Drawable> drawables = new ArrayList<>();
    static Player player = new Player(new Circle(new V2(100, 100), 20, Color.YELLOW));
    static {
        ArrayList<Circle> circles = new ArrayList<>();
        circles.add(new Circle(new V2(300, 300), 40, Color.BLACK));
        circles.add(new Circle(new V2(500, 600), 100, Color.BLACK));
        ArrayList<Rect> rects = new ArrayList<>();
        rects.add(new Rect(new V2(0, 0), new V2(50, screenY), Color.GRAY));
        rects.add(new Rect(new V2(0, 0), new V2(screenX, 50), Color.GRAY));
        rects.add(new Rect(new V2(0, screenY - 50), new V2(screenX, 50), Color.GRAY));
        rects.add(new Rect(new V2(screenX - 50, 0), new V2(50, screenY), Color.GRAY));
        bounds.addAll(rects);
        drawables.addAll(rects);
        bounds.addAll(circles);
        drawables.addAll(circles);

        new KillerSquare(new Rect(new V2(400, 200), new V2(50, 50), Color.RED));

        drawables.add(player.shape);
        drawables.add(player);
        updatables.add(player);


    }

    //ВСЕ РИСОВАНИЕ ТУТ
    static void draw(Graphics2D g) {
        for(Drawable d :drawables){
            d.draw(g);
        }
    }

    //ИЗМЕНИЕНИЕ СОСТОЯНИЯ  МИРА ТУТ
    static void update(double dt) {
        for(Updatable u: updatables){
            u.update(dt);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_W:
                player.speed = new V2(0, -50);break;
            case KeyEvent.VK_S:
                player.speed = new V2(0, 50);break;
            case KeyEvent.VK_A:
                player.speed = new V2(-50, 0);break;
            case KeyEvent.VK_D:
                player.speed = new V2(50, 0);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}