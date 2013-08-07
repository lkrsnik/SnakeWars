import java.util.Set;
import java.util.TreeSet;


public class SnakeHead{
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	
	public int oldX;
	public int oldY;
	private boolean hasTail=false;
	//private SnakeTail tail;
	//Set<SnakeTail> s = new TreeSet<SnakeTail>();
	
	private int oldDirection=-3;
	private int mTempDirection;
	private int mX;
	private int mY;
	private int mSpeed;
	
	public SnakeHead(int speed, int zacX, int zacY, int tempDirection) {
		mTempDirection=tempDirection;
		mSpeed = speed;
		mX = zacX;
		mY = zacY;
	}
	
	public SnakeHead(int speed) {
		this.mTempDirection=RIGHT;
		this.mSpeed = speed;
		this.mX = 60;
		this.mY = 60;
	}
	
	public void previousState(){
		//You could eat the cheese and than hit...
		try{
			mX = oldX;
			mY = oldY;
		} catch (Exception e) {}
	}
	
	public void setTempDirection(int tempDirection) { mTempDirection=tempDirection; }
	public int getTempDirection() { return mTempDirection; }
	public int getX() { return mX; }
	public int getY() { return mY; }
	public int getSpeed() { return mSpeed; }
	
	public void move(int step, int direction) {
		switch (direction) {
		case UP:
			if(oldDirection==DOWN){
				//System.out.println("Now!");
				move(step,DOWN);
				return;
			}
			oldX=mX;
			oldY=mY;
			mY -= step;
			//if(hasTail)
				//tail.move(oldX, oldY);
			break;
		case DOWN:
			if(oldDirection==UP){
				move(step,UP);
				return;
			}
			oldX=mX;
			oldY=mY;
			mY += step;
			//if(hasTail)
			//	tail.move(oldX, oldY);
			break;
		case RIGHT:
			if(oldDirection==LEFT){
				move(step,LEFT);
				return;
			}
			oldX=mX;
			oldY=mY;
			mX += step;
			//if(hasTail)
			//	tail.move(oldX, oldY);
			break;
		case LEFT:
			if(oldDirection==RIGHT){
				move(step,RIGHT);
				return;
			}
			oldX=mX;
			oldY=mY;
			mX -= step;
			//if(hasTail)
			//	tail.move(oldX, oldY);
			break;
		}
		oldDirection=direction;
	}
}