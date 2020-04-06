import java.text.DecimalFormat;
class BabyName implements Comparable
{
	String name;
	boolean gender;
	int count, birthYear;
	BabyName (String n, boolean g, int count , int y)
	{
		this.name = n;
		this.gender = g;
		this.count = count;
		this.birthYear = y;
	}
	boolean isFemale ()
	{
		if (gender)
			return true;
		else
			return false;
	}
	String getName()
	{
		return name;
	}
	int getCount ()
	{
		return count;
	}
	int getBirthYear ()
	{
		return birthYear;
	}
	void setName(String n)
	{
		this.name = n;
	}
	void setGender (boolean g)
	{
		this.gender = g;
	}
	void setCount (int count)
	{
		this.count = count;
	}
	void setBirthYear (int birthYear)
	{
		this.birthYear = birthYear;
	}
	public String toString ()
	{
		DecimalFormat df = new DecimalFormat ("###,###,###");
		if (gender)
		return (df.format(count) + " girls named " + name + " in " + birthYear );
		else
		return (df.format(count) + " boys named " + name + " in " + birthYear );
	}
	public int compareTo(Object other)
	{
		BabyName otherBaby = (BabyName) other;
		return (otherBaby.count -  this.count);
	}

	public static void main(String[] args) {
		BabyName babyName = new BabyName ("Jessica",true,46473,1990);
		System.out.println (babyName.toString());
	}
}