//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public abstract class Character{
	protected String name;
	protected int hp, attack, spAttack, defense, spDefense;
	protected int baseHp, baseAttack, baseSpAttack, baseDefense, baseSpDefense;// These vars keep track of the original stat values before any changes
	protected double multiplier, evasiveness;
	// Accessor Methods //
	public int getDefense(){
		return defense;
	}
	public int getSpDefense(){
		return defense;
	}
	public int getHP(){
		return hp;
	}
	public double getEvasiveness(){
		return evasiveness;
	}
	public String getName(){
		return name;
	}
	//////////////////////////
	public boolean isAlive(){
		return (hp > 0);
	}
	public void lowerHP(int dmg){
		hp -= dmg;
	}
	public int attack(Character target){
		int damage = (int) (5 * Math.random()) + (int) (attack * multiplier) - target.getDefense();
		if (damage <= 0){
			damage = 1;
		}
		if ( ((int) (100 * Math.random()) + 1) < target.getEvasiveness()){
			damage = 0;
		}
		else
			target.lowerHP(damage);
		return damage;
	}
	public int spAttack(Character target){
		int damage = (int) (5 * Math.random()) + (int) (spAttack * multiplier) - target.getSpDefense();
		if (damage <= 0){
			damage = 1;
		}
		if ( ((int) (100 * Math.random()) + 1) < target.getEvasiveness()){
			damage = 0;
		}
		else
			target.lowerHP(damage);
		return damage;
	}
	public abstract int primaryAttack(Character target);// Differentiates whether attack or spAttack should be used
	public abstract void specialize();
	public abstract void normalize();
	public static String about(){
		return "\nThe available classes are:\n" + 
				"(1) Warrior: has high hp, attack, and defense but low multiplier rating\n" + 
				"(2) Mage: has low hp, attack, and defense, but high multiplier rating\n" + 
				"Choose one of the above by entering its corresponding number.\n";
	}	
}

