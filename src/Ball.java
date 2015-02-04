import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball {

	private int width;
	private int height;
	private int x;
	private int y;
	private int vx;
	private int vy;
	private boolean move = false;
	
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
