import java.awt.Color;
import java.awt.Graphics;


public class Paddle {

	int width;
	int height;
	int x;
	int y;
	int speed = 10;
	int score = 0;
	private boolean win = false;
	
	public Paddle(int w, int h, int x, int y){
		width = w;
		height = h;
		this.x = x;
		this.y = y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public int getSpeed(){
		return speed;
	}
	
	public void setScore(int s){
		
		score = s;
		
	}
	
	public int getScore(){
		return score;
	}
	
	public void setWin(){
		if(getScore() == 7){
			win=true;
		}
		else
			win = false;
	}
	
	public boolean getWin(){
		return win;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
}
