package edu.mephi.java;

import edu.mephi.java.engine.Game;
import edu.mephi.java.engine.RenderingScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        RenderingScene.renderScene(frame);
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_R) {
                    game.restart();
                }
                if (!game.getKeyPressed()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (game.getDirection().y == 0) game.setDirection(new Point(0, -1));
                            game.setKeyPressed(true);
                            break;
                        case KeyEvent.VK_DOWN:
                            if (game.getDirection().y == 0) game.setDirection(new Point(0, 1));
                            game.setKeyPressed(true);
                            break;
                        case KeyEvent.VK_LEFT:
                            if (game.getDirection().x == 0) game.setDirection(new Point(-1, 0));
                            game.setKeyPressed(true);
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (game.getDirection().x == 0) game.setDirection(new Point(1, 0));
                            game.setKeyPressed(true);
                            break;

                    }
                }
            }
        });

    }

}