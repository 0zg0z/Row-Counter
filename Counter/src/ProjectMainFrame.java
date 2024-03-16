import java.awt.Component;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.util.List;
import java.awt.event.*;  
import javax.swing.*;

import java.awt.FlowLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class ProjectMainFrame extends JFrame {
	private ProjectPart pro;
	private JPanel contentPane;
	private ProjectPart currentPart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectMainFrame frame = new ProjectMainFrame(new Project("proje1"));
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
	public ProjectMainFrame(Project p) {
		pro = p.getCurrentPart();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	System.out.print("rarararrara");//burada dosyaya yazdırmayı yap
            	p.saveData();
            	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
		setBounds(100, 100, 678, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(pro.getCounter() + "");
		lblNewLabel.setBounds(180, 45, 120, 54);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setBounds(56, 45, 77, 57);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
			            lblNewLabel.setText(pro.decCounter() + "");  
			        }  
			    });
		
		contentPane.setFocusable(true);
		contentPane.addKeyListener(getShortcutKeyListener(pro,lblNewLabel));
		JPanel reminderpanel = new JPanel();
		JLabel reminderText = new JLabel("");
		reminderText.setBounds(78, 137, 126, 20);
		if(pro.getReminder(pro.getCounter()) != null) {
			
			reminderpanel.setBackground(SystemColor.activeCaption);
			reminderpanel.setBounds(21, 135, 570, 32);
			
			reminderText.setText(pro.getReminder(pro.getCounter()).text); 
		}
		

		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(323, 45, 97, 57);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
			            lblNewLabel.setText(pro.incCounter() + "");  
			            if(pro.getReminder(pro.getCounter()) != null && pro.getReminder(pro.getCounter()).start == pro.getCounter() ) 
			            {
			            	showReminderFrame f2 = new showReminderFrame(pro.getReminder(pro.getCounter()));
			            	reminderText.setText(pro.getReminder(pro.getCounter()).text);
			            	f2.setVisible(true);
			            	f2.setDefaultCloseOperation(f2.DISPOSE_ON_CLOSE);
			            	
			            }
			            else if(pro.getReminder(pro.getCounter()) != null) 
			            {
			            	
			            }
			        }  
			    });
		
		JButton addReminder = new JButton("add Reminder");
		addReminder.setBounds(443, 75, 97, 21);
		addReminder.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
						AddReminderFrame f = new AddReminderFrame(pro);
						f.setVisible(true);
			        }  
			    });
		
		if(p.getPartStatus() == 1 || p.getPartStatus() == 3) {
			JButton nextPartButton = new JButton("Next Part");
			nextPartButton.setBounds(275, 150, 97, 21);
			nextPartButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
							p.nextPart();
							ProjectMainFrame f = new ProjectMainFrame(p);
							f.setVisible(true);
							setVisible(false);
				        }  
				    });
			contentPane.add( nextPartButton);
		}
		
		if(p.getPartStatus() > 1 ) {
		JButton previousPartButton = new JButton("Previous Part");
		previousPartButton.setBounds(50, 150, 97, 21);
		previousPartButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
						p.previousPart();
						ProjectMainFrame f = new ProjectMainFrame(p);
						f.setVisible(true);
						setVisible(false);
			        }  
			    });
		contentPane.add(previousPartButton);
		}
		contentPane.setLayout(null);
		contentPane.add(reminderText);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton);
		contentPane.add(addReminder);
		contentPane.add(reminderpanel);
		JLabel partNameLabel = new JLabel(pro.getName());
		partNameLabel.setBounds(21, 10, 45, 13);
		contentPane.add(partNameLabel);
		
		JButton denemButton = new JButton("denem");
		denemButton.setBounds(476, 351, 85, 21);
		denemButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
						JLabel denem = new JLabel("nksdnkndsjnsan \n sjkjasdahskd");
						denem.setBounds(476, 321, 85, 21);
						contentPane.add(denem);
						revalidate();
						repaint();
			        }  
			    });
		contentPane.add(denemButton);
		
		/*
		JTextPane lblNewLabel_1 = new JTextPane();
		lblNewLabel_1.setText(	pro.description);
	
		lblNewLabel_1.setBounds(37, 247, 315, 32);
		contentPane.add(lblNewLabel_1);
		*/

		String formatted = pro.description.replace("\n", "<br>");
		formatted = "<html><font size='4'>" + formatted + "</font></html>";
		JLabel label = new JLabel(formatted);
		JScrollPane scroll = new JScrollPane(label);
		scroll.setBounds(37, 200, 315, 200);
		contentPane.add(scroll);
		
		JButton finishButton = new JButton("finished");
		finishButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
						p.Finished();
			        }  
			    });
		finishButton.setBounds(476, 382, 85, 21);
		contentPane.add(finishButton);
		
	
	}

	

	public static KeyListener getShortcutKeyListener(ProjectPart p, JLabel lblNewLabel ) {
	    KeyListener listener = new KeyListener() {

	        @Override
	        public void keyReleased(KeyEvent evt) {
	        	 if (evt.getKeyCode() == KeyEvent.VK_1) {
		                //System.out.print("oldu0");
		            } else if (evt.getKeyCode() == KeyEvent.VK_2) {
		            	//System.out.print("oldu1");
		            } 
	        	
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	        	 if (e.getKeyCode() == KeyEvent.VK_1) {
		               // System.out.print("oldu2");
		            } else if (e.getKeyCode() == KeyEvent.VK_2) {
		            	//System.out.print("oldu3");
		            } 
	        	 
	        }

	        @Override
	        public void keyPressed(KeyEvent evt) {
	        	 if (evt.getKeyCode() == KeyEvent.VK_1) {
		                System.out.print("oldu4");
		                lblNewLabel.setText(p.incCounter() + "");  
			            if(p.getReminder(p.getCounter()) != null && p.getReminder(p.getCounter()).start == p.getCounter() ) 
			            {
			            	showReminderFrame f2 = new showReminderFrame(p.getReminder(p.getCounter()));
			            	
			            	f2.setVisible(true);
			            	f2.setDefaultCloseOperation(f2.DISPOSE_ON_CLOSE);
			            }
			            else if(p.getReminder(p.getCounter()) != null) 
			            {
			            	
			            }
		                
		            } else if (evt.getKeyCode() == KeyEvent.VK_2) {
		            	System.out.print("oldu5");
		            } 
	        	
	        }
	    };
	    return listener;
	}
}
