import java.awt.event.*; 
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LabMenuListener implements ActionListener {
   private MyWorld  world;
   public LabMenuListener (MyWorld  w){
      world = w;
   }
   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      
      // Actions associated to main manu options
      if (text.equals("My Scenario")){
	     Ball b0 = new Ball(1,0.1,2.5,1);
	     FixedHook f0 = new FixedHook(1.0);
	     Spring s0 = new Spring(1,1);
	     s0.attachAend(f0);
	     s0.attachBend(b0);
	     world.addElement(b0);
             world.addElement(s0);
	     world.addElement(f0);
      }
	  
      if (text.equals("Clear All")) {  
             world.clearElements();
      }

      if (text.equals("Ball")) {
             Ball b0 = new Ball(1, 0.1,4.0, 1);
	     world.addElement(b0);
       
      }
      
      
      if (text.equals("FixedHook")){
    	     FixedHook f0 = new FixedHook(1.0);
    	     world.addElement(f0);
    	  
      } ; 

      if (text.equals("Spring")){
    	     Spring s0 = new Spring(1,1);
    	     world.addElement(s0); 	  
      }

     
      if (text.equals("Start")){
	     world.start();

      } ;
      if (text.equals("Stop")){
             world.stop();
      }
			   
      if (text.equals("Delta time")) {
             String data = JOptionPane.showInputDialog("Enter delta t [s]");
             world.setDelta_t(Double.parseDouble(data));
      }
		
      if (text.equals("Refresh time")){
     	     String data = JOptionPane.showInputDialog("Enter Refresh t [s]");
	     world.setRefreshPeriod(Double.parseDouble(data));		
      }

   }
}
