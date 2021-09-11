import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;


class TimerThread extends Thread
{
	int i;
	public void run()
	{
		try
		{
			for(i = 120 ; i >= 0 ; i--)
			{
				Student.timer2.setText(""+i);
				Thread.sleep(1000);
			}
			
			if(i == -1)
			{
				JOptionPane.showMessageDialog(Student.frame,"Time is over &  your test is submitted");
				
				Student.l1.setVisible(true);
				Student.l2.setVisible(true);
				Student.test.setEnabled(false);
				Student.test.setVisible(false);
				Student.b1.setVisible(true);
				Student.b1.setEnabled(true);
				Student.b2.setVisible(true);
				Student.b2.setEnabled(true);
				Student.b3.setVisible(true);
				Student.b3.setEnabled(true);
				Student.b4.setVisible(true);
				Student.b4.setEnabled(true);
			}
	}
		

		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}