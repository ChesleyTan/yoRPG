// Chesley Tan, Johnathan Yan, Christopher Kim
// Pd 9
// HW26
// 2013-11-17
package characters;
public class Mage extends Character{
	public Mage(String name){
		this.name = name;
		hp = 95;
		baseHp = hp;
		attack = 30;
		baseAttack = attack;
		spAttack = 80;
		baseSpAttack = spAttack;
		defense = 30;
		baseDefense = defense;
		spDefense = 60;
		baseSpDefense = spDefense;
		evasiveness = 10;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		return spAttack(target);
	}
	public void specialize(){
		spAttack = baseSpAttack + 40;
		defense = baseDefense - 10;
		spDefense = baseSpDefense - 10;
	}
	public void normalize(){
		spAttack = baseSpAttack;
		defense = baseDefense;
		spDefense = baseSpDefense;
	}

}
