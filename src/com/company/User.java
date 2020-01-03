package com.company;

import java.util.Scanner;

public class User {

    //게임 플레이어 3자리수 입력받기
    public int[] input(){

        Scanner sc  = new Scanner(System.in);
        int [] userNum  = new int[3];
        while(true){
            System.out.print("숫자를 입력해주세요:");
            int num = sc.nextInt();
            if(isNumberCheck(num)){
                System.out.print("자릿수가 틀렸습니다.");
                continue;
            }
            userNum = saveDigit(num);
        }
    }

    //자릿수 저장하기
    public int[] saveDigit(int num){
        int [] userNum = new int[3];

        int num1 = num/100;  //백의 자리
        int num2 = num/10%10;  //십의 자리
        int num3 = num%10;   //일의 자리

        userNum[0] = num1;
        userNum[1] = num2;
        userNum[2] = num3;

        return userNum;
    }

    //자릿수 판별 하기
    public boolean isNumberCheck(int num){
        int cnt = 0;
        boolean flag = true;

        while(num>0){
            num/=10;
            cnt++;
        }

        if(cnt!=3){
            flag = false;
        }
        return flag;
    }










}
