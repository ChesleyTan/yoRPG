//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Balrog extends Character{
	public Balrog(){
		hp = 185;
		baseHp = hp;
		attack = (int) (25 * Math.random()) + 65;
		baseAttack = attack;
		spAttack = (int) (25 * Math.random()) + 65;
		baseSpAttack = spAttack;
		defense = 30;
		baseDefense = defense;
		spDefense = 30;
		baseSpDefense = spDefense;
		evasiveness = 5;
		multiplier = 1.5;
	}
	
	public int primaryAttack(Character target){
		if ((int) (2 * Math.random()) == 0)
			return attack(target);
		else
			return spAttack(target);
	}
	public void normalize(){}
	public void specialize(){}
}
