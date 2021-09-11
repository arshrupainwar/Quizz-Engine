import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

class login
{
	Frame frame;
	Panel p1;
	static TextField t1,t2;
	Label l1,l2,l3,l4,login;
	Label background;
	Choice c;
	Button b1;
	
	login()
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
		
		p1 = new Panel();
		p1.setLayout(null);
		p1.setBackground(new Color(61, 51, 77));
		p1.setBounds(520,110,400,600);
		frame.add(p1);
		
		login = new Label("Login");
		//login.setBackground(Color.red);
		login.setFont(new Font("Arial Rounded MT bold",0,44));
		login.setBounds(140,30,200,50);
		p1.add(login);
		
		l1 = new Label("Username");
		//l1.setBackground(Color.red);
		l1.setFont(new Font("Arial Rounded MT bold",0,26));
		l1.setBounds(100,130,200,40);
		p1.add(l1);
		
		t1 = new TextField();
		t1.setFont(new Font("Arial Rounded MT bold",0,18));
		t1.setBounds(100,180,200,30);
		p1.add(t1);
		
		l2 = new Label("Password");
		//l2.setBackground(Color.red);
		l2.setFont(new Font("Arial Rounded MT bold",0,24));
		l2.setBounds(100,240,200,40);
		p1.add(l2);
		
		t2 = new TextField();
		t2.setEchoChar('*');
		t2.setFont(new Font("Arial Rounded MT bold",1,18));
		t2.setBounds(100,290,200,30);
		p1.add(t2);
		
		l3 = new Label("Login Type");
		//l2.setBackground(Color.red);
		l3.setFont(new Font("Arial Rounded MT bold",0,24));
		l3.setBounds(100,350,200,40);
		p1.add(l3);
		
		c = new Choice();
		c.setBounds(100,390,200,40);
		c.add("");
		c.add("admin");
		c.add("student");	
		p1.add(c);
		
		b1 = new Button("Login");
		b1.setBounds(135,480,130,40);
		b1.setFont(new Font("Arial Rounded MT bold",0,24));
		p1.add(b1);
		
		//login button
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e2)
			{
				String id,pass,role,databaseid="",databasepass="",databaserole="";
				
				role = c.getSelectedItem();
				
				if(c.getSelectedItem().equals("admin"))
				{
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						
						String query = "select * from login where role ='admin'";
						PreparedStatement st = cn.prepareStatement(query);
						ResultSet rs = st.executeQuery();
						
						rs.next();
						{
							if(rs.getString(1).equalsIgnoreCase(t1.getText()) && rs.getString(2).equalsIgnoreCase(t2.getText()))
							{
								new Admin();
							}
						}
					cn.close();				
					}
						
					catch(Exception e3)
					{
						JOptionPane.showMessageDialog(null,e3.getMessage());
					}
				}
				
				else if(c.getSelectedItem().equals("student"))
				{
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz?user=root&password=india");
						
						String query = "select * from login where role ='student'";
						PreparedStatement st = cn.prepareStatement(query);
						ResultSet rs = st.executeQuery();
					
						while(rs.next())
						{
							if(rs.getString(1).equalsIgnoreCase(t1.getText()) && rs.getString(2).equalsIgnoreCase(t2.getText()))
							{
								new Student();
								Student.l2.setText(rs.getString(4));
							}
						}
					}

					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage());
					}
				}
				
				else 
				{
					JOptionPane.showMessageDialog(null,"invalid username or password");
				}						
			}
		});	
	}
	
	public static void main ( String ar[] )
	{
		login l = new login();
	}
	
}// database table name is login for login panel