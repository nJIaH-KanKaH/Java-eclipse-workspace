import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import hierarchy.*;

public class Window extends JFrame {
	
	private JButton openButton;
	
	private JButton add1Button;
	private JButton add2Button;
	
	private JButton openXMLButton;
	private JButton saveToXMLButton;
	
	private JTabbedPane pane;
	private JPanel mainPanel;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	
	private List<Lamp>list=new ArrayList<>();
	
	private List<String>displayedData;
	private List<String>displayedData2;
	private List<String>displayedData3;
	private List<String>displayedData4;
	private List<String>displayedData5;
	
	private JList <String> displayedList;
	private JList <String> displayedList2;
	private JList <String> displayedList3;
	private JList <String> displayedList4;
	private JList<String>displayedList5;
	
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JScrollPane scrollPane4;
    private JScrollPane scrollPane5;
    
    private JFileChooser fileChooser = new JFileChooser();
    
    private DefaultListModel <String> displayedListModel = new DefaultListModel<String>();
    private DefaultListModel <String> displayedListModel2 = new DefaultListModel<String>();
    private DefaultListModel <String> displayedListModel3 = new DefaultListModel<String>();
    private DefaultListModel <String> displayedListModel4 = new DefaultListModel<String>();
    private DefaultListModel <String> displayedListModel5 = new DefaultListModel<String>();
    
    private JMenuBar bar;
    
    public Window() {
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        
        pane=new JTabbedPane();
        setLayout(new BorderLayout());
        add(pane,BorderLayout.CENTER);
        initMainPanel();
        pane.addTab("Main view",mainPanel);
        initPanel2();
        pane.addTab("from cheap to expensive organisation AAAA", panel2);
        initPanel3();
        pane.addTab("Lamps sorted by price/P", panel3);
        initPanel4();
        pane.addTab("List of organisations", panel4);
        initPanel5();
        pane.addTab("lamp with average nubmer", panel5);
        
        bar=getBar();
        this.setJMenuBar(bar);
        setVisible(true);
    }
    
