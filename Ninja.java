public class Ninja extends Character{
	public Ninja(String name){
		this.name = name;
		hp = 100;
		baseHp = hp;
		attack = 50;
		baseAttack = attack;
		spAttack = 30;
		baseSpAttack = spAttack;
		defense = 30;
		baseDefense = defense;
		spDefense = 30;
		baseSpDefense = spDefense;
		evasiveness = 20;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		return attack(target);
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

