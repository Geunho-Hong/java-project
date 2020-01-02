package com.company;

import java.util.Scanner;

public class User {

    Scanner sc  = new Scanner(System.in);

    //게임 플레이어 3자리수 입력받기
    public int[] input(){
        int num = 0;
        int[] userNum = new int[3];
        while (true){
            System.out.print("숫자를 입력해 주세요:");
            num = sc.nextInt();
        }
    }


}
