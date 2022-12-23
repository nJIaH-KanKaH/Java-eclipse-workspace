import java.util.Calendar;
import java.awt.*;
import javax.swing.JPanel;
 
public class Tsk1 extends JPanel {

		public static final int MOMENT=Calendar.getInstance().get(Calendar.SECOND);
		
        public Tsk1() {

            setDoubleBuffered(true);
            setPreferredSize(new Dimension(600, 400));
        }

        private void drawCircle(Graphics g, Point center, int radius) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(center.x - radius / 2, center.y - radius / 2, radius, radius);
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
    	    int radius = Math.min(O.x, O.y) - 20;
    	    int radiusSecond = radius - 10;
    	    double angle;
    	    for (int i = 0; i < 12; i++) {
    	        angle = Math.PI / 2 - i * Math.PI / 6;
    	        Point point = getEndPoint(angle, radius);
    	        drawCircle(g, point, 8);
    	    }
    	    Calendar time = Calendar.getInstance();
    	    int second = time.get(Calendar.SECOND)-MOMENT;
    	    angle = Math.PI / 2 - second * Math.PI / 30;
    	    Point p = getEndPoint(angle, radiusSecond);
    	    g.setColor(Color.RED);
    	    g.drawLine(O.x, O.y, p.x, p.y);
    	}
        
        private int sign (int x) {
        	return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        }

        public void drawBresenhamLine (int xstart, int ystart, int xend, int yend, Graphics g)
        {
        	int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        	dx = xend - xstart;
        	dy = yend - ystart;

        	incx = sign(dx);

        	incy = sign(dy);

        	if (dx < 0) dx = -dx;
        	if (dy < 0) dy = -dy;

        	if (dx > dy)
        	{
        		pdx = incx;	pdy = 0;
        		es = dy;	el = dx;
        	}
        	else
        	{
        		pdx = 0;	pdy = incy;
        		es = dx;	el = dy;
        	}

        	x = xstart;
        	y = ystart;
        	err = el/2;
        	g.fillRect(x, y, 1, 1);

        	for (int t = 0; t < el; t++)
        	{
        		err -= es;
        		if (err < 0)
        		{
        			err += el;
        			x += incx;
        			y += incy;
        		}
        		else
        		{
        			x += pdx;
        			y += pdy;
        		}

        		g.fillRect(x, y, 1, 1);
        	}
        }
}