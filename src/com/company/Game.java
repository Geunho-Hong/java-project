package com.company;

import java.util.Scanner;

public class Game {

    public void startGame(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("숫자를 입력해주세요");
            int num = sc.nextInt();

            if(reply()){
                break;
            }
        }
    }

    public static boolean reply() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임을 새로 시작하려면 1, 종료하려면" +
                "2를 입력하세요");
        int num = sc.nextInt();

        return num == 2;
    }

}