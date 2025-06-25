package lab1;

import java.util.*;

public class task5 {
    public static void main(String[] args) {
        int[] arr = new Random().ints(20, -50, 51).toArray(); 

        List<Integer> even = new ArrayList<>();
        List<Integer> fibo = new ArrayList<>();
        List<Integer> negAboveMinus17 = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();

        Set<Integer> fibSet = new HashSet<>();
        int a = 0, b = 1;
        while (b <= 1000) {
            fibSet.add(b);
            int next = a + b;
            a = b;
            b = next;
        }

        for (int n : arr) {
            if (n % 2 == 0) even.add(n);
            if (fibSet.contains(n)) fibo.add(n);
            if (n < 0 && n > -17) negAboveMinus17.add(n);
            if (isPrime(n)) primes.add(n);
        }

        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Even numbers: " + even);
        System.out.println("Fibonacci numbers: " + fibo);
        System.out.println("Negative numbers > -17: " + negAboveMinus17);
        System.out.println("Prime numbers: " + primes);
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }
}
