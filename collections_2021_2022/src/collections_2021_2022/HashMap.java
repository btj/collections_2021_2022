package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashMap implements Map {
	
	/**
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     buckets[i].entrySet().stream().allMatch(e ->
	 *        |         Math.floorMod(((Entry)e).getKey().hashCode(),
	 *        |                buckets.length) == i
	 *        |     )
	 *        | )
	 * @representationObject
	 * @representationObjects
	 */
	private Map[] buckets;
	
	/**
	 * @post | size() == 0
	 */
	public HashMap(int capacity) {
		buckets = new Map[Math.max(1, capacity)];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArrayMap();
	}
	
	@Override
	public Set entrySet() {
		HashSet result = new HashSet(size());
		for (Map bucket : buckets)
			for (Object entry : bucket.entrySet().toArray())
				result.add(entry);
		return result;
	}
	
	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(b -> b.size()).sum();
	}
	
	private Map getBucket(Object key) {
		return buckets[Math.floorMod(key.hashCode(), buckets.length)];
	}
	
	@Override
	public boolean containsKey(Object key) {
		return getBucket(key).containsKey(key);
	}

	@Override
	public Object get(Object key) {
		return getBucket(key).get(key);
	}
	
	@Override
	public void put(Object key, Object value) {
		getBucket(key).put(key, value);
	}
	
	@Override
	public void remove(Object key) {
		getBucket(key).remove(key);
	}

}
