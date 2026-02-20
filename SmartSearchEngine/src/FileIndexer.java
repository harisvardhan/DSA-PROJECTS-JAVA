import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * FileIndexer.java
 *
 * How it works:
 *   - Scans all .txt files inside a given directory
 *   - Reads every word in each file
 *   - Builds an Inverted Index:
 *       HashMap<String, HashSet<String>>
 *           key   = word  (lowercase)
 *           value = set of file names that contain that word
 *
 * This is the same technique used by search engines like Google.
 */
public class FileIndexer {

    // Inverted Index: word -> set of files containing that word
    private HashMap<String, HashSet<String>> index = new HashMap<>();

    // Total files scanned
    private int fileCount = 0;

    // -------------------------------------------------------
    // Build the index from a directory path
    // -------------------------------------------------------
    public void buildIndex(String directoryPath) {
        File folder = new File(directoryPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: Folder not found -> " + directoryPath);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in: " + directoryPath);
            return;
        }

        for (File file : files) {
            indexFile(file);
            fileCount++;
        }

        System.out.println("Indexed " + fileCount + " file(s) from: " + directoryPath);
    }

    // -------------------------------------------------------
    // Read one file and add its words to the index
    // -------------------------------------------------------
    private void indexFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                // Read one word, make it lowercase, strip punctuation
                String word = scanner.next()
                        .toLowerCase()
                        .replaceAll("[^a-z0-9]", ""); // keep only letters and digits

                if (word.isEmpty()) continue;

                // If this word is new, create a fresh HashSet for it
                // Then add the current filename to that set
                index.computeIfAbsent(word, k -> new HashSet<>())
                     .add(file.getName());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not read file: " + file.getName());
        }
    }

    // -------------------------------------------------------
    // Search: return all files containing the given word
    // -------------------------------------------------------
    public Set<String> search(String word) {
        String key = word.toLowerCase().trim();
        return index.getOrDefault(key, new HashSet<>());
    }

    // -------------------------------------------------------
    // Show all unique words in the index (the vocabulary)
    // -------------------------------------------------------
    public void showAllWords() {
        if (index.isEmpty()) {
            System.out.println("Index is empty. Please index a folder first.");
            return;
        }
        System.out.println("Total unique words indexed: " + index.size());
        System.out.println("Words: " + index.keySet());
    }

    public int getFileCount() { return fileCount; }
    public int getWordCount() { return index.size(); }
}
