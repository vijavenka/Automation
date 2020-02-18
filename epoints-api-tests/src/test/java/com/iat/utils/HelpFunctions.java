package com.iat.utils;

import java.util.Random;

public class HelpFunctions {

    public int returnRandomValue(int range) {
        return new Random().nextInt(range);
    }
}