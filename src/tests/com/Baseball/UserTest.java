package com.Baseball;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    User user = new User();

    @Before
    public void setup(){
        user = new User();
    }

    @Test
    public void input(){
        int[] arr = new int[3];
        arr = user.input();
        for(int num:arr){
            System.out.print(num +" ");
        }
    }

    @Test
    public void saveDigit(){
        int arr [] = user.saveDigit(324);
        int arr2[] = user.saveDigit(324);
        assertArrayEquals(arr,arr2);
    }

    @Test
    public void isNumberCheck(){
        boolean flag = true;
        flag = user.isNumberCheck(123);
        assertTrue(flag);
    }

    @Test
    public void isNumberDifferent(){
        boolean flag = true;
        flag = user.isNumberDifferent(463);
        assertTrue(flag);
    }

    @Test
    public void isValidateNumber(){
        boolean flag = true;
        flag = user.isValidateNumber(324);
        assertTrue(flag);
    }

    @Test
    public void isNotZero(){
        boolean flag = true;
        flag = user.isNotZero(324);
        assertTrue(flag);
    }






}