
import java.io.FileReader;
import java.util.Scanner;


public class CityNamesHangManGame
{
    String arr[] = new String[1298];
    int lives = 3;
    String originalWord = "";
    int numberOfDash = 0;
    static char word [];
    public CityNamesHangManGame() {
        try {
            Scanner sc = new Scanner (new FileReader("cities-name-list.txt"));
            int i=0;
            while (sc.hasNext())
                arr[i++] = sc.next();
        } catch (Exception e) {
        }
    }
    
    String chooseWord()
    {
        int randomNumber = (int)(Math.random()*1298 + 0);
        String s = arr[randomNumber];
        s = s.substring(1,s.length()-2);
        originalWord = s;
        return s;
    }
    
    
    void filler(String s)
    {
        word = s.toCharArray();
        for (int i=0;i<word.length;++i)
        {
            if (i%2==0)
                word[i] = '_';
            else
                word[i] = word[i];
        }
    }
    
    void playGame()
    {
        Scanner sc = new Scanner (System.in);
        if (word.length %2== 0)
        {
            numberOfDash = word.length/2;
        }
        else
        {
//            numberOfDash = word.length/2+1;
        }
       // System.out.println("Number of Dash " + numberOfDash);
        System.out.println ("Welcome to Play Game");
        while (lives > 0 && numberOfDash > 0)
        {
            System.out.println("You have " + lives + " left");
            for (char i:word)
                System.out.print (i + " ");
            System.out.println ();
            System.out.println("Make a Guess");
            char guess = sc.nextLine().charAt(0);
            if (originalWord.contains(String.valueOf(guess)))
            {
                System.out.println ("Your guess was right");
                for (int i=0;i<originalWord.length();++i)
                {
                    char v = originalWord.charAt(i);
                    if (v == guess)
                    {
                        word[i] = guess;
                        numberOfDash--;
                    }
                }
                System.out.println("Dash left " + numberOfDash);
            }
            else
            {
                System.out.println ("Your guess was wrong. You loose a live");
                lives--;
                continue;
            }
        }
        if (numberOfDash == 0)
            System.out.println("You Won the Game");
        else
            System.out.println("You loose the Game");
    }
    
    public static void main(String[] args) {
        CityNamesHangManGame obj = new CityNamesHangManGame();
        String temp = obj.chooseWord();
        //System.out.println (temp);
        obj.filler(temp);
        obj.playGame();
    }
}