import java.util.Random;

public class Spawner {

	private Random r;
	private Handler handler;
	private HUD hud;
	private int level;
	private int nextLevel;
	
	public Spawner() {
		r = new Random();
		handler = Game.getHandler();
		hud = Game.getHUD();
		level = 0;
		nextLevel = 0;
	}
	
	public void tick() {
		level = (int) (hud.getScore() / 15);
		
		if(level == nextLevel) {
			nextLevel = level+1;
			spawn("Asteroid", r.nextInt(10) + 1);
		}
	}
	
	private void spawn(String name, int amount) {
		for (int i = 0; i < amount; i++) {
			if (name == "Asteroid")
				handler.add(new Asteroid(), Game.MIDDLEGROUND);
		}
	}
	
}
