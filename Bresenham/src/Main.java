import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
 
    public Main() {
        super("Bresenham's algorithm");
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public int sign (int x) {
    	return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    public void drawBLine (int xstart, int ystart, int xend, int yend, Graphics g)
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
    
    public void drawBCircle(int x0, int y0, int radius,Graphics g)
    {
        int x = radius;
        int y = 0;
        int radiusError = 1 - x;
        while (x >= y)
        {
            g.fillRect(x + x0, y + y0,1,1);
            g.fillRect(y + x0, x + y0,1,1);
            g.fillRect(-x + x0, y + y0,1,1);
            g.fillRect(-y + x0, x + y0,1,1);
            g.fillRect(-x + x0, -y + y0,1,1);
            g.fillRect(-y + x0, -x + y0,1,1);
            g.fillRect(x + x0, -y + y0,1,1);
            g.fillRect(y + x0, -x + y0,1,1);
            y++;
            if (radiusError < 0)
            {
                radiusError += 2 * y + 1;
            }
            else
            {
                x--;
                radiusError += 2 * (y - x + 1);
            }
        }
    }
 
     public void drawLines(Graphics g) {
        int x1=(int) (Math.random()*200)+200;
        int y1=(int) (Math.random()*200)+200;
        int x2=(int) (Math.random()*200)+200;
        int y2=(int) (Math.random()*200)+200;
        int r=60;
        int x0=(x1+x2)/2;
        int y0=(y1+y2)/2;
        drawBLine(x1, y1, x2, y2, g);
        drawBCircle(x0, y0, r, g);
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}