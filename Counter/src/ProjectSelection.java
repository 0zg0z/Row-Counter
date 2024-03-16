import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProjectSelection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectSelection frame = new ProjectSelection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjectSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel HeaderLabel = new JLabel("Welcome to Row Counter");
		HeaderLabel.setBounds(159, 10, 117, 13);
		contentPane.add(HeaderLabel);
		
		JLabel lblNewLabel = new JLabel("Pick a project");
		lblNewLabel.setBounds(10, 35, 68, 13);
		contentPane.add(lblNewLabel);
		

		JButton[] projects = new JButton[10];
		int m = 0;
		try {
			File myObj = new File("./src/projects.txt");
			Scanner myReader = new Scanner(myObj);
	
			while(myReader.hasNextLine()) {
				String name = myReader.nextLine();
				m = m + 30;
				JButton P = new JButton(name);
				P.setBounds(10 , 50 + m, 200, 20);
				P.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						 ProjectMainFrame frame;
						try {
							frame = new ProjectMainFrame(new Project(name));
							 frame.setVisible(true);
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					        }  
					    });
				contentPane.add(P);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JButton addNewProject = new JButton("Add New");
		addNewProject.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				addProjectFrame f = new addProjectFrame();
				f.setVisible(true);
				setVisible(false);
			        }  
			    });
		addNewProject.setBounds(331, 71, 85, 21);
		contentPane.add(addNewProject);
		
		
		
		JButton finishedProjectsButon = new JButton("finished Projects");
		finishedProjectsButon.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				setSize(800,374);
				JPanel panel = new JPanel();
				panel.setBounds(451, 10, 307, 317);
				contentPane.add(panel);
				int n = 0;
				try {
					File myObj = new File("./src/finishedProjects.txt");
					Scanner myReader = new Scanner(myObj);
			
					while(myReader.hasNextLine()) {
						String name = myReader.nextLine();
						if (name != "") {
				//		n = n + 30;
						JButton P = new JButton(name);
					//	P.setBounds(10 , 50 + m, 200, 20);
						P.addActionListener(new ActionListener(){  
							public void actionPerformed(ActionEvent e){
								 ProjectMainFrame frame;
								try {
									frame = new ProjectMainFrame(new Project(name));
									 frame.setVisible(true);
								} catch (NumberFormatException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								 
							        }  
							    });
						panel.add(P);
						}
					}
				} catch (FileNotFoundException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
				
			        }  
			    });
		finishedProjectsButon.setBounds(331, 218, 85, 21);
		contentPane.add(finishedProjectsButon);
		
		
	}
}
