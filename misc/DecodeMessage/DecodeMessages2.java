//quesion: src: https://www.youtube.com/watch?v=qli-JCrSwuk
/*
 How Many Ways to Decode This Message?
 Mapping is as follows:
 a -> 1
 b -> 2
 ......
 z -> 26
 Input will be like this: data = "123"
 Output: 5
 12 -> 'a' and 'ab'
 01 -> no result (0 message)
 271 -> 1
 
 */



package misc;

public class DecodeMessages2 {
	
	//recursive soln
	public static int totalMessages(String data) {
		return utl(data, data.length());
	}
	public static int utl(String data, int k) {
		if (k == 0) return 1;
		if(data.charAt(data.length()-k) == '0') return 0;
		int x = utl(data, k-1);
		int y = 0;
		String remainingString = data.substring(data.length() - k, data.length());
		if (remainingString.length() >= 2 ) {
			Integer value = Integer.valueOf(remainingString.substring(0, 2));
			if (value <=26)
				y = utl(data, k-2);
			
		}
		return x+y;
	}
	
	//dynamic programming top down
	public static int topDown(String data) {
		int [] table = new int[data.length()+1];
		topDownUtl(data, data.length(), table);
		return table[data.length()];
	}
	public static int topDownUtl (String data, int k, int[] table) {
		if (k == 0) return 1;
		if(data.charAt(data.length()-k) == '0') return 0;
		if (table[k] != 0) return table[k];
		int x = utl(data, k-1);
		int y = 0;
		String remainingString = data.substring(data.length() - k, data.length());
		if (remainingString.length() >= 2 ) {
			Integer value = Integer.valueOf(remainingString.substring(0, 2));
			if (value <=26)
				y = utl(data, k-2);
			
		}
		table[k] = x+y;
		return table[k];
	}
	
	public static void main (String [] args) {
		System.out.println(totalMessages("123"));
		System.out.println(topDown("123"));
	}
}
