//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Mage extends Character{
	public Mage(String name){
		this.name = name;
		hp = 95;
		attack = 30;
		spAttack = 80;
		defense = 25;
		spDefense = 50;
		evasiveness = 50;
		multiplier = 1.0;
	}
	public int primaryAttack(Character target){
		return spAttack(target);
	}
	public void specialize(){
		multiplier = 2.5;
		defense = 20;
	}
	public void normalize(){
		multiplier = 2.0;
		defense = 25;
	}

}
