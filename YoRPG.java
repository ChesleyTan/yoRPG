// Chesley Tan, Johnathan Yan, Christopher Kim
// Pd 9
// HW26
// 2013-11-17

import java.io.*;
import java.util.Scanner;

public class YoRPG { // Driver

	static int wins = 0;
	static int playerClass = 1; //Default class is 1, which is warrior.
	
    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //change this constant to set number of encounters in a game
    //public final static int MAX_ENCOUNTERS = 10;

    private Character player1;   
    private Character smaug; 

    private int moveCount;
    private boolean gameOver;
    private int difficulty;
	private static int points;// Currency for shop
	private static int score;

    private Scanner in = new Scanner(System.in);
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public YoRPG() {
	moveCount = 0;
	gameOver = false;
	newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    /*=============================================
      void newGame() -- facilitates info gathering to begin a new game
      pre:  
      post: according to user input, modifies instance var for difficulty 
      and instantiates a Warrior
      =============================================*/
    public void newGame() {

	String s;
	String name = "";
	s = "Welcome to Ye Olde RPG!\n";

	s += "\nChoose your difficulty: \n";
	s += "\t1: Easy\n";
	s += "\t2: Not so easy\n";
	s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
	s += "Selection: ";
	System.out.print( s );

	difficulty = InputValidator.nextValidInt(in,1,3);

	s = Character.about();
	s += "Class selection: ";
	System.out.print( s );
	playerClass = InputValidator.nextValidInt(in,1,5);
	
	s = "\nIntrepid adventurer, what doth thy call thyself? (State your name): ";
	System.out.print( s );
	in.nextLine(); // Advance Scanner cursor to next line
	if (in.hasNext()){
		name = in.nextLine();
	}
	else{
		System.out.println("Please input a name.");
	}


	//instantiate the player's character
	//Note: default playerClass is 1 because it was initialized as such
	// New Characters added here
	if (playerClass == 1)
		player1 = new Warrior(name);
	else if (playerClass == 2)
		player1 = new Mage(name);
	else if (playerClass == 3)
		player1 = new Ninja(name);
	else if (playerClass == 4)
		player1 = new Robot(name);
	else if (playerClass == 5)
		player1 = new Gambler(name);
	else
		player1 = new Warrior(name);

    }//end newGame()

	public boolean bossPlayTurn() { // Boss mode, after every 3 wins an encounter with Balrog!
		int i = 1; // default choice for attack prompt
		int d1, d2;
		smaug = new Balrog(difficulty, wins);
		
		System.out.println( "\nYour braveries have awoken the Balrog!");
		System.out.println("Beware traveller, for the Balrog isn't as meager as the monsters!");
		System.out.println();
		statSummary(player1);

	    while( smaug.isAlive() && player1.isAlive() ) {

		
		// Give user the option of preparing in a ready stance:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		do {
		    System.out.println( "\nBrave " + player1.getName() + ", do you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!\n\t3: Retreat to the shop!" );
			i = InputValidator.nextValidInt(in,1,3);
			if (i == 3){
				int expense = Shop.show(player1, points);
				if (expense == -200){
					player1 = Hero.transform(player1);
					points += expense;
				}
				else
					points += expense;
				System.out.println();
			}
		}while(i == 3); // Do-while loop for infinite uses of shop
		if ( i == 2 )
		    player1.specialize();
		else
		    player1.normalize();

		d1 = player1.primaryAttack( smaug );
		d2 = smaug.primaryAttack( player1 );

		System.out.println("=========================================");
		if (d1 == 0){
			System.out.println("The Balrog evaded the attack!");
			System.out.println("The Balrog Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		else{
			System.out.println( player1.getName() + " dealt " + d1 +
				    " points of damage.");
			System.out.println("The Balrog Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		if (d2 == 0){
			System.out.println(player1.getName() + " evaded the monster's attack!");
			System.out.println(player1.getName() + " Health: \n" + StatBar.getHpBar(player1));
		}
		else{
			System.out.println( "The Balrog hit back for " + d2 +
				    " points of damage.");
			System.out.println(player1.getName() + " Health: \n" + StatBar.getHpBar(player1));
	    }
		System.out.println("=========================================");
		}//end while
		
		//option 1: you & the monster perish
	    if ( !smaug.isAlive() && !player1.isAlive() ) {
		System.out.println("'Twas an epic battle, to be sure... ");
		System.out.println("You cut The Balrog down,");
		System.out.println("but you were slain by the creature of the caves." );
		score += 10;
		return false;
	    }
	    //option 2: you slay the beast
	    else if ( !smaug.isAlive() ) {
		System.out.println( "HuzzaaH! You raise your fists and crush the skull of the Balrog!" );
		score += 10;
		if (playerClass == 5){
			points += 20;}
		else{
			points += 15;}
		wins++;
		return true;
	    }
	    //option 3: the beast slays you
	    else{ //( !player1.isAlive() )
		System.out.println( "You were no match for the Balrog!" );
		return false;
	    }
	}//end boss	
		
    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior player1 has been initialized
      post: Returns true if player wins (monster dies).
            Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn() {

	int i = 1; // default choice for attack prompt
	int d1, d2;

	if (wins % 4 == 0 && wins != 0){
		return bossPlayTurn();
	}
	if (player1.isAlive()){
	//if ( Math.random() >= ( difficulty / 3.0 ) )
	//    System.out.println( "\nNothing to see here. Move along!" );

	    System.out.println( "Lo, yonder monster approacheth!" );
		System.out.println();
	    statSummary(player1);
		smaug = new Monster(difficulty, wins);

	    while( smaug.isAlive() && player1.isAlive() ) {

		// Give user the option of preparing in a ready stance:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		do {
			System.out.println( "\nDo you feel lucky?" );
			System.out.println( "\t1: Nay.\n\t2: Aye!\n\t3: Retreat to the shop!" );
			i = InputValidator.nextValidInt(in,1,3);
			if (i == 3){
				int expense = Shop.show(player1, points);
				if (expense == -200){
					player1 = Hero.transform(player1);
					points += expense;
				}
				else
					points += expense;
				System.out.println();
			}
		}while (i == 3);// Do-while loop for infinite uses of shop
		if ( i == 2 )
		    player1.specialize();
		else
		    player1.normalize();

		d1 = player1.primaryAttack( smaug );
		d2 = smaug.primaryAttack( player1 );

		System.out.println("=========================================");
		if (d1 == 0){
			System.out.println("Ye Olde Monster evaded the attack!");
			System.out.println("Ye Olde Monster Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		else{
			System.out.println( player1.getName() + " dealt " + d1 +
				    " points of damage.");
			System.out.println("Ye Olde Monster Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		if (d2 == 0){
			System.out.println(player1.getName() + " evaded the enemy's attack!");
			System.out.println(player1.getName() + " Health: \n" + StatBar.getHpBar(player1));
		}
		else{
			System.out.println( "Ye Olde Monster hit back for " + d2 +
				    " points of damage.");
			System.out.println(player1.getName() + " Health: \n" + StatBar.getHpBar(player1));
	    }
		System.out.println("=========================================");
		}//end while

	    //option 1: you & the monster perish
	    if ( !smaug.isAlive() && !player1.isAlive() ) {
		System.out.println( "'Twas an epic battle, to be sure... " + 
				    "You cut ye olde monster down, but " +
				    "with its dying breath ye olde monster " +
				    "laid a fatal blow upon thy skull." );
		score += 10;
		return false;
	    }
	    //option 2: you slay the beast
	    else if ( !smaug.isAlive() ) {
		System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
		score += 10;
		if (playerClass == 5){
			points += 20;}
		else{
			points += 15;}
		wins++;
		return true;
	    }
	    //option 3: the beast slays you
	    else if ( !player1.isAlive() ) {
		System.out.println( "Ye olde self hath expired. You got dead." );
		return false;
	    }
	}
	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void main( String[] args ) {

		YoRPG game = new YoRPG();

		while (game.playTurn()){
			//Unlimited play	
		}

		System.out.println( "Thy game doth be over." );
		System.out.println("Your score was: " + score + ".");

	}//end main
	public void statSummary(Character c){ // A display of your stats after every battle
		System.out.println("Your Stats:");
		System.out.println("*****************************************");
		System.out.println("Wins: " + wins);
		System.out.println("Health: " + ((int) (c.getHp() * 1.0 / c.getBaseHp() * 100.0)) + "%");
		System.out.println("Defense: " + c.getDefense());
		System.out.println("SP Defense: " + c.getSpDefense());
		System.out.println("Attack: " + c.getAttack());
		System.out.println("SP Attack: " + c.getSpAttack());
		System.out.println("*****************************************");		
	}

}//end class YoRPG

