package com.alibaba.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mr.cai
 * @date 2020/3/21 - 23:45
 */
public class StrFromLeet {
    public static void main(String[] args) {
        List<String> s = findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.println(s);
    }



    public static List<String> findRepeatedDnaSequences(String s) {
        int i = 0;
        List<String> str = new ArrayList<>();
        List<String> str1 = new ArrayList<>();
        int j = s.length();
        while (j - i>9){
            String str2 = s.substring(i,i+10);
            if(str.contains(str2)){
                str1.add(str2);
            }
            str.add(str2);
            i++;
        }


        return str1;
    }
}
