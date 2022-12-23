package Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.awt.*;
import java.io.*;
import org.xml.sax.SAXException;

public class Window extends JFrame {
	private JTabbedPane pane;
	
    private JButton openButton;
    private JButton addButton;
    private JList <String> displayedList;
    public JList<String> displayedList2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JFileChooser fileChooser = new JFileChooser();
    private TreeSet <String> displayedData = new TreeSet<String>();
    private List<String>displayedData1=new ArrayList<>();
    private List<String>displayedData2=new ArrayList<>();
    private List<Result>data0=new ArrayList<>();
    private TreeSet <Result> data = new TreeSet<Result>(new ResComparator());
    private DefaultListModel <String> displayedListModel = new DefaultListModel<String>();
    private DefaultListModel<String>displayedListModel2=new DefaultListModel<>();
    private JMenuBar bar;

    public Window() {
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());
        pane=new JTabbedPane();
        add(pane,BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JPanel panel2=new JPanel();
        panel.setLayout(new GridBagLayout());
        panel2.setLayout(new GridBagLayout());
        displayedList = new JList<String>();
        displayedList = new JList<String>(displayedListModel);
        scrollPane = new JScrollPane(displayedList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        displayedList2 = new JList<String>();
        displayedList2 = new JList<String>(displayedListModel2);
        scrollPane2 = new JScrollPane(displayedList2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        bar=getBar();
        this.setJMenuBar(bar);
        panel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel2.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
        pane.add("only",panel);
        pane.add("all",panel2);
        setVisible(true);
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
                    data0 = Utils.getFromFile(file);
                    refreshList();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Every line in file must contain long, string, int and an int separated by whitespaces", "Error occured: ", JOptionPane.PLAIN_MESSAGE);
                }
        });
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(this, "Wrong input data", "Error", JOptionPane.PLAIN_MESSAGE);
    }

    private void createAddButton() {
        addButton = new JButton("Add semester result");
        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Input ID");
            if( id == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(id).matches()) {
                showErrorDialog();
                return;
            }
            String lastName = JOptionPane.showInputDialog("Input last name");
            if( lastName == null || ! Pattern.compile("[a-zA-Zа-€ј-я]+").matcher(lastName).matches()) {
                showErrorDialog();
                return;
            }
            String course = JOptionPane.showInputDialog("Input course");
            if( course == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(course).matches()) {
                showErrorDialog();
                return;
            }
            String group = JOptionPane.showInputDialog("Input group");
            if( group == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(group).matches()) {
                showErrorDialog();
                return;
            }
            Result tmp = new Result(Long.parseLong(id), lastName, Integer.parseInt(course), Integer.parseInt(group));
            data0.add(tmp);
            refreshList();
        });
    }

    private void refreshList() {
        displayedData1 = Utils.getData1(data0);
        displayedListModel.clear();
        for(Iterator<String> i = displayedData1.iterator(); i.hasNext();) {
            displayedListModel.addElement(i.next());
        }
        displayedData2 = Utils.getDataList(data0);
        displayedListModel2.clear();
        for(Iterator<String> i = displayedData2.iterator(); i.hasNext();) {
            displayedListModel2.addElement(i.next());
        }
    }

    private JButton getSaveToXmlButton() {
        JButton saveButton = new JButton("Save to XML");
        saveButton.addActionListener(e -> {
            saveToXml();
            JOptionPane.showMessageDialog(this, "data saved to file output.xml", "", JOptionPane.PLAIN_MESSAGE);
        });
        return saveButton;
    }

    private JButton getOpenXMLButton() {
        JButton openXMLButton = new JButton("Open XML");
        openXMLButton.addActionListener(e -> {
            try {
                fileChooser.setDialogTitle("ќктрытие файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    data0 = Utils.getFromXML(fileChooser.getSelectedFile());
                    data=Utils.generateTreeSet(data0);
                    refreshList();
                }
            } catch (SAXException | ParserConfigurationException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Can't input data", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return openXMLButton;
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(0, 10, 10));
        createOpenButton();
        buttonPanel.add(openButton);
        createAddButton();
        buttonPanel.add(addButton);
        buttonPanel.add(getSaveToXmlButton());
        buttonPanel.add(getOpenXMLButton());
        return buttonPanel;
    }
    
    private JMenuBar getBar() {
    	JMenuBar menuBar=new JMenuBar();
    	createOpenButton();
    	menuBar.add(openButton);
    	createAddButton();
    	menuBar.add(addButton);
    	menuBar.add(getSaveToXmlButton());
    	menuBar.add(getOpenXMLButton());
    	return menuBar;
    }

    private void saveToXml() {
        try (FileWriter out = new FileWriter("output.xml")) {
            out.write("<?xml version=\"1.0\"?>" + "\n");
            out.write("<Results>" + "\n");
            Iterator<Result> i = data0.iterator();
            while (i.hasNext()) {
                out.write(i.next().toXML() + "\n");
            }
            out.write("</Results>");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Can't output data", "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
