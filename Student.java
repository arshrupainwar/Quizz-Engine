import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;

class Student
{	
	static Frame frame;
	static Panel test,score,rank;
	static JButton b1,b2,b3,b4,sb1,rb1,tb1,tb2,tb3,tb4;
	static Label l1,l2,s1,s2,s3,s4,s5,s6,s7,s8,s9,r1,r2,r3,r4,r5,r6,r7,r8,r9;
	static JRadioButton jr1,jr2,jr3,jr4,jr5;
	static ButtonGroup Bg;
	static Label qno,ques,timer1,timer2;
	static int z = 1,result=0;
	static String answere;
	
	Student()
	{
		frame = new Frame();
		frame.setLayout(null);
		frame.setBackground( new Color(194, 215, 242));
		frame.setVisible(true);
		frame.setSize(1000,800);
		
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e1)
			{
				frame.dispose();
			}
		});
		
		l1 = new Label("Welcome");
		//l1.setBackground(Color.red);
		l1.setFont(new Font("Arial Rounded MT bold",1,45));
		l1.setBounds(400,300,200,60);
		frame.add(l1);
		
		l2 = new Label("Student Name");
		//l2.setBackground(Color.red);
		l2.setFont(new Font("Arial Rounded MT bold",1,45));
		l2.setBounds(400,400,200,60);
		frame.add(l2);
		
		//frame.add(new JLabel(new ImageIcon("D:\\MCA\\java\\project\\Quiz Engien\\bkg1.jpeg")));
	
	/*=================================== Start Test =================================*/
	
		b1 = new JButton("Start Test");
		b1.setFont(new Font("Arial Rounded MT bold",0,22));
		b1.setBounds(50,200,150,70);
		frame.add(b1);
		
		test = new Panel();
		test.setLayout(null);
		test.setBackground(new Color(121, 121, 133));
		test.setBounds(50,70,900,680);
		test.setEnabled(false);
		
		jr1 = new JRadioButton("");
		jr1.setBackground(new Color(121, 121, 133));
		jr1.setFont(new Font("Arial Rounded MT bold",1,20));
		jr1.setBounds(50,300,150,25);
		jr1.setEnabled(true);
		test.add(jr1);
		
		jr2 = new JRadioButton("");
		jr2.setBackground(new Color(121, 121, 133));
		jr2.setFont(new Font("Arial Rounded MT bold",1,20));
		jr2.setBounds(50,350,150,25);
		jr2.setEnabled(true);
		test.add(jr2);
		
		jr3 = new JRadioButton("");
		jr3.setBackground(new Color(121, 121, 133));
		jr3.setFont(new Font("Arial Rounded MT bold",1,20));
		jr3.setBounds(50,400,150,25);
		jr3.setEnabled(true);
		test.add(jr3);
		
		jr4 = new JRadioButton("");
		jr4.setBackground(new Color(121, 121, 133));
		jr4.setFont(new Font("Arial Rounded MT bold",1,20));
		jr4.setBounds(50,450,150,25);	
		jr4.setEnabled(true);
		test.add(jr4);
		
		jr5 = new JRadioButton("");
		jr5.setEnabled(true);
		jr5.setSelected(true);
		
		Bg = new ButtonGroup();
		Bg.add(jr1);
		Bg.add(jr2);
		Bg.add(jr3);
		Bg.add(jr4);
		Bg.add(jr5);
		
		timer1 = new Label("Time Left");
		//timer1.setBackground(Color.red);
		timer1.setFont(new Font("Arial Rounded MT bold",1,16));
		timer1.setBounds(800,40,80,30);
		test.add(timer1);
		
		timer2 = new Label("00:00");
		//timer2.setBackground(Color.red);
		timer2.setFont(new Font("Arial Rounded MT bold",1,16));
		timer2.setBounds(800,80,80,30);
		test.add(timer2);
		
		qno = new Label("");
		//qno.setBackground(Color.red);
		qno.setFont(new Font("Arial Rounded MT bold",1,20));
		qno.setBounds(50,180,25,40);
		test.add(qno);
		
		ques = new Label();
		//ques.setBackground(Color.red);
		ques.setFont(new Font("Arial Rounded MT bold",1,28));
		ques.setBounds(100,180,700,40);
		test.add(ques);
		
		tb1 = new JButton("Back");
		tb1.setFont(new Font("Arial Rounded MT bold",0,22));
		tb1.setBounds(50,600,100,40);
		test.add(tb1);
		
		//Back button
		tb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int a = JOptionPane.showConfirmDialog(frame ,"Are you Sure?");
				
				if(a == 0)
				{
					l1.setVisible(true);
					l2.setVisible(true);
					test.setEnabled(false);
					test.setVisible(false);
					b1.setVisible(true);
					b1.setEnabled(true);
					b2.setVisible(true);
					b2.setEnabled(true);
					b3.setVisible(true);
					b3.setEnabled(true);
					b4.setVisible(true);
					b4.setEnabled(true);
					
				}
			}
		});
		
		tb2 = new JButton("Previous");
		tb2.setFont(new Font("Arial Rounded MT bold",0,22));
		tb2.setBounds(350,600,100,40);
		test.add(tb2);
		
		//PREVIOUS BUTTON
		tb2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{	
					--z;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						
					String query = "select * from questions where qno ='"+z+"'";
					PreparedStatement st = cn.prepareStatement(query);
					ResultSet rs = st.executeQuery();
										
					if(rs.next())
					{
						qno.setText(rs.getString(1));
						ques.setText(rs.getString(2));
						jr1.setText(rs.getString(3));
						jr2.setText(rs.getString(4));
						jr3.setText(rs.getString(5));
						jr4.setText(rs.getString(6));
					}
					
					System.out.println("previous"+z);
					cn.close();	
					
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
						
			}
		});
	
		tb3 = new JButton("Next");
		tb3.setFont(new Font("Arial Rounded MT bold",0,22));
		tb3.setBounds(460,600,100,40);
		test.add(tb3);
		
		
		// next button
		tb3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(jr1.isSelected())
				{
					if(jr1.getText().equalsIgnoreCase(answere))
					{
						Student.result++;	
					}
					else
					{
						result--;	
					}
				}
				
				else if(jr2.isSelected())
				{
					if(jr2.getText().equalsIgnoreCase(answere))
					{
						result++;	
					}
					
					else
					{
						result--;
					}
				}
					
				else if(jr3.isSelected())
				{
					if(jr3.getText().equalsIgnoreCase(answere))
					{
					result++;	
					}
						
					else
					{
						result--;	
					}
				}
					
				else 
				{
					if(jr4.getText().equalsIgnoreCase(answere))
					{
						result++;	
					}
					
					else
					{
						result--;	
					}
				}
			
				try
				{	
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						
					String query = "select * from questions where qno ='"+z+"'";
					PreparedStatement st = cn.prepareStatement(query);
					ResultSet rs = st.executeQuery();
										
					if(rs.next())
					{
						qno.setText(rs.getString(1));
						ques.setText(rs.getString(2));
						jr1.setText(rs.getString(3));
						jr2.setText(rs.getString(4));
						jr3.setText(rs.getString(5));
						jr4.setText(rs.getString(6));
						answere = rs.getString(7);
						System.out.println("database   "+answere);
					}
					
					System.out.println("next"+z);
					System.out.println("this is result  "+result);	
			
					cn.close();
					z++;				
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
				jr1.setSelected(false);
				jr2.setSelected(false);
				jr3.setSelected(false);
				jr4.setSelected(false);				
				jr5.setSelected(true);	
				
				if(z==8)
				{
					JOptionPane.showMessageDialog(Student.frame,"Questions are over");
				}					
			}
		});
		
		tb4 = new JButton("Submit");
		tb4.setFont(new Font("Arial Rounded MT bold",0,22));
		tb4.setBounds(750,600,100,40);
		test.add(tb4);
		
		//Submit button
		tb4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{	
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						
					String query = "update login set score = ?  where id = '"+login.t1.getText()+"' ";
					PreparedStatement st = cn.prepareStatement(query);
					st.setInt(1,result);
					
					st.executeUpdate();
					cn.close();
					JOptionPane.showMessageDialog(Student.frame,"Your test is submitted successfully");
					l1.setVisible(true);
					l2.setVisible(true);
					test.setEnabled(false);
					test.setVisible(false);
					b1.setVisible(true);
					b1.setEnabled(true);
					b2.setVisible(true);
					b2.setEnabled(true);
					b3.setVisible(true);
					b3.setEnabled(true);
					b4.setVisible(true);
					b4.setEnabled(true);				
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
			
		});			
		
		// start test
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				l1.setVisible(false);
				l2.setVisible(false);
				test.setEnabled(true);
				test.setVisible(true);
				frame.add(test);
				b1.setVisible(false);
				b1.setEnabled(false);
				b2.setVisible(false);
				b2.setEnabled(false);
				b3.setVisible(false);
				b3.setEnabled(false);
				b4.setVisible(false);
				b4.setEnabled(false);
				System.out.println("b1");
				JOptionPane.showMessageDialog(frame,"Your Timer Will Start Now");
				TimerThread t3=new TimerThread();
				t3.start();
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
					String query = "select * from questions where qno ='"+z+"'";
					PreparedStatement st = cn.prepareStatement(query);
					
					ResultSet rs = st.executeQuery();
					rs.next();
					{
						qno.setText(rs.getString(1));
						ques.setText(rs.getString(2));
						jr1.setText(rs.getString(3));
						jr2.setText(rs.getString(4));
						jr3.setText(rs.getString(5));
						jr4.setText(rs.getString(6));
						answere = rs.getString(7);
						System.out.println("database   "+answere);
					}

					System.out.println("this is result  "+result);	
		
					cn.close();
					z++;	
				}
					
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}	
			}
		});

		
	/*=================================== Score ======================================*/
	
		b2 = new JButton("Score");
		b2.setFont(new Font("Arial Rounded MT bold",0,22));
		b2.setBounds(50,500,150,70);
		frame.add(b2);
		
		score = new Panel();
		score.setLayout(null);
		score.setBackground(new Color(121, 121, 133));
		score.setBounds(250,70,500,670);
		score.setEnabled(false);
		
		s1 = new Label("Score");
		//s1.setBackground(Color.red);
		s1.setFont(new Font("Arial Rounded MT bold",0,44));
		s1.setBounds(180,50,180,60);
		score.add(s1);
		
		s2 = new Label("Name:");
		//s2.setBackground(Color.red);
		s2.setFont(new Font("Arial Rounded MT bold",0,34));
		s2.setBounds(50,180,100,40);
		score.add(s2);
		
		s3 = new Label("Roll No:");
		//s3.setBackground(Color.red);
		s3.setFont(new Font("Arial Rounded MT bold",0,34));
		s3.setBounds(50,260,120,40);
		score.add(s3);
		
		s4 = new Label("Department:");
		//s4.setBackground(Color.red);
		s4.setFont(new Font("Arial Rounded MT bold",0,34));
		s4.setBounds(50,340,180,40);
		score.add(s4);
		
		s5 = new Label("Score:");
		//s5.setBackground(Color.red);
		s5.setFont(new Font("Arial Rounded MT bold",0,34));
		s5.setBounds(50,420,100,40);
		score.add(s5);
		
		//database name
		s6 = new Label("");
		//s6.setBackground(Color.red);
		s6.setFont(new Font("Arial Rounded MT bold",0,44));
		s6.setBounds(250,180,180,40);
		score.add(s6);
		
		//database rollno
		s7 = new Label("");
		//s7.setBackground(Color.red);
		s7.setFont(new Font("Arial Rounded MT bold",0,34));
		s7.setBounds(250,260,180,40);
		score.add(s7);
		
		//database department
		s8 = new Label("");
		//s8.setBackground(Color.red);
		s8.setFont(new Font("Arial Rounded MT bold",0,34));
		s8.setBounds(250,340,180,40);
		score.add(s8);
		
		//database score
		s9 = new Label("");
		//s9.setBackground(Color.red);
		s9.setFont(new Font("Arial Rounded MT bold",0,34));
		s9.setBounds(250,420,180,40);
		score.add(s9);
		
		sb1 = new JButton("OK");
		sb1.setFont(new Font("Arial Rounded MT bold",0,22));
		sb1.setBounds(180,530,150,50);
		score.add(sb1);
		
		//ok button
		sb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				score.setEnabled(false);
				score.setVisible(false);
				l1.setVisible(true);
				l2.setVisible(true);
				b1.setEnabled(true);
				b3.setEnabled(true);
			}
		});
		
		//Score
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				l1.setVisible(false);
				l2.setVisible(false);
				b1.setEnabled(false);
				b3.setEnabled(false);
				test.setVisible(false);
				rank.setVisible(false);
				score.setVisible(true);
				score.setEnabled(true);
				frame.add(score);
				System.out.println("b2");
			
				try
				{	System.out.println(login.t1.getText());
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
					String query = "select name,rno,department,score from login where id = ?";
					PreparedStatement st = cn.prepareStatement(query);
					st.setInt(1,Integer.parseInt(login.t1.getText()));
					
					ResultSet rs = st.executeQuery();
					
					rs.next();
					{
						s6.setText(rs.getString(1));
						s7.setText(rs.getString(2));
						s8.setText(rs.getString(3));
						s9.setText(rs.getString(4));
					}
					
					cn.close();
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}				
			}
		});
		
		
	/*============================================ Rank ================================*/
	
		b3 = new JButton("Rank");
		b3.setFont(new Font("Arial Rounded MT bold",0,22));
		b3.setBounds(800,200,150,70);
		frame.add(b3);
		
		rank = new Panel();
		rank.setLayout(null);
		rank.setBackground(new Color(121, 121, 133));
		rank.setBounds(250,70,500,670);
		rank.setEnabled(false);
		
		r1 = new Label("Rank");
		//r1.setBackground(Color.red);
		r1.setFont(new Font("Arial Rounded MT bold",0,44));
		r1.setBounds(180,50,180,60);
		rank.add(r1);
		
		r2 = new Label("Name:");
		//r2.setBackground(Color.red);
		r2.setFont(new Font("Arial Rounded MT bold",0,34));
		r2.setBounds(50,180,100,40);
		rank.add(r2);
		
		r3 = new Label("Roll No:");
		//r3.setBackground(Color.red);
		r3.setFont(new Font("Arial Rounded MT bold",0,34));
		r3.setBounds(50,260,120,40);
		rank.add(r3);
		
		r4 = new Label("Department:");
		//r4.setBackground(Color.red);
		r4.setFont(new Font("Arial Rounded MT bold",0,34));
		r4.setBounds(50,340,180,40);
		rank.add(r4);
		
		r5 = new Label("Rank:");
		//r5.setBackground(Color.red);
		r5.setFont(new Font("Arial Rounded MT bold",0,34));
		r5.setBounds(50,420,100,40);
		rank.add(r5);
		
		//database name
		r6 = new Label("");
		//s6.setBackground(Color.red);
		r6.setFont(new Font("Arial Rounded MT bold",0,44));
		r6.setBounds(250,180,180,40);
		rank.add(r6);
		
		//database rollno
		r7 = new Label("");
		//r7.setBackground(Color.red);
		r7.setFont(new Font("Arial Rounded MT bold",0,34));
		r7.setBounds(250,260,180,40);
		rank.add(r7);
		
		//database department
		r8 = new Label("");
		//r8.setBackground(Color.red);
		r8.setFont(new Font("Arial Rounded MT bold",0,34));
		r8.setBounds(250,340,180,40);
		rank.add(r8);
		
		//database score
		r9 = new Label("");
		//r9.setBackground(Color.red);
		r9.setFont(new Font("Arial Rounded MT bold",0,34));
		r9.setBounds(250,420,180,40);
		rank.add(r9);
		
		rb1 = new JButton("OK");
		rb1.setFont(new Font("Arial Rounded MT bold",0,22));
		rb1.setBounds(180,530,150,50);
		rank.add(rb1);
		
		// ok button
		rb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				l1.setVisible(true);
				l2.setVisible(true);
				b1.setEnabled(true);
				b2.setEnabled(true);
				rank.setEnabled(false);
				rank.setVisible(false);
			}
		});
		
		//rank
		b3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				l1.setVisible(false);
				l2.setVisible(false);
				b1.setEnabled(false);
				b2.setEnabled(false);
				test.setVisible(false);
				score.setVisible(false);
				rank.setVisible(true);
				rank.setEnabled(true);
				frame.add(rank);
				System.out.println("b3");
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
					String query = "select name,rno,department from login where id = ?";
					PreparedStatement st = cn.prepareStatement(query);
					st.setInt(1,Integer.parseInt(login.t1.getText()));
					
					ResultSet rs = st.executeQuery();
					
					rs.next();
					{
						r6.setText(rs.getString(1));
						r7.setText(rs.getString(2));
						r8.setText(rs.getString(3));
						//r9.setText(rs.getString(4));
					}
					
					cn.close();
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}	
			}
		});
	
	/*=========================================== Exit ===================================*/	
	
		b4 = new JButton("Exit");
		b4.setFont(new Font("Arial Rounded MT bold",0,22));
		b4.setBounds(800,500,150,70);
		frame.add(b4);
		
		b4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				System.out.println("b4");
			}
		});
	}
		
}