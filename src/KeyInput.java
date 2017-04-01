import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	Player player;
	
	private boolean[] keyDown = {false, false, false};
	
	public KeyInput() {
		this.handler = Game.getHandler();
		player = handler.getPlayer();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (player != null) {
			if (key == KeyEvent.VK_W) {
				player.dy = -5;
				keyDown[0] = true;
			} else if (key == KeyEvent.VK_S) {
				player.dy = 5;
				keyDown[1] = true;
			} else if (key == KeyEvent.VK_SPACE) {
				player.fire();
				keyDown[2] = true;
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			keyDown[0] = false;
		} else if (key == KeyEvent.VK_S) {
			keyDown[1] = false;
		} else if (key == KeyEvent.VK_SPACE) {
			keyDown[2] = false;
		}
		
		if(!keyDown[0] && !keyDown[1])
			player.dy = 0;
		
		player.dy = 0;
	}
	
}
