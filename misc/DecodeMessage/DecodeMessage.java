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
 
 */

package misc;

public class DecodeMessage {
	public static final int SIZE = 26;
	public static int totalMessages(int[] mapping, String data) {
		if (data.length() == 0)
			return 1;
		char start = data.charAt(0);
		if (start == '0')
			return 0;
		int x = totalMessages(mapping, data.substring(1));
		int y = 0;
		if (data.length() >= 2 ) {
			int valOfTwoDigits = Integer.valueOf(data.substring(0, 2));
			if (valOfTwoDigits <= 26)
				y = totalMessages(mapping, data.substring(2));
		}
			
		return x+y;
	}
	
	/*
	 * In top down approach we store the length of data in table and the corresponding number of precomputed messages.
	 * */
	public static int totalMessagesTopDownUtl(int[] mapping, String data) {
		int[] table = new int[data.length()+1];
		table[0] = 1;   //base case
		return totalMessagesTopDownUtl(mapping, data, table);
	}
	public static int totalMessagesTopDownUtl(int[] mapping, String data, int[] table) {
		if (data.length() == 0)
			return 1;
		if (table[data.length()] != 0)
			return table[data.length()];    //return precomputed value
		
		char start = data.charAt(0);
		if (start == '0')
			return 0;
		int x = totalMessages(mapping, data.substring(1));
		int y = 0;
		if (data.length() >= 2)
			y = totalMessages(mapping, data.substring(2));
		
		table[data.length()] = x+y;
		return x+y;
	}
	
	
	
	public static void main (String [] args) {
		int []mapping = new int[SIZE+1];
		for (int i=1; i<=SIZE; i++) {
			mapping[i] = i;
		}
		String data = "273";
		System.out.println(totalMessages(mapping, data));
		System.out.println(totalMessagesTopDownUtl(mapping, data));
	}
}
