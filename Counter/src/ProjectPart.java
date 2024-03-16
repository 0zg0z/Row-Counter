import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class ProjectPart {
	private int counter;
	private List<Reminder> reminders;
	private String name;
	public String description;
	
	public ProjectPart() 
	{
		counter = 0;
		reminders = new ArrayList<Reminder>();
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void addReminder(int start, int finish, String text) throws IOException 
	{
		Reminder rem = new Reminder(start,finish,text);
		reminders.add(rem);
	}
	
	public int incCounter() 
	{
		counter++;
		return counter;
	}
	public int decCounter() 
	{
		if(counter > 0)
			counter--;
		return counter;
	}
	public int getCounter() 
	{
		return counter;
	}
	public void setCounter(int count) 
	{
		counter = count;
	}
	
	public Reminder getReminder(int row) 
	{
		for(Reminder reminder: reminders) 
		{
			if(reminder.isIn(row)) 
			{
				return reminder;
			}
		}
		
		return null;
	}
	public List<Reminder> getReminderList() 
	{
		return reminders;
	}
	
}
