
import java.util.*;
import java.io.*;

import javax.swing.Timer;

import java.awt.event.*;
/** This class determines update the position and view of all physics elements 
 * @author Agustin Gonzalez
 * @author Camilo Barra
 * @author Roberto Cifuentes
 * @author Oscar Tapia
 * @version 27/05/2014
 */
public class MyWorld implements ActionListener {
   private PrintStream out;
   private int N=0;
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
   private MyWorldView view;   // NEW
   private Timer passingTime;   // NEW
   private double t;        // simulation time
   private double delta_t;        // in seconds
   private double refreshPeriod;  // in seconds
   private ArrayList<PhysicsElement> elements_contains = new ArrayList<PhysicsElement>(); 
   
   /**
    * Default contructor
    */
   public MyWorld(){
      this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
   }
   
   /**
    * This method implement a listener that compute and update the states of all physics elements each 60 milliseconds
    * @param output
    */
   public MyWorld(PrintStream output){
      out = output;
      t = 0;
      refreshPeriod = 0.06;      // 60 [ms]
      delta_t = 0.00001;          // 0.01 [ms]
      elements = new ArrayList<PhysicsElement>();
      view = null;
      passingTime = new Timer((int)(refreshPeriod*1000), this);
      passingTime.addActionListener(this);
   }
   
   /**
    * This method clear the array whit all elements and repained each element
    */
   public void clearElements(){
		elements.clear();
		view.repaintView();
   }

   /**
    * This method adds a physics element to a array and repained the element
    * @param e physics element 
    */
   public void addElement(PhysicsElement e) {
      elements.add(e);
      view.repaintView();
   }
   /**
    * This method set view
    * @param view
    */
   public void setView(MyWorldView view) {
      this.view = view;
   }
   /**
    * set the delta time
    * @param delta delta time
    */
   public void setDelta_t(double delta) {
      delta_t = delta;
   }
   /**
    * set the refresh period and convert it to milliseconds
    * @param rp refreshed period
    */
   public void setRefreshPeriod (double rp) {
      refreshPeriod = rp;
      passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
   }
   /**
    * Start the simulation
    */
   public void start() {
      if(passingTime.isRunning()) return;
      passingTime.start();      
   }
   /**
    * Stop the simulation
    */
   public void stop(){
		if(passingTime.isRunning())
			passingTime.stop();
   }
   
   /** Method compute an update the state of the each physics elements and repaint each element
    * @param event 60 milliseconds elapsed
    */
   public void actionPerformed (ActionEvent event) {  // like simulate method of Assignment 1, 
      double nextStop=t+refreshPeriod;                // the arguments are attributes here.
      for (; t<nextStop; t+=delta_t){
         for (PhysicsElement e: elements)
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.computeNextState(delta_t,this); // compute each element next state based on current global state
            }
         for (PhysicsElement e: elements)  // for each element update its state. 
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.updateState();            // update its state
            }
      }
        repaintView();
   }
   /**
    * Call repaint of myworldview
    */
   public void repaintView(){
      view.repaintView();
   }
/**
 * Analyze all balls that can collide with me (ball)
 * @param me ball
 * @return the ball that call collide
 */
   public Ball findCollidingBall(Ball me) {
      for (PhysicsElement e: elements){
         if ( e instanceof Ball) {
            Ball b = (Ball) e;
            if ((b!=me) && b.collide(me)) return b;
         }
		 }
      return null;
   }
   
   public FixedHook findCollidingFixedHook(Ball me) {
	      for (PhysicsElement e: elements){
	         if ( e instanceof FixedHook) {
	            FixedHook f = (FixedHook) e;
	            if (me.collideHook(f)){ 
	            	return f;
	            }
	         }
			 }
	      return null;
	}
	 
 /**
  * Method returns a element of array's physics elements
  * @return elements 
  */
   public ArrayList<PhysicsElement> getPhysicsElements(){
      return elements;
   }
   /**
    * Increase N, N is used when change elements that contains the cursor
    */
   public void setN(){
	   N++;   
   }
   /**
	* Method find a element that contains the cursor and save it in elements_contains, using a key "n" can be changed to return other element
    * @param x 	cursor's coordinate x
    * @param y 	cursor's coordinate y
    * @return elements_contains.get(N) element selected with key "n".
    */
   public PhysicsElement  find(double x, double y) {
	  elements_contains.clear();
	   for(PhysicsElement e: elements) 
		   if(e.contains(x, y)){
			   elements_contains.add(e);
		   }
	   if(elements_contains.size()>=1){
		   if(N>elements_contains.size())
			   N=0;
		   return elements_contains.get(N);
	   }
	   return null;
}
}
