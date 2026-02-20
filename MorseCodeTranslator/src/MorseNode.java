/**
 * MorseNode.java
 * Represents a single node in the Morse Code Binary Tree.
 * Each node holds a character (letter/digit) and has two children:
 *   left  -> dot   (.)
 *   right -> dash  (-)
 */
public class MorseNode {
    char letter;          // The decoded character at this node ('\0' if empty)
    MorseNode left;       // dot branch
    MorseNode right;      // dash branch

    /** Creates an empty node (no letter assigned). */
    public MorseNode() {
        this.letter = '\0';
        this.left   = null;
        this.right  = null;
    }

    /** Creates a node pre-assigned with a letter. */
    public MorseNode(char letter) {
        this.letter = letter;
        this.left   = null;
        this.right  = null;
    }
}
