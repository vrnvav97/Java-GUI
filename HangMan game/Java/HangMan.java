//suppose the word to be guessed and the one which is entered by user is both lowercase
import java.util.*;
class HangMan
{
	int lives,dash;
	static Scanner sc;
	static List<Character> al;
	String word;
	static char filler[];
	HangMan ()
	{
		lives = 5;
		dash = 0;
		word = "gamers";
	}
	void arrayFiller ()
	{
		filler = word.toCharArray();
		int ptr = 0;
		for (char ch:filler)
		{
			if (ch==' ')
				filler[ptr] = ' ';
			else
			{
				filler[ptr] = '_';
				dash++;
			}
			ptr++;
		}
	}
	public static void main(String[] args) {
		HangMan hm = new HangMan();
		al = new ArrayList<>();
		hm.arrayFiller();
		char x = ' ';
		sc = new Scanner (System.in);
		while (hm.lives>0 && hm.dash>0)
		{
			System.out.println (String.valueOf(filler) + " " + "Number of lives remaining " + hm.lives + "\n");
			System.out.println ("Make a guess");
			try
			{
			 x = sc.nextLine().charAt(0);
			}
			catch (StringIndexOutOfBoundsException e)
			{
				System.out.println ("Enter a valid input");
				continue;
			}
			if (al.contains(x)) 
				continue;
			al.add(x);
			if (hm.word.contains(String.valueOf(x)))
			{
				System.out.println ("Your guess was right \n\n");
				char tempA[] = hm.word.toCharArray();
				for (int i=0;i<hm.word.length();++i)
				{
					if (tempA[i] == x)
					{
						filler[i] = x;
						--hm.dash;
					}
				}
			}
			else
			{
				System.out.println ("Oh! Your guess was wrong \n\n");
				hm.lives--;
			}
		}
		System.out.println (hm.word);
		if (hm.lives == 0)
			System.out.println ("------Game over------");
		else if (hm.dash == 0)
			System.out.println ("------You Won------");
	}
}