    private void initMainPanel() {
    	mainPanel=new JPanel();
    	mainPanel.setLayout(new GridBagLayout());
    	displayedList = new JList<String>(displayedListModel);
    	scrollPane = new JScrollPane(displayedList);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	mainPanel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void initPanel2() {
    	panel2=new JPanel();
    	panel2.setLayout(new GridBagLayout());
    	displayedList2 = new JList<String>(displayedListModel2);
    	scrollPane2 = new JScrollPane(displayedList2);
    	scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	panel2.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void initPanel3() {
    	panel3=new JPanel();
    	panel3.setLayout(new GridBagLayout());
    	displayedList3 = new JList<String>(displayedListModel3);
    	scrollPane3 = new JScrollPane(displayedList3);
    	scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	panel3.add(scrollPane3, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void initPanel4() {
    	panel4=new JPanel();
    	panel4.setLayout(new GridBagLayout());
    	displayedList4 = new JList<String>(displayedListModel4);
    	scrollPane4 = new JScrollPane(displayedList4);
    	scrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	panel4.add(scrollPane4, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void initPanel5() {
    	panel5=new JPanel();
    	panel5.setLayout(new GridBagLayout());
    	displayedList5 = new JList<String>(displayedListModel5);
    	scrollPane5 = new JScrollPane(displayedList5);
    	scrollPane5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	panel5.add(scrollPane5, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private JMenuBar getBar() {
    	JMenuBar menuBar=new JMenuBar();
    	createOpenButton();
    	menuBar.add(openButton);
    	createAdd1Button();
    	createAdd2Button();
    	menuBar.add(add1Button);
    	menuBar.add(add2Button);
    	createSaveToXmlButton();
    	menuBar.add(saveToXMLButton);
    	createOpenXMLButton();
    	menuBar.add(openXMLButton);
    	return menuBar;
    }
    
    private void createOpenButton() {
        openButton = new JButton("Open");
        openButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Opening file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
                try {
                    File file = fileChooser.getSelectedFile();
                    int n=0;
                    list = Utils.getFromFile(file,n);
                    refresh();
                    JOptionPane.showMessageDialog(this,"number of lamps"+n,"",JOptionPane.PLAIN_MESSAGE);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Every line in file must contain string, int, int string separated by whitespaces", "Error occured: ", JOptionPane.PLAIN_MESSAGE);
                }
        });
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(this, "Wrong input data", "Error", JOptionPane.PLAIN_MESSAGE);
    }

    private void createAdd1Button() {
        add1Button = new JButton("Add LampN");
        add1Button.addActionListener(e -> {
            String org = JOptionPane.showInputDialog("Input org");
            if( org == null || ! Pattern.compile("[a-zA-Zа-€ј-я]+").matcher(org).matches()) {
                showErrorDialog();
                return;
            }
            String P = JOptionPane.showInputDialog("Input P");
            if(P == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(P).matches()) {
                showErrorDialog();
                return;
            }
            String time = JOptionPane.showInputDialog("Input time");
            if( time == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(time).matches()) {
                showErrorDialog();
                return;
            }
            LampN tmp = new LampN(org,Integer.parseInt(P),Integer.parseInt(time));
            list.add(tmp);
            refresh();
        });
    }
    
    private void createAdd2Button() {
        add2Button = new JButton("Add LampD");
        add2Button.addActionListener(e -> {
        	String org = JOptionPane.showInputDialog("Input org");
            if( org == null || ! Pattern.compile("[a-zA-Zа-€ј-я]+").matcher(org).matches()) {
                showErrorDialog();
                return;
            }
            String P = JOptionPane.showInputDialog("Input P");
            if(P == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(P).matches()) {
                showErrorDialog();
                return;
            }
            String number = JOptionPane.showInputDialog("Input number");
            if( number == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(number).matches()) {
                showErrorDialog();
                return;
            }
            LampD tmp = new LampD(org,Integer.parseInt(P),Integer.parseInt(number));
            list.add(tmp);
            refresh();
        });
    }
    
    private void refresh() {
    	refreshView();
    	refreshView2();
    	refreshView3();
    	refreshView4();
    	refreshView5();
    }
    
    private void refreshView() {
    	displayedData=Utils.getData(list);
    	displayedListModel.clear();
    	for(Iterator<String>i=displayedData.iterator();i.hasNext();) {
    		displayedListModel.addElement(i.next());
    	}
    }
    
    private void refreshView2() {
    	displayedData2=Utils.getData2(list,"AAAA");
    	displayedListModel2.clear();
    	for(Iterator<String>i=displayedData2.iterator();i.hasNext();) {
    		displayedListModel2.addElement(i.next());
    	}
    }
    
    private void refreshView3() {
    	displayedData3=Utils.getData3(list);
    	displayedListModel3.clear();
    	for(Iterator<String>i=displayedData3.iterator();i.hasNext();) {
    		displayedListModel3.addElement(i.next());
    	}
    }
    
    private void refreshView4() {
    	displayedData4=Utils.getData4(list);
    	displayedListModel4.clear();
    	for(Iterator<String>i=displayedData4.iterator();i.hasNext();) {
    		displayedListModel4.addElement(i.next());
    	}
    }
    
    private void refreshView5() {
    	displayedData5=Utils.getData5(list);
    	displayedListModel5.clear();
    	for(Iterator<String>i=displayedData5.iterator();i.hasNext();) {
    		displayedListModel5.addElement(i.next());
    	}
    }

    private void createSaveToXmlButton() {
        saveToXMLButton = new JButton("Save to XML");
        saveToXMLButton.addActionListener(e -> {
            saveToXml();
            JOptionPane.showMessageDialog(this, "data saved to file out.xml", "", JOptionPane.PLAIN_MESSAGE);
        });
    }

    private void createOpenXMLButton() {
        openXMLButton = new JButton("Open XML");
        openXMLButton.addActionListener(e -> {
            try {
                fileChooser.setDialogTitle("ќктрытие файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                	int n=0;
                    list = Utils.getFromXML(fileChooser.getSelectedFile(),n);
                    refresh();
                    JOptionPane.showMessageDialog(this,"number of lamps"+n,"",JOptionPane.PLAIN_MESSAGE);
                }
            } catch (SAXException | ParserConfigurationException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Can't input data", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private void saveToXml() {
        try (FileWriter out = new FileWriter("out.xml")) {
            out.write("<?xml version=\"1.0\"?>" + "\n");
            out.write("<Lamps>" + "\n");
            Iterator<Lamp> i = list.iterator();
            while (i.hasNext()) {
                out.write(i.next().toXML() + "\n");
            }
            out.write("</Lamps>");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Can't output data", "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
