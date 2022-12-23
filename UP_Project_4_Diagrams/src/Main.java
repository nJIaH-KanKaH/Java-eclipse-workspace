import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org
 

 
public class Main{
	public static JPanel panel;
 
	public static void main(String[] args) throws Exception {
	    JFrame f = new JFrame("Timer");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLayout(new BorderLayout());
	 
	    
	    f.add(panel, BorderLayout.CENTER);
	    f.pack();
	    f.setVisible(true);
	} 
}