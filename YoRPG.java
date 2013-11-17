
import java.io.*;
import java.util.*;

public class YoRPG {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //change this constant to set number of encounters in a game
    public final static int MAX_ENCOUNTERS = 5;

    private Character pat;   
    private Monster smaug; 

    private int moveCount;
    private boolean gameOver;
    private int difficulty;

    private InputStreamReader isr;
    private BufferedReader in;
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public YoRPG() {
	moveCount = 0;
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
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
	int playerClass = 1; //Default class is 1, which is warrior.
	s = "Welcome to Ye Olde RPG!\n";

	s += "\nChoose your difficulty: \n";
	s += "\t1: Easy\n";
	s += "\t2: Not so easy\n";
	s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
	s += "Selection: ";
	System.out.print( s );

	try {
	    difficulty = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = Character.about();
	s += "Class selection: ";
	System.out.print( s );
	try {
	    playerClass = Integer.parseInt(in.readLine());
	}
	catch ( IOException e ) { }
	
	s = "Intrepid adventurer, what doth thy call thyself? (State your name): ";
	System.out.print( s );

	try {
	    name = in.readLine();
	}
	catch ( IOException e ) { }


	//instantiate the player's character
	//Note: default playerClass is 1 because it was initialized as such
	if (playerClass == 1)
		pat = new Warrior( name );
	else if (playerClass == 2)
		pat = new Mage(name);

    }//end newGame()


    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior pat has been initialized
      post: Returns true if player wins (monster dies).
            Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn() {

	int i = 1;
	int d1, d2;

	if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "Nothing to see here. Move along!" );

	else {
	    System.out.println( "Lo, yonder monster approacheth!" );

	    smaug = new Monster();

	    while( smaug.isAlive() && pat.isAlive() ) {

		// Give user the option of preparing in a ready stance:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "\nDo you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		if ( i == 2 )
		    pat.specialize();
		else
		    pat.normalize();

		d1 = pat.primaryAttack( smaug );
		d2 = smaug.primaryAttack( pat );

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (d1 == 0){
			System.out.println("Ye Olde Monster evaded the attack!");
			System.out.println("Ye Olde Monster Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		else{
			System.out.println( pat.getName() + " dealt " + d1 +
				    " points of damage.");
			System.out.println("Ye Olde Monster Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		if (d2 == 0){
			System.out.println(pat.getName() + " evaded the monster's attack!");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
		}
		else{
			System.out.println( "Ye Olde Monster hit back for " + d2 +
				    " points of damage.");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
	    }
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}//end while

	    //option 1: you & the monster perish
	    if ( !smaug.isAlive() && !pat.isAlive() ) {
		System.out.println( "'Twas an epic battle, to be sure... " + 
				    "You cut ye olde monster down, but " +
				    "with its dying breath ye olde monster. " +
				    "laid a fatal blow upon thy skull." );
		return false;
	    }
	    //option 2: you slay the beast
	    else if ( !smaug.isAlive() ) {
		System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
		return true;
	    }
	    //option 3: the beast slays you
	    else if ( !pat.isAlive() ) {
		System.out.println( "Ye olde self hath expired. You got dead." );
		return false;
	    }
	}//end else

	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	YoRPG game = new YoRPG();

	int encounters = 0;

	while( encounters < MAX_ENCOUNTERS ) {
	    if ( !game.playTurn() )
		break;
	    encounters++;
	    System.out.println();
	}

	System.out.println( "Thy game doth be over." );

    }//end main

}//end class YoRPG

