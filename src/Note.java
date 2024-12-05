package src;

import javax.swing.*;
import java.awt.*;

public class Note extends JPanel {
    private int xPosition; // 노트의 x 좌표
    private int yPosition; // 노트의 y 좌표
    private boolean active; // 노트가 활성 상태인지 여부
    private int time; // 노트의 타이밍
    private char key; // 노트와 연결된 키
    private String instrumentType; // 악기 타입 (예: "piano", "guitar", "drum")
    private boolean lineReached; // 라인에 도달했는지 여부 추가
    private long reachTime; // 라인에 도달한 시간 (밀리초)

    public Note(int xPosition, int time, char key, String instrumentType) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.time = time;
        this.active = true;
        this.key = key;
        this.instrumentType = instrumentType;
        this.lineReached = false; // 기본값은 false
        this.reachTime = 0; // 기본값

        // 악기별 크기 설정
        int width, height;
        switch (instrumentType.toLowerCase()) {
            case "piano":
                width = 128;
                height = 50;
                break;
            case "guitar":
                width = 115;
                height = 40;
                break;
            case "drum":
                width = 157;
                height = 80;
                break;
            default:
                width = 130; // 기본 크기
                height = 50;
                break;
        }

        setBounds(xPosition, yPosition, width, height); // 크기와 초기 위치 설정
    }

    public void moveDown(int speed) {
        yPosition += speed;
        setLocation(xPosition, yPosition);

        // 라인에 도달한지 확인 (라인 Y 좌표는 650으로 설정)
        if (yPosition >= 650 && yPosition <= 670) { // 라인 범위 내
            lineReached = true; // 라인 도달 상태 설정
        } else {
            lineReached = false; // 라인 범위 밖에서는 비활성화
        }

        if (yPosition > 720) { // 화면 아래로 벗어나면 비활성화
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public char getKey() {
        return key;
    }

    public int getTime() {
        return time;
    }

    public boolean isLineReached() {
        return lineReached;
    }

    public long getReachTime() {
        return reachTime;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; // Graphics를 Graphics2D로 변환

        // 배경 색상 설정
        Color backgroundColor = Color.decode("#4AC3FF"); // 원하는 배경 색상

        g2d.setColor(backgroundColor); // 색상 적용
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0); // 둥근 모서리를 위한 라디우스 적용 (30은 라디우스 값)
    }
}