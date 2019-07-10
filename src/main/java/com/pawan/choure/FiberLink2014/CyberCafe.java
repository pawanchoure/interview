package com.pawan.choure.FiberLink2014;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CyberCafe {

    public static void main(String[] args) {

        System.out.println(runComputerSimulation(2, "ABBAJJKZKZ"));
    }


    static int findLIS(int[] s) {
        int largest_seq = 1;
        for (int i = 1; i < s[0]; i++) {
            int largest_seq_small = 1;
            int sequence = i;
            for (int j = sequence + 1; j <= s[0]; j++) {
                if (s[sequence] < s[j]) {
                    largest_seq_small++;
                }
                sequence++;
            }
            if (largest_seq < largest_seq_small) {
                largest_seq = largest_seq_small;
            }
        }
        System.out.print(largest_seq);
        return largest_seq;
    }

    // function to convert decimal to binary
    static Map<Integer, Integer> decToBinary(int n) {
        int[] binaryNum = new int[10000];
        int i = 0;
        while (n > 0) {
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--) {

            System.out.print(binaryNum[j]);

        }
        return null;

    }

    static int runComputerSimulation(int numComputers, String customers) {
        ArrayList<Character> customerWithLogging = new ArrayList<>();
        ArrayList<Character> customerOutWithoutLogging = new ArrayList<>();
        char[] chars = customers.toCharArray();
        for (int i = 0, n = chars.length; i < n; i++) {
            Character cust = chars[i];
            if (customerWithLogging.size() < numComputers || customerWithLogging.contains(cust)) {
                if (!customerWithLogging.contains(cust))
                    customerWithLogging.add(cust);
                else
                    customerWithLogging.remove(cust);
            } else if (!customerOutWithoutLogging.contains(cust) && !customerWithLogging.contains(cust))
                customerOutWithoutLogging.add(cust);
        }
        if (customerOutWithoutLogging.size() == 0)
            return 0;
        return customerOutWithoutLogging.size();

    }


}
