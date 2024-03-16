
public class Reminder {
	int start;
	int finish;
	String text;
	
	public Reminder(int s, int f, String t) 
	{
		start = s;
		finish = f;
		text = t;
	}
	
	public boolean isIn(int i) 
	{
		if(i >= start && i <= finish)
			return true;
		return false;
	}
}
