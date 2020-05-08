package com.ispong.oxygen.java.collections.map;

import java.util.List;

/**
 * 把map集成方法和 hashmap自带的方法全都用一边
 */
public class HashMapDemo {

    public static void main(String[] args) {

//        for (int i = 1; i < 10; i++) {
//            for (int j = i; j < 10; j++) {
//                System.out.print(i + "*" + j + "=" + i * j+"  ");
//            }
//            System.out.println("");
//        }

        for (int i = 1; i < 10; i++) {
//            System.out.println(i);
            for(int j = i; j < 10; j++){
                System.out.print(i + "*" + j + "=" + i * j+"  ");
            }
            System.out.println();
        }
    }
}
