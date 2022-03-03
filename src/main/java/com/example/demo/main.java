package com.example.demo;

import java.util.*;

public class main {
    public static void main(String[] args) {

//        System.out.println(numWaterBottles(numBottles, numExchange));
        System.out.println(": "+minSteps("leetcode",  "practice"));
    }

    public static int numWaterBottles(int numBottles, int numExchange) {

        if(numBottles == numExchange) {
            return numBottles + 1;
        }
        if ((numBottles % numExchange) != 0) {
            numExchange -= 1;
        }
        int returnResult = numBottles + numExchange + 1;
        return returnResult;
    }

    public static int minSteps(String s, String t) {
//        List<String> tList = new ArrayList<>();
//        List<String> sList = new ArrayList<>();
//        List<String> dupString = new ArrayList<>();
//
//        int counter = 0 ;
//        for (int i = 0; i < s.length(); i++) {
//            sList.add(String.valueOf(s.charAt(i)));
//        }
//        for (int i = 0; i < t.length(); i++) {
//
//            if (!sList.contains(String.valueOf(t.charAt(i)))) {
//                counter ++;
//            }
//            if (sList.contains(String.valueOf(t.charAt(i))) && !dupString.contains(String.valueOf(t.charAt(i))) ) {
//                counter ++;
//            }
//            dupString.add(String.valueOf(t.charAt(i)));
//        }
//
//
//        return counter;

        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(char ch : t.toCharArray()) {
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0)
                    map.remove(ch);
            }
        }
        int ans = 0;
        for(char ch : map.keySet())
            ans += map.get(ch);

        return ans;


    }
}
