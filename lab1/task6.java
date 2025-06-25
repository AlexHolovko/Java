package lab1;

import java.util.*;

public class task6 {
    public static void main(String[] args) {
        double[] arr = {1.2, 0.5, 7.0, 2.6, 5.0};
        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[maxIndex]) maxIndex = i;

        Arrays.sort(arr, 0, maxIndex);

        Arrays.sort(arr, maxIndex + 1, arr.length);

        double[] result = new double[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        System.out.println("After sorting: " + Arrays.toString(result));
    }
}
