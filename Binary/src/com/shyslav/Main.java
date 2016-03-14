package com.shyslav;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static int max = 0;
    public static Map<Integer, Integer> answersMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        //Максимальное допустимое число для обработки
        int N = Integer.parseInt(args[1]);
        Scanner scan = new Scanner(Paths.get(args[0]));
        //Лист который содержит подходящие елементы
        ArrayList<Integer> elements = new ArrayList<>();
        while (scan.hasNext()) {
            int tmp = scan.nextInt();
            if (tmp < N) {
                elements.add(tmp);
            }
        }
        for (int e : elements) {
            toBinary(e);
        }
        OutputMap(answersMap);
    }

    /**
     * Функция которая переводит число в двоичную систему, подсчитывает количество единиц
     *
     * @param number - входящеее десятичное число
     */
    private static void toBinary(int number) {
        //переменная хранит текущее значение числа
        int original = number;
        //переменная хранящая количество единиц в числе
        int counter = 0;
        while (number != 0) {
            if (number % 2 != 0) {
                //System.out.print(" "+ 1 + " ");
                counter++;
            } else {
                // System.out.print(" " + 0 + " ");
            }
            //округлить в меньшую сторону
            number = (int) Math.floor(number / 2);
        }
        //если найден новое число с большим количеством единиц
        if (counter > max) {
            answersMap.clear();
            answersMap.put(original, counter);
            max = counter;
        } else if (counter == max) {
            answersMap.put(original, counter);
        }
    }

    /**
     * Функция для вывода карты
     *
     * @param map входящая карта
     */
    public static void OutputMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + "->" + e.getValue() + "->" + Integer.toBinaryString(e.getKey()));
        }
    }

}
