package edu.mephi.java.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Game extends JPanel implements ActionListener {
    private final int TILE_SIZE = 20;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private boolean gameOver = false;
    private final Timer timer;
    private Boolean keyPressed;

    // координаты местоположения элементов змеи
    private final java.util.List<Point> snake;
    // направление движения
    private Point direction;
    // координаты еды
    private Point food;
    // счетчик очков

    public Game() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE + 30));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("You pressed " + e.getKeyChar() + " key!");
            }
        });

        timer = new Timer(300, this);
        timer.start();

        // инициализация змейки
        snake = new ArrayList<>();
        snake.add(new Point(10, 10));
        direction = new Point(1, 0);

        food = new Point();

        spawnFood();
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public void setKeyPressed(Boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public Boolean getKeyPressed() {
        return keyPressed;
    }

    // передвижение змейки со временем
    @Override
    public void actionPerformed(ActionEvent e) {
        keyPressed = false;

        // создаем новую голову
        Point newHead = new Point(snake.getFirst().x + direction.x, snake.getFirst().y + direction.y);
        System.out.println(newHead);

        // проверка на столкновения
        if (newHead.x < 0 || newHead.x > WIDTH || newHead.y < 0 || newHead.y > HEIGHT || snake.contains(newHead)) {
            System.out.println("GAME OVER");
            gameOver = true;
            timer.stop(); // Останавливаем таймер, если игра завершена
            repaint();
            return;
        }

        snake.addFirst(newHead); // добавляем голову

        if (newHead.equals(food)) {
            RenderingScene.updateScore();
            spawnFood();
        } else {
            snake.removeLast(); // удаляем хвост
        }
        repaint();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // создание новой еды
    private void spawnFood() {
        int x;
        int y;
        do {
            x = (int) (Math.random() * WIDTH);
            y = (int) (Math.random() * WIDTH);
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }

    // - перезапуск игры
    public void restart() {
        // Сброс состояния игры
        snake.clear();
        snake.add(new Point(10, 10)); // Начальная позиция змейки
        direction = new Point(1, 0); // Начальное направление
        gameOver = false;
        RenderingScene.clearScore();
        spawnFood();
        timer.start();
        repaint();
    }

    // отрисовка змеи, еды и счета
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.magenta);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE + 1, p.y * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
        }

        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String gameOverText = "Game Over!";
            int textWidth = g.getFontMetrics().stringWidth(gameOverText);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;
            g.drawString(gameOverText, x, y);
        }
    }


}
