import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;



public class Window extends JFrame {
	private static final String[] columnNames = {"Flag","Country","Capital","Price","Is active"};
	
	private JButton addButton;
	
	private JTabbedPane pane;
	private JPanel mainPanel;
	private JPanel secondPanel;
	
	private JList <Tour> displayedList;
	private JScrollPane scrollPane2;
	private DefaultListModel /*<Tour>*/ displayedListModel = new DefaultListModel();
	
	private JLabel label=new JLabel();
	
	private List<Tour>tours;
	
	Object[][]data0;
	
	private JTable displayedTable;
	
	private int sum;
	private DefaultTableModel displayedTableModel;

    private JScrollPane scrollPane;
    
    private JMenuBar bar;
    
    public Window() {
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        
        tours=new ArrayList<Tour>();
    	Utils.readList(Main.PATH_STRING, tours);
        
        pane=new JTabbedPane();
        setLayout(new BorderLayout());
        add(pane,BorderLayout.CENTER);
        initMainPanel();
        initSecondPanel();
        pane.addTab("Main view",mainPanel);
        pane.addTab("Sec view", secondPanel);
        
        bar=getBar();
        this.setJMenuBar(bar);
        setVisible(true);
    }
    
    private void initSecondPanel() {
    	secondPanel=new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	
    	displayedListModel=new DefaultListModel();
    	displayedList=new JList<>(displayedListModel);
    	displayedList.setCellRenderer(new ListCellRendererCustomable());
    	displayedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//single selection at JList   
    	displayedList.setSelectionBackground(Color.ORANGE);
//    	displayedList.addListSelectionListener(e->{
//    		Tour tour=displayedList.getSelectedValue();
//    		label.setIcon(tour.getCountryIcon());
//    		label.setText(tour.getCountryName()+" "+tour.getCountryCapital());
//    	});
    	displayedList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Tour tour=displayedList.getSelectedValue();
	    		label.setIcon(tour.getCountryIcon());
	    		label.setText(tour.getCountryName()+" "+tour.getCountryCapital());				
			}
		}); 	
    	
    	scrollPane2 = new JScrollPane(displayedList);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        secondPanel.add(label);
    	secondPanel.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    	
    	for(Tour i:tours) {
    		displayedListModel.add(displayedListModel.size(), i);
    	}
    }
    
    private void initMainPanel() {
    	mainPanel=new JPanel();
    	mainPanel.setLayout(new GridBagLayout());
    	
    	displayedTableModel=new DefaultTableModel(columnNames,0) {
    		public Class getColumnClass(int column) {
    			return getValueAt(0, column).getClass();
    		}
    		
    		public boolean isCellEditable(int row,int column) {
    			if(row==0)
    				return false;
    			return super.isCellEditable(row, column);
    		}
    	};
    	displayedTableModel.addTableModelListener(new TableModelListener() {
			boolean hasChanged=false;
			@Override
			public void tableChanged(TableModelEvent arg0) {
					if(!hasChanged) {
					sum=0;
					for(int i=1;i<displayedTableModel.getRowCount();i++) {
						if((boolean)displayedTableModel.getValueAt(i, 4)) {
							sum+=(int)displayedTableModel.getValueAt(i, 3);
						}
					}
					hasChanged=true;
					displayedTableModel.setValueAt(sum,0,3);
					hasChanged=false;
				}
			}
		});
    	displayedTable=new JTable(displayedTableModel);
    	displayedTable.setRowHeight(Main.CELL_WH);
    	displayedTable.setCellSelectionEnabled(true);
    	displayedTable.setModel(displayedTableModel);
    	
    	displayedTableModel.addRow(new Object[] { new ImageIcon()," ","Sum",0,false });
    	for(Tour i:tours) {
    		displayedTableModel.addRow(new Object[] {i.getCountryIcon(),i.getCountryName(),i.getCountryCapital(),i.getPrice(),i.isActive()});
    	}
    	
    	scrollPane=new JScrollPane(displayedTable);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	//mainPanel.add(sumField, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    	mainPanel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void createAddButton() {
        addButton = new JButton("Add country info");
        addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String countryIconpath = JOptionPane.showInputDialog("Input countryIcon path");
	            String country = JOptionPane.showInputDialog("Input country name");
	            if( country == null || ! Pattern.compile("[a-zA-Zà-ÿÀ-ß]+").matcher(country).matches()) {
	                showErrorDialog();
	                return;
	            }
	            String description = JOptionPane.showInputDialog("Input description");
	            if( description == null || ! Pattern.compile("[a-zA-Zà-ÿÀ-ß]+").matcher(description).matches()) {
	                showErrorDialog();
	                return;
	            }
	            String price = JOptionPane.showInputDialog("Input price");
	            if( price == null || ! Pattern.compile("[0-9][0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?").matcher(price).matches()) {
	                showErrorDialog();
	                return;
	            }
	            ImageIcon countryIcon=new ImageIcon(new File(countryIconpath).getAbsolutePath());
	            Tour tmp = new Tour(countryIcon, country, description, Integer.parseInt(price));
	            tours.add(tmp);
	            displayedListModel.clear();
	            for(Tour i:tours) {
	        		displayedListModel.add(displayedListModel.size(), i);
	        	}
	        	displayedTableModel.addRow(new Object[] {tmp.getCountryIcon(),tmp.getCountryName(),tmp.getCountryCapital(),tmp.getPrice(),tmp.isActive()});
			}
		});
    }
    
    private void showErrorDialog() {
        JOptionPane.showMessageDialog(this, "Wrong input data", "Error", JOptionPane.PLAIN_MESSAGE);
    }
    
    private JMenuBar getBar() {
    	JMenuBar menuBar=new JMenuBar();
    	createAddButton();
    	menuBar.add(addButton);
    	return menuBar;
    }
}