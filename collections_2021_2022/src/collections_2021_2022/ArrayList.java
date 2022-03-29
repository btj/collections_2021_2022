package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayList implements List {
	
	/**
	 * @invar | elements != null
	 * @invar | 0 <= size && size <= elements.length
	 * @invar | Arrays.stream(elements, 0, size).allMatch(e -> e != null)
	 * @representationObject
	 */
	private Object[] elements;
	private int size;
	
	/**
	 * @post | size() == 0
	 */
	public ArrayList() {
		elements = new Object[10];
		size = 0;
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Object get(int index) {
		return elements[index];
	}
	
	@Override
	public boolean contains(Object object) {
		return Arrays.stream(elements).anyMatch(e -> object.equals(e));
	}
	
	@Override
	public int indexOf(Object object) {
		return IntStream.range(0, size())
				 .filter(i -> toArray()[i].equals(object))
				 .findFirst().orElse(-1);
	}

	@Override
	public void add(Object object) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, size * 2);
		}
		elements[size++] = object;
	}
	
	@Override
	public Object remove(int index) {
		Object result = elements[index];
		System.arraycopy(elements, index + 1, elements, index, --size - index);
		return result;
	}
	
	@Override
	public void remove(Object object) {
		int index = indexOf(object);
		if (0 <= index)
			remove(index);
	}
	
}
