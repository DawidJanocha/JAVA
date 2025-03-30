import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberCombinations {

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\djano\\OneDrive\\Desktop\\AUEB\\CH10\\untitled\\src\\numbers.txt";
        String outputFile = "valid_combinations.txt";
        List<Integer> numbers = readNumbersFromFile(inputFile);


        Integer[] sortedNumbers = numbers.toArray(new Integer[0]);
        Arrays.sort(sortedNumbers);

        List<List<Integer>> validCombinations = new ArrayList<>();


        generateCombinations(sortedNumbers, new ArrayList<>(), 0, validCombinations);

        writeValidCombinations(outputFile, validCombinations);
    }

    private static List<Integer> readNumbersFromFile(String fileName) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String numStr : line.split("\\s+")) {
                    int num = Integer.parseInt(numStr);
                    if (num >= 1 && num <= 49) {
                        numbers.add(num);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static void generateCombinations(Integer[] numbers, List<Integer> current, int start, List<List<Integer>> results) {
        if (current.size() == 6) {
            if (isValidCombination(current)) {
                results.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            current.add(numbers[i]);
            generateCombinations(numbers, current, i + 1, results);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isValidCombination(List<Integer> combination) {
        return !hasMoreThan4Evens(combination) &&
                !hasMoreThan4Odds(combination) &&
                !hasMoreThan2Contiguous(combination) &&
                !hasMoreThan3SameEnding(combination) &&
                !hasMoreThan3InSameTens(combination);
    }

    private static boolean hasMoreThan4Evens(List<Integer> combination) {
        long count = combination.stream().filter(n -> n % 2 == 0).count();
        return count > 4;
    }

    private static boolean hasMoreThan4Odds(List<Integer> combination) {
        long count = combination.stream().filter(n -> n % 2 != 0).count();
        return count > 4;
    }

    private static boolean hasMoreThan2Contiguous(List<Integer> combination) {
        Integer[] sorted = combination.toArray(new Integer[0]);
        Arrays.sort(sorted);
        int contiguousCount = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i - 1] + 1) {
                contiguousCount++;
                if (contiguousCount > 2) {
                    return true;
                }
            } else {
                contiguousCount = 1;
            }
        }
        return false;
    }

    private static boolean hasMoreThan3SameEnding(List<Integer> combination) {
        int[] endings = new int[10]; // τελευταίοι αριθμοί 0-9
        for (int num : combination) {
            endings[num % 10]++;
        }
        for (int count : endings) {
            if (count > 3) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasMoreThan3InSameTens(List<Integer> combination) {
        int[] tens = new int[5]; // δεκαδικές θέσεις 1-49 (1-10, 11-20, 21-30, 31-40, 41-49)
        for (int num : combination) {
            if (num <= 10) tens[0]++;
            else if (num <= 20) tens[1]++;
            else if (num <= 30) tens[2]++;
            else if (num <= 40) tens[3]++;
            else tens[4]++;
        }
        for (int count : tens) {
            if (count > 3) {
                return true;
            }
        }
        return false;
    }

    private static void writeValidCombinations(String fileName, List<List<Integer>> combinations) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (List<Integer> combination : combinations) {
                writer.write(combination.stream().map(String::valueOf).collect(Collectors.joining(", ")));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}