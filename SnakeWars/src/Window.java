import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Window extends JFrame {
	  public Window(String naslov) {
	    setTitle(naslov);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Toolkit tk=Toolkit.getDefaultToolkit();
	    Dimension d=tk.getScreenSize();
	    int width=d.width;
	    int height=d.height;
	    setBounds(width/8,height/8,3*width/4,3*height/4);
	} 
}
