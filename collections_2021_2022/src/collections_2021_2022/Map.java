package collections_2021_2022;

import java.util.stream.Stream;

/**
 * Each instance of this interface stores a set of key-value pairs with unique keys.
 */
public interface Map {
	
	interface Entry {
		/**
		 * @post | result != null 
		 */
		Object getKey();
		/**
		 * @post | result != null
		 */
		Object getValue();
	}
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | result.stream().allMatch(e -> e instanceof Entry)
	 * @post | result.stream().map(e -> ((Entry)e).getKey()).distinct().count() == result.size()
	 * @creates | result
	 */
	Set entrySet();
	
	/**
	 * @post | result == entrySet().size()
	 * @return
	 */
	int size();
	
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream().anyMatch(e -> ((Entry)e).getKey().equals(key))
	 */
	boolean containsKey(Object key);
	
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream()
	 *       |                     .filter(e -> ((Entry)e).getKey().equals(key))
	 *       |                     .map(e -> ((Entry)e).getValue())
	 *       |                     .findFirst().orElse(null)
	 */
	Object get(Object key);
	
	/**
	 * @pre | key != null
	 * @pre | value != null
	 * @mutates | this
	 * @post | get(key).equals(value)
     * @post | entrySet().stream().allMatch(e -> old(entrySet()).contains(e) || ((Entry)e).getKey().equals(key))
     * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).getKey().equals(key) || entrySet().contains(e))
	 */
	void put(Object key, Object value);
	
	/**
	 * @pre | key != null
	 * @mutates | this
	 * @post | entrySet().stream().allMatch(e -> !((Entry)e).getKey().equals(key) && old(entrySet()).contains(e))
	 * @post | old(entrySet()).stream().allMatch(e -> ((Entry)e).getKey().equals(key) || entrySet().contains(e))
	 */
	void remove(Object key);

}
