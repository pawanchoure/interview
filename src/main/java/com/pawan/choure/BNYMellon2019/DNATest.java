package com.pawan.choure.BNYMellon2019;

import java.util.HashMap;

public class DNATest {

    private static final HashMap<Character, Character> complement;

    static {
        complement = new HashMap<>();
        complement.put('A', 'T');
        complement.put('T', 'A');
        complement.put('C', 'G');
        complement.put('G', 'C');
    }

    public static void main(String[] args) {
        System.out.printf(dnaComplement("ACCGGGTTTT"));
    }


    public static String dnaComplement(String s) {
        String reverseDNA = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reverseDNA = reverseDNA + complement.get(s.charAt(i));
        }
        return reverseDNA;
    }
}
