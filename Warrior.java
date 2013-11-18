// Chesley Tan, Johnathan Yan, Christopher Kim
// Pd 9
// HW26
// 2013-11-17
public class Warrior extends Character{
	public Warrior(String name){
		this.name = name;
		hp = 125;
		baseHp = hp;
		attack = 70;
		baseAttack = attack;
		spAttack = 30;
		baseSpAttack = spAttack;
		defense = 40;
		baseDefense = defense;
		spDefense = 25;
		baseSpDefense = spDefense;
		evasiveness = 3;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		return attack(target);
	}
	public void specialize(){
		attack = baseAttack + 50;
		defense = baseDefense - 20;
		spDefense = baseSpDefense - 20;
	}
	public void normalize(){
		attack = baseAttack;
		defense = baseDefense;
		spDefense = baseSpDefense;
	}

}
