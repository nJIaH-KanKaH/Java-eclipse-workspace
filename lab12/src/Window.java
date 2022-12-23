import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {

    private JTable table;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem openXml = new JMenuItem("Open XML");
    private JMenuItem saveAsXml = new JMenuItem("Save as XML");
    private JMenuItem openBinary = new JMenuItem("Open binary");
    private JMenuItem saveAsBinary = new JMenuItem("Save as binary");
    private JMenuItem addRow = new JMenuItem("Add row");
    private JMenuItem deleteRows = new JMenuItem("Remove selected rows");
    private JMenuItem checkConformity = new JMenuItem("Check XML conformity");
    private JMenuItem calculatePropertiesMenuItem = new JMenuItem("Calculate properties with SAX");
    private JMenuItem transformFile = new JMenuItem("Transform XML file");
    private JMenuItem saveAsHtmlMenuItem = new JMenuItem("Save as HTML");

    public Window() throws HeadlessException {

        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar.add(menu);
        pack();

        menu.add(openXml);
        menu.add(saveAsXml);
        menu.addSeparator();
        menu.add(openBinary);
        menu.add(saveAsBinary);
        menu.addSeparator();
        menu.add(addRow);
        menu.add(deleteRows);
        menu.addSeparator();
        menu.add(checkConformity);
        menu.add(calculatePropertiesMenuItem);
        menu.add(transformFile);
        menu.add(saveAsHtmlMenuItem);

        table = new JTable();
        table.setModel(new TableModel(new ArrayList<XmlFileSpecifications>()));
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        add(scrollPane);
        setJMenuBar(menuBar);
        setVisible(true);

        openXml.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                table.setModel(new TableModel(XmlFileSpecifications.readInstancesFromXml(chooser.getSelectedFile())));
            }
        });

        saveAsXml.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    XmlFileSpecifications.saveInstancesToXml(((TableModel) table.getModel()).getElements(),
                            chooser.getSelectedFile());
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
        });

        openBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    table.setModel(
                            new TableModel(XmlFileSpecifications.readInstancesFromBinary(chooser.getSelectedFile())));
                } catch (ClassNotFoundException | IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
        });

        saveAsBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    XmlFileSpecifications.saveInstancesToBinary(
                        ((TableModel) table.getModel()).getElements(), chooser.getSelectedFile());
                }
                catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
        });

        saveAsHtmlMenuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    XmlFileSpecifications.saveInstancesAsHtml(
                        ((TableModel) table.getModel()).getElements(), chooser.getSelectedFile());
                }
                catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
        });

        addRow.addActionListener(e -> {
            ((TableModel) table.getModel()).getElements().add(new XmlFileSpecifications("", "", 0, 0));
            ((TableModel) table.getModel()).fireTableDataChanged();
            table.repaint();
            table.revalidate();
        });
        
        deleteRows.addActionListener(e -> {
            ((TableModel) table.getModel()).deleteSelectedRows(table);
            table.repaint();
            table.revalidate();
        });
        
        checkConformity.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this, XmlFileSpecifications.checkXmlConformity(chooser.getSelectedFile()));
            }
        });

        calculatePropertiesMenuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this, XmlFileSpecifications.calculateProperties(chooser.getSelectedFile()));
            }
        });

    }

}
