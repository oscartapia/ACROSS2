import java.util.*;
import java.awt.*;
/** This class together BallView.java determines all physics and visualization of a ball
 * @author Agustin Gonzalez
 * @author Camilo Barra
 * @author Roberto Cifuentes
 * @author Oscar Tapia
 * @version 20/06/2014
 */

public class Ball extends PhysicsElement implements Simulateable, SpringAttachable {
    private static int id=0;  // Ball identification number
    private final double mass;
    private final double radius;
    private double pos_t;     // current position at time t
    private double pos_tPlusDelta;  // next position in delta time in future
    private double speed_t;   // speed at time t
    private double speed_tPlusDelta;   // speed in delta time in future
    private double a_t;    // acceleration at time t
    private double a_tMinusDelta;
    private BallView view;  // Ball view of Model-View-Controller design pattern
    private ArrayList<Spring> springs;
    private SimpleAudioPlayer sound = new SimpleAudioPlayer();
    private Ball(){   // nobody can create a block without state
        this(1.0,0.1,0,0);
    }
    
   /** 
    * Constructor of the ball
    * @param mass           Determines the mass of an object ball
    * @param radius         Determines the radius of an object ball
    * @param position       Determines the position of an object ball
    * @param speed          Determines the speed of an object ball
     */
    public Ball(double mass, double radius, double position, double speed){
        super(id++);
        this.mass = mass;
        this.radius = radius;
        pos_t = position;
        speed_t = speed;
        a_t=a_tMinusDelta=0;
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
    * Method returns the net force exerted by the springs
    * @return force sum of forces exerted on the ball
    */
    public double getNetForce(){
        double force=0;
        for (Spring s:springs)
            force+=s.getForce(this);
        return force;
    }
    public void computeNextState(double delta_t, MyWorld world) {
        Ball b;  // Assumption: on collision we only change speed.
        if ((b=world.findCollidingBall(this))!= null){ /* elastic collision */
            speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
            pos_tPlusDelta = pos_t;
            sound.BallSound();
        } else {
            a_t= getNetForce()/mass;
            speed_tPlusDelta = speed_t+(3*a_t-a_tMinusDelta)*delta_t/2;
            pos_tPlusDelta = pos_t + speed_t*delta_t + (4*a_t-a_tMinusDelta)*delta_t*delta_t/2;
        }
    }
    
    
   /**
    * Method determines whether the ball will collide with another ball
    * @param b  this is a another ball
    * @return closeEnougth && approaching   returns true if the balls will collide  
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
    * Method updates the ball's state
    */
    public void updateState(){
        pos_t = pos_tPlusDelta;
        speed_t = speed_tPlusDelta;
        a_tMinusDelta=a_t;
    }
   /**
    * Method updates the ball's view
    */
    public void updateView (Graphics2D g) {   // NEW
        view.updateView(g);  // update this Ball's view in Model-View-Controller design pattern
    }
    
   /**
    * Method determines if cursor contains the ball'shape using ball's view
    * @param x  cursor's coordinate x
    * @param y  cursor's coordinate y
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
   /**
    * Method determines the springs added to ball
    * @param s  spring added to spring's array
    */
    public void attachSpring(Spring s){
        springs.add(s);
    }
         
    /**
        * Method not implemented
        * @param s spring
        */
    public void detachSpring(Spring s){
        springs.remove(s);
    }
}
