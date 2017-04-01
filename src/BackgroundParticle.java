import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BackgroundParticle extends GameObject {
	
	private static Random r = new Random();
	private Handler handler;
	private int width, height;
	
	public BackgroundParticle() {
		super(Game.WIDTH + r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BackgroundParticle);
		handler = Game.getHandler();
		width = height = r.nextInt(5);
		dx = -10;
	}

	public void tick() {
		x += dx;
		
		if(x <= 0) {
			handler.remove(this);
			handler.add(new BackgroundParticle(), Game.BACKGROUND);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
