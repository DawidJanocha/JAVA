import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CharCounter {

    public static void main(String[] args) {
        // Πίνακας 128x2 για χαρακτήρες και μετρητές
        String[][] frequencyTable = new String[128][2];

        // Αρχικοποίηση τον πίνακα
        for (int i = 0; i < 128; i++) {
            frequencyTable[i][0] = null; // Χαρακτήρας
            frequencyTable[i][1] = "0"; // Πλήθος
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\djano\\OneDrive\\Desktop\\AUEB\\CH10\\untitled\\src\\input.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;

                // Αγνόηση κενών και γραμμών
                if (Character.isWhitespace(character)) {
                    continue;
                }

                // Αν είναι εντός των λατινικών χαρακτήρων
                if (character >= 0 && character < 128) {
                    int index = (int) character;

                    // Αν ο χαρακτήρας δεν έχει καταχωρηθεί ακόμα
                    if (frequencyTable[index][0] == null) {
                        frequencyTable[index][0] = String.valueOf(character); // Αποθήκευση χαρακτήρα
                    }

                    // Αυξάνουμε το μετρητή
                    frequencyTable[index][1] = String.valueOf(Integer.parseInt(frequencyTable[index][1]) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Διαλογή και εμφάνιση των αποτελεσμάτων
        System.out.println("Character Frequency:");
        for (String[] entry : frequencyTable) {
            if (entry[0] != null) {
                System.out.println("Character: " + entry[0] + ", Count: " + entry[1]);
            }
        }

        // Ταξινόμηση κατά συχνότητα
        String[][] frequencyList = Arrays.copyOf(frequencyTable, frequencyTable.length);
        Arrays.sort(frequencyList, (a, b) -> {
            if (a[0] == null) return 1; // Βάλτε το null στο τέλος
            if (b[0] == null) return -1;
            return Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])); // Ταξινόμηση φθίνουσα
        });

        // Εμφάνιση ταξινομημένων
        System.out.println("\nSorted by Frequency:");
        for (String[] entry : frequencyList) {
            if (entry[0] != null) {
                System.out.println("Character: " + entry[0] + ", Count: " + entry[1]);
            }
        }
    }
}4