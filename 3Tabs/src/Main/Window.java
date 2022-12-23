package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel listsPanel;
    private JPanel gridPanel;
    private JPanel imagesPanel;
    private String temporaryLabelString;
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;

    private static final Color DEFAULT_BUTTON_COLOR = Color.LIGHT_GRAY;
    private static final Color BUTTON_COLOR = Color.DARK_GRAY;
    private static final int GRID_SIZE = 6;
    private static final String[] DATA_1 = {"el1", "el2", "el3", "el4", "el5"};
    private static final String[] DATA_2= {"el5","el6","el7","el8","el9","el10"};

    public Window() {
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        tabbedPane = new JTabbedPane();
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        initializeListsPanel();
        tabbedPane.addTab("Lists", listsPanel);
        initializeGridPanel();
        tabbedPane.addTab("Grid", gridPanel);
        initializeImagesPanel();
        tabbedPane.addTab("Images", imagesPanel);
        setVisible(true);
    }

    private void initializeListsPanel() {
        listsPanel = new JPanel();
        listsPanel.setLayout(new BorderLayout());
        leftListModel = new DefaultListModel<String>();
        for (String i : DATA_1) {
            leftListModel.addElement(i);
        }
        JList <String> leftList = new JList<String>(leftListModel);
        leftList.setSelectedIndex(0);
        leftList.setPreferredSize(new Dimension(250,700));
        listsPanel.add(new JScrollPane(leftList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.WEST);

        rightListModel = new DefaultListModel<String>();
        for(String i:DATA_2) {
        	rightListModel.addElement(i);
        }
        JList <String> rightList = new JList<String>(rightListModel);
        rightList.setSelectedIndex(0);
        rightList.setPreferredSize(new Dimension(250,700));
        listsPanel.add(new JScrollPane(rightList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);

        JButton leftToRightButton = new JButton(">");
        listsPanel.add(leftToRightButton, BorderLayout.NORTH);
        leftToRightButton.addActionListener(e -> {
            while (!leftList.isSelectionEmpty()) {
                rightListModel.addElement(leftList.getSelectedValue());
                leftListModel.remove(leftList.getSelectedIndex());
            }
        });

        JButton rightToLeftButton = new JButton("<");
        listsPanel.add(rightToLeftButton, BorderLayout.SOUTH);
        rightToLeftButton.addActionListener(e -> {
            while (!rightList.isSelectionEmpty()) {
                leftListModel.addElement(rightList.getSelectedValue());
                rightListModel.remove(rightList.getSelectedIndex());
            }
        });

    }

    private void initializeGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        temporaryLabelString = new String("Clicked!");
        MouseListener listener = new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String tmp = ((JButton) e.getSource()).getText();
                ((JButton) e.getSource()).setText(temporaryLabelString);
                temporaryLabelString = tmp;
            }

            public void mouseReleased(MouseEvent e) {
                String tmp = ((JButton) e.getSource()).getText();
                ((JButton) e.getSource()).setText(temporaryLabelString);
                temporaryLabelString = tmp;
            }

            public void mouseEntered(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(BUTTON_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(DEFAULT_BUTTON_COLOR);
            }

            public void mouseClicked(MouseEvent e) {
            }
        };
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JButton tmp = new JButton(String.valueOf(i + 1));
            tmp.setBackground(DEFAULT_BUTTON_COLOR);
            tmp.addMouseListener(listener);
            gridPanel.add(tmp);
        }
    }

    private void initializeImagesPanel() {
        imagesPanel = new JPanel();
        imagesPanel.setLayout(new GridLayout(4,1));
        ButtonGroup radioGroup = new ButtonGroup();
        ImageIcon defaultIcon = new ImageIcon("images/default.png");
        ImageIcon pressedIcon = new ImageIcon("images/pressed.png");
        ImageIcon rolloverIcon = new ImageIcon("images/rollover.png");
        ImageIcon selectedIcon = new ImageIcon("images/selected.png");
        for(int i = 0; i < 4; i++) {
            JRadioButton btn = new JRadioButton(defaultIcon);
            btn.setPressedIcon(pressedIcon);
            btn.setRolloverIcon(rolloverIcon);
            btn.setSelectedIcon(selectedIcon);
            radioGroup.add(btn);
            imagesPanel.add(btn);
        }
    }

}
