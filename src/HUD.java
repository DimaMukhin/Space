import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	private Handler handler;
	
	private static final int WIDTH = 200; // the width of the health box
	private static final int HEIGHT = 10; // the height of the health box
	
	private int timer = 10;
	private int health = 100;
	private int score;

	public HUD() {
		handler = Game.getHandler();
	}

	public void tick() {
		timer--;
		if (timer <= 0) {
			timer = 10;
			score++;
		}
		
		health = Game.clamp(health, 0, 100);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(10, 10, WIDTH, HEIGHT);
		
		g.setColor(Color.green);
		g.fillRect(10, 10, health * 2, HEIGHT);
		
		g.setColor(Color.red);
		g.drawString("Score " + score, 20, 20);
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
