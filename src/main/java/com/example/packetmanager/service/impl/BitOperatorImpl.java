package com.example.packetmanager.service.impl;

import com.example.packetmanager.service.BitOperator;
import org.springframework.stereotype.Component;

/**
 * Этот класс используется в тех случаях, когда необходимо получить несколько
 * байт для 1-ой переменной.
 * Также здесь находится метод перевода HEX строки в DEC int[].
 */
@Component
public class BitOperatorImpl implements BitOperator {

    @Override
    public int convertBait(int[] values, int start, int length) {
        int result = switch (length) {
            case 2 -> convertTwoBait(values, start, 0);
            case 3 -> convertThreeBait(values, start, 0);
            case 4 -> convertFourBait(values, start);
            default -> 0;
        };

        return result;
    }

    @Override
    public int[] hexStringToIntArray(String hexString) {
        int len = hexString.length();
        int[] data = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = Integer.parseInt(hexString.substring(i, i + 2), 16);
        }
        return data;
    }

    private int convertTwoBait(int[] values, int start , int offense) {
        int valFirst = values[start];
        int valSecond = values[start + 1];
        return ((valSecond & 0xFF) << 8 + offense) | ((valFirst & 0xFF) << offense);
    }

    private int convertThreeBait(int[] values, int start, int offense) {
        int valThird = values[start];
        int convertTwo = convertTwoBait(values, start + 1, offense + 8);
        return (convertTwo | ((valThird & 0xFF) << offense));
    }

    private int convertFourBait(int[] values, int start) {
        int valFourth = values[start];
        int convertThree = convertThreeBait(values, start + 1, 8);
        return (convertThree | (valFourth & 0xFF));
    }
}
