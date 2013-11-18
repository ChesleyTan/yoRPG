// Chesley Tan, Johnathan Yan, Christopher Kim
// Pd 9
// HW26
// 2013-11-17
public class Ninja extends Character{
	public Ninja(String name){
		this.name = name;
		hp = 110;
		baseHp = hp;
		attack = 55;
		baseAttack = attack;
		spAttack = 30;
		baseSpAttack = spAttack;
		defense = 35;
		baseDefense = defense;
		spDefense = 35;
		baseSpDefense = spDefense;
		evasiveness = 25;
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

