import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        MorseTree morseTree = new MorseTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Morse Code Translator");
        System.out.println("---------------------");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Encrypt (Text to Morse)");
            System.out.println("2. Decrypt (Morse to Text)");
            System.out.println("3. Show Reference Table");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter text: ");
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Input cannot be empty.");
                    } else {
                        System.out.println("Morse Code: " + morseTree.encode(input));
                    }
                }
                case "2" -> {
                    System.out.println("Separate characters with a space. Separate words with ' / '");
                    System.out.print("Enter Morse code: ");
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Input cannot be empty.");
                    } else {
                        System.out.println("Decoded Text: " + morseTree.decode(input));
                    }
                }
                case "3" -> {
                    System.out.println();
                    System.out.println("Morse Code Reference Table:");
                    System.out.println("---------------------------");
                    Map<Character, String> sorted = new TreeMap<>(MorseTree.getEncodingMap());
                    for (Map.Entry<Character, String> entry : sorted.entrySet()) {
                        System.out.println(entry.getKey() + " : " + entry.getValue());
                    }
                }
                case "4" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Enter 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }
}
