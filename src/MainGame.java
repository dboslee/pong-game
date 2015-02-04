import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class MainGame extends Canvas implements Runnable {
	
	public int height = 600;
	public int width = height * 16 / 9;
	public static String title = "PONG";
	
	private Thread thread;
	private boolean running = false;
	
	private JFrame frame;
	private Keyboard key;
	
	public Paddle player1;
	public Paddle player2;
	public Ball ball;
	
	public MainGame(){
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
		player1 = new Paddle(20, 100, 0, 0);
		player2 = new Paddle(20, 100, width - 20, 0);
		ball = new Ball(20, 20, 5, width / 2, height / 2);
	}
	
	public synchronized void start(){
		
		running = true;
		thread = new Thread(this, title);
		thread.start();
	}
	
	public void stop(){
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				delta--;
			}
			render();
		}
		stop();
	}
	
	public void update(){
		key.update();
		paddleMove();
		ballMove();
		ballCollision();
		paddleCollision();
	}

	private void hit(Graphics g) {
		
		g.setClip(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
		if(g.hitClip(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()))
				ball.setvx(-ball.getvx());
		g.setClip(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
		if(g.hitClip(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()))
				ball.setvx(-ball.getvx());
		
	}

	private void ballCollision() {
		
		if(ball.getX() < 0){
			ball.setMove(false);
			ball.setX(width/2);
			ball.setY(height/2);
			ball.setvx(-ball.getvx());
			player2.setScore(player2.getScore() + 1);
		}
			
		if(ball.getX() > width){
			ball.setMove(false);
			ball.setX(width/2);
			ball.setY(height/2);
			ball.setvx(-ball.getvx());
			player1.setScore(player1.getScore() + 1);
		}
			
		if(ball.getY() < 0)
			ball.setvy(-ball.getvy());
		if(ball.getY() > height - 20)
			ball.setvy(-ball.getvy());
	}

	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		player1.paint(g);
		player2.paint(g);
		ball.paint(g);
		showScore(g);
		g.dispose();
		bs.show();
		hit(g);
	}
	
	private void paddleMove() {
		
		if(key.up1){
			player1.setY(player1.getY()-player1.getSpeed());
		}
		if(key.down1){
			player1.setY(player1.getY()+player1.getSpeed());
		}
		
		if(key.up2){
			player2.setY(player2.getY()-player2.getSpeed());
		}
		if(key.down2){
			player2.setY(player2.getY()+player2.getSpeed());
		}
		
	}
	
	private void ballMove() {
		
		if(key.enter)
			ball.setMove(true);
		if(ball.getMove()){
			ball.setX(ball.getX() + ball.getvx());
			ball.setY(ball.getY() + ball.getvy());
		}
	}
	
	private void paddleCollision() {

		if(player1.getY() < 0)
			player1.setY(0);
		else if(player1.getY() + player1.getHeight() > height)
			player1.setY(height - player1.getHeight());
		
		if(player2.getY() < 0)
			player2.setY(0);
		else if(player2.getY() + player1.getHeight() > height)
			player2.setY(height - player1.getHeight());
		
	}
	
	public void showScore(Graphics g){
			g.setFont(new Font("DS-Digital", Font.PLAIN, 100));
			g.setColor(Color.WHITE);
			String str1 = "" + player1.getScore();
			g.drawString(str1, 100, 100);
			String str2 = "" + player2.getScore();
			g.drawString(str2, width - 150, 100);
	}
	
	public static void main(String[] args){
		MainGame game = new MainGame();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setTitle(title);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		game.start();
	}
	

}
