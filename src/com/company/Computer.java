package com.company;

import java.util.Random;

public class Computer {

    //Strike 판별여부
    public static boolean isStrike(int num1 ,int num2){
        return num1 == num2;
    }

    //랜덤 값을 만들어낸다
    public void createNum(int[] answerNum){
        int tmp = 0;
        answerNum = new int[3];
        for(int i=0; i<=2; i++){
            tmp = (int)(Math.random()*9)+1;
            answerNum[i] = tmp;
        }
    }


    



}
