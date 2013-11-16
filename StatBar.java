public class StatBar{
	public static String getHpBar(Character c){
		int stars = c.getHp() / c.getBaseHp() * 100;
		return starrify(stars);
	}
	public static String stringMultiply(String s, int n){
		int i = 0;
		String retStr = "";
		while (i < n){
			retStr += s;
		}
		return retStr;
	}
	public static String starrify(int stars){
		return stringMultiply("*",stars) + stringMultiply("-",100-stars);
	}
}
