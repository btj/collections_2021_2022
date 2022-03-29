package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @invar The elements are non-null.
 *     | stream().allMatch(e -> e != null)
 * @invar The set has no duplicates. 
 *     | stream().distinct().count() == size()
 */
public interface Set {
	
	/**
	 * @inspects | this
	 * @creates | result
	 */
	public Object[] toArray();
	
	/**
	 * @inspects | this
	 * 
	 * @post | result == toArray().length
	 */
	public int size();
	
	public default Stream<Object> stream() { return Arrays.stream(toArray()); }
	/**
	 * @pre | object != null
	 * 
	 * @post | result == stream().anyMatch(e -> object.equals(e))
	 */
	public boolean contains(Object object);
	
	/**
	 * @pre | object != null
	 * 
	 * @mutates | this
	 * 
	 * @post | contains(object)
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> contains(e))
	 * @post | stream().allMatch(e -> object.equals(e) || Arrays.stream(old(toArray())).anyMatch(e1 -> e1.equals(e)))
	 */
	public void add(Object object);
	
	/**
	 * @pre | object != null
	 * 
	 * @mutates | this
	 * 
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> object.equals(e) || contains(e))
	 * @post | stream().allMatch(e -> !object.equals(e) && Arrays.stream(old(toArray())).anyMatch(e1 -> e1.equals(e)))
	 */
	public void remove(Object object);

}
