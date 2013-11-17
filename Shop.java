import java.util.Scanner;

public class Shop{
	public static int show(Character c, int pointsAvailable){
		Scanner scan1 = new Scanner(System.in);
		System.out.println("\nYou have " + pointsAvailable + " points available to spend.");
		System.out.println( "(1)[10 points] Heal Potion - Heals health to full.\n" + 
				"(2)[10 points] Strength Potion - Increases attack and special attack.\n" +
				"(3)[10 points] Defense Potion - Increases defense and special defense.\n" +
				"(4)[200 points] Transform to Hero class.\n" +
				"(5) Leave the shop.");
				
		System.out.print("Your selection: ");
		int i = scan1.nextInt();
		if (i == 1 && pointsAvailable >= 10){
			c.setHp(c.getBaseHp());
			return -10;				
		}
		else if (i == 2 && pointsAvailable >= 10){
			c.setAttack(c.getAttack() + 30);
			c.setBaseAttack(c.getBaseAttack() + 30);
			c.setSpAttack(c.getSpAttack() + 30);
			c.setBaseSpAttack(c.getBaseSpAttack() + 30);
			return -10;
		}
		else if (i == 3 && pointsAvailable >= 10){
			c.setDefense(c.getDefense() + 10);
			c.setBaseDefense(c.getBaseDefense() + 10);
			c.setSpDefense(c.getSpDefense() + 10);
			c.setBaseSpDefense(c.getBaseSpDefense() + 10);
			return -10;
		}
		else if (i == 4 && pointsAvailable >= 200){
			return -200;
		}
		else{
			return 0;
		}
		
	}
}