package collections_2021_2022;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedHashSetTest {

	@Test
	void test() {
		LinkedHashSet mySet = new LinkedHashSet();
		assertArrayEquals(new Object[] {}, mySet.toArray());
		assertEquals(0, mySet.size());
		assertFalse(mySet.contains(33));
		assertFalse(mySet.contains(77));
		assertFalse(mySet.contains(99));
		
		mySet.add(77);
		assertArrayEquals(new Object[] {77}, mySet.toArray());
		assertFalse(mySet.contains(33));
		assertTrue(mySet.contains(77));
		assertFalse(mySet.contains(99));
		
		mySet.add(99);
		assertArrayEquals(new Object[] {77, 99}, mySet.toArray());
		assertFalse(mySet.contains(33));
		assertTrue(mySet.contains(77));
		assertTrue(mySet.contains(99));
		
		mySet.add(33);
		assertArrayEquals(new Object[] {77, 99, 33}, mySet.toArray());
		assertTrue(mySet.contains(33));
		assertTrue(mySet.contains(77));
		assertTrue(mySet.contains(99));
		
		mySet.remove(99);
		assertArrayEquals(new Object[] {77, 33}, mySet.toArray());
		assertTrue(mySet.contains(33));
		assertTrue(mySet.contains(77));
		assertFalse(mySet.contains(99));
	}

}
