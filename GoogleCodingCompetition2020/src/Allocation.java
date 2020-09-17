import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Allocation {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./InputFiles/AllocationInput.txt"));
        int numTest = sc.nextInt();
        for (int i = 0; i < numTest; i++) {
            int numHouses = sc.nextInt();
            int budget = sc.nextInt();
            int[] houses = new int[numHouses];
            for (int j = 0; j < numHouses; j++) {
                houses[j] = sc.nextInt();
            }
            System.out.println("Case #" + (i + 1) + ": " + findMax(houses, budget));
        }
    }

    public static int findMax(int[] houses, int budget) {
        int max = 0;
        Arrays.sort(houses);
        int i = 0;
        while (i < houses.length && budget - houses[i] >= 0) {
            max++;
            budget -= houses[i];
            i++;
        }
        return max;
    }
}
