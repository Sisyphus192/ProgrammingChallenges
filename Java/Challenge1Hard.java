import java.util.Scanner;
import java.util.Random;

/*
Derek Thomas

This is a reverse number guessing game; the user thinks of a number between 1 and 100
The program will then try and guess the number the user is thinking of. After each guess
the user will tell the program if the guess was correct or needs to be higher or lower.
The program uses a binary search for guesses and should take a maximum of 7 steps.
*/

public class Challenge1Hard {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("I'm going to try and guess your number");
		Random rand = new Random();
		int guess = rand.nextInt(100)+1;  	// First guess can be any random number, every number is only a 1/100 chance
		int high = 101;						// Our starting upper bound, needs to be 101 because of math
		int low = 1;						// Our starting lower bound
		boolean number_guessed = false;
		System.out.println("Is it? " + guess + " Was I (r)ight? Or do I need to go (h)igher or (l)ower?");
		while(!number_guessed) {
			String cmd = input.nextLine();
			switch (cmd.toUpperCase()) {	// I use .toUpperCase() so I don't have to worry about checking for twice as many characters 
				case "R":					// And I use switch instead of bunch of else if statements.
					System.out.println("Hurrah! I guessed the number");
					number_guessed = true;	// Set number_guessed to true to exit loop
					break;
				case "H":
					low = guess; 			// If our previous geuss was too low it becomes our new lower bound
					guess = (guess + high) / 2; 
					System.out.println("Let me try again is it " + guess + "?");
					System.out.println("Was I (r)ight? Or do I need to go (h)igher or (l)ower?");
					break;
				case "L":
					high = guess;			// If our previous guess was too low it become our new upper bound
					guess = (guess + low) / 2;
					System.out.println("Let me try again is it " + guess + "?");
					System.out.println("Was I (r)ight? Or do I need to go (h)igher or (l)ower?");
					break;
				default:
					System.out.println("The only valid inputs are 'r' 'h' or 'l'");
					break;
			}
		}
	}
}