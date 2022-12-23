package by.bsu.fpmi.Z;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.nio.channels.NetworkChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DragNDropFrame extends JFrame{
	public static final int WIDTH=800;
	public static final int HEIGHT=600;
	public static final String FIELD_1="Change me";
	public static final String FIELD_2="tapped";
	public static final String FIELD_3="catch me";
	public static final String FIELD_4="U've caught me";
	public static final Dimension BUTTON_BOUNDS=new Dimension(120, 30);
	public static final Point PASSIVE_BUTTON_COORDINATES=new Point(30,30);
	public static final Point RUNNIN_BUTTON_COORDINATES=new Point(180,30);
	public static final Point TEXT_FIELD_1_POINT=new Point(30,60);
	public static final Point TEXT_FIELD_2_POINT=new Point(30,90);
	public static final Point TEXT_FIELD_3_POINT=new Point(30,HEIGHT-150);
	
	public static final Dimension FRAME_SIZE=new Dimension(WIDTH,HEIGHT);
	
    private JLabel textLabel;
    private JLabel uLabel;
    private JLabel mouseCoordinates;
    private JButton runninButton;
    private JButton passiveButton;

    public DragNDropFrame() {
         super("test frame");
         createGUI();
    }
    
    public void setPoint(Point p) {
    	mouseCoordinates.setText("point x "+p.x+" y "+p.y);
    }

    public void createGUI() {
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setResizable(false);

         JPanel panel = new JPanel();
         panel.setLayout(null);

         passiveButton = new JButton(FIELD_1);
         runninButton=new JButton(FIELD_3);
         textLabel=new JLabel(FIELD_2);
         textLabel.setSize(150,30);
         textLabel.setVisible(false);
         textLabel.setLocation(TEXT_FIELD_1_POINT);
         uLabel=new JLabel(FIELD_4);
         uLabel.setVisible(false);
         textLabel.setSize(150,30);
         uLabel.setLocation(TEXT_FIELD_2_POINT);
         mouseCoordinates=new JLabel("point x 0 y 0");
         mouseCoordinates.setSize(150, 30);
         mouseCoordinates.setVisible(true);
         mouseCoordinates.setLocation(TEXT_FIELD_3_POINT);
         
         passiveButton.setSize(BUTTON_BOUNDS);
         runninButton.setSize(BUTTON_BOUNDS);
         passiveButton.setLocation(PASSIVE_BUTTON_COORDINATES);
         runninButton.setLocation(RUNNIN_BUTTON_COORDINATES);
         
         runninButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
				Point newPoint=Utils.randPoint(WIDTH-(int)BUTTON_BOUNDS.getWidth(), HEIGHT-(int)BUTTON_BOUNDS.getHeight());
				//Point newPoint=Utils.runnin(WIDTH-(int)BUTTON_BOUNDS.getWidth(), HEIGHT-(int)BUTTON_BOUNDS.getHeight(), runninButton.getLocation());
				runninButton.setLocation(newPoint);
			}
			
			public void mouseClicked(MouseEvent e) {
				uLabel.setVisible(true);
			}
		});
         passiveButton.addMouseMotionListener(new buttonEventListener());
         passiveButton.addKeyListener(new buttonEventListener());
         passiveButton.addMouseListener(new buttonEventListener());
         
         panel.addMouseMotionListener(new MouseMotionListener() {
			
        	 @Override
             public void mouseDragged(MouseEvent e) {
                 setPoint(e.getPoint());
             }

             @Override
             public void mouseMoved(MouseEvent e) {
             	setPoint(e.getPoint());
             }
		});
         
         panel.add(runninButton);
         panel.add(passiveButton);
         panel.add(textLabel);
         panel.add(uLabel);
         panel.add(mouseCoordinates);
         getContentPane().add(panel);
         setPreferredSize(FRAME_SIZE);
    }
    
    class buttonEventListener implements KeyListener, MouseMotionListener,MouseListener {

        @Override
        public void keyTyped(KeyEvent e) {
        	if(passiveButton.isSelected())
			{
				String newname=passiveButton.getText();
				if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
					newname=newname.replaceFirst(".$", "");
				}else
					newname+=e.getKeyChar();
				if(e.getKeyChar()==KeyEvent.VK_D) {
					textLabel.setVisible(false);
				}
			passiveButton.setText(newname);
			}
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.isControlDown()){
                passiveButton.setLocation(e.getX() + passiveButton.getX(), e.getY() + passiveButton.getY());
            }
            int x = e.getX() + passiveButton.getX();
            int y = e.getY() + passiveButton.getY();
            setPoint(new Point(x, y));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX() + passiveButton.getX();
            int y = e.getY() + passiveButton.getY();
            setPoint(new Point(x, y));
        }

        public void mouseExited(MouseEvent e) {
			passiveButton.setSelected(false);
		}
			
		public void mouseEntered(MouseEvent e) {
			passiveButton.setSelected(true);
		}
			
		public void mouseClicked(MouseEvent e) {
			textLabel.setVisible(true);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
    }

    public static void run() {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                   JFrame.setDefaultLookAndFeelDecorated(true);
                   DragNDropFrame game=new DragNDropFrame();
                   game.pack();
                   game.setLocationRelativeTo(null);
                   game.setVisible(true);
              }
         });
    }
}
