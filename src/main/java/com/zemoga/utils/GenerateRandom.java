package com.zemoga.utils;

import java.util.Random;

public class GenerateRandom {

    public static int generateRandom() {
        int upperbound = 10000;
        Random rand = new Random();
        int intRandom = rand.nextInt(upperbound);
        return intRandom;
    }
}
