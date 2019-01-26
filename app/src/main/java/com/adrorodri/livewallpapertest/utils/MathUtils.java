package com.adrorodri.livewallpapertest.utils;

class MathUtils {
    static int getRandomInt(int max, int min) throws NumberFormatException {
        return (int) getRandomFloat(max, min);
    }

    static float getRandomFloat(float max, float min) throws NumberFormatException {
        if (min > max) {
            throw new NumberFormatException("Min value can not be greater than max value");
        }
        return (float) (Math.random() * (max - min) + min);
    }
}
