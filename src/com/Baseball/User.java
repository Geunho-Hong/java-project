package com.Baseball;

import java.util.Scanner;

public class User {

    private Computer computer = new Computer();

    //게임 플레이어 3자리수 입력받기
    public int[] input(){
        Scanner sc  = new Scanner(System.in);
        int num = 0;
        int[] userNum = new int[3];

        while(true){
            System.out.print("숫자를 입력해주세요:");
            num = sc.nextInt();

            if(isValidateNumber(num)){
                break;
            }
            System.out.println("서로 다른 세자리 수를 입력하세요");
        }
        userNum = saveDigit(num);
        return userNum;
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

    //서로 다른 숫자를 입력해야 True를 Return 한다
    public boolean isNumberDifferent(int num){

        boolean checkNumber = false;
        int[] numArr = saveDigit(num);
        int num1 = numArr[0];
        int num2 = numArr[1];
        int num3 = numArr[2];
        if(num1!=num2 && num1!=num3 && num2!=num3){
            checkNumber = true;
        }
        return checkNumber;
    }

    // Zero 가 입력되면 안된다
    public boolean isNotZero(int num){

        boolean checkZero = false;
        int [] numArr = saveDigit(num);
        int num1 = numArr[0];
        int num2 = numArr[1];
        int num3 = numArr[2];

        if(num1 !=0 && num2 !=0 && num3!=0) {
            checkZero = true;
        }
        return checkZero;
    }

    //숫자 확인
    public boolean isValidateNumber(int num){
        return isNumberCheck(num) && isNumberDifferent(num) && isNotZero(num);
    }



}
