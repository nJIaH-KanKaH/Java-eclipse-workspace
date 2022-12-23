import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Window extends JFrame {
	
	private JButton findButton;

	private JPanel secondPanel;
	private JTextArea area;
	
	private JList <String> displayedList;
	private JScrollPane scrollPane2;
	private DefaultListModel <String> displayedListModel = new DefaultListModel<String>();
	private List<String>items;
	public static final String PATTERN = "(\\d{4}-\\d{2}-\\d{2})";
	Matcher m;
    
    public Window() {
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 360);
        setResizable(false);
        
        items=new ArrayList<String>();

        setLayout(new BorderLayout());
        initSecondPanel();
        add(secondPanel);
        
        setVisible(true);
    }
    
    private void initSecondPanel() {
    	secondPanel=new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	//createFindButton();
    	area=new JTextArea(2, 30);
    	area.setMaximumSize(new Dimension(200, 100));
    	area.setText("Input text with dates in format yyyy-mm-dd");
    	
    	displayedListModel=new DefaultListModel<>();
    	displayedList=new JList<>(displayedListModel);    	
    	displayedList.setPreferredSize(new Dimension(200, 60));
    	displayedList.setMaximumSize(new Dimension(200, 100));
    	
    	scrollPane2 = new JScrollPane(displayedList);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setPreferredSize(new Dimension(200, 100));
        secondPanel.add(area);
        //secondPanel.add(findButton);
    	secondPanel.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
    	
    	area.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				m=Pattern.compile(PATTERN).matcher(area.getText());
	            if(m.find()) {
	            	items.clear();
	            	do {
		            	String dateString=m.group();
		            	try {
							Date date=new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
							items.add(dateString);
						} catch (ParseException e1) {
							items.add(dateString+" wrong date format");
							e1.printStackTrace();
						}
					} while (m.find());
	            }
	            else {
	            	items.clear();
	            	items.add("no dates in text");
	            }
	            refresh();
			}
		});
    }

    private void refresh() {
    	displayedListModel.clear();
        for(String i:items) {
    		displayedListModel.add(displayedListModel.size(), i);
    	}
    }
}