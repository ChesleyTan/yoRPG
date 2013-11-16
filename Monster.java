//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Monster extends Character{
	public Monster(){
		hp = 150;
		baseHp = hp;
		attack = (int) (25 * Math.random()) + 40;
		baseAttack = attack;
		spAttack = 10;
		baseSpAttack = spAttack;
		defense = 20;
		baseDefense = defense;
		spDefense = 20;
		baseSpDefense = spDefense;
		evasiveness = 4;
		multiplier = 1;
	}
	public int primaryAttack(Character target){
		return attack(target);
	}
	public void normalize(){}
	public void specialize(){}
}
