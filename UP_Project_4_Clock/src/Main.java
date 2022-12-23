import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.Timer;
 

 
public class Main{
 
	public static void main(String[] args) throws Exception {
	    JFrame f = new JFrame("Timer");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLayout(new BorderLayout());
	 
	    final Tsk1 clock = new Tsk1();
	    clock.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    f.add(clock, BorderLayout.CENTER);
	    f.pack();
	    f.setVisible(true);
	 
	    Timer timer = new Timer(500, new ActionListener() {
	 
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            clock.repaint();
	        }
	 
	    });
	    timer.start();
	} 
}