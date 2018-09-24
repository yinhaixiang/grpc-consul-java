package com.sean.grpc;

import java.util.Random;

public class TT {
    public static void main(String[] args) {
        int a = 5;
        Random rand = new Random();
        while (true) {
            System.out.println(rand.nextInt(a));
        }
    }
}
