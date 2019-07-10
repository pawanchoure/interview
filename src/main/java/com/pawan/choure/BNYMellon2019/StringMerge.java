package com.pawan.choure.BNYMellon2019;

public class StringMerge {
    public static void main(String[] args) {
        System.out.printf(mergeStrings("abcg", "def"));
    }

    static String mergeStrings(String a, String b) {
        //Handle Case 1 String b is empty
        if (a != null && b == null) {
            return a;
        }
        //Handle Case 2 String a is empty
        if (a == null && b != null) {
            return b;
        }
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars1.length; i++) {
            sb.append(chars1[i]);
            if (chars2.length - 1 >= i) {
                sb.append(chars2[i]);
            }
        }

        if (chars2.length > chars1.length) {
            for (int j = chars1.length; j < chars2.length; j++) {
                sb.append(chars2[j]);
            }
        }
        return sb.toString();
    }
}
