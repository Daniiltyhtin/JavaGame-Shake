package edu.mephi.java.engine;

import javax.swing.*;
import java.awt.*;

public abstract class RenderingScene {
    private static JLabel scoreLabel;
    private static int score = 0;

    public static void renderScene(JFrame frame) {

        // Панель для счета (полоса внизу)
        JLabel scoreBar = new JLabel();
        scoreBar.setBackground(Color.GRAY); // Цвет фона
        scoreBar.setPreferredSize(new Dimension(400, 40)); // Высота полосы
        scoreBar.setLayout(new FlowLayout(FlowLayout.CENTER)); // Выравнивание по центру
        scoreBar.setOpaque(true);

        // Текст счета
        scoreLabel = new JLabel("Счет: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Шрифт и размер
        scoreLabel.setForeground(Color.WHITE); // Цвет текста


        // Добавляем текст на панель
        scoreBar.add(scoreLabel);
        frame.add(scoreBar, BorderLayout.PAGE_START);
    }

    public static void clearScore() {
        score = 0;
    }

    public static void updateScore() {
        score += 1;
        scoreLabel.setText("Счет: " + score);
    }
}
