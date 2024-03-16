import java.io.File;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Project {
	private List<ProjectPart> parts;
	public String name;
	private int currPart;
	private int totalPart;
	
	public Project(String n) throws NumberFormatException, IOException {
		name = n;
		currPart = 0;
		parts = new ArrayList<>();
		totalPart = 0;
		getData();

	}
	public Project(String n,List<ProjectPart> part) throws NumberFormatException, IOException {
		name = n;
		parts = part;
		currPart = 0;
		totalPart = part.size();

	}
	

	public ProjectPart getCurrentPart() {
		return parts.get(currPart);
	}
	public void nextPart() {
		currPart++;
	}
	public void previousPart() {
		currPart--;
	}
	
	public int getPartStatus() {
		if(totalPart - 1 == 0) {  //sol sağ yok
			System.out.print("\n 0");
			return 0;
		}
		if(currPart == 0) {  // sol yok sağ var
			System.out.print("\n 1");
			return 1;
		}
		else if (currPart == totalPart - 1) {  // sol var sağ yok
			System.out.print("\n 2");
			return 2;
		}
		else {                     //ikisi de var
			System.out.print("\n" + totalPart + "   "+ currPart);
			System.out.print("\n 3");
			return 3;
		}
		
	}
	public void getData() throws NumberFormatException, IOException {
		 try {
		      File myObj = new File("./src/"+name+".txt");
		      Scanner myReader = new Scanner(myObj);
		      if(myReader.hasNextLine()) {
		    	  currPart = myReader.nextInt();
		    	  System.out.print(currPart);
		      }
		      int partCount = 0;
		      while (myReader.hasNextLine()) {
		    	String desc = "";
		        String data = myReader.nextLine();
		        char[] dataArr = data.toCharArray();
		        if(data != "" && dataArr[0] == '#') {
		        totalPart++;
		        System.out.print(totalPart + "  ");
		        ProjectPart part = new ProjectPart();
		        partCount++;
		        part.setName(data.split("#")[1]);
		        part.setCounter(myReader.nextInt());  //burada hata veriyor
		        parts.add(part);
		        
		        }
		        else if(data != "" && dataArr[0] == '*') {
		        	String line = myReader.nextLine();
		        	 char[] lineArr = line.toCharArray();
		        	while(myReader.hasNextLine() && lineArr[0] != '*') {
		        		desc += "\n" + line;
		        		line = myReader.nextLine();
		        		lineArr[0] = line.toCharArray()[0];
		        	}
		        	
		        	ProjectPart part = parts.get(partCount-1);
		        	part.description = desc;
		        }
		        else if(data != "" ) {
		        	ProjectPart part = parts.get(partCount-1);
		        	String[] remArr = data.split("-");
			        part.addReminder(Integer.parseInt(remArr[0]),Integer.parseInt(remArr[1]),remArr[2]);
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
	}
	
	public void saveData() {
		 try {
		      FileWriter myWriter = new FileWriter("./src/"+name+".txt");
		      
		      myWriter.write(currPart + "");
		      for(ProjectPart part: parts) 
		      {
		    	  myWriter.write("\n#" + part.getName());
		    	  myWriter.write("\n" + part.getCounter());
		    	  myWriter.write("\n***");
		    	  myWriter.write("\n" + part.description);
		    	  myWriter.write("\n***");
		    	  List<Reminder> reminders = part.getReminderList();
		    	  for(Reminder reminder: reminders) 
					{
						myWriter.write("\n" + reminder.start + "-" + reminder.finish + "-" + reminder.text);
					}
		      }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void Finished() {
		 try {
			 File myObj = new File("./src/projects.txt");
				Scanner myReader = new Scanner(myObj);
				String[] Projectnames = new String[20];
				int i = 0;
				while(myReader.hasNextLine()) {
					String data = myReader.nextLine();
					if(!data.equals(name)) {
						Projectnames[i] = data;
						i++;
					}
				}
		      FileWriter myWriter = new FileWriter("./src/projects.txt");
		      for(int m = 0 ; m < i ; m++ ) {
		    	  myWriter.write(Projectnames[m] + "\n");
		      }
		      myWriter.close();
		      
		   	  String filename= "./src/finishedProjects.txt";
		      FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		      fw.write("\n" + name );//appends the string to the file
		      fw.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
}
