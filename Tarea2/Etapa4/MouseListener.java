import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.geom.Point2D;

public class MouseListener extends MouseAdapter {
   private MyWorld world;
   private PhysicsElement currentElement;
   public MouseListener (MyWorld w){
      world = w;
      currentElement=null;
   } 
   public void mouseMoved(MouseEvent e) {
      Point2D.Double p = new Point2D.Double(0,0); // Change mouse coordenates from
      MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);// pixels to meters.
      PhysicsElement newElement = world.find(p.getX(), p.getY()); 
      if (newElement == currentElement) return;
      if (currentElement != null) {
         currentElement.setReleased();
         currentElement = null;
      }
      if (newElement != null) { 
         currentElement = newElement;
         currentElement.setSelected();
      }
      world.repaintView();
   }
   public void mouseDragged(MouseEvent e) {
	   Point2D.Double p= new Point2D.Double(0,0);
           MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);

	   if(currentElement!=null){
		   currentElement.dragTo(p.getX());
	   }
       world.repaintView();
   }
   public void mouseReleased(MouseEvent e) {
      if (currentElement == null) return;
      if (currentElement instanceof Spring) {
         Point2D.Double p= new Point2D.Double(0,0);
         MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);

          // we dragged a spring, so we look for and attachable element near by  
         PhysicsElement element1 = world.find(p.getX(),p.getY());
         if(!(element1 instanceof Spring)){
        	 SpringAttachable element= (SpringAttachable) element1; //Encontro un SpringAttachable
        	 if (element != null) {
            // we dragged a spring and it is near an attachable element,
            // so we hook it to a spring end.
        	 	Spring spring = (Spring) currentElement;
        	 	double a=spring.getAendPosition();
        	 	double b=spring.getBendPosition();
        	 	double difA=Math.abs((a-p.getX())/a);
        	 	double difB=Math.abs((b-p.getX())/b);
        	 	if (difA>0.005)
        	 		spring.attachAend(element);
           
        	 	if (difB>0.005)
        	 		spring.attachBend(element);
        	 }
         }
      }    
      currentElement.setReleased();
      currentElement = null;
      world.repaintView();
   }
}
