import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	public static final int BACKGROUND = 0;
	public static final int MIDDLEGROUND = 1;
	public static final int FOREGROUND = 2;
	
	private Thread thread;
	private boolean running = false;
	private STATE state;

	private static Menu menu;
	private static Handler handler;
	private static HUD hud;
	private Spawner spawner;
	private Player player;
	
	public Game() {
		new Window("Space", WIDTH, HEIGHT, this);
		
		state = STATE.Menu;
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this);
		spawner = new Spawner();
		player = new Player(10, HEIGHT / 2);
		
		handler.add(player , Game.MIDDLEGROUND);
		
		// adding particle effect to the background
		for(int i = 0; i < 100; i++)
			handler.add(new BackgroundParticle(), BACKGROUND);
		
		addKeyListener(new KeyInput());
		addMouseListener(menu);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {

				tick();
				delta--;

			}

			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;

			}

		}

		stop();
	}
	
	public void tick() {
		if (hud.getHealth() == 0) {
			state = STATE.Over;
			player.setVisible(false);
		}
		
		handler.tick();
		
		if (state == STATE.Menu || state == STATE.Over) {
			menu.tick();
		} else if (state == STATE.Game) {
			spawner.tick();
			hud.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			this.createBufferStrategy(3);
			return;

		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// draw objects below:
		handler.render(g);
		
		if (state == STATE.Menu || state == STATE.Over) {
			menu.render(g);
		} else if (state == STATE.Game) {
			hud.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int x, int min, int max) {
		int ret = x;
		
		if (x < min)
			ret = min;
		else if (x > max)
			ret = max;
		
		return ret;
	}
	
	public static Handler getHandler() {
		return handler;
	}
	
	public static HUD getHUD() {
		return hud;
	}
	
	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}
	
	public Spawner getSpawner() {
		return spawner;
	}

	public void setSpawner(Spawner spawner) {
		this.spawner = spawner;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
