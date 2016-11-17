import java.util.Arrays;
import java.util.Scanner;

/**
 *Derek Thomas
 *
 * This program does three things
 * First, given a 4 digit number will return the largest digit
 * Second, given a 4 digit number will return the same digits sorted in descending order
 * Third, counts the number of iterations in Kaprekar's routine which is explained in
 * detail in the relevant fuction docstring.
 */

public class Easy287_Kaprekars_Routine {
  public static void main(String[] args) {
    int[] intArr1 = {1234, 3253, 9800, 3333, 120};
    int[] intArr2 = {6589, 5455, 6174};
    for (int i = 0; i < intArr1.length; i++) {
      System.out.format("Looking at number: %d\n", intArr1[i]);
      System.out.format("Largest digit: %d\n", largest_digit(Integer.toString(intArr1[i])));
      System.out.format("Digits in descending order: %s\n\n", desc_digits(Integer.toString(intArr1[i])));
    }
    for (int i = 0; i < intArr2.length; i++) {
      System.out.format("Looking at number: %d\n", intArr2[i]);
      System.out.format("Kaprekar's number: %s\n\n", kaprekar(Integer.toString(intArr2[i]), 0));
    }

  }

  /**
   * Returns an int that is the largest digit in a String of digits.
   */
  private static int largest_digit(String number) {
    char[] num = check_num(number).toCharArray();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < num.length; i++) {
      if (Integer.parseInt(Character.toString(num[i])) > max) {
        max = Integer.parseInt(Character.toString(num[i]));
      }
    } 
    return max;
  }

  /**
   * Returns a String of digits arranged in descending order.
   */
  private static String desc_digits(String number) {
    char[] charArr = check_num(number).toCharArray();
    Arrays.sort(charArr);
    String num = new String(charArr);
    String reverse = "";
    for (int i = num.length() - 1; i >= 0; i--) {
      reverse = reverse + num.charAt(i);
    }
    return reverse;
  }

  /**
   * Returns a String of digits arranged in ascending order.
   */
  private static String asc_digits(String number) {
    char[] charArr = check_num(number).toCharArray();
    Arrays.sort(charArr);
    return new String(charArr);
  }

  /**
   * Returns an int that is the number of cycles in Kaprekar's Routine, which is as follows:
   * 1. Take any four-digit number, using at least two different digits. (Leading zeros are allowed.)
   * 2. Arrange the digits in descending and then in ascending order to get two four-digit numbers, adding leading zeros if necessary.
   * 3. Subtract the smaller number from the bigger number.
   * 4. Go back to step 2.
   */
  private static int kaprekar(String number, int counter) {
    if (number.equals("6174")) {
      return counter;
    }
    number = Integer.toString(Integer.parseInt(desc_digits(number)) - Integer.parseInt(asc_digits(number)));
    if (number.equals("6174")) {
      return counter + 1;
    } else {
      return kaprekar(number, counter + 1);
    }
  }

  /**
   * Verifies that a String of digits has at least 4 digits, if it has less add leading zeros.
   */
  private static String check_num(String number) {
    if (number.length() > 4) {
      System.out.println("You have entered a number with more than 4 digits");
    }
    while (number.length() < 4) {
      number = "0" + number;
    }
    return number;
  }
}