package com.Baseball;

public class Game {

    public void startGame(){

        User user = new User();
        Computer computer = new Computer();

        boolean checkAnswer = false;
        int[] answer = new int[3]; // Pc 가 생성한 RandomNumber
        int[] input = new int[3]; // 사용자가 입력한 Input 값
        int[] ballCnt = new int[2]; // ballCnt 값

        computer.createNum(answer);  //정답 생성

        do {
            input = user.input();
            ballCnt[0] = 0;  // Strike 초기화
            ballCnt[1] = 0;  // Ball 초기화

            ballCnt = computer.isCheckNumber(ballCnt, answer, input);
            checkAnswer = computer.compareNumber(ballCnt);

        }while(checkAnswer== false);

    }

}
