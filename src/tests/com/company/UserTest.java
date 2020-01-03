package com.company;

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
        assertEquals(flag,true);
    }




}