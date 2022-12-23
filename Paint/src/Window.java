import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Window extends JFrame{
	private JButton openButton;
    private JButton saveButton;
    private JButton colorSelection;
    private PaintingPanel drawingPanel;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser = new JFileChooser("/home/");

    public Window() {
        super("Paint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new GridBagLayout());
        JPanel buttonPanel = getButtonPanel();
        panel.add(buttonPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 1, new Insets(0, 0, 0, 0), 0, 0));
        createDrawingPanel();
        createScrollPane();
        panel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH, 1, new Insets(0, 0, 0, 0), 0, 0));
        setVisible(true);
    }

    private void createScrollPane() {
        scrollPane = new JScrollPane(drawingPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void createDrawingPanel() {
        drawingPanel = new PaintingPanel(this);
        drawingPanel.addPaintListener(e -> {
            SwingUtilities.invokeLater(() -> scrollPane.getHorizontalScrollBar().setValue((int) e.getX()));
            SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue((int) e.getY()));
        });
    }

    private void createOpenButton() {
        openButton = new JButton("Open");
        openButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Opening file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
                try {
                    drawingPanel.setImage(ImageIO.read(fileChooser.getSelectedFile()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex, "Error occured: ", JOptionPane.PLAIN_MESSAGE);
                }
        });
    }


    private void createSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Saving file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
                try {
                    ImageIO.write(drawingPanel.getImage(), "png", fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(this, "File " + fileChooser.getSelectedFile() + " saved");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex, "Error occured: ", JOptionPane.PLAIN_MESSAGE);
                }
        });
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(0, 10, 10));
        colorSelection = new JButton("Color");
        colorSelection.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose a color", drawingPanel.getColor());
            if (color != null)
                drawingPanel.setColor(color);
        });
        buttonPanel.add(colorSelection);
        createOpenButton();
        buttonPanel.add(openButton);
        createSaveButton();
        buttonPanel.add(saveButton);
        return buttonPanel;
    }
}
