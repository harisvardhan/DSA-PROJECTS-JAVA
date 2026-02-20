import java.util.HashMap;

/**
 * MorseTree.java
 * Builds and manages the Morse Code Binary Tree used for DECODING.
 *
 * Tree layout (root = empty node):
 *   dot  (.) -> go LEFT
 *   dash (-) -> go RIGHT
 *
 * Standard ITU-R Morse code letters A-Z and digits 0-9 are inserted.
 */
public class MorseTree {

    private final MorseNode root;

    // ---------------------------------------------------------------
    // Morse encoding table (letter -> morse string)
    // Used by the encoder; also drives tree construction.
    // ---------------------------------------------------------------
    private static final HashMap<Character, String> ENCODING_MAP = new HashMap<>();

    static {
        // Letters
        ENCODING_MAP.put('A', ".-");
        ENCODING_MAP.put('B', "-...");
        ENCODING_MAP.put('C', "-.-.");
        ENCODING_MAP.put('D', "-..");
        ENCODING_MAP.put('E', ".");
        ENCODING_MAP.put('F', "..-.");
        ENCODING_MAP.put('G', "--.");
        ENCODING_MAP.put('H', "....");
        ENCODING_MAP.put('I', "..");
        ENCODING_MAP.put('J', ".---");
        ENCODING_MAP.put('K', "-.-");
        ENCODING_MAP.put('L', ".-..");
        ENCODING_MAP.put('M', "--");
        ENCODING_MAP.put('N', "-.");
        ENCODING_MAP.put('O', "---");
        ENCODING_MAP.put('P', ".--.");
        ENCODING_MAP.put('Q', "--.-");
        ENCODING_MAP.put('R', ".-.");
        ENCODING_MAP.put('S', "...");
        ENCODING_MAP.put('T', "-");
        ENCODING_MAP.put('U', "..-");
        ENCODING_MAP.put('V', "...-");
        ENCODING_MAP.put('W', ".--");
        ENCODING_MAP.put('X', "-..-");
        ENCODING_MAP.put('Y', "-.--");
        ENCODING_MAP.put('Z', "--..");
        // Digits
        ENCODING_MAP.put('0', "-----");
        ENCODING_MAP.put('1', ".----");
        ENCODING_MAP.put('2', "..---");
        ENCODING_MAP.put('3', "...--");
        ENCODING_MAP.put('4', "....-");
        ENCODING_MAP.put('5', ".....");
        ENCODING_MAP.put('6', "-....");
        ENCODING_MAP.put('7', "--...");
        ENCODING_MAP.put('8', "---..");
        ENCODING_MAP.put('9', "----.");
    }

    // ---------------------------------------------------------------
    // Constructor: build tree from encoding map
    // ---------------------------------------------------------------
    public MorseTree() {
        root = new MorseNode();
        for (HashMap.Entry<Character, String> entry : ENCODING_MAP.entrySet()) {
            insert(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Inserts a character into the binary tree at the position
     * defined by its Morse code sequence.
     */
    private void insert(char letter, String morseCode) {
        MorseNode current = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                if (current.left == null) current.left = new MorseNode();
                current = current.left;
            } else if (symbol == '-') {
                if (current.right == null) current.right = new MorseNode();
                current = current.right;
            }
        }
        current.letter = letter;
    }

    // ---------------------------------------------------------------
    // ENCODE – Text to Morse  (O(1) per character via HashMap)
    // ---------------------------------------------------------------
    /**
     * Encodes plain text (letters and digits) to Morse code.
     * Words are separated by " / ", individual characters by a space.
     *
     * @param text Input string (case-insensitive)
     * @return Morse code string, or an error message for unknown chars
     */
    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        String upper = text.toUpperCase().trim();

        for (int i = 0; i < upper.length(); i++) {
            char ch = upper.charAt(i);

            if (ch == ' ') {
                // word separator
                sb.append(" / ");
            } else if (ENCODING_MAP.containsKey(ch)) {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
                sb.append(ENCODING_MAP.get(ch));
            } else {
                return "[ERROR] Unknown character '" + ch + "'. Only A-Z and 0-9 are supported.";
            }
        }
        return sb.toString().trim();
    }

    // ---------------------------------------------------------------
    // DECODE – Morse to Text  (Tree traversal)
    // ---------------------------------------------------------------
    /**
     * Decodes a Morse code string back to plain text.
     * Symbols within a character are separated by a single space.
     * Words are separated by " / " (with surrounding spaces).
     *
     * @param morseCode Morse string (e.g. "... --- ..." or "... --- ... / ...")
     * @return Decoded text, or an error message on invalid input
     */
    public String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        // Split words by " / "
        String[] words = morseCode.trim().split("\\s*/\\s*");

        for (int w = 0; w < words.length; w++) {
            if (w > 0) result.append(' ');

            String word = words[w].trim();
            if (word.isEmpty()) continue;

            // Split individual characters by whitespace
            String[] symbols = word.split("\\s+");
            for (String symbol : symbols) {
                if (symbol.isEmpty()) continue;

                char decoded = traverseTree(symbol);
                if (decoded == '\0') {
                    return "[ERROR] Unknown Morse sequence: '" + symbol + "'";
                }
                result.append(decoded);
            }
        }
        return result.toString();
    }

    /**
     * Traverses the binary tree following dots (left) and dashes (right).
     *
     * @param morseSymbol A single Morse character sequence, e.g. ".-"
     * @return The decoded letter, or '\0' if not found
     */
    private char traverseTree(String morseSymbol) {
        MorseNode current = root;
        for (char ch : morseSymbol.toCharArray()) {
            if (ch == '.') {
                if (current.left == null) return '\0';
                current = current.left;
            } else if (ch == '-') {
                if (current.right == null) return '\0';
                current = current.right;
            } else {
                return '\0'; // invalid symbol
            }
        }
        return current.letter;
    }

    // Expose encoding map for display purposes
    public static HashMap<Character, String> getEncodingMap() {
        return ENCODING_MAP;
    }
}
