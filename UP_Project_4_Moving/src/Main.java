import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Hashtable;


 
public class Main{	
	public static JButton left=new JButton("<");
	public static JButton right=new JButton(">");
	public static JSlider slider;
	public static JPanel panel=new JPanel();
	public static final String FILENAME="mars.png";
	public static final float SCALE=0.05f;
	public static long sp=0l;
	public static int speed=1;
	public static boolean isRight=true;
	
	private static void addButtonListeners() {
		left.addMouseListener(new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isRight)
					isRight=false;
			}
		});
		right.addMouseListener(new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isRight)
					isRight=true;
			}
		});
	}
	
 	private static void generateSlider() {
		slider=new JSlider(1,5,1);
		slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable position = new Hashtable();
        position.put(1, new JLabel("1"));
        position.put(2, new JLabel("2"));
        position.put(3, new JLabel("3"));
        position.put(4, new JLabel("4"));
        position.put(5, new JLabel("5"));
        slider.setLabelTable(position);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
              speed = slider.getValue();
            }
          });
        panel.add(slider);
	}
	
	public static void main(String[] args) throws Exception {
	    JFrame f = new JFrame("Planet");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLayout(new BorderLayout());
	    f.setResizable(true);
	    
	    generateSlider();
	    panel.add(left);
	    panel.add(right);
	    addButtonListeners();
	    
	    final Tsk2 mover = new Tsk2(FILENAME,SCALE);
	    f.add(panel,BorderLayout.NORTH);
	    f.add(mover, BorderLayout.CENTER);
	    f.pack();
	    f.setVisible(true);
	 
	    Timer timer = new Timer(100, new ActionListener() {
	 
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	sp+= isRight ? (long)(speed) : (long)(0-speed);
	        	mover.sp=sp;
	            mover.repaint();
	        }
	    });
	    timer.start();
	} 
}