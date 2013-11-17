public class StatBar{
	public static String getHpBar(Character c){
		int stars = (int) (c.getHp() * 1.0 / c.getBaseHp() * 100.0);
		//System.out.println("c.getHp(): " + c.getHp());
		//System.out.println("c.getBaseHp()" + c.getBaseHp());
		//System.out.println("Stars: " + stars);
		if (stars < 0) stars = 0;
		return starrify(stars);
	}
	public static String stringMultiply(String s, int n){
		int i = 0;
		String retStr = "";
		while (i < n){
			retStr += s;
			i++;
		}
		return retStr;
	}
	public static String starrify(int stars){
		return stars + "% \t|" + stringMultiply("x",stars) + stringMultiply("-",100-stars) + "|";
	}
}
