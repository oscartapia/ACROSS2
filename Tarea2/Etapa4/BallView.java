
import java.awt.*;
import java.awt.geom.*;
/** This class determines the visualization of a ball
 * @author Agustin Gonzalez
 * @author Camilo Barra
 * @author Roberto Cifuentes
 * @author Oscar Tapia
 * @version 27/05/2014
 */
public class BallView {
   private Color color = Color.BLUE;
   private Ellipse2D.Double shape = null;
   private Ball ball;
   /**
    * Constructor creates ball shape
    * @param b ball
    */
   public BallView (Ball b){
       ball = b;
       shape = new Ellipse2D.Double(b.getPosition()-b.getRadius(), -b.getRadius(), 2*b.getRadius(), 2*b.getRadius());
   }

   /**
    * Method determines if cursor contains the ball'shape
    * @param x 	cursor's coordinate x
    * @param y 	cursor's coordinate y
    * @return true if cursor contains the shape of the ball else false
    */
    public boolean contains (double x, double y){
        double distance = Math.pow(x-shape.getCenterX(), 2.0) + Math.pow(y-shape.getCenterY(),
                2.0);
        if (distance <= Math.pow(ball.getRadius(), 2.0))
            return true;
        else
            return false;
        
   }
    /**
     * Method changes ball's color to red when ball is selected
     */
   public void setSelected (){
      color = Color.RED;
   }
   /**
    * Method changes ball's color to blue when ball is released
    */
   public void setReleased() {
      color = Color.BLUE;
   }
   
   /**
    * Method updated the shape adds color and full the ball
    * @param g is object graphics of MyWorldView(that extends JPanel)
    */
   void updateView(Graphics2D g) {
      double radius = ball.getRadius();
      shape.setFrame(ball.getPosition()-radius, -radius, 2*radius, 2*radius);
      g.setColor(color);
      g.fill(shape);
   }
}
