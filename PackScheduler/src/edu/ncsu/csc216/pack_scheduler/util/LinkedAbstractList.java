package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;


/**
 * the custom linked abstract list that used in schedule.
 *
 * @param <E> E
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** front */
	private ListNode front;
	/** size */
	private int size;
	/** capacity */
	private int capacity;
	
	/**
	 * linkedAbstractList
	 * 
	 * @param capacity capacity
	 */
	public LinkedAbstractList(int capacity) {
		if(capacity <= 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int idx) {
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException("get");
		}
		ListNode c = front;
		for(int i = 0; i < idx; i++) {
			c = c.next;
		}
		
		
		return c.data;
	}
	
	@Override
	public void add(int idx, E value) {
		
		if(size == capacity) {
			throw new IllegalArgumentException();
		}
		if(value == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++) {
			if(value.equals(this.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (front == null) {
			ListNode wasd = new ListNode(value);
			front = wasd;
		} else if (idx == 0) {
			ListNode current = front;
			ListNode temp = new ListNode(value, current);
			front = temp;
		} else {
			ListNode current = front;
			
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			
			current.next = new ListNode(value, current.next);
		}
		
		size++;
		
		
		
		
		
	}
	
	@Override
	public E remove(int idx) {
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}

		ListNode temp;
		if (idx == 0) {
			temp = front;
			front = front.next;
			size--;
			return temp.data;
		} else{
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			E value = current.next.data;
			current.next = current.next.next;
			size--;
			return value;
		}
	}
	
	@Override
	public E set(int idx, E value) {
		if(value == null) {
			throw new NullPointerException();
		}
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < size; i++) {
			if(value.equals(this.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		E data;
		if (idx == 0) {
			data = front.data;
			front.data = value;
		} else {
			ListNode current = front;
			
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			data = current.next.data;
			current.next.data = value;
		}
		
		return data; 
		
		
		
	}
	
	
	private class ListNode {
		/** data */
		public E data;
		/** next */
		public ListNode next;
		
		public ListNode(E data, ListNode next){
			this.data = data;
			this.next = next;
		}
		
		public ListNode(E data){
			this.data = data;
			this.next = null;
		}
	}


}
