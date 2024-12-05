package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class playPiano extends JFrame
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new playPiano().setVisible(true));
    }

    public playPiano() {
        setTitle("Sound Mate - Play Piano");         // super("Sound Mate"); 같은 기능
        this.setSize(1280,720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // 컨트롤러 패널
        src.menuBar menuBar = new menuBar();
        this.add(menuBar, BorderLayout.NORTH);

        // 악보 패널
        SheetMusicPanel sheetMusicPanel = new SheetMusicPanel();
        sheetMusicPanel.setPreferredSize(new Dimension(1190, 100));
        sheetMusicPanel.setBackground(Color.WHITE);
        this.add(sheetMusicPanel, BorderLayout.EAST);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                sheetMusicPanel.addNoteToSheet(keyChar);    // 악보 패널에 음표 추가
                menuBar.recordKey(keyChar);                 // 녹음된 키 추가
            }
        });

        // 피아노 키보드 패널
        Piano piano = new Piano(sheetMusicPanel);
        this.add(piano, BorderLayout.SOUTH);

        // 키보드 포커스를 Piano에 설정
        piano.setFocusable(true);
        piano.requestFocusInWindow();

    }
}
