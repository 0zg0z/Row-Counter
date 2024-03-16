import java.awt.EventQueue;

import java.io.FileWriter;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JRadioButton;

public class addProjectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	int i;
	private final int PART_GAP = 100;
	private JTextField every_row_text;
	private JTextField length_text;
	private JTextField single_row_text;
	private JTextField multi_start_text;
	private JTextField multi_finish_text;
	private JTextField until_row_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProjectFrame frame = new addProjectFrame();
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
	public addProjectFrame() {
		i= 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Project");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 330, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(10, 41, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(69, 38, 186, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
	
		JTextField[] PartNameTextFields=new JTextField[20];
		JTextArea[] descriptionsTextFields = new JTextArea[20];
		ProjectPart[] Parts=new ProjectPart[20];
		
		JButton addPartButton = new JButton("Add Part");
		addPartButton.setBounds(10, 95, 155, 21);
		addPartButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
						
						
						JLabel addPartText = new JLabel("Part Name: ");
						addPartText.setBounds(10, 63 + i * PART_GAP, 70, 13);
						contentPane.add(addPartText);
						
						JTextField nametextField = new JTextField();
						nametextField.setBounds(90, 63 + i * PART_GAP, 205, 19);
						contentPane.add(nametextField);
						
						JLabel descriptionLabel = new JLabel("Description:");
						descriptionLabel.setBounds(10, 82 + i * PART_GAP, 60, 13);
						contentPane.add(descriptionLabel);
						
						JTextArea descriptionText = new JTextArea();
						JScrollPane scroll = new JScrollPane(descriptionText);
						scroll.setBounds(90, 87 + i * PART_GAP, 186, 60);
						contentPane.add(scroll);
						PartNameTextFields[i + 1] = nametextField;
						descriptionsTextFields[i + 1] = descriptionText;
						JButton addReninder = new JButton("Add Reminder");
						addReninder.setBounds(300, 63 + i * PART_GAP, 70, 13);
						addReninder.addActionListener(new ActionListener(){  
							public void actionPerformed(ActionEvent e){
							
								    ProjectPart part;
									if(Parts[i] != null) {
										 part = Parts[i];
									}
									else {
									 part = new ProjectPart();
										Parts[i] = part;
									}
									
									part.setName(nametextField.getText());
									part.description = descriptionText.getText();
									
									addReminders(part);
									
									
								}
										
							        
							    });
						contentPane.add(addReninder);
						
						
						addPartButton.setBounds(10, 160 + i * PART_GAP, 155, 21);
						JPanel panel = new JPanel();
						panel.setBackground(new Color(175, 238, 238));
						panel.setBounds(10, 63 + i * PART_GAP, 426, 80);
						contentPane.add(panel);
						setSize(450, 300);
						revalidate();
						repaint();
						i++;
			        }  
			    });
		
		contentPane.add(addPartButton);
		
		JButton FinishButton = new JButton("Add Project ");
		FinishButton.setBounds(313, 10, 113, 44);
		FinishButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
					List<ProjectPart> partList = new ArrayList();
					for(int i = 1; i < 20; i++) {
						if(PartNameTextFields[i] != null) {
							if(Parts[i] != null) {
								partList.add(Parts[i]);
							}
							else {
								ProjectPart p = new ProjectPart();
								p.setName(PartNameTextFields[i].getText());
								p.description = descriptionsTextFields[i].getText();
								partList.add(p);
							}
						}
					}
					try {
						Project newProject = new Project(textField.getText(), partList);
						String filename= "./src/projects.txt";
					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					    fw.write("\n" + newProject.name );//appends the string to the file
					    fw.close();
					    newProject.saveData();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			        }  
					
			
			    });
		contentPane.add(FinishButton);
	

	}
	public void addReminders(ProjectPart part) {
		setSize(800,374);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(216, 191, 216));
		panel.setBounds(436, 0, 350, 337);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton every_row_sel = new JRadioButton("Every");
		every_row_sel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		every_row_sel.setBackground(new Color(216, 191, 216));
		every_row_sel.setBounds(5, 50, 57, 21);
		panel.add(every_row_sel);
		
		every_row_text = new JTextField();
		every_row_text.setBounds(60, 52, 38, 19);
		panel.add(every_row_text);
		every_row_text.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Row, Length");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(108, 50, 85, 21);
		panel.add(lblNewLabel_2);
		
		length_text = new JTextField();
		length_text.setBounds(186, 52, 43, 19);
		panel.add(length_text);
		length_text.setColumns(10);
		
		JRadioButton single_row_sel = new JRadioButton("Single Row:");
		single_row_sel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		single_row_sel.setBackground(new Color(216, 191, 216));
		single_row_sel.setBounds(5, 86, 93, 21);
		panel.add(single_row_sel);
		
		single_row_text = new JTextField();
		single_row_text.setBounds(98, 88, 57, 19);
		panel.add(single_row_text);
		single_row_text.setColumns(10);
		
		JRadioButton multi_row_sel = new JRadioButton("Multiple Row: ");
		multi_row_sel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		multi_row_sel.setBackground(new Color(216, 191, 216));
		multi_row_sel.setBounds(5, 120, 107, 21);
		panel.add(multi_row_sel);
		
		multi_start_text = new JTextField();
		multi_start_text.setBounds(108, 122, 47, 19);
		panel.add(multi_start_text);
		multi_start_text.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("to");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(165, 120, 28, 21);
		panel.add(lblNewLabel_2_1);
		
		multi_finish_text = new JTextField();
		multi_finish_text.setBounds(185, 122, 44, 19);
		panel.add(multi_finish_text);
		multi_finish_text.setColumns(10);
		
		JLabel lblNewLabel_2_2 = new JLabel("Text:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(13, 163, 85, 21);
		panel.add(lblNewLabel_2_2);
		
		JTextArea ReminderText = new JTextArea();
		JScrollPane rscroll = new JScrollPane(ReminderText);
		rscroll.setBounds(13, 190, 186, 60);
		panel.add(rscroll);
		ButtonGroup bg=new ButtonGroup();
		bg.add(multi_row_sel);
		bg.add(every_row_sel);
		bg.add(single_row_sel);
		
		JButton NewReminder = new JButton("Add Reminder");
		NewReminder.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				    if(multi_row_sel.isSelected()) {
				    	try {
				    		
							part.addReminder(Integer.parseInt(multi_start_text.getText()), Integer.parseInt(multi_finish_text.getText()), ReminderText.getText());
							System.out.print("multiple row selected");
				    	} catch (NumberFormatException | IOException err) {
							
						}
				    }
				    else if(single_row_sel.isSelected()) {
				    	try {
							part.addReminder(Integer.parseInt(single_row_text.getText()), Integer.parseInt(single_row_text.getText()), ReminderText.getText());
						} catch (NumberFormatException | IOException err) {
							
						}
				    }
				    else if(every_row_sel.isSelected()) {
				    	
				    	try {
				    		int fark = Integer.parseInt(every_row_text.getText());
				    		int kal = Integer.parseInt(length_text.getText());
				    		int until = Integer.parseInt(until_row_text.getText());
				    		
				    		for(int i = 1; i <= until;i++  ) {
				    			if(i % (kal + fark) == fark + 1) {
				    				part.addReminder(i, i + kal - 1 , ReminderText.getText());
				    			}
				    		}
						} catch (NumberFormatException err) {
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				    else {
				    	
				    }
				    JPanel Sucsess_panel = new JPanel();
					Sucsess_panel.setBackground(new Color(127, 255, 0));
					Sucsess_panel.setBounds(5, 10, 235, 28);
					panel.add(Sucsess_panel);
					Sucsess_panel.setLayout(null);
					
					JLabel lblNewLabel_3 = new JLabel("Added Succsessfully" + part.getReminderList().size() + "reminders");
					lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_3.setBounds(10, 0, 293, 24);
					Sucsess_panel.add(lblNewLabel_3);
					revalidate();
					repaint();
					
			        }  
			    });
		NewReminder.setBounds(165, 277, 107, 21);
		panel.add(NewReminder);
		
		
		
		JLabel lblNewLabel_2_3 = new JLabel("until");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_3.setBounds(233, 50, 28, 21);
		panel.add(lblNewLabel_2_3);
		
		until_row_text = new JTextField();
		until_row_text.setBounds(264, 52, 38, 19);
		panel.add(until_row_text);
		until_row_text.setColumns(10);
		
		JLabel lblNewLabel_2_3_1 = new JLabel(". row");
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_3_1.setBounds(301, 50, 39, 21);
		panel.add(lblNewLabel_2_3_1);
	}
	
}
