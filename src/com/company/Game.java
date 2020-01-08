package com.company;

import java.util.Scanner;

public class Game {

    public void startGame(){

        User user = new User();
        Computer computer = new Computer();

        boolean check = true;
        int[] answer = new int[3];
        int[] input = new int[3];
        int[] ballCnt = new int[2];

        computer.createNum(answer);

        do {
            input = user.input();
            for (int num : input) {
                System.out.print(num + " ");
            }

            System.out.println();
            for (int num : answer) {
                System.out.print(num + " ");
            }
            ballCnt = computer.isCheckNumber(ballCnt, answer, input);
            computer.compareNumber(ballCnt);

        }while(!check);

        if(input == answer){
            System.out.println("정답을 맞추셨습니다 !");
        }

    }

}
