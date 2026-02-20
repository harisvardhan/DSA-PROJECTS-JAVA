import java.util.Scanner;
import java.util.Set;

/*
 * Main.java - Smart Search Engine
 *
 * How it works:
 *   1. You give it a folder path
 *   2. It reads all .txt files and builds an Inverted Index
 *      (HashMap: word -> which files contain that word)
 *   3. You type a word to search
 *   4. It instantly tells you which files contain that word
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileIndexer indexer = new FileIndexer();

        System.out.println("Smart Search Engine");
        System.out.println("-------------------");
        System.out.println("Reads your .txt files and lets you search words across them.");
        System.out.println();

        // Step 1: Ask for folder path
        System.out.print("Enter folder path to index (press Enter for sample_files): ");
        String path = scanner.nextLine().trim();

        if (path.isEmpty()) {
            path = "sample_files";
        }

        // Step 2: Build the index
        System.out.println();
        System.out.println("Scanning folder: " + path);
        indexer.buildIndex(path);
        System.out.println("Total unique words found: " + indexer.getWordCount());

        // Step 3: Search loop
        System.out.println();
        System.out.println("Index is ready. You can now search words.");
        System.out.println("Type 'quit' to exit.");
        System.out.println();

        while (true) {
            System.out.print("Search word: ");
            String word = scanner.nextLine().trim();

            if (word.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (word.isEmpty()) {
                System.out.println("Please enter a word.");
                continue;
            }

            Set<String> results = indexer.search(word);

            if (results.isEmpty()) {
                System.out.println("No files found containing the word: " + word);
            } else {
                System.out.println("Found in " + results.size() + " file(s):");
                for (String fileName : results) {
                    System.out.println("  -> " + fileName);
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
