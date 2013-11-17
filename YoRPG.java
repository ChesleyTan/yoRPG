import java.io.*;
import java.util.*;

public class YoRPG {

	static int wins = 0;
	
    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //change this constant to set number of encounters in a game
    //public final static int MAX_ENCOUNTERS = 10;

    private Character pat;   
    private Character smaug; 

    private int moveCount;
    private boolean gameOver;
    private int difficulty;
	private static int points;// Currency for shop
	private static int score;

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
	
	s = "\nIntrepid adventurer, what doth thy call thyself? (State your name): ";
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
	else if (playerClass == 3)
		pat = new Ninja(name);
	else if (playerClass == 4)
		pat = new Robot(name);

    }//end newGame()

	public boolean bossPlayTurn() {
		int i = 1;
		int d1, d2;
		smaug = new Balrog();
		
		System.out.println( "Your braveries have awoken the Balrog!");
		System.out.println("Beware traveller, for the Balrog isn't as meager as the monsters!");

	    while( smaug.isAlive() && pat.isAlive() ) {

		
		// Give user the option of preparing in a ready stance:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "\nBrave " + pat.getName() + ", do you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!\n\t3: Retreat to the shop!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		if (i == 3){
			int expense = Shop.show(pat, points);
			if (expense == -200){
				pat = transform(pat);
				points += expense;
			}
			else
				points += expense;
			System.out.println();
			try {
					statSummary(pat);
					System.out.println( "\nDo you feel lucky?" );
					System.out.println( "\t1: Nay.\n\t2: Aye!" );
					i = Integer.parseInt( in.readLine() );
				}
			catch ( IOException e ) { }
		}
		else if ( i == 2 )
		    pat.specialize();
		else
		    pat.normalize();

		d1 = pat.primaryAttack( smaug );
		d2 = smaug.primaryAttack( pat );

		System.out.println("=========================================");
		if (d1 == 0){
			System.out.println("The Balrog evaded the attack!");
			System.out.println("The Balrog Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		else{
			System.out.println( pat.getName() + " dealt " + d1 +
				    " points of damage.");
			System.out.println("The Balrog Health: \n" + StatBar.getHpBar(smaug) + "\n");
		}
		if (d2 == 0){
			System.out.println(pat.getName() + " evaded the monster's attack!");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
		}
		else{
			System.out.println( "The Balrog hit back for " + d2 +
				    " points of damage.");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
	    }
		System.out.println("=========================================");
		}//end while
		
		//option 1: you & the monster perish
	    if ( !smaug.isAlive() && !pat.isAlive() ) {
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
		points += 15;
		wins++;
		return true;
	    }
	    //option 3: the beast slays you
	    else{ //( !pat.isAlive() )
		System.out.println( "You were no match for the Balrog!" );
		return false;
	    }
	}//end boss	
		
    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior pat has been initialized
      post: Returns true if player wins (monster dies).
            Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn() {

	int i = 1;
	int d1, d2;

	if (wins % 4 == 0 && wins != 0){
		return bossPlayTurn();
	}
	if (pat.isAlive()){
	//if ( Math.random() >= ( difficulty / 3.0 ) )
	//    System.out.println( "\nNothing to see here. Move along!" );

	    System.out.println( "Lo, yonder monster approacheth!" );
		System.out.println();
	    statSummary(pat);
		smaug = new Monster();

	    while( smaug.isAlive() && pat.isAlive() ) {

		// Give user the option of preparing in a ready stance:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "\nDo you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!\n\t3: Retreat to the shop!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		if (i == 3){
			int expense = Shop.show(pat, points);
			if (expense == -200){
				pat = transform(pat);
				points += expense;
			}
			else
				points += expense;
			System.out.println();
			try {
					statSummary(pat);
					System.out.println( "\nDo you feel lucky?" );
					System.out.println( "\t1: Nay.\n\t2: Aye!" );
					i = Integer.parseInt( in.readLine() );
				}
			catch ( IOException e ) { }
		}
		else if ( i == 2 )
		    pat.specialize();
		else
		    pat.normalize();

		d1 = pat.primaryAttack( smaug );
		d2 = smaug.primaryAttack( pat );

		System.out.println("=========================================");
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
			System.out.println(pat.getName() + " evaded the enemy's attack!");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
		}
		else{
			System.out.println( "Ye Olde Monster hit back for " + d2 +
				    " points of damage.");
			System.out.println(pat.getName() + " Health: \n" + StatBar.getHpBar(pat));
	    }
		System.out.println("=========================================");
		}//end while

	    //option 1: you & the monster perish
	    if ( !smaug.isAlive() && !pat.isAlive() ) {
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
		points += 15;
		wins++;
		return true;
	    }
	    //option 3: the beast slays you
	    else if ( !pat.isAlive() ) {
		System.out.println( "Ye olde self hath expired. You got dead." );
		return false;
	    }
	}
	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

		YoRPG game = new YoRPG();

		//int encounters = 0;

		//while( encounters <= MAX_ENCOUNTERS ) {
		//    if ( !game.playTurn() )
		//	break;
		//    encounters++;
		//    System.out.println();

		while (game.playTurn()){
			//Unlimited play	
		}

		System.out.println( "Thy game doth be over." );
		System.out.println("Your score was: " + score + ".");

	}//end main
	public void statSummary(Character c){
		System.out.println("Your Stats:");
		System.out.println("*****************************************");
		System.out.println("Wins: " + wins);
		System.out.println("Health: " + ((int) (pat.getHp() * 1.0 / pat.getBaseHp() * 100.0)) + "%");
		System.out.println("Defense: " + pat.getDefense());
		System.out.println("SP Defense: " + pat.getSpDefense());
		System.out.println("Attack: " + pat.getAttack());
		System.out.println("SP Attack: " + pat.getSpAttack());
		System.out.println("*****************************************");		
	}
	public Character transform(Character c){
		c = new Hero(c.getName(),c.getAttack(),c.getSpAttack(),c.getDefense(),c.getSpDefense());
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Your body starts to glow a bright bluish-white as your enemy stands petrified. \n" + 
			"Your body is becoming larger and more imposing...\n" +
			"Your eyes glow a dark red...\n" + 
			"Congratulations, you've attained your ultimate power, your Hero stage.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return c;
	}

}//end class YoRPG

