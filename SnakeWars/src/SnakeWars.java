
public class SnakeWars {

	private static int sizeBeginX=60;
	private static int sizeBeginY=60;
	private static int sizeEndX=860;
	private static int sizeEndY=660;
	private static Window o;
	//private static theSnake aaa = new theSnake(10);
	//One Player
	private static SnakeHead[] snakes={new SnakeHead(10)};
	//Two Players
	//private static SnakeHead[] snakes={new SnakeHead(10), new SnakeHead(10,840,640,3)};
	//Three Players
	//private static SnakeHead[] snakes={new SnakeHead(10), new SnakeHead(10,840,640,3),new SnakeHead(10,60,640,1)};
	//Four Players
	//private static SnakeHead[] snakes={new SnakeHead(10), new SnakeHead(10,840,640,3),new SnakeHead(10,60,640,0),new SnakeHead(10,840,60,1)};
	
	//private static SnakeHead[] snakes={new SnakeHead(10), new SnakeHead(10,840,640,3)};
	public static void main(String[] args) {
		o = new Window("Snake");
	    //Grafical painting of Paint panel
		Field field=new Field(snakes,sizeBeginX,sizeBeginY,sizeEndX,sizeEndY);
	    Paint rp = new Paint(field,sizeBeginX,sizeBeginY,sizeEndX,sizeEndY);
		o.add(rp);
		o.setVisible(true);
		field.run(rp);
	}

}
