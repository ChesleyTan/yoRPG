// Written by Chesley Tan
/*=======================================================
	class InputValidator
	includes more robust methods for validating input using Scanner
=======================================================*/
import java.util.Scanner;
public class InputValidator{
	/*========================================================
	int nextValidInt(Scanner, int, int) -- Returns next valid input from Scanner
		and validates using a lower bound and upper bound for int
	post: returns validated user input from Scanner
	========================================================*/
	public static int nextValidInt(Scanner s, int lo, int hi){
		Integer input = null;
		do{
			if (s.hasNextInt()){
				input = (Integer) s.nextInt();
			}
			else{ // Case: there is no integer in the next input
				System.out.println("Please input a number.");
				s.next();  // this prevents build up of empty lines when Scanner receives empty input but does not return anything or move the cursor
						   // advances past all empty lines to allow searching for a new valid input
				continue;  // finish iteration of loop
			}
			if ((int) input < lo || (int) input > hi){ // Checks if input is within desired bounds
				System.out.println("Please input a valid number.");
				s.nextLine(); // prevents hasNextInt() infinite loop from the same bad input
							  // advance Scanner cursor to next line to allow searching for next int
			}
		}while(input == null || (int) input < lo || (int) input > hi); // Case when non-integer input or input not in desired bounds
		return input;
	}
}
