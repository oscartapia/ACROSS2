
import java.util.*;
import java.awt.*;
/** This class together BallView.java determines all physics and visualization of a ball
 * @author Agustin Gonzalez
 * @author Camilo Barra
 * @author Roberto Cifuentes
 * @author Oscar Tapia
 * @version 27/05/2014
 */

public class Ball extends PhysicsElement implements Simulateable,SpringAttachable {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   private BallView view;  // Ball view of Model-View-Controller design pattern
   private ArrayList<Spring> springs; 
	private double a_t = 0;     
	 
   public Ball(){   // nobody can create a block without state
     this(1.0,0.1,0,0);
   }
   
   /** 
    * Constructor of the ball
    * @param mass			Determines the mass of an object ball
    * @param radius			Determines the radius of an object ball
    * @param position		Determines the position of an object ball
    * @param speed			Determines the speed of an object ball
     */
   public Ball(double mass, double radius, double position, double speed){
      super(id++);
      this.mass = mass;
      this.radius = radius;
      pos_t = position;
      speed_t = speed;
      view = new BallView(this);
		springs = new ArrayList<Spring>();
   }
   /**
    * Method that returns the ball's mass
    * @return mass 
    */
   public double getMass() {
      return mass;
   }
   /**
    * Method that returns the ball's radius
    * @return radius 
    */
   public double getRadius() {
      return radius;
   }
   /**
    * Method that returns the ball's position
    * @return pos_t
    */
   public double getPosition() {
      return pos_t;
   }
   /**
    * Method that returns the ball's speed
    * @return speed_t 
    */
   public double getSpeed() {
      return speed_t;
   }
   /**
    * Method determines the springs added to ball
    * @param s	spring added to spring's array
    */
	public void attachSpring(Spring s){		
		springs.add(s);
	}
	/**
	* Method returns the net force exerted by the springs
    * @return force sum of forces exerted on the ball
    */
	public double getNetForce() {
      double force=0;
      for (Spring s:springs) //Roam Springs attacched to the ball
      force+=s.getForce(this);
      return force;
		}
		
	/**
	    * Method not implemented
	    * @param s spring
	    */		
	public void detachSpring(Spring s){}
	
	/**
	    * Method computes the ball's next state
	    * @param delta_t 	amount of time used to calculate the next state
	    * @param world 		object that contains all the physical elements
	    */	
   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed. 
	 a_t= getNetForce()/mass;
	 Ball c=(Ball)(world.findCollidingBall(this));
     if ((b=c)!= null){ /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        pos_tPlusDelta = pos_t;
     } else {
        speed_tPlusDelta = speed_t + (a_t)*delta_t;
        pos_tPlusDelta = pos_t + speed_t*delta_t;
     }
     FixedHook f=(FixedHook)world.findCollidingFixedHook(this);
     if(f!=null){
    	 speed_t =-speed_t;
    	 speed_tPlusDelta = speed_t;
    	 pos_tPlusDelta = pos_t + speed_t*delta_t;
	 }
     
   }
   
   
   /**
    * Method determines whether the ball will collide with another ball
    * @param b 	this is a another ball
    * @return closeEnougth && approaching	returns true if the balls will collide 	
    */
   public boolean collide(Ball b) {
     if (this == b) return false;
     boolean closeEnougth = Math.abs(getPosition()-b.getPosition()) < (getRadius()+b.getRadius());
     boolean approaching = getSpeed() > b.getSpeed();
     if (b.getPosition() < getPosition())
        approaching = getSpeed() < b.getSpeed();
     return closeEnougth && approaching;
   }
   
   /**
    * Method determines whether the ball will collide with a fixed hook
    * @param f 	fixed hook
    * @return true if ball will collid with a fixed hook else returns false
    */
   public boolean collideHook(FixedHook f) {
	   double postball1=this.getPosition()-this.getRadius();
	   double posthook1=f.getPosition()+0.1;
	   if (postball1-posthook1>0){
		   return false; }
	   double postball2=this.getPosition()+this.getRadius();
	   double posthook2=f.getPosition()-0.1;
	   if (posthook2-postball2>0)
		   return false;
	   else return true;
	}
   
   /**
    * Method updates the ball's state
    */   
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }
   /**
    * Method updates the ball's view
    */
   public void updateView (Graphics2D g) {   // NEW
     view.updateView(g);  // update this Ball's view in Model-View-Controller design pattern     
   }
   
   /**
    * Method determines if cursor contains the ball'shape using ball's view
    * @param x 	cursor's coordinate x
    * @param y 	cursor's coordinate y
    * @return true if cursor contains the shape of the ball else false
    */
   public boolean contains(double x, double y) {
      return view.contains(x,y);
   }
   /**
    * Method ball selected change ball's color
    */
   public void setSelected(){
      view.setSelected();
   }
   /**
    * Method ball released change ball's color
    */
   public void setReleased(){
      view.setReleased();
   }
   /**
    * Method move the ball to cursor's position x
    * @param x cursor's coordinate x
    */
   public void dragTo(double x){
      pos_t=x;
   }
   /**
    * Method returns the name Ball more its identifier
    * @return a string with ball's description
    */
   public String getDescription() {
     return "Ball_" + getId()+":x";
   }
   /**
    * Method returns the ball's position
    * @return a string with ball's position
    */
   public String getState() {
     return getPosition()+"";
   }
}
