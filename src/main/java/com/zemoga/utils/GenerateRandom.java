package com.zemoga.utils;

import java.util.Random;

public class GenerateRandom {

    private GenerateRandom() { throw new IllegalStateException("Utility class"); }

    public static int generateRandom() {
        int upperbound = 10000;
        Random rand = new Random();
        return rand.nextInt(upperbound);
    }
}
