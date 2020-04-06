import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
class BabyNamesDatabase
{
	ArrayList<BabyName> al;
	BabyNamesDatabase ()
	{
		al = new ArrayList<>();
	}
	public void readBabyNameData(String filename){
		try
		{
			FileInputStream file = new FileInputStream(filename);
			Scanner sc = new Scanner (file);
			sc.useDelimiter("[,\r\n]+");
			while (sc.hasNext())
			{
				String name = sc.next();
				String gender = sc.next();
				int count = Integer.parseInt(sc.next());
				int year = Integer.parseInt(sc.next());
				boolean isFemale;
				if (gender.equalsIgnoreCase("f"))
				 isFemale = true;
				else
				 isFemale = false;
				BabyName babyName = new BabyName(name, isFemale, count, year);
				al.add(babyName);
			}
			file.close();
		}
		catch (IOException e)
		{
			System.out.println ("Failed to read the data file: "+ filename);
		}
	}
	public int countAllNames ()
	{
		return al.size();
	}
	public int countAllGirls ()
	{
		int count = 0;
		for (BabyName i:al)
		{
			if(i.isFemale())
				count+=i.count;
		}
		return count;
	}
	public int countAllBoys ()
	{
		int count =0;
		for (BabyName i:al)
		{
			if(!i.isFemale())
				count+=i.count;
		}
		return count;
	}
	public ArrayList <BabyName> searchForYear(int year)
	{
		ArrayList<BabyName> result = new ArrayList<>();
		for (BabyName i:al)
		{
			if (i.birthYear == year)
			{
				result.add(i);
			}
		}
		Collections.sort(result);
		return result;
	}
	public BabyName mostPopularGirl(int year)
	{
		ArrayList<BabyName> list = searchForYear(year);
		int maximum = Integer.MIN_VALUE;
		System.out.println ("Initial maximum value " + maximum);
		BabyName result = null;
		for (BabyName i:list)
		{
			if((i.count > maximum) && (i.isFemale()))
			{
				maximum = i.count;
				result = i;
			}
		}
		return result;
	}
	public BabyName mostPopularBoy(int year)
	{
		ArrayList<BabyName> list = searchForYear(year);
		int maximum = Integer.MIN_VALUE;
		BabyName result = null;
		for (BabyName i:list)
		{
			if((i.count > maximum) && (!i.isFemale()))
			{
				maximum = i.count;
				result = i;
			}
		}
		return result;
	}
	public ArrayList <BabyName> searchForName(String name)
	{
		ArrayList<BabyName> result = new ArrayList<>();
		for (BabyName i:al)
		{
			if ((i.name).equalsIgnoreCase(name))
			{
				result.add(i);
			}
		}
		return result;
	}
	public ArrayList <BabyName> topTenNames(int year) 
	{
		ArrayList<BabyName> result = searchForYear(year);
		if (result.size() == 0)
			return (new ArrayList<>(0));
		ArrayList<BabyName> newList = new ArrayList<>(10);
		for (int i=0;i<10;++i)
		{
			newList.add(result.get(i));
		}
		return newList;
	}
}