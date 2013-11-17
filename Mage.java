//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Mage extends Character{
	public Mage(String name){
		this.name = name;
		hp = 95;
		baseHp = hp;
		attack = 30;
		baseAttack = attack;
		spAttack = 80;
		baseSpAttack = spAttack;
		defense = 25;
		baseDefense = defense;
		spDefense = 50;
		baseSpDefense = spDefense;
		evasiveness = 5;
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
