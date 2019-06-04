package labs;

import java.util.Random;
import java.util.Scanner;

public class RandomRanges {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int low, high;
        int[] randoms = {0, 0, 0}; //empty array with 3 slots
        Random random = new Random();

        System.out.print("Enter low: ");
        low = in.nextInt();
        System.out.print("Enter high: ");
        high = in.nextInt();

        for (int i = 0; i < 3; i++) {
            randoms[i] = random.nextInt(high - low + 1) + low;
        }

        System.out.printf("Random values: %d %d %d\n", randoms[0], randoms[1], randoms[2]);
    }
}
