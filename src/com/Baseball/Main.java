package com.Baseball;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while(true){
            Game game = new Game();
            game.startGame();

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
