package src;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {
    public GameOverScreen(int score, int maxCombo, String gameTitle) {
        setTitle("Game Over");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 제목 표시
        JLabel titleLabel = new JLabel("Game Over: " + gameTitle, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

        /// 점수 및 콤보 표시
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(20, 10));

        JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // 글자 크기 24로 설정

        JLabel comboLabel = new JLabel("Max Combo: " + maxCombo, SwingConstants.CENTER);
        comboLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // 글자 크기 24로 설정

        statsPanel.add(scoreLabel);
        statsPanel.add(comboLabel);

        add(statsPanel, BorderLayout.CENTER);


        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton homeButton = new JButton("Back to Home");
        homeButton.addActionListener(e -> {
            dispose(); // 종료 화면 닫기
            SwingUtilities.invokeLater(GameWindow::new); // GameWindow로 이동
        });
        buttonPanel.add(homeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}