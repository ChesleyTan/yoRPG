//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public abstract class Character{
	protected String name;
	protected int hp, attack, spAttack, defense, spDefense;
	protected int baseHp, baseAttack, baseSpAttack, baseDefense, baseSpDefense;// These vars keep track of the original stat values before any changes
	protected double multiplier, evasiveness;								   // If we make the base stats final, we have to initialize the changeable variables outside the constructor and run a super() to set these base vars because the super() has to be the first line in the constructor and finals can only be initialized in declaration or a constructor
	// Accessor Methods //
	public int getAttack(){
		return attack;
	}
	public int getSpAttack(){
		return spAttack;
	}
	public int getDefense(){
		return defense;
	}
	public int getSpDefense(){
		return spDefense;
	}
	public int getBaseAttack(){
		return baseAttack;
	}
	public int getBaseSpAttack(){
		return baseSpAttack;
	}
	public int getBaseDefense(){
		return baseDefense;
	}
	public int getBaseSpDefense(){
		return baseSpDefense;
	}
	public int getHp(){
		return hp;
	}
	public int getBaseHp(){
		return baseHp;
	}
	public double getEvasiveness(){
		return evasiveness;
	}
	public String getName(){
		return name;
	}
	// Mutator methods //
	public int setAttack(int attack){
		this.attack = attack;
		return attack;
	}
	public int setSpAttack(int spAttack){
		this.spAttack = spAttack;
		return spAttack;
	}
	public int setDefense(int defense){
		this.defense = defense;
		return defense;
	}
	public int setSpDefense(int spDefense){
		this.spDefense = spDefense;
		return spDefense;
	}
	public int setBaseAttack(int baseAttack){
		this.baseAttack = baseAttack;
		return baseAttack;
	}
	public int setBaseSpAttack(int baseSpAttack){
		this.baseSpAttack = baseSpAttack;
		return baseSpAttack;
	}
	public int setBaseDefense(int baseDefense){
		this.baseDefense = baseDefense;
		return baseDefense;
	}
	public int setBaseSpDefense(int baseSpDefense){
		this.baseSpDefense = baseSpDefense;
		return baseSpDefense;
	}
	public int setHp(int hp){
		this.hp = hp;
		return hp;
	}
	public int setBaseHp(int baseHp){
		this.baseHp = baseHp;
		return baseHp;
	}
	public double setEvasiveness(double evasiveness){
		this.evasiveness = evasiveness;
		return evasiveness;
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
		target.lowerHP(damage);
		return damage;
	}
	
	public abstract int primaryAttack(Character target);// Differentiates whether attack or spAttack should be used
	public abstract void specialize();
	public abstract void normalize();
	public static String about(){
		return "\nThe available classes are:\n" + 
				"(1) Warrior: has high hp, attack, and defense but low special attack, special defense, and evasiveness.\n" + 
				"(2) Mage: has high special attack, and special defense, but low hp, attack, defense, and moderate evasiveness.\n" +
				"(3) Ninja: has moderate hp, attack, defense, and special defense, and low special attack, but high evasiveness.\n" +
				"(4) Robot: has very high hp, moderate attack, special attack, defense, special defense, and very low evasiveness. Hits randomly with attack or special attack.\n" +  
				"(5) Gambler: has slightly below moderate hp, attack, defense, special defense, very high evasivenes. Attack damage is random and disregards defense,you may get lucky, you may not. " +
				"Gamblers get an extra 5 points per round.\n\n" +
				"Choose one of the above by entering its corresponding number.\n";
	}	
}

