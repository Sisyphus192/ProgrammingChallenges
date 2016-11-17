import java.util.Scanner;

/**
 * Derek Thomas
 *
 * This program will prompt the user to either encode or decode a String
 * with a Caesar Cipher, and then ask for the offset to use.
 */

public class Easy3_Caesar_Cipher {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean running = true;
    String text;
    int offset;
    while (running) {
      System.out.println("Would you like to (e)ncode, (d)ecode, or (q)uit?");
      String cmd = input.nextLine();
      switch (cmd.toUpperCase()) {
        case "E":
          System.out.println("Enter the text to encode");
          text = input.nextLine();
          System.out.println("What offset would you like to use?");
          offset = Integer.parseInt(input.nextLine());
          String encodedText = encode_decode(text, offset, 'e');
          System.out.println(encodedText);
          break;
        case "D":
          System.out.println("Enter the text to decode");
          text = input.nextLine();
          System.out.println("What offset would you like to use?");
          offset = Integer.parseInt(input.nextLine());
          System.out.println(encode_decode(text, offset, 'd'));
          break;
        case "Q":
          running = false;
          break;
        default:
          System.out.println("The only valid inputs are 'e', 'd', and 'q'.");
      }
    }
  }

  private static String encode_decode(String text, int offset, char encodeDecode) {
    StringBuffer out = new StringBuffer(text.length());
    char[] charArr = text.toCharArray();
    for (int c : charArr) {
      if (c >= 65 && c <= 90) {
        if (encodeDecode == 'e') {
          c = ((c - 65) + offset) % 26 + 65;
        } else if (encodeDecode == 'd') {
          c = ((c - 65 + 26) - offset) % 26 + 65;
        }
      } else if (c >= 97 && c <= 122) {
        if (encodeDecode == 'e') {
          c = ((c - 97) + offset) % 26 + 97;
        } else if (encodeDecode == 'd') {
          c = ((c - 97 + 26) - offset) % 26 + 97;
        }
      }
      out.append((char)c);
    }
    return out.toString();
  }
}