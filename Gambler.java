public class Gambler extends Character{
	public Gambler(String name){
		this.name = name;
		hp = 100;
		baseHp = hp;
		attack = 55;
		baseAttack = attack;
		spAttack = 25;
		baseSpAttack = spAttack;
		defense = 25;
		baseDefense = defense;
		spDefense = 25;
		baseSpDefense = spDefense;
		evasiveness = 45;
		multiplier = 1.5;
	}
	
	public int gambleAttack(Character target){
		int damage = (int) (10 * Math.random()) + (int) (attack * multiplier * Math.random());
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
	
	public int primaryAttack(Character target){
		return gambleAttack(target);
	}
	public void specialize(){
		attack = baseAttack + 30;
		defense = baseDefense - 5;
		spDefense = baseSpDefense - 5;
	}
	public void normalize(){
		attack = baseAttack;
		defense = baseDefense;
		spDefense = baseSpDefense;
	}
}
