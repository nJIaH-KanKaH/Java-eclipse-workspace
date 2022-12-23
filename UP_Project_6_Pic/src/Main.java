import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main extends JFrame {
	final static String PATH = "C:/Java-eclipse-workspace/UP_Project_6_Pic";
	public static final String BASIC_IMG="belarus_photo3.jpg";
	final static int N = 2;
	private ImageIcon image;
	private JPanel panel;
	private int[][] imageNumber;
	private ImageIcon[] icons;
	private JPanel hintPanel;
	private int partHeight, partWidth;
	private int imageHeight, imageWidth;
	private static Utils.TextureAtlas atlas;
	private BufferedImage bufferedImage;

	public Main() {
		super("First task");
		setLayout(new GridLayout(1, 2, 0, 0));
		panel = new JPanel(new GridLayout(N, N, 0, 0));
		hintPanel = new JPanel(new GridLayout(1, 0, 0, 0));
		imageNumber = new int[N][N];
		
		atlas=new Utils.TextureAtlas(BASIC_IMG);

		imageHeight = atlas.image.getHeight();
		imageWidth = atlas.image.getWidth();

		partHeight = imageHeight / N;
		partWidth = imageWidth / N;

		setResizable(false);
		add(panel);
		add(hintPanel);
		hintPanel.add(new JLabel(new ImageIcon(Utils.resize(atlas.image, imageWidth/2, imageHeight/2))));
		setVisible(true);

		createMenu();
		initialisePartsOfImage();
		generateNewGame();

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		for (String fileItem : new String[] { "New", "Open...", /* "TestFeature", */ "Exit" }) {
			JMenuItem item = new JMenuItem(fileItem);
			item.setActionCommand(fileItem);
			item.addActionListener(e -> {
				if (e.getActionCommand().equals("New")) {
					generateNewGame();
				} else if (e.getActionCommand().equals("Exit")) {
					System.exit(0);
				} else if (e.getActionCommand().equals("Open...")) {
					openFile();
				}
			});
			fileMenu.add(item);

		}
		fileMenu.insertSeparator(1);
		fileMenu.insertSeparator(3);
		fileMenu.insertSeparator(5);

		menu.add(fileMenu);
		setJMenuBar(menu);
	}

	public void openFile() {
		JFileChooser jfc = new JFileChooser(PATH);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			try {
				BufferedImage img = ImageIO.read(selectedFile);
				atlas.image=Utils.resize(img, Utils.TextureAtlas.STANDARD_WIDTH, Utils.TextureAtlas.STANDART_HEIGHT);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Please, open right file!");
			}
		}
		imageHeight = atlas.image.getHeight();
		imageWidth = atlas.image.getWidth();
		partHeight = imageHeight / N;
		partWidth = imageWidth / N;

		panel.setPreferredSize(new Dimension(imageWidth + 10, imageHeight + 10));
		hintPanel.setPreferredSize(new Dimension(imageWidth + 10, imageHeight + 10));
		setPreferredSize(new Dimension(imageWidth * 2 + 20, imageHeight + 10));
		setBounds(0, 0, imageWidth * 2 + 20, imageHeight + 10);
		pack();

		hintPanel.removeAll();
		hintPanel.add(new JLabel(new ImageIcon(Utils.resize(atlas.image, imageWidth/2, imageHeight/2))));

		initialisePartsOfImage();
		generateNewGame();

	}

	public void generateNewGame() {
		int[] temp = new int[N * N];
		Random rand = new Random();
		int row, column;
		do {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i * N + j] = 0;
					imageNumber[i][j] = 0;
				}
			}

			for (int i = 0; i < N * N; i++) {
				do {
					row = rand.nextInt(N);
					column = rand.nextInt(N);
				} while (imageNumber[row][column] != 0);
				temp[row * N + column] = i;
				imageNumber[row][column] = i;
			}
		} while (canBeSolved(temp));

		repaintPanel();
	}
	
	public static boolean canBeSolved(int[] numbersOnField) {
		int inversions = 0;
		for (int i = 0; i < N * N; i++) {
			if (numbersOnField[i] == 0) {
				inversions += i / N +1;
				continue;
			}
			for (int j = i + 1; j < N * N; j++) {
				if (numbersOnField[j] > numbersOnField[i]) {
					inversions++;
				}
			}
		}
		return inversions % 2 == 0;
	}

	public void repaintPanel() {
		panel.removeAll();
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(partWidth, partHeight));
				button.setFocusable(false);
				index = imageNumber[i][j];
				if (index == 0)
					index = N * N - 1;
				else
					index--;
				button.setIcon(icons[index]);
				button.setText(Integer.toString(imageNumber[i][j]));
				panel.add(button);
				button.addActionListener(new MyActionListener());
				button.setVisible(true);
				if (imageNumber[i][j] == 0) {
					button.setVisible(false);
					System.out.println("button"+i+j);
					MyActionListener listener=(MyActionListener)(button.getActionListeners()[0]);
					listener.active=false;		
				}
			}
		}

		panel.validate();
		panel.repaint();
	}

	public void initialisePartsOfImage() {
		icons = new ImageIcon[N * N];
		BufferedImage part;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				part = atlas.cut((j) * partWidth, (i) * partHeight, partWidth, partHeight);
				icons[i * N + j] = new ImageIcon(part);
			}
		}
	}

	private class MyActionListener implements ActionListener {
		public boolean active=true;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(active) {
				JButton button = (JButton) e.getSource();
				int row = 0, column = 0;
				int num = Integer.parseInt(button.getText());
				int oldzX=0;int oldzY=0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (imageNumber[i][j] == num) {
							row = i;
							column = j;
							break;
						}
					}
				}
				if (column > 0) {
					if (imageNumber[row][column - 1] == 0) {
						imageNumber[row][column - 1] = num;
						imageNumber[row][column] = 0;
						oldzX=row;oldzY=column-1;
					}
				}
				if (column < N - 1) {
					if (imageNumber[row][column + 1] == 0) {
						imageNumber[row][column + 1] = num;
						imageNumber[row][column] = 0;
						oldzX=row;oldzY=column+1;
					}
				}
				if (row > 0) {
					if (imageNumber[row - 1][column] == 0) {
						imageNumber[row - 1][column] = num;
						imageNumber[row][column] = 0;
						oldzX=row-1;oldzY=column;
					}
				}
				if (row < N - 1) {
					if (imageNumber[row + 1][column] == 0) {
						imageNumber[row + 1][column] = num;
						imageNumber[row][column] = 0;
						oldzX=row+1;oldzY=column;
					}
				}
	
				if (checkAnswer()) {
					panel.removeAll();
					Graphics2D gr = (Graphics2D) panel.getGraphics();
					gr.drawImage(atlas.image, 0, 0, null);
				} else {
					JButton oldzB=(JButton)(panel.getComponentAt(oldzX, oldzY));
					int newI = imageNumber[row][column];
					if (newI == 0)
						newI = N * N - 1;
					else
						newI--;
					int oldI= imageNumber[oldzX][oldzY];
					if (oldI == 0)
						oldI = N * N - 1;
					else
						oldI--;
					button.setIcon(icons[newI]);
					oldzB.setIcon(icons[oldI]);
					button.setText(Integer.toString(0));
					oldzB.setText(Integer.toString(num));
					oldzB.setVisible(true);
					button.setVisible(false);
					
					for(int i=0;i<N;i++) {
						for(int j=0;j<N;j++) {
							System.out.print(imageNumber[i][j]+" ");
						}
						System.out.println();
					}
					((MyActionListener)(button.getActionListeners()[0])).active=false;
					((MyActionListener)(oldzB.getActionListeners()[0])).active=true;
					panel.revalidate();
					panel.repaint();
				}

			}
		}
	}

	public boolean checkAnswer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1)
					return imageNumber[i][j] == 0;
				else if (imageNumber[i][j] != i * N + j + 1)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		new Main();
	}

}