//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Warrior extends Character{
	public Warrior(String name){
		this.name = name;
		hp = 125;
		attack = 70;
		spAttack = 30;
		defense = 40;
		spDefense = 25;
		evasiveness = 3;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		return attack(target);
	}
	public void specialize(){
		attack = 150;
		defense = 20;
	}
	public void normalize(){
		attack = 100;
		defense = 40;
	}

}
