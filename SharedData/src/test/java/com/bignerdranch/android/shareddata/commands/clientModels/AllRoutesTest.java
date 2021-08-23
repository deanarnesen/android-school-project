package com.bignerdranch.android.shareddata.commands.clientModels;

import org.junit.Test;

import static org.junit.Assert.*;

public class AllRoutesTest {
    @Test
    public void initTest(){
        AllRoutes routes = new AllRoutes();
        assertEquals(100, routes.getSize());
    }
}