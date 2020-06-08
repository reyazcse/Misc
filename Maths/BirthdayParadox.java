/*
Question:
	What is the minimum no of people required in a room so that there is a 50% chance of two people having the same birthday

Solution:
	For two persons not having bday on same day, prob = (365/365) * (364/365)
	For two persons having bday on same day, prob = 1 - (365/365) * (364/365)
	For n persons having bday on same day,prob = 1  - (365/365) * (364/365) * (363/365) * .....and so on till n terms
											   = 1 - P
	So we want (1 - P ) >= 0.5 which means P <= 0.5
	So while P > 0.5, we keep subtracting prob  as shown in the code below	
	When i = 23, we have P = P: 0.4927027656760144
	So no of people to have prob at least 50 percent is 23 (bcoz on i=23, we have P = P: 0.4927027656760144 and hence 1-P is at least 50 pc)


 * */

package misc;

public class BirthdayParadox {
	public int atLeastFiftyPC() {
		int i = 1;						//i is no of person
		double P = 365/365;
		while (P > .5) {
			P *= ((double)(365-i)/365);
			i++;
		}
		return i;
	}
	public static void main(String[] args) {
		BirthdayParadox obj = new BirthdayParadox();
		System.out.println(obj.atLeastFiftyPC());			//ans is 23

	}

}
