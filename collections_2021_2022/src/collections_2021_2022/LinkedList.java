package collections_2021_2022;

public class LinkedList implements List {
	
	private class Node {
		/**
		 * @invar | previous != null
		 * @invar | next != null
		 * @invar | previous.next == this
		 * @invar | next.previous == this
		 * @invar | (value == null) == (this == sentinel)
		 * 
		 * @peerObject
		 */
		private Node previous;
		private Object value;
		/**
		 * @peerObject
		 */
		private Node next;
		
		private int getSize() {
			return this == sentinel ? 0 : 1 + next.getSize();
		}
	}
	
	/**
	 * @invar | sentinel != null
	 * @invar | size == sentinel.next.getSize()
	 * @representationObject
	 */
	private Node sentinel;
	private int size;

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		Node n = sentinel.next;
		for (int i = 0; i < size; i++) {
			result[i] = n.value;
			n = n.next;
		}
		return result;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private Node getNode(int index) {
		if (index < size / 2) {
			Node n = sentinel.next;
			while (index > 0) {
				n = n.next;
				index--;
			}
			return n;
		} else {
			Node n = sentinel;
			while (index < size) {
				n = n.previous;
				index++;
			}
			return n;
		}
	}
	
	@Override
	public Object get(int index) {
		return getNode(index).value;
	}
	
	@Override
	public int indexOf(Object object) {
		int result = 0;
		Node n = sentinel.next;
		for (;;) {
			if (n == sentinel)
				return -1;
			if (object.equals(n.value))
				return result;
			n = n.next;
			result++;
		}
	}
	
	@Override
	public boolean contains(Object object) {
		return indexOf(object) != -1;
	}

	/**
	 * @post | size() == 0
	 */
	public LinkedList() {
		sentinel = new Node();
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
	}
	
	@Override
	public void add(Object object) {
		Node n = new Node();
		n.next = sentinel;
		n.previous = sentinel.previous;
		n.value = object;
		n.next.previous = n;
		n.previous.next = n;
	}
	
	@Override
	public Object remove(int index) {
		Node n = getNode(index);
		n.next.previous = n.previous;
		n.previous.next = n.next;
		return n.value;
	}
	
	@Override
	public void remove(Object object) {
		int index = indexOf(object);
		if (index >= 0)
			remove(index);
	}
}
