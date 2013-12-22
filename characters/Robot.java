// Chesley Tan, Johnathan Yan, Christopher Kim
// Pd 9
// HW26
// 2013-11-17
package characters;
public class Robot extends Character{ // new Robot subclass for Character superclass
	public Robot(String name){
		this.name = name;
		hp = 200;
		baseHp = hp;
		attack = 30;
		baseAttack = attack;
		spAttack = 50;
		baseSpAttack = spAttack;
		defense = 40;
		baseDefense = defense;
		spDefense = 40;
		baseSpDefense = spDefense;
		evasiveness = 1;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		if ((int) (2 * Math.random()) == 0)
			return attack(target);
		else
			return spAttack(target);
	}
	public void specialize(){
		attack = baseAttack + 40;
		spAttack = baseSpAttack + 40;
		defense = baseDefense - 30;
		spDefense = baseSpDefense - 30;
	}
	public void normalize(){
		attack = baseAttack;
		spAttack = baseSpAttack;
		defense = baseDefense;
		spDefense = baseSpDefense;
	}
}

