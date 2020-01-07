package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    private Computer computer = new Computer();

    //메소드 실행전에 반드시 실행된다
    @Before
    public void setup(){
        computer = new Computer();
    }

    @Test
    public void isStrike(){
        boolean flag = computer.isStrike(3,3);
        assertTrue(flag);
    }

    @Test
    public void createNum() {
        int [] arr = new int[3];
        computer.createNum(arr);
        assertEquals(arr,arr);
    }

    @Test
    public void isBall(){
        int [] answer = {5,1,4};
        int [] input = {2,3,5};
        boolean flag = computer.isBall(answer,input,2);
        assertTrue(flag);
    }

    @Test
    public void isCheckNumber(){
        //2 ball 1 Strike
        int answer[] = {3,4,5};
        int input[] = {4,3,5};
        int ballCnt[] = new int[2];
        int checkArr [] = {1,2};

        ballCnt = computer.isCheckNumber(ballCnt,answer,input);
        assertArrayEquals(ballCnt,checkArr);
    }

    @Test
    public void compareNumber(){
        int[] ballCnt = {3,0};
        assertTrue(computer.compareNumber(ballCnt));
    }



}