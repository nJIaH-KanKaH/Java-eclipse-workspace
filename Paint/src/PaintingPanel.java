import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PaintingPanel extends JPanel{

	public static final Dimension PANEL_BOUNDS=new Dimension(120, 120);

	public static final int WIDTH=400;
	public static final int HEIGHT=300;
	public static final Dimension FRAME_SIZE=new Dimension(WIDTH,HEIGHT);
	public interface PaintListener {
        void painted(Point pont);
    }

    public void addPaintListener(PaintListener instance) {
        paintListener = instance;
    }

    BufferedImage currentImage;
    private Point currentImageUsedSize;        
    private PaintListener paintListener;
    private Point currentPoint;
    private Color color;
    private Window containingWindow;

    public PaintingPanel(Window instance) {
        containingWindow = instance;
        currentImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        currentImage.getGraphics().setColor(Color.red);

        currentImageUsedSize = new Point(containingWindow.getWidth(), containingWindow.getHeight());
        setBackground(Color.white);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentPoint = new Point(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point nextPoint = new Point(e.getX(), e.getY());
                addLine(currentPoint, nextPoint, color);
                currentPoint = nextPoint;
                if(! isMouseWithinWindow()) {
                    paintListener.painted(currentPoint);
                }
                revalidate();
                repaint();
            }
        });

    }

    private void addLine(Point pointA, Point pointB, Color color) {
        currentImageUsedSize.x = Math.max(currentImageUsedSize.x, Math.max(pointA.x, pointB.x));
        currentImageUsedSize.y = Math.max(currentImageUsedSize.y, Math.max(pointA.y, pointB.y));
        if(pointA.x > currentImage.getWidth() || pointB.x > currentImage.getWidth() || pointA.y > currentImage.getHeight() || pointB.y > currentImage.getHeight()) {
            BufferedImage temporaryImage = new BufferedImage(5000 +  currentImage.getWidth(), 5000 + currentImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            temporaryImage.getGraphics().drawImage(currentImage, 0, 0, null);
            currentImage = temporaryImage;
            System.gc();
        }
        currentImage.getGraphics().setColor(color);
        currentImage.getGraphics().drawLine(pointA.x, pointA.y, pointB.x, pointB.y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color instance) {
        color = instance;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(currentImage, 0, 0, null);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(currentImageUsedSize.x, currentImageUsedSize.y);
    }
    public boolean isMouseWithinWindow()
    {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        Rectangle bounds = containingWindow.getBounds();
        bounds.setLocation(containingWindow.getLocationOnScreen());
        return bounds.contains(mousePos);
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(image.getGraphics());
        return image;
    }

    public void setImage(BufferedImage instance) {
        currentImage = instance;
        revalidate();
        repaint();
    }
}
