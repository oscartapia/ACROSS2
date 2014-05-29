import java.awt.*;
import java.awt.geom.*;

public class FixedHookView {
   private final double width=0;  // fixed point width
   private Color color = Color.GREEN;
   private Rectangle2D.Double shape = null;
   private FixedHook hook;

    public FixedHookView (FixedHook h){
        hook = h;
        shape = new Rectangle2D.Double(h.getPosition()-0.1, -0.1, 0.2, 0.2);
    }


    public boolean contains (double x, double y){
    	double x1,x2,y1,y2;
    	double a=0.2;

    	x1=shape.getX();
    	x2=x1+shape.getWidth();
    	y1=shape.getY();
    	y2=y1+shape.getHeight();
    	if((x>=x1)&&(x<=x2)&&(y>=y1)&&(y<=y2)){return true;}
    	else return false;
}
    public void setSelected (){
        color = Color.YELLOW;
    }
    public void setReleased() {
        color = Color.GREEN;
    }
    void updateView(Graphics2D g) {
        shape.setFrame(hook.getPosition()-0.1, -0.1, 2*0.1, 2*0.1);
        g.setColor(color);
        g.fill(shape);
    }
}

