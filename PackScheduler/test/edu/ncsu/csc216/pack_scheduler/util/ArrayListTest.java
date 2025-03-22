package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {

	@Test
	void testSize() {
		ArrayList<Integer> wasd = new ArrayList<>();
		assertEquals(0, wasd.size());
	}

	@Test
	void testAdd() {
		ArrayList<Integer> x = new ArrayList<>();
		x.add(0, 1);
		assertEquals(1, x.get(0));
		
		
		Exception e1 = assertThrows(NullPointerException.class,
				() -> x.add(1, null));
		assertEquals(null, e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> x.add(1, 1));
		assertEquals(null, e2.getMessage());
		
		Exception e3 = assertThrows(IndexOutOfBoundsException.class,
				() -> x.add(6, 5));
		assertEquals(null, e3.getMessage());
		
		x.add(0, 2);
		assertEquals(2, x.get(0));
		assertEquals(1, x.get(1));
		
		x.add(0, 3);
		x.add(0, 4);
		x.add(0, 5);
		x.add(0, 6);
		x.add(0, 7);
		x.add(0, 8);
		x.add(0, 9);
		x.add(0, 10);
	}
	
	@Test
	void testRemove() {
		ArrayList<Integer> x = new ArrayList<>();
		x.add(0, 0);
		x.add(1, 1);
		x.add(2, 2);
		x.add(3, 3);
		assertEquals(2, x.remove(2));
		assertEquals(3, x.size());
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> x.remove(12));
		assertEquals(null, e1.getMessage());
	}
	
	@Test
	void testSet() {
		ArrayList<Integer> x = new ArrayList<>();
		x.add(0, 0);
		x.add(1, 1);
		x.add(2, 2);
		x.add(3, 3);
		x.set(1, 5);
		assertEquals(5, x.get(1));
		
		Exception e1 = assertThrows(NullPointerException.class,
				() -> x.set(1, null));
		assertEquals(null, e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> x.set(1, 2));
		assertEquals(null, e2.getMessage());
		
		Exception e3 = assertThrows(IndexOutOfBoundsException.class,
				() -> x.set(12, 12));
		assertEquals(null, e3.getMessage());
	}

}
