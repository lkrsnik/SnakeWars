
public class SnakeTail  {
	
	private int oldX;
	private int oldY;
	private int mX;
	private int mY;
	private int mSpeed;
	public boolean isShown=false;
	
	public SnakeTail() {}

	
	public SnakeTail(int zacX, int zacY) {
		//mSpeed = speed;
		mX = zacX;
		mY = zacY;
	}
	public void previousState(){
		//You could eat the snake and than hit...
		try{
			mX = oldX;
			mY = oldY;
		} catch (Exception e) {}
	}
	
	public int getOldX() { return oldX; }
	public int getOldY() { return oldY; }
	
	public int getX() { return mX; }
	public int getY() { return mY; }
	public int getSpeed() { return mSpeed; }
	public void move(int x, int y) {
		oldX=mX;
		oldY=mY;
		mX=x;
		mY=y;
		if(!isShown){
			isShown=true;
		}
	}
	
}
