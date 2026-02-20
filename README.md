# Java DSA Projects

Two Java projects that apply core Data Structures and Algorithms concepts to real-world use cases.

---

## Project 1 — Morse Code Translator

A console application that converts text to Morse code and back using two different data structures for each direction.

### How It Works

- **Encoding (Text → Morse):** Each character is looked up in a `HashMap<Character, String>` for O(1) speed.
- **Decoding (Morse → Text):** A Binary Tree is traversed — a dot moves left, a dash moves right — until the letter at that node is found.

### Data Structures Used

| Structure | Purpose |
|-----------|---------|
| `HashMap` | Fast O(1) encoding lookup |
| Binary Tree (`MorseNode`) | Tree traversal for decoding |

### Project Structure

```
MorseCodeTranslator/
├── src/
│   ├── MorseNode.java   # Binary tree node
│   ├── MorseTree.java   # HashMap encoder + tree decoder
│   └── Main.java        # Console menu
└── out/                 # Compiled .class files
```

### How to Run

```bash
cd MorseCodeTranslator
javac -d out src\MorseNode.java src\MorseTree.java src\Main.java
java -cp out Main
```

### Menu Options

```
1. Encrypt (Text to Morse)
2. Decrypt (Morse to Text)
3. Show Reference Table
4. Exit
```

> **Decoding format:** Separate characters with a space, separate words with ` / `
> Example: `.... . .-.. .-.. ---` = `HELLO`

---

## Project 2 — Smart Search Engine for Personal Files

A console application that scans a folder of `.txt` files, builds an inverted index, and lets you instantly search which files contain a specific word — the same technique used by real search engines like Google.

### How It Works

- Reads every `.txt` file in a given directory.
- For each word found, it records which files contain that word.
- When you search a word, it returns the list of matching files instantly.

### Data Structures Used

| Structure | Purpose |
|-----------|---------|
| `HashMap<String, HashSet<String>>` | Inverted index — maps each word to the files containing it |
| `HashSet<String>` | Stores unique file names per word (no duplicates) |

### Project Structure

```
SmartSearchEngine/
├── src/
│   ├── FileIndexer.java   # Builds the inverted index
│   └── Main.java          # Console search interface
├── sample_files/          # Sample .txt files for testing
└── out/                   # Compiled .class files
```

### How to Run

```bash
cd SmartSearchEngine
javac -d out src\FileIndexer.java src\Main.java
java -cp out Main
```

Then press **Enter** when asked for a folder path to use the built-in `sample_files`, or enter any folder path on your computer.

### Search Format

```
Search word: calculus
Found in 1 file(s):
  -> Calculus_Notes.txt

Search word: quit
Goodbye!
```

> You can point it at any folder on your computer containing `.txt` files — not just the sample files.

---

## Requirements

- Java JDK 17 or higher
- Any terminal (PowerShell, CMD, or VS Code integrated terminal)
