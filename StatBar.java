public class StatBar{
	public static String getHpBar(Character c){
		int stars = (int) ((c.getHp() * 1.0 / c.getBaseHp() * 100.0)/5);
		int pct = (int)((c.getHp() * 1.0) / c.getBaseHp() * 100.0);
		//System.out.println("c.getHp(): " + c.getHp());
		//System.out.println("c.getBaseHp()" + c.getBaseHp());
		//System.out.println("Stars: " + stars);
		if (stars == 0 && c.getHp() > 0) stars = 1;
		if (stars < 0) stars = 0;
		if (pct == 0  && c.getHp() > 0) pct = 1; 
		if (pct < 0) pct = 0;
		return starrify(stars, pct);
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
	
	
	public static String starrify(int stars, int pct){
		return pct + "% \t|" + stringMultiply("x",stars) + stringMultiply("-",20-stars) + "|";
	}
}
