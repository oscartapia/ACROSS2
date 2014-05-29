import java.util.*;
import java.awt.*;

public class FixedHook extends PhysicsElement implements SpringAttachable {
    private static int id=0;  // Ball identification number
    double pos_t;
    private ArrayList<Spring> springs;  // ArrayList can grow, arrays cannot.
    private FixedHookView view;  //FixedHook view of Model-View-Controller design pattern

    public FixedHook(double position){
        super(++id);
        pos_t = position;
        springs = new ArrayList<Spring>();
        view = new FixedHookView(this);
    }

    public void attachSpring(Spring s){
        springs.add(s);
    }
    public double getPosition(){
        return pos_t;
    }
    public String getDescription(){
        return "FixedHook_" + getId();
    }
    public void computeNextState(double delta_t, MyWorld world) {}
    public void updateState(){}
    public void updateView (Graphics2D g) {   // NEW
        view.updateView(g);  // update this Ball's view in Model-View-Controller design pattern
    }
    public void detachSpring(Spring s){};
    public boolean contains(double x, double y) {
        return view.contains(x,y);
    }
    public void setSelected(){
        view.setSelected();
    }
    public void setReleased(){
        view.setReleased();
    }
    public void dragTo(double x){
        pos_t=x;
    }

    public String getState(){ //Return the position in string format
        String a=String.valueOf(pos_t); // Convert format double to String
        return a;
    }
}