package com.kenzie.groupwork.kenziejavacompiler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Compiler check that ensures the curly braces in a file are evenly matched. The file must contain an even number of
 * opening and closing curly braces. This check does not perform any validation that the curly braces are in legal
 * locations in the file. [NOTE] Your implementation does not need to handle escaped curly braces within strings
 * literals.
 */
public class BalancedCurlyBraceValidator {

    private static final char OPEN = '{';
    private static final char CLOSE = '}';
    private boolean debug = false;

    /**
     * Validates that the curly braces in the list of provided file characters are balanced.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean check(List<Character> fileCharacters) {
        // TODO: complete this method
        Queue<Character> q = new LinkedList<>();
        int count = 0;

        for (Character c : fileCharacters) {
            q.add(c);
        }

        while (!q.isEmpty()) {
            char c = q.remove();
            if (c == OPEN) {
                count++;
            } else if (c == CLOSE) {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        if (count == 0) {
            return true;
        }
        return false;
    }

    /**
     * Does the above and prints out debugging information. This can be combined into one method, but separating these
     * out so we can see both the basic solution and the extension solution.
     * @param fileCharacters all characters in a java file
     * @return true if the braces are balanced, false otherwise
     */
    public boolean checkExtension(List<Character> fileCharacters) {
        //keeping track of open braces
        Stack<Character> openStack = new Stack<>();

        String longestString = "";
        String currentString = "";
        int extraClose = 0;
        int extraOpen = openStack.size();

        //loop thru fileCharacters
        for (Character c : fileCharacters) {
            //checking if a close bracket comes before an unpaired open bracket
            if (c == CLOSE && openStack.isEmpty()) {
                extraClose++;
            } else if (c == CLOSE) {
                //matching an open bracket with a close bracket
                openStack.pop();
                //checking for the longest string
                if (currentString.length() > longestString.length()) {
                    longestString = currentString;
                }
                //resetting current string
                currentString = "";
            } else if (c == OPEN) {
                openStack.push(c);

            }  else if (!openStack.isEmpty()) {
                currentString = currentString + c;
            }
        }

        System.out.println("Total number of unbalanced brackets is " + (extraClose + extraOpen) +
                "\nTotal unbalanced open brackets: " + extraOpen +
                "\nTotal unbalanced close brackets: " + extraClose +
                "\nLongest string of characters between a pair of balanced brackets: " + longestString);

        if (extraClose == 0 && openStack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Use this to enable or disable additional debug messages.
     * @param debug the value to set the debug variable
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
