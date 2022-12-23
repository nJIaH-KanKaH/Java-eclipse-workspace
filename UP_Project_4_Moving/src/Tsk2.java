import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Calendar;

import javax.swing.JPanel;
public class Tsk2 extends JPanel {	
		public static final int MOMENT=Calendar.getInstance().get(Calendar.SECOND);
		public static Utils.Sprite planetSprite;
		public long sp=0l;
		public int speed=0;
		
        public Tsk2(String fileName,float scale) {
        	planetSprite=new Utils.Sprite(Utils.ResourceLoader.loadImage(fileName), scale);
            setDoubleBuffered(true);
            setPreferredSize(new Dimension(640, 480));
        }
     
        private Point getEndPoint(double angle, int radius) {
            Point O = new Point(getSize().width / 2, getSize().height / 2);
            int x = (int) (O.x + radius * Math.cos(angle));
            int y = (int) (O.y - radius * Math.sin(angle));
            return new Point(x, y);
        }
        
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            drawClock(g);
        }
        
        private void drawClock(Graphics g) {
    	    Point O = new Point(getSize().width / 2, getSize().height / 2);
    	    int radius = Math.min(O.x, O.y) - 70;
    	    double angle;
    	    angle = Utils.calc(sp/*, radius*/);
    	    Point p = getEndPoint(angle, radius);
    	    g.setColor(Color.RED);
    	    g.drawLine(O.x, O.y, p.x, p.y);
    	    planetSprite.render(g, p.x, p.y);
    	}
}