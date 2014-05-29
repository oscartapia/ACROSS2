import java.util.*;
import java.awt.event.*;


public class KeyBoardListener extends KeyAdapter {
	private MyWorld world;
	public KeyBoardListener(MyWorld w){
		world = w;
		
	}
	
	public void keyPressed(KeyEvent e){
		
		char key_n = 'n';
		char ch = e.getKeyChar();
		if(ch == key_n ){
			world.setN();
			/* n++;
			element = elements_contains.get(n);
			if(n>elements_contains.size()) n =0;
			*/
    
		} 
	}	

}
