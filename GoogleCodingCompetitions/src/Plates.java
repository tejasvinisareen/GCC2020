import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Plates {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./InputFIles/PlatesInput.txt"));
        int numTest = sc.nextInt();
        for (int i = 0; i < numTest; i++) {
            int numStacks = sc.nextInt();
            int numPlatesInStack = sc.nextInt();
            int numPlatesWanted = sc.nextInt();
            int[][] plates = new int[numStacks][numPlatesInStack];
            for (int j = 0; j < numStacks; j++) {
                for (int k = 0; k < numPlatesInStack; k++) {
                    plates[j][k] = sc.nextInt();
                }
            }

            System.out.println("Case #" + (i + 1) + ": " + findMax(plates, numPlatesWanted));
        }
    }

    public static int findMax(int[][] plates, int numPlatesWanted) {
        return numPlatesWanted;
    }
}
