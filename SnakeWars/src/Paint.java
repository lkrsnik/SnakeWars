import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Paint extends JPanel implements KeyListener {	
	
	
	private Field mField;
	private int mSizeBeginX;
	private int mSizeBeginY;
	private int mSizeEndX;
	private int mSizeEndY;
	private Image mTailPicture;
	private Image mCheesePicture;
	private Image mHeadPicture;
	private Image mBackgroundPicture;
	//TEST
	private PrefTest test=new PrefTest();
	private BufferedImage finalImg;
	//TEST
	public Paint(Field field,int sizeBeginX,int sizeBeginY, int sizeEndX, int sizeEndY){
		mSizeBeginX=sizeBeginX;
		mSizeBeginY=sizeBeginY;
		mSizeEndX=sizeEndX;
		mSizeEndY=sizeEndY;
		mField=field;
		mTailPicture = loadPicture("rep.png");
		mCheesePicture = loadPicture("cheese.png");
		mHeadPicture = loadPicture("glava.png");
		mBackgroundPicture=new BufferedImage(20, 20, 5);
		this.addKeyListener(this);  // This class has its own key listeners.
        this.setFocusable(true);    // Allow panel to get focus
        
        //TEST
        //int type = mCheesePicture.getType();
        
        finalImg = new BufferedImage(mSizeEndX+2-mSizeBeginX, mSizeEndY+2-mSizeBeginY, 5);
        finalImg.createGraphics().drawRect(0, 0, mSizeEndX+1-mSizeBeginX, mSizeEndY+1-mSizeBeginY);
        //finalImg.createGraphics().drawRect(10, 10, 20,20);
        
        //TEST
        
        
	}
	
	
    protected void paintComponent(Graphics g) {
    	
      //final long startTime = System.nanoTime();
      
      super.paintComponent(g);
      //System.out.println("repaint: "+(System.nanoTime()-startTime));
      //finalImg.createGraphics().drawRect(0, 0, mSizeEndX+2-mSizeBeginX, mSizeEndY+2-mSizeBeginY);
      //finalImg.createGraphics().c
      
      
      
      
      
      //g.drawRect(mSizeBeginX-1, mSizeBeginY-1, mSizeEndX+1-mSizeBeginX, mSizeEndY+1-mSizeBeginY);
      
      //draw cheese
      
      if(mField.cheeseAdd!=null){
    	  Integer[] coordinatesCheAdd = mField.cheese.get(mField.cheeseAdd);
    	  finalImg.createGraphics().drawImage(mCheesePicture, coordinatesCheAdd[0]-mSizeBeginX+1, coordinatesCheAdd[1]-mSizeBeginY+1, null);
    	  mField.cheeseAdd=null;
      }
      
      
      /*if(mField.cheeseRemove!=null){
    	  Integer[] coordinatesCheRemove = mField.cheese.get(mField.cheeseRemove);
    	  finalImg.createGraphics().drawImage(mBackgroundPicture, coordinatesCheRemove[0], coordinatesCheRemove[1], null);
      }*/
      // ---------------- THE SLOWEST ------------- //
      
      /*for(int i=mSizeBeginY;i<=mSizeEndY;i+=20){
    	  for(int j=mSizeBeginX;j<=mSizeEndX;j+=20){
    		  if(mField.cheese.contains(i+","+j))
    			  g.drawImage(mCheesePicture, j, i, null);
    	  }
      }*/
      //String allCheese=mField.cheese.toString();
      //String[] cheeseCoord=allCheese.split(", ");
      
      //System.out.println(allCheese);
      //System.out.println(cheeseCoord[0]);
      
      //Object[] cheeseCoord=mField.cheese.values().toArray();
      
      
      // ---------------- THE FASTEST ------------- //
      
      /*for(Integer[] coordinates : mField.cheese.values())
      {
    	  
    	  g.drawImage(mCheesePicture, coordinates[0], coordinates[1], null);
    	  
      }*/
      
      // ------------------------------------------//
      /*for (int i=0;i<cheeseCoord.length;i++){
    	  Integer [] coordinates= (Integer[]) cheeseCoord[i];
    	  
    	  g.drawImage(mCheesePicture, coordinates[0], coordinates[1], null);
    	  
      }*/
      
      /*
      for (int i=0;i<cheeseCoord.length;i++){
    	  String[] aCheeseCoord = cheeseCoord[0].toString().split(",");
    	  g.drawImage(mCheesePicture,Integer.parseInt(aCheeseCoord[0]), Integer.parseInt(aCheeseCoord[1]), null);
      }*/
      
      //for(int i=1;i<=mField.cheese.size();i++){
        //g.drawImage(mCheesePicture, mField.cheese.get(i).getX(), mField.cheese.get(i).getY(), null);
      	//mField.cheese.get(i).getX();
      //}
      
      
      //draw head
      
      
      
      
      //test.start();
      //draw tail
      
      for(int j=0;j<mField.snakes.length;j++){
    	  if(mField.tails.get(j+1).size()>0){
    		  
    		  finalImg.createGraphics().drawImage(mBackgroundPicture,mField.tails.get(j+1).get(mField.tails.get(j+1).size()).getOldX()+1-mSizeBeginX,mField.tails.get(j+1).get(mField.tails.get(j+1).size()).getOldY()-mSizeBeginY+1,null);
    		  finalImg.createGraphics().drawImage(mTailPicture,mField.tails.get(j+1).get(1).getX()-mSizeBeginX+1,mField.tails.get(j+1).get(1).getY()-mSizeBeginY+1,null);
    		  
    	  }
      }
      
      
      //Paint picture
      test.start();
      g.drawImage(finalImg, mSizeBeginX-1,  mSizeBeginY-1, null);
      test.speed();
      //g.drawImage(mCheesePicture,80,80,null);
      //g.drawImage(mBackgroundPicture,100,100,null);
      //g.drawImage(mBackgroundPicture,60,60,null);
      //g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
      
      for(int j=0;j<mField.snakes.length;j++){
    	  g.drawImage(mHeadPicture, mField.snakes[j].getX(),mField.snakes[j].getY(),null);
      }
      
      /*for(int j=0;j<mField.snakes.length;j++){
	      if(0<mField.tails.get(j+1).size())
	    	  g.drawImage(mTailPicture,mField.tails.get(j+1).get(1).getX(),mField.tails.get(j+1).get(1).getY(),null);
	      for(int i=1;i<=mField.tails.get(j+1).size();i++){
	          if(mField.tails.get(j+1).get(i).isShown)
	        	  g.drawImage(mTailPicture,mField.tails.get(j+1).get(i).getX(),mField.tails.get(j+1).get(i).getY(),null);
	      }
      }*/
      
      /*if(1<mField.tail.size()){
	  if(mField.tail.get(mField.tail.size()).isShown)
		  g.drawImage(HeadPicture("rep.png"),mField.tail.get(mField.tail.size()).getX(),mField.tail.get(mField.tail.size()).getY(),null);
	  else
		  g.drawImage(HeadPicture("rep.png"),mField.tail.get(mField.tail.size()-1).getX(),mField.tail.get(mField.tail.size()-1).getY(),null);
  }*/
      
      
      
    }
      
    public Image loadPicture(String pictureName){
		  BufferedImage img = null;
		  
		  //
		  /*GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  GraphicsDevice device = env.getDefaultScreenDevice();
		  GraphicsConfiguration config = device.getDefaultConfiguration();
		  BufferedImage img = config.createCompatibleImage(20, 20, Transparency.TRANSLUCENT);*/
		  
		  
		  try {
			  
		      img = ImageIO.read(new File(pictureName));
		      System.out.print(img.getType());
		      
		  } catch (IOException e) {
		  }
		  return img;
	}


	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		//System.out.println(key.getKeyChar());
		switch(key.getKeyCode()){
			case KeyEvent.VK_UP:	mField.snakes[0].setTempDirection(0);	break;
			case KeyEvent.VK_DOWN:	mField.snakes[0].setTempDirection(1);	break;
			case KeyEvent.VK_RIGHT:	mField.snakes[0].setTempDirection(2);	break;
			case KeyEvent.VK_LEFT:	mField.snakes[0].setTempDirection(3);	break;
			case KeyEvent.VK_W:		mField.snakes[1].setTempDirection(0);	break;
			case KeyEvent.VK_S:		mField.snakes[1].setTempDirection(1);	break;
			case KeyEvent.VK_D:		mField.snakes[1].setTempDirection(2);	break;
			case KeyEvent.VK_A:		mField.snakes[1].setTempDirection(3);	break;
			case KeyEvent.VK_T:		mField.snakes[2].setTempDirection(0);	break;
			case KeyEvent.VK_G:		mField.snakes[2].setTempDirection(1);	break;
			case KeyEvent.VK_H:		mField.snakes[2].setTempDirection(2);	break;
			case KeyEvent.VK_F:		mField.snakes[2].setTempDirection(3);	break;
			case KeyEvent.VK_I:		mField.snakes[3].setTempDirection(0);	break;
			case KeyEvent.VK_K:		mField.snakes[3].setTempDirection(1);	break;
			case KeyEvent.VK_L:		mField.snakes[3].setTempDirection(2);	break;
			case KeyEvent.VK_J:		mField.snakes[3].setTempDirection(3);	break;
			default: break;
		
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}

 }

