//Chesley Tan
//pd 9
//HW#26
//2013-11-14
public class Monster extends Character{
	public Monster(){
		hp = 150;
		baseHp = hp;
		attack = (int) (25 * Math.random()) + 40;
		baseAttack = attack;
		spAttack = (int) (25 * Math.random()) + 40;
		baseSpAttack = spAttack;
		defense = 20;
		baseDefense = defense;
		spDefense = 20;
		baseSpDefense = spDefense;
		evasiveness = 4;
		multiplier = 1;
	}
	public Monster(int difficulty, int round){
		this();
		if (difficulty == 2){
			multiplier += (round * 2.0 / 100.0);
			defense += (round * 2);
			baseDefense = defense;
			spDefense += (round * 2);
			baseSpDefense = spDefense;
		}
		else if (difficulty == 3){
			multiplier += (round * 3.0 / 100.0);
			defense += (round * 3);
			baseDefense = defense;
			spDefense += (round * 3);
			baseSpDefense = spDefense;
		}
		else {
			defense += (round);
			baseDefense = defense;
			spDefense += (round);
			baseSpDefense = spDefense;
		}
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
