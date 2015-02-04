import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball {

	int width;
	int height;
	int x;
	int y;
	int vx;
	int vy;
	boolean move = false;
	
	public Ball(int w, int h, int s, int x, int y){
		width = w;
		height = h;
		vx = s;
		vy = s;
		this.x = x;
		this.y = y;
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
		
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setvx(int s){
		vx = s;
	}
	
	public int getvx(){
		return vx;
	}
	
	public void setvy(int s){
		vy = s;
	}
	
	public int getvy(){
		return vy;
	}
	
	public void setMove(boolean m){
		move = m;
	}
	
	public boolean getMove(){
		return move;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.magenta);
		g.fillOval(x, y, width, height);
	}
	
}
