//Chesley Tan
//pd 9
//HW#25
//2013-11-14
public class Monster extends Character{
	public Monster(){
		hp = 150;
		attack = (int) (25 * Math.random()) + 40;
		spAttack = 10;
		defense = 20;
		spDefense = 20;
		evasiveness = 4;
		multiplier = 1;
	}
	public int primaryAttack(Character target){
		return attack(target);
	}
	public void normalize(){}
	public void specialize(){}
}
