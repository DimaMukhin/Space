import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Asteroid extends GameObject {

	private static Random r = new Random();
	private Handler handler;
	private Image img = Toolkit.getDefaultToolkit().createImage("res/astroid.png");
	private int width = 64;
	private int height = 64;
	
	public Asteroid() {
		super(Game.WIDTH + r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Asteroid);
		handler = Game.getHandler();
		dy = 0;
		dx = -5;
	}

	public Rectangle getBoundary() {
		return new Rectangle(x, y, width, height);
	}
	
	// TODO: make animation
	public void destroy() {
		handler.remove(this);
	}
	
	public void tick() {
		x += dx;
		
		if (x + width < 0) {
			handler.remove(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
