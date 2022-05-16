package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashSet implements Set {
	
	private static final int MAX_LOAD_FACTOR = 3;
	
	/**
	 * @invar | buckets != null
	 * @invar | Arrays.stream(buckets).allMatch(bucket -> bucket != null)
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i].stream().allMatch(e ->
	 *        |         Math.floorMod(e.hashCode(), buckets.length) == i))
	 * @invar | size == Arrays.stream(buckets).mapToInt(b -> b.size()).sum()
	 * @representationObject
	 * @representationObjects
	 */
	private Set[] buckets;
	private int size;
	
	private Set getBucket(Object object) {
		return buckets[Math.floorMod(object.hashCode(), buckets.length)];
	}
	
	/**
	 * @post | size() == 0
	 */
	public HashSet(int capacity) {
		buckets = new Set[Math.max(1, capacity)];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArraySet();
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.stream(buckets).flatMap(b -> b.stream()).toArray();
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean contains(Object object) {
		return getBucket(object).contains(object);
	}
	
	@Override
	public void add(Object object) {
		if (!contains(object)) {
			getBucket(object).add(object);
			size++;
			if (size / buckets.length > MAX_LOAD_FACTOR) {
				Set[] newBuckets = new Set[buckets.length * 2];
				for (int i = 0; i < newBuckets.length; i++) {
					newBuckets[i] = new ArraySet();
				}
				Set[] oldBuckets = buckets;
				buckets = newBuckets;
				for (Set oldBucket : buckets) {
					for (Object element : oldBucket.toArray())
						add(element);
				}
			}
		}
	}
	
	@Override
	public void remove(Object object) {
		if (contains(object)) {
			getBucket(object).remove(object);
			size--;
		}
	}

}
