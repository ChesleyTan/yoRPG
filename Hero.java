public class Hero extends Character{
	public Hero(String name, int attack, int spAttack, int defense, int spDefense){
		this.name = name;
		hp = 500;
		baseHp = hp;
		this.attack = attack;
		baseAttack = this.attack;
		this.spAttack = spAttack;
		baseSpAttack = this.spAttack;
		this.defense = defense;
		baseDefense = this.defense;
		this.spDefense = spDefense;
		baseSpDefense = this.spDefense;
		evasiveness = 10;
		multiplier = 2.0;
	}
	public int primaryAttack(Character target){
		if ((int) (2 * Math.random()) == 0)
			return attack(target);
		else
			return spAttack(target);
	}
	public void specialize(){
		attack = baseAttack + 20;
		spAttack = baseSpAttack + 20;
		defense = baseDefense - 20;
		spDefense = baseSpDefense - 20;
	}
	public void normalize(){
		attack = baseAttack;
		spAttack = baseSpAttack;
		defense = baseDefense;
		spDefense = baseSpDefense;
	}
	public static Character transform(Character c){
		c = new Hero(c.getName(),c.getAttack(),c.getSpAttack(),c.getDefense(),c.getSpDefense());
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Your body starts to glow a bright bluish-white as your enemy stands petrified. \n" + 
			"Your body is becoming larger and more imposing...\n" +
			"Your eyes glow a dark red...\n" + 
			"Congratulations, you've attained your ultimate power, your Hero stage.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return c;
	}
}

