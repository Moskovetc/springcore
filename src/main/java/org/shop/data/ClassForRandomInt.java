package org.shop.data;

import org.shop.annotation.InjectRandomInt;

public class ClassForRandomInt {

    private static final int MIN = 2;
    private static final int MAX = 20;

    @InjectRandomInt(min = MIN, max = MAX)
    private int randomInt;

    @Override
    public String toString() {
        return "ClassForRandomInt{" +
                "randomInt=" + randomInt +
                '}';
    }

    public int getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(int randomInt) {
        this.randomInt = randomInt;
    }
}
