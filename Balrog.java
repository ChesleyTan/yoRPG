//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Balrog extends Character{ // "Boss stages for our game, Boss named Balrog
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
	public Balrog(int difficulty, int round){
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
