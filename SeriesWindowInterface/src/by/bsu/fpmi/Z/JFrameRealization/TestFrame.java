package by.bsu.fpmi.Z.JFrameRealization;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import by.bsu.fpmi.Z.Series.Exponential;
import by.bsu.fpmi.Z.Series.Liner;
import by.bsu.fpmi.Z.Series.Series;
import by.bsu.fpmi.Z.Series.Series.NoSuchElementException;

public class TestFrame extends JFrame {
	
	public static final String FIELD_1="Enter first &diff:";
	public static final String FIELD_2="Enter filename";
	public static final String FIELD_3="Enter number of elements";
	
    private JTextField textField;
    private Series series=new Liner(1f,0f);

    public TestFrame() {
         super("Test frame");
         createGUI();
    }

    public void createGUI() {
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JPanel panel = new JPanel();
         panel.setLayout(new FlowLayout());

         JRadioButton button1 = new JRadioButton("Exponential");
         panel.add(button1);

         JRadioButton button2 = new JRadioButton("Liner");
         button2.setSelected(true);
         panel.add(button2);

         JButton button3 = new JButton("Get Sum&Series to file");
         button3.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
				button3.setSelected(false);
			}
			
			public void mouseEntered(MouseEvent e) {
				button3.setSelected(true);
			}
			
			public void mouseClicked(MouseEvent e) {
			}
		});
         button3.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				if(button3.isSelected())
				{
					String newname=button3.getText();
					if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
						newname=newname.replaceFirst(".$", "");
					}else
						newname+=KeyEvent.getKeyText(e.getKeyCode());
				button3.setText(newname);
				}
			}
			
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
         panel.add(button3);
         

         textField = new JTextField();
         textField.setColumns(30);
         panel.add(textField);
         
         JTextField field1=new JTextField(FIELD_1);
         panel.add(field1);
         JTextField fieldWithFirst=new JTextField(1);
         panel.add(fieldWithFirst);
         JTextField fieldWithDiff=new JTextField(1);
         panel.add(fieldWithDiff);
         JTextField field3=new JTextField(FIELD_3);
         panel.add(field3);
         JTextField fieldWithNumber=new JTextField(1);
         panel.add(fieldWithNumber);
         JTextField field2=new JTextField(FIELD_2);
         panel.add(field2);
         JTextField fieldWithName=new JTextField(20);
         panel.add(fieldWithName);
          
         button2.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                   series=new Liner(1f, 1f);
                   button2.setSelected(true);
                   button1.setSelected(false);
              }
         });
         
         button1.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                  series=new Exponential(1f, 1f);
                  button2.setSelected(false);
                  button1.setSelected(true);
             }
        });
         
         button3.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                  try {
                	  float first=Float.valueOf(fieldWithFirst.getText().isEmpty()?"0":fieldWithFirst.getText());
                	  float diff=Float.valueOf(fieldWithDiff.getText().isEmpty()?"0":fieldWithDiff.getText());
                	  series=button2.isSelected()? new Liner(first,diff):new Exponential(first,diff);
                	  int n=Integer.valueOf(fieldWithNumber.getText().isEmpty()?"0":fieldWithNumber.getText());
                	series.getSeriesToFile(fieldWithName.getText(),n);
    				textField.setText("text printed to file named "+fieldWithName.getText()+".txt");
    			} catch (NoSuchElementException e1) {
    				textField.setText("No such element");
    			} catch (IOException e1) {
    				textField.setText("Wrong file");
				} catch (Exception e2) {
					textField.setText("wrong input");
				}
             }
		});

         getContentPane().add(panel);
         setPreferredSize(new Dimension(400, 150));
    }

    public static void run() {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                   JFrame.setDefaultLookAndFeelDecorated(true);
                   TestFrame frame = new TestFrame();
                   frame.pack();
                   frame.setLocationRelativeTo(null);
                   frame.setVisible(true);
              }
         });
    }
}
