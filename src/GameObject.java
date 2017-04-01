import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	private ID id;

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		dx = 0;
		dy = 0;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public Rectangle getBoundary() {
		return new Rectangle(x, y, x, y);
	}
	
	public ID getId() {
		return id;
	}
	
}
