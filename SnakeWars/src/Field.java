import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;




public class Field{
	private int mSizeBeginX;
	private int mSizeBeginY;
	private int mSizeEndX;
	private int mSizeEndY;
	public SnakeHead [] snakes;
	public HashMap<String,Integer[]> cheese = new HashMap<String,Integer[]>();
	//public TreeMap<Integer, SnakeTail> tail = new TreeMap<Integer, SnakeTail>();
	public TreeMap<Integer, TreeMap<Integer, SnakeTail>> tails = new TreeMap<Integer, TreeMap<Integer, SnakeTail>>();
	String cheeseAdd=null;
	//String cheeseRemove=null;
	
	public Field(SnakeHead [] inSnakes,int sizeBeginX,int sizeBeginY, int sizeEndX, int sizeEndY) {
		mSizeBeginX=sizeBeginX;
		mSizeBeginY=sizeBeginY;
		mSizeEndX=sizeEndX;
		mSizeEndY=sizeEndY;
		snakes=inSnakes;
		for(int i=1;i<=inSnakes.length;i++){
			tails.put(i, new TreeMap<Integer, SnakeTail>());
		}
	}
	
	public void run(Paint rp) {
		boolean running=true;
		while(running){
			rp.repaint();
			addCheese(0.08);
			//if(eatCheese())
			boolean[] snakesThatEat = eatCheese();
			for(int j=0;j<snakes.length;j++){
				if(snakesThatEat[j])
					addTail(j+1);
			}
			//running=premikanje(200,snakes[0].getTempDirection(),rp);
			
			running=premikanje(200,rp);
			
		} 
		//for(int j=0;j<snakes.length;j++)
		//	snakes[j].previousState();
		
		rp.repaint();
	}
	
	//private boolean premikanje(int speed,int direction, Paint rp){
	private boolean premikanje(int speed, Paint rp){
	   for(int j=0;j<snakes.length;j++){
		   	snakes[j].move(20, snakes[j].getTempDirection());
		   	if(tails.get(j+1).size()>0)
		   		tails.get(j+1).get(1).move(snakes[j].oldX,snakes[j].oldY);
		   	for(int i=2;i<=tails.get(j+1).size();i++){
		   		tails.get(j+1).get(i).move(tails.get(j+1).get(i-1).getOldX(), tails.get(j+1).get(i-1).getOldY());
		   	}
		   	if(!stillMoves()){
		   		snakes[j].previousState();
		   		for(int i=1;i<=tails.get(j+1).size();i++){
		   			tails.get(j+1).get(i).previousState();
		   		}
		   		return false;
		   	}
	   }
	   	//for(int i=1;i<=tail.size();i++){
	   	//	tail.get(i).move(snakes[0].oldX,snakes[0].oldY);
	   	//}
	   	try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   	return true;
	}
	private boolean stillMoves(){
		//System.out.println("Zdaj");
		//boolean stillMoving=true;
		for(int j=0;j<snakes.length;j++){
			if(!(snakes[j].getX()<(mSizeEndX) && snakes[j].getX()>mSizeBeginX-20 &&
					snakes[j].getY()<(mSizeEndY) && snakes[j].getY()>mSizeBeginY-20)){
				
				//System.out.println(snakes[0].getX());
				return false;
			}
			for(int k=0;k<snakes.length;k++){
				for(int i=1;i<=tails.get(k+1).size();i++){
					if(tails.get(k+1).get(i).getX()==snakes[j].getX() &&tails.get(k+1).get(i).getY()==snakes[j].getY())
						return false;
				}
				if(j!=k && snakes[j].getX()==snakes[k].getX() && snakes[j].getY() == snakes[k].getY())
					return false;
			}
		}	
		
		return true;
	}
	private void addCheese(double occurRate){
		double random=Math.random();
		if(random<occurRate){
			int ID=(int)(((random*100)/(occurRate*100))*((mSizeEndX-mSizeBeginX)/20*(mSizeEndY-mSizeBeginY)/20));
			Integer locX=(ID%((mSizeEndX-mSizeBeginX)/20))*20+mSizeBeginX;
			Integer locY=(int)((ID/((mSizeEndX-mSizeBeginX)/20))*20)+mSizeBeginY;
			if(addElement(locX,locY)){
				//Cheese aCheese=new Cheese(locX,locY);
				//Integer x=new Integer(locX);
				//Integer y=new Integer(locY);
				Integer[] coordinates={locX,locY};
				cheese.put(locX+","+locY,coordinates);
				cheeseAdd=locX+","+locY;
				//System.out.println("Cheese created: "+locX+","+locY);
			}
		}
	}
	private boolean addElement(int x, int y){
		/*for(int i=1;i<=cheese.size();i++){
			
			if(cheese.get(i).getX()==x && cheese.get(i).getY()==y)
				return false;
		}*/
		if(cheese.containsKey(x+","+y))
			return false;
		if(snakes[0].getX()==x && snakes[0].getY() == y)
			return false;
		
		return true;
	}
	private boolean [] eatCheese(){
		boolean[] results=new boolean [snakes.length]; 
		for(int j=0;j<snakes.length;j++){
			results[j]=false;
		}
		for(int j=0;j<snakes.length;j++){
			if(cheese.containsKey(snakes[j].getX()+","+snakes[j].getY())){
				cheese.remove(snakes[j].getX()+","+snakes[j].getY());
				results[j]=true;
				
			}
			
			
			/*for(int i=1;i<=cheese.size();i++){
			//System.out.println(snakes[0].getX()+" == "+cheese.get(i).getX()+" ||||| "+snakes[0].getY()+" == "+ cheese.get(i).getX());
				if(snakes[j].getX()==cheese.get(i).getX() && snakes[j].getY() == cheese.get(i).getY()){
					//System.out.println("NOW!");
					//removes that cheese
					cheese.remove(i);
					//if it was the last cheese or the last one in TreeSet
					if(cheese.size()+1!=i){
						//puts the cheese that is last in TreeSet on the number of removed one and than erases last one
						cheese.put(i, cheese.get(cheese.size()+1));
						cheese.remove(cheese.size());
					}
					results[j]=true;
				}
			}*/
		}	
		return results;
	}
	private void addTail(int snakeNumber) {
		if(tails.get(snakeNumber).size()==0){
			tails.get(snakeNumber).put(tails.get(snakeNumber).size()+1, new SnakeTail(snakes[0].oldX,snakes[0].oldY));
		}else{
			tails.get(snakeNumber).put(tails.get(snakeNumber).size()+1, new SnakeTail(tails.get(snakeNumber).get(tails.get(snakeNumber).size()).getOldX(),tails.get(snakeNumber).get(tails.get(snakeNumber).size()).getOldY()));
		}
		
		
	}
	
}
