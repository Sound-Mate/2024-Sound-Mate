package src;

import src.db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    public static void main(String[] args) {
        new Login();
    }

    public Login() {
        // 프레임 설정
        JFrame f = new JFrame("Sound Mate - Login");
        f.setSize(1280, 720);
        f.getContentPane().setBackground(Color.white);
        f.setLocationRelativeTo(null);       // 창을 화면 가운데에 위치
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(null);  // 레이아웃을 null로 설정
        panel.setBackground(Color.white);

        // 컴포넌트 생성 및 위치 설정
        JLabel l1 = new JLabel("아이디 : ");    // 아이디 라벨
        l1.setBounds(520, 230, 80, 30);
        JTextField id = new JTextField();       // 아이디 입력창
        id.setBounds(600, 230, 150, 30);

        JLabel l2 = new JLabel("비밀번호 : ");   // 비밀번호 라벨
        l2.setBounds(520, 270, 80, 30);
        JPasswordField pw = new JPasswordField(); // 비밀번호 입력창
        pw.setBounds(600, 270, 150, 30);

        JButton btn = new JButton("로그인");     // 로그인 버튼
        btn.setBounds(600, 320, 150, 30);

        // 버튼 클릭 이벤트
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = id.getText();          // 입력된 아이디
                String password = String.valueOf(pw.getPassword()); // 입력된 비밀번호

                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(f, "로그인 성공!");
                } else {
                    JOptionPane.showMessageDialog(f, "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
                }
            }
        });

        // 패널에 컴포넌트 추가
        panel.add(l1);  panel.add(id);
        panel.add(l2);  panel.add(pw);
        panel.add(btn);

        // 프레임에 패널 추가
        f.add(panel);
        f.setVisible(true);
    }

    // 사용자 인증 메서드
    private boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            isAuthenticated = rs.next(); // 결과가 있으면 로그인 성공
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }
}
