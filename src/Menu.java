import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{

	private int bWidth; // button width
	private int bHeight;
	private boolean help;
	
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public Menu(Game game) {
		this.game = game;
		handler = Game.getHandler();
		hud = Game.getHUD();
		
		help = false;
		bWidth = 250;
		bHeight = 50;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.getState() == STATE.Menu) {
			if (mouseOver((Game.WIDTH-bWidth)/2, 150, (Game.WIDTH-bWidth)/2+bWidth, 150+bHeight, mx, my)) {
				game.setState(STATE.Game);
			} else if (mouseOver((Game.WIDTH-bWidth)/2, 250, (Game.WIDTH-bWidth)/2+bWidth, 250+bHeight, mx, my)) {
				help = true;
			} else if (mouseOver((Game.WIDTH-bWidth)/2, 350, (Game.WIDTH-bWidth)/2+bWidth, 350+bHeight, mx, my)) {
				System.exit(1);
			}
		} else if (game.getState() == STATE.Over) {
			if (mouseOver((Game.WIDTH-bWidth)/2, 250, (Game.WIDTH-bWidth)/2+bWidth, 250+bHeight, mx, my)) {
				hud.setHealth(100);
				hud.setScore(0);
				handler.removeAll(ID.Asteroid);
				handler.getPlayer().setVisible(true);
				game.setSpawner(new Spawner());
				game.setState(STATE.Game);
			} else if (mouseOver((Game.WIDTH-bWidth)/2, 350, (Game.WIDTH-bWidth)/2+bWidth, 350+bHeight, mx, my)) {
				System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public boolean mouseOver(int x1, int y1, int x2, int y2, int mx, int my) {
		boolean over = false;
		
		if (mx >= x1 && mx <= x2)
			if (my >= y1 && my <= y2)
				over = true;
		
		return over;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font header1 = new Font("arial", 1, 50);
		Font header2 = new Font("arial", 1, 30);
		Font header3 = new Font("arial", 1, 15);
		
		if (game.getState() == STATE.Menu) {
			g.setFont(header1);
			g.setColor(Color.gray);
			g.drawString("Space", Game.WIDTH / 2 - 70, 80);
			
			g.setFont(header2);
			
			g.setColor(Color.black);
			g.fillRect((Game.WIDTH - bWidth) / 2, 150, bWidth, bHeight);
			g.setColor(Color.blue);
			g.drawRect((Game.WIDTH - bWidth) / 2, 150, bWidth, bHeight);
			g.drawString("Play", Game.WIDTH / 2 - 34, 150 + 34);
			
			if (help == false) {
				g.setColor(Color.black);
				g.fillRect((Game.WIDTH - bWidth) / 2, 250, bWidth, bHeight);
				g.setColor(Color.blue);
				g.drawRect((Game.WIDTH - bWidth) / 2, 250, bWidth, bHeight);
				g.drawString("Help", Game.WIDTH / 2 - 34, 250 + 34);
			} else {
				g.setFont(header3);
				g.drawString("Use W S to move, Space to shoot", Game.WIDTH / 2 - 120, 250 + 34);
				g.setFont(header2);
			}
			
			
			g.setColor(Color.black);
			g.fillRect((Game.WIDTH - bWidth) / 2, 350, bWidth, bHeight);
			g.setColor(Color.blue);
			g.drawRect((Game.WIDTH - bWidth) / 2, 350, bWidth, bHeight);
			g.drawString("Quit", Game.WIDTH / 2 - 34, 350 + 34);
		} else if (game.getState() == STATE.Over) {
			g.setFont(header1);
			g.setColor(Color.red);
			g.drawString("Game Over", Game.WIDTH / 2 - 135, 80);
			
			g.setFont(header2);
			
			g.drawString("You lost with a score of " + hud.getScore(), (Game.WIDTH - bWidth) / 2 - 60, 170);
			
			g.setColor(Color.black);
			g.fillRect((Game.WIDTH - bWidth) / 2, 250, bWidth, bHeight);
			g.setColor(Color.blue);
			g.drawRect((Game.WIDTH - bWidth) / 2, 250, bWidth, bHeight);
			g.drawString("Play again", Game.WIDTH / 2 - 75, 250 + 34);
			
			g.setColor(Color.black);
			g.fillRect((Game.WIDTH - bWidth) / 2, 350, bWidth, bHeight);
			g.setColor(Color.blue);
			g.drawRect((Game.WIDTH - bWidth) / 2, 350, bWidth, bHeight);
			g.drawString("Quit", Game.WIDTH / 2 - 34, 350 + 34);
		}
		
	}
	
}
