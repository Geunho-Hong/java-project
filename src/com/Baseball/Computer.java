package com.Baseball;

import java.util.ArrayList;

public class Computer {

    // strike, ball 체크후에 출력
    public boolean compareNumber(int[] ballCnt) {

        boolean checkAnswer = false;

        if (ballCnt[0] == 0 && ballCnt[1] == 0) {
            System.out.println("Nothing");
        }

        if (ballCnt[1] == 0 && ballCnt[0] != 0) {
            System.out.println(ballCnt[0] + "스트라이크");
            if (ballCnt[0] == 3) {
                System.out.println("3개의 숫자를 모두 맞추셨습니다 ! 게임 종료");
                checkAnswer = true;
            }
        }
        if (ballCnt[0] == 0 && ballCnt[1] != 0) {
            System.out.println(ballCnt[1] + "볼");
        }
        if (ballCnt[0] != 0 && ballCnt[1] != 0) {
            System.out.println(ballCnt[0] + "스트라이크 " + ballCnt[1] + " 볼");
        }
        return checkAnswer;
    }

    /**
     * Check Number
     *
     * @Param ballCnt[0] = Strike, ballCnt[1] = ball
     */
    public int[] isCheckNumber(int[] ballCnt, int[] answer, int[] input) {

        for (int i = 0; i < 3; i++) {
            if (isStrike(answer[i], input[i])) {
                ballCnt[0]++;
            }
            if (isBall(answer, input, i)) {
                ballCnt[1]++;
            }
        }
        return ballCnt;
    }

    //Strike 판별여부
    public boolean isStrike(int num1, int num2) {
        return num1 == num2;
    }

    //ball 판별여부
    public boolean isBall(int[] answer, int[] input, int value) {

        boolean checkBall = false;
        for (int i = 0; i < 3; i++) {

            //strike 일 경우
            if (answer[i] == input[i]) {
                continue;
            }

            if (answer[i] == input[value]) {
                checkBall = true;
                break;
            }
        }
        return checkBall;
    }

    //랜덤 값을 만들어낸다
    public void createNum(int[] answerNum) {
        int tmp = 0, cnt = 0;
        ArrayList<Integer> checkList = new ArrayList<>();

        while (cnt <= 2) {
            tmp  = (int)(Math.random()*9)+1; //1부터 9까지 수 생성

            if(!checkList.contains(tmp)){
                answerNum[cnt] = tmp;
                checkList.add(tmp);
                cnt++;
            }
        }
    }


}
