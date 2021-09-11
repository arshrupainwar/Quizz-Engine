import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;

class Admin
{
	Frame frame;
	JButton b1,b2,b3,sb1,sq1;
	Panel student,question,details;
	Label l1,s1,s2,s3,s4,s5,q1,q2,q3,q4,q5,q6,q7,d1,d2;
	TextField st1,st2,st3,qt1,qt2,qt3,qt4,qt5,dt1;
	Choice c1;
	TextArea ta;
	JTable jt1;
	
	void fill(String m)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
			String query ="select * from login where name like = '%"+m+"%'";
			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			DefaultTableModel dtm = new DefaultTableModel();
			dtm.addColumn("id");
			dtm.addColumn("password");
			dtm.addColumn("role");
			dtm.addColumn("name");
			dtm.addColumn("rno");
			dtm.addColumn("department");
			dtm.addColumn("Score");
			
			while(rs.next())
			{
				String q[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
				dtm.addRow(q);
			}
						
			jt1.setModel(dtm);
			cn.close();
		}
					
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
									
	}
	
	void fill()
	{	
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
			String query ="select * from login";
			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			DefaultTableModel dtm = new DefaultTableModel();
			dtm.addColumn("id");
			dtm.addColumn("password");
			dtm.addColumn("role");
			dtm.addColumn("name");
			dtm.addColumn("rno");
			dtm.addColumn("department");
			dtm.addColumn("Score");
			
			while(rs.next())
			{
				String q[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
				dtm.addRow(q);
			}
						
			jt1.setModel(dtm);
			cn.close();
		}
					
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}	
	}
	
	Admin()
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
		l1.setFont(new Font("Arial Rounded MT bold",0,60));
		l1.setBounds(500,340,250,100);
		frame.add(l1);
		
	/*================================== Add Student ===============================*/
	
		b1 = new JButton("Add Student");
		b1.setFont(new Font("Arial Rounded MT bold",0,18));
		b1.setBounds(100,200,150,70);
		frame.add(b1);

		student = new Panel();
		student.setLayout(null);
		student.setBackground(new Color(121, 121, 133));
		student.setBounds(350,70,600,670);
		student.setEnabled(false);
		
		s1 = new Label("Add Student");
		s1.setFont(new Font("Arial Rounded MT bold",0,40));
		//s1.setBackground(Color.red);
		s1.setBounds(200,30,220,80);
		student.add(s1);
		
		s2 = new Label("Name:");
		s2.setFont(new Font("Arial Rounded MT bold",0,30));
		//s2.setBackground(Color.red);
		s2.setBounds(60,150,100,50);
		student.add(s2);
		
		st1 =new TextField();
		st1.setBounds(280,150,280,40);
		st1.setFont(new Font("Arial Rounded MT bold",0,28));
		student.add(st1);
		
		s3 = new Label("Roll No:");
		s3.setFont(new Font("Arial Rounded MT bold",0,30));
		//s3.setBackground(Color.red);
		s3.setBounds(60,250,110,50);
		student.add(s3);
		
		st2 =new TextField();
		st2.setBounds(280,250,280,40);
		st2.setFont(new Font("Arial Rounded MT bold",0,28));
		st2.setEnabled(false);
		student.add(st2);
		
		s4 = new Label("Department:");
		s4.setFont(new Font("Arial Rounded MT bold",0,30));
		//s4.setBackground(Color.red);
		s4.setBounds(60,350,170,50);
		student.add(s4);
		
		c1 = new Choice();
		c1.setBounds(280,350,170,100);
		c1.add("");
		c1.add("B.tech");
		c1.add("B.Sc");
		c1.add("MCA");
		student.add(c1);
		
		s5 = new Label("Password:");
		s5.setFont(new Font("Arial Rounded MT bold",0,30));
		//s5.setBackground(Color.red);
		s5.setBounds(60,450,140,50);
		student.add(s5);
		
		st3 =new TextField();
		st3.setBounds(280,450,280,40);
		st3.setFont(new Font("Arial Rounded MT bold",0,28));
		st3.setEnabled(false);
		student.add(st3);
		
		sb1 = new JButton("Save");
		sb1.setBounds(220,580,150,60);
		sb1.setFont(new Font("Arial Rounded MT bold",0,26));
		student.add(sb1);
		
		// save button
		sb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e4)
			{
				if(st1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please enter student name");
				}
				
				else if(c1.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please select department");
				}
				else 
				{	
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						String query = "insert into login (name,rno,department,password,role) values(?,?,?,?,?)";
						PreparedStatement st = cn.prepareStatement(query);
					
						st.setString(1,st1.getText()); //name
						st.setInt(2,Integer.parseInt(st2.getText())); //rno
						st.setString(3,c1.getSelectedItem()); //department
						st.setString(4,st3.getText()); //password
						st.setString(5,"student");
						st.executeUpdate();		
						cn.close();
						JOptionPane.showMessageDialog(frame,"Student Added");
					
					}
				
					catch(Exception e5)
					{
						JOptionPane.showMessageDialog(null,e5.getMessage());
					}
				}
			}
		});
		
		//add student
		b1.addActionListener(new ActionListener()
		{
			int a;
			public void actionPerformed(ActionEvent e3)
			{
				st1.setText("");
				c1.add("");
				l1.setEnabled(false);
				l1.setVisible(false);
				details.setVisible(false);
				question.setVisible(false);
				student.setVisible(true);
				student.setEnabled(true);
				frame.add(student);
				System.out.println("add student");
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
					String query = "select rno from login";
					PreparedStatement st = cn.prepareStatement(query);
					
					ResultSet  rs = st.executeQuery();
					
					// for setting rno on text field and incrementing it by 1
					while(rs.next())
					{
						a = rs.getInt(1);
						
					}
					st2.setText(++a +"");
					st3.setText("1234");	
					cn.close();
				}
				
				catch(Exception e5)
				{
					JOptionPane.showMessageDialog(null,e5.getMessage());
				}

			}
		});
		
		
		
	/*================================== Add Question ============================*/
	
		b2 = new JButton("Add Question");
		b2.setFont(new Font("Arial Rounded MT bold",0,18));
		b2.setBounds(100,350,150,70);
		frame.add(b2);
		
		question = new Panel();
		question.setLayout(null);
		question.setBackground(new Color(121, 121, 133));
		question.setBounds(350,70,600,670);
		question.setEnabled(false);
		
		q1 = new Label("Add Question");
		q1.setFont(new Font("Arial Rounded MT bold",0,36));
		//q1.setBackground(Color.red);
		q1.setBounds(200,40,220,50);
		question.add(q1);
		
		q2 = new Label("Type Question");
		//q2.setBackground(Color.red);
		q2.setFont(new Font("Arial Rounded MT bold",0,28));
		q2.setBounds(60,150,250,50);
		question.add(q2);
		
		ta = new TextArea();
		ta.setBounds(60,220,500,100);
		ta.setFont(new Font("Arial Rounded MT bold",0,24));
		question.add(ta);
		
		q3 = new Label("Option1");
		//q3.setBackground(Color.red);
		q3.setFont(new Font("Arial Rounded MT bold",0,26));
		q3.setBounds(60,350,100,40);
		question.add(q3);
		
		qt1 = new TextField();
		qt1.setBounds(60,400,100,30);
		qt1.setFont(new Font("Arial Rounded MT bold",0,20));
		question.add(qt1);
		
		q4 = new Label("Option2");
		//q4.setBackground(Color.red);
		q4.setFont(new Font("Arial Rounded MT bold",0,26));
		q4.setBounds(60,450,100,40);
		question.add(q4);
		
		qt2 = new TextField();
		qt2.setBounds(60,500,100,30);
		qt2.setFont(new Font("Arial Rounded MT bold",0,20));
		question.add(qt2);
		
		q5 = new Label("Option3");
		//q5.setBackground(Color.red);
		q5.setFont(new Font("Arial Rounded MT bold",0,26));
		q5.setBounds(270,350,100,40);
		question.add(q5);
		
		qt3 = new TextField();
		qt3.setBounds(270,400,100,30);
		qt3.setFont(new Font("Arial Rounded MT bold",0,20));
		question.add(qt3);
		
		q6 = new Label("Option4");
		//q6.setBackground(Color.red);
		q6.setFont(new Font("Arial Rounded MT bold",0,26));
		q6.setBounds(270,450,100,40);
		question.add(q6);
		
		qt4 = new TextField();
		qt4.setBounds(270,500,100,30);
		qt4.setFont(new Font("Arial Rounded MT bold",0,20));
		question.add(qt4);
		
		q7 = new Label("Answere");
		//q7.setBackground(Color.red);
		q7.setFont(new Font("Arial Rounded MT bold",0,26));
		q7.setBounds(460,350,100,40);
		question.add(q7);
		
		qt5 = new TextField();
		qt5.setBounds(460,400,100,30);
		qt5.setFont(new Font("Arial Rounded MT bold",0,20));
		question.add(qt5);
		
		sq1 = new JButton("Save");
		sq1.setBounds(260,580,130,50);
		sq1.setFont(new Font("Arial Rounded MT bold",0,26));
		question.add(sq1);
		
		//save button
		sq1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e6)
			{
				if(ta.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type question");
				}
				
				else if(qt1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type Option1");
				}
				
				else if(qt2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type Option2");
				}
				
				else if(qt3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type Option3");
				}
				
				else if(qt4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type Option4");
				}
				
				else if(qt5.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please type Answere");
				}
				
				else
				{
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						String query = "insert into questions (questions,op1,op2,op3,op4,ans) values(?,?,?,?,?,?)";
						PreparedStatement st = cn.prepareStatement(query);
						//st.setInt(1,"");
						st.setString(1,ta.getText());
						st.setString(2,qt1.getText());
						st.setString(3,qt2.getText());
						st.setString(4,qt3.getText());
						st.setString(5,qt4.getText());
						st.setString(6,qt5.getText());
					
						st.executeUpdate();
						cn.close();
						JOptionPane.showMessageDialog(null,"Question Added");
						ta.setText("");
						qt1.setText("");
						qt2.setText("");
						qt3.setText("");
						qt4.setText("");
						qt5.setText("");
					}
				
					catch(Exception e5)
					{
						JOptionPane.showMessageDialog(null,e5.getMessage());
					}
				}
			}
		});

		//add question
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e3)
			{
				l1.setEnabled(false);
				l1.setVisible(false);
				student.setVisible(false);
				details.setVisible(false);
				question.setVisible(true);
				question.setEnabled(true);
				frame.add(question);
				System.out.println("Add Question");
			}
		});
		
		
		
	/*================================== Student details =============================*/
		
		b3 = new JButton("Student details");
		b3.setFont(new Font("Arial Rounded MT bold",0,18));
		b3.setBounds(100,500,150,70);
		frame.add(b3);
		
		details = new Panel();
		details.setLayout(null);
		details.setBackground(new Color(121, 121, 133));
		details.setBounds(350,70,600,670);
		details.setEnabled(false);
		
		d1 = new Label("Student Details");
		d1.setFont(new Font("Arial Rounded MT bold",0,36));
		//d1.setBackground(Color.red);
		d1.setBounds(200,40,280,50);
		details.add(d1);
		
		d2 = new Label("Enter Name");
		d2.setFont(new Font("Arial Rounded MT bold",0,30));
		//d2.setBackground(Color.red);
		d2.setBounds(60,130,180,40);
		details.add(d2);
		
		dt1 = new TextField();
		dt1.setBounds(60,180,180,40);
		details.add(dt1);
		
		dt1.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				fill(dt1.getText());
			}
		});
		
		jt1 = new JTable();
		jt1.setBounds(60,250,480,370);
		DefaultTableModel dlm=new DefaultTableModel();
		details.add(jt1);
		
		JScrollPane p = new JScrollPane(jt1);
		p.setBounds(60,250,480,370);
		details.add(p);
		//student details
		b3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e3)
			{
				l1.setEnabled(false);
				l1.setVisible(false);;
				student.setVisible(false);
				question.setVisible(false);
				details.setVisible(true);
				details.setEnabled(true);
				frame.add(details);
				System.out.println("hello3");
				fill();
			}
		});			
	}
}
// database table name is login for addstudent section 