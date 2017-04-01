import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	private Handler handler = Game.getHandler();
	
	private int width = 25;
	private int height = 3;
	
	public Bullet(int x, int y) {
		super(x, y, ID.Bullet);
		dx = 5;
	}
	
	public Rectangle getBoundary() {
		return new Rectangle(x, y, width, height);
	}

	public void tick() {
		x += dx;
		
		collision();
		
		if (x > Game.WIDTH) {
			handler.remove(this);
		}
	}

	private void collision() {
		Node curr = handler.getFirst();
		
		while (curr != null) {
			if (curr.getObject().getId() == ID.Asteroid) {
				if (getBoundary().intersects(curr.getObject().getBoundary())) {
					handler.remove(this);
					((Asteroid) curr.getObject()).destroy();
				}
			}
			curr = curr.getNext();
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

}
