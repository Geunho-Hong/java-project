package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    Computer computer = new Computer();

    //메소드 실행전에 반드시 실행된다
    @Before
    public void setup(){
        computer = new Computer();
    }

    @Test
    public void isStrike(){
        boolean flag = computer.isStrike(3,3);
        assertEquals(flag,true);
    }

    @Test
    public void createNum() {
        int [] arr = new int[3];
        computer.createNum(arr);
    }


}