import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[120];
	public boolean up1, down1, up2, down2, enter;
	
	public void update(){
		up1 = keys[KeyEvent.VK_W];
		down1 = keys[KeyEvent.VK_S];
		up2 = keys[KeyEvent.VK_O];
		down2 = keys[KeyEvent.VK_L];
		enter = keys[KeyEvent.VK_ENTER];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
