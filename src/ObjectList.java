

public class ObjectList {

	private Node top;
	
	public ObjectList() {
		top = null;
	}
	
	public void add(GameObject object, int priority) {
		Node curr = top;
		Node prev = null;
		
		while(curr != null && curr.getPriority() < priority) {
			prev = curr;
			curr = curr.getNext();
		}
		
		Node newNode = new Node(object, priority, curr);
		
		if(prev == null) {
			top = newNode;
		} else {
			prev.setNext(newNode);
		}
	}
	
	public void remove(GameObject object) {
		Node curr = top;
		Node prev = null;
		
		while (curr != null && curr.getObject() != object) {
			prev = curr;
			curr = curr.getNext();
		}
		
		if (curr != null) {	
			if (prev == null) {
				top = curr.getNext();
			} else {
				prev.setNext(curr.getNext());
			}
		}
	}
	
	public void removeAll(ID id) {
		Node curr = top;
		
		while (curr != null) {
			if (curr.getObject().getId() == id) {
				remove(curr.getObject());
			}
			curr = curr.getNext();
		}
	}
	
	public Node getFirst() {
		return top;
	}
	
}

class Node {
	
	private GameObject object;
	private Node next;
	private int priority;
	
	public Node(GameObject object, int priority, Node next) {
		this.object = object;
		this.next = next;
	}
	
	public GameObject getObject() {
		return object;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}