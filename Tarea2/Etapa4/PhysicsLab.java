import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;

public class PhysicsLab {
   public static void main(String[] args) {
      PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
      lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      lab_gui.setVisible(true);
   }
}

class PhysicsLab_GUI extends JFrame {
   public PhysicsLab_GUI() {
  		   
		setTitle("My Small and Nice Physics Laboratory");
      setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+200);  // height+50 to account for menu height
      MyWorld world = new MyWorld();
      MyWorldView  worldView = new MyWorldView(world);
      world.setView(worldView);
      add(worldView);  
      LabMenuListener menuListener = new LabMenuListener(world);
      /*  .....   */;
		createConfiguration(world);
		setJMenuBar(createLabMenuBar(menuListener));
		   }

   public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
      JMenuBar mb = new JMenuBar();
      
      JMenu menu1 = new JMenu ("Configuration");
      mb.add(menu1);
      JMenu menu = new JMenu("MyWorld");
      mb.add(menu);
	  
	  JMenu subMenu = new JMenu("Insert");  
      menu1.add(subMenu);
      
      JMenuItem menuItem = new JMenuItem("Ball");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      
      JMenuItem menuItem1 = new JMenuItem("FixedHook");
      menuItem1.addActionListener(menu_l);
      subMenu.add(menuItem1);
      
      JMenuItem menuItem2 = new JMenuItem("Spring");
      menuItem2.addActionListener(menu_l);
      subMenu.add(menuItem2);
 /*....*/      
      
      menuItem = new JMenuItem("Start");
      menuItem.addActionListener(menu_l);
      menu.add(menuItem);		
/* ...*/
	  menuItem = new JMenuItem("My Scenario");
      menuItem.addActionListener(menu_l);
      menu1.add(menuItem);	
	  
	  menuItem = new JMenuItem("Stop");
      menuItem.addActionListener(menu_l);
      menu.add(menuItem);
	  
	  menuItem = new JMenuItem("Clear All");
      menuItem.addActionListener(menu_l);
      menu.add(menuItem);
		
		
		JMenu subMenu1 = new JMenu("simulator");
		menu.add(subMenu1);
		
		menuItem = new JMenuItem("Delta time");
      menuItem.addActionListener(menu_l);
      subMenu1.add(menuItem);
		
		menuItem = new JMenuItem("Refresh time");
      menuItem.addActionListener(menu_l);
      subMenu1.add(menuItem);

		
      return mb;          
   }
	private void createConfiguration(MyWorld world) {  // Please note how similar it is to "Etapa 4" of T1
      double mass = 1.0;      // 1 [kg] 
      double radius = 0.1;    // 10 [cm]
      double position = 0.5;  // 1 [m] 
      double speed = 1.0;     // 0.5 [m/s]
      FixedHook b0 = new FixedHook(0.5);
      Ball b1 = new Ball(mass, radius, 1.0, 0);
		
	  Ball b2 = new Ball(mass, radius, 1.5, 0);
      Ball b3 = new Ball(mass, radius, 5.0, 0);
		
		
		Spring s1 = new Spring(1,1);
		Spring s2 = new Spring(1,1);
		Spring s3 = new Spring(1,1);
		
		s1.attachAend(b0);
		s1.attachBend(b1);
		
		s2.attachAend(b2);
		s2.attachBend(b3);
		
		s3.attachAend(b1);
		s3.attachBend(b2);


		world.addElement(s3);
		
		world.addElement(s1);
        world.addElement(b0);
        world.addElement(b1);
		
		world.addElement(s2);
        world.addElement(b2);
        world.addElement(b3);

		
		
   }

	
	   
}