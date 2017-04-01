import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

	private Image img;
	private Handler handler;
	private HUD hud;
	private int width, height;
	private int bulletTimer;
	private boolean visible;

	public Player(int x, int y) {
		super(x, y, ID.Player);
		img = Toolkit.getDefaultToolkit().createImage("res/spaceship.png");
		handler = Game.getHandler();
		hud = Game.getHUD();
		height = 64;
		width = 64;
		bulletTimer = 0;
		visible = true;
	}

	public Rectangle getBoundary() {
		return new Rectangle(x, y, width, height);
	}
	
	private void collision() {
		Node curr = handler.getFirst();
		
		while (curr != null) {
			if (curr.getObject().getId() == ID.Asteroid) {
				if (getBoundary().intersects(curr.getObject().getBoundary())) {
					// collision with an Asteroid
					hud.setHealth(hud.getHealth() - 1);
				}
			}
			curr = curr.getNext();
		}
	}
	
	public void fire() {
		if (bulletTimer == 0 && visible) {
			handler.add(new Bullet(x + width, y + height / 2), Game.MIDDLEGROUND);
			bulletTimer = 50;
		}
	}
	
	public void tick() {
		x += dx;
		y += dy;
		
		if (bulletTimer > 0) bulletTimer--;
		
		collision();
		
		x = Game.clamp(x, 0, Game.WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - 95);
	}

	public void render(Graphics g) {
		if (visible)
			g.drawImage(img, x, y, null);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
