package collections_2021_2022;

record MyEntry(Object key, Object value) implements Map.Entry {
	public Object getKey() { return key; }
	public Object getValue() { return value; }
}

public class ArrayMap implements Map {
	
	/**
	 * @invar | entries != null
	 * @invar | entries.stream().allMatch(e -> e instanceof Entry)
	 * @invar | entries.stream()
	 *        |        .map(e -> ((Entry)e).getKey())
	 *        |        .distinct()
	 *        |        .count() == entries.size()
	 * @representationObject
	 */
	private ArrayList entries;
	
	@Override
	public Set entrySet() {
		HashSet result = new HashSet(entries.size());
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}
	
	@Override
	public int size() {
		return entries.size();
	}
	
	@Override
	public boolean containsKey(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				return true;
		return false;
	}
	
	@Override
	public Object get(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				return ((Entry)entries.get(i)).getValue();
		return null;
	}
	
	@Override
	public void remove(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				entries.remove(i);
	}
	
	@Override
	public void put(Object key, Object value) {
		remove(key);
		entries.add(new MyEntry(key, value));
	}

}
