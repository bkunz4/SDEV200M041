import java.io.*;
import java.util.*;

public class SymbolMatch {
  public static void main(String[] args) throws IOException {
    // Checks command-line argument
    if (args.length != 1) {
      System.out.println("Usage: Java SymbolMatch Source-codeFileName");
      System.exit(0);
    }

    // Checks if file exists
    File file = new File(args[0]);
    if (!file.exists()) {
      System.out.println("The file " + args[0] + " does not exist!");
      System.exit(1);
    }

    // Creates a stack
    Stack<Character> symbols = new Stack<>();

    try ( // Creates an input stream for file
        Scanner input = new Scanner(file);
    ) {
      // Continuously reads chars from input
      while (input.hasNext()) {
        String line = input.nextLine();
        for (int i = 0; i < line.length(); i++) {
          char ch = line.charAt(i);
          // Pushes symbols (, {, and [ on to the stack
          if (ch == '(' || ch == '{' || ch == '[') {
            symbols.push(ch);
          } // Processes stack
          else if (ch == ')' || ch == '}' || ch == ']') {
            processSymbols(symbols, ch);
          }
        }
      }
    }

    System.out.println("The source-code " +
      (symbols.isEmpty() ? "has" : "does not have") + " correct pairs.");	
  }

  // Method matches grouping symbols
  private static void processSymbols(
      Stack<Character> stack, Character ch) {
    // Removes matching symbols from stack
    if ((stack.peek() == '(' && ch == ')') ||
       (stack.peek() == '[' && ch == ']') ||
       (stack.peek() == '{' && ch == '}')) {
      stack.pop();	
    }
    else if ((stack.peek() != '(' && ch == ')') ||
       (stack.peek() != '[' && ch == ']') ||
       (stack.peek() != '{' && ch == '}')) {
      System.out.println("The Java source-code does not have" 
        + " correct pairs.");
      System.exit(1);
    }
  }
}