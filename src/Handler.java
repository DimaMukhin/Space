import java.awt.Graphics;

public class Handler {

	private ObjectList objectList;
	
	public Handler() {
		objectList = new ObjectList();
	}
	
	public void add(GameObject object, int layer) {
		objectList.add(object, layer);
	}
	
	public void remove(GameObject object) {
		objectList.remove(object);
	}
	
	public void removeAll(ID id) {
		objectList.removeAll(id);
	}
	
	public Player getPlayer() {
		Node curr = objectList.getFirst();
		Player player = null;
		
		while (curr != null && curr.getObject().getId() != ID.Player) {
			curr = curr.getNext();
		}
		
		if (curr != null && curr.getObject().getId() == ID.Player)
			player = (Player) curr.getObject();
		
		return player;
	}
	
	public void tick() {
		Node curr = objectList.getFirst();
		
		while(curr != null) {
			curr.getObject().tick();
			curr = curr.getNext();
		}
	}
	
	public void render(Graphics g) {
		Node curr = objectList.getFirst();

		while (curr != null) {
			curr.getObject().render(g);
			curr = curr.getNext();
		}
	}
	
	public Node getFirst() {
		return objectList.getFirst();
	}
	
}
