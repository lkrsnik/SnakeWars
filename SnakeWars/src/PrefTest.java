
public class PrefTest {
	private long startTime;
	private long max;
	private long min=5000000;
	private int repeats=0;
	private long timeSum;
	//private long average;
	public void start(){
		startTime = System.nanoTime();
	}
	public void speed(){
		long time=System.nanoTime()-startTime;
		if(max<time)
			max=time;
		if(min>time)
			min=time;
		timeSum+=time;
		repeats++;
		System.out.println("Time: "+time+" Average: "+(timeSum/repeats)+" MaxTime: "+max+" MinTime: "+min);
		
		
	}
}
