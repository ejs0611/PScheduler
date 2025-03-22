package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * the class that extends array List
 *
 * @param <E> the list that is used to store elements
 */
public class ArrayList<E> extends AbstractList<E> {

	/**the size of the integer */
	private static final int INIT_SIZE = 10;
	/**the list that is used to store elements */
	private E[] list; 
	/**the size of the list */
	private int size;
	
	/** mark the unused filed unchecked*/
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE]; 
		this.size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void add(int index, E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		
		for(int i = 0; i < this.size(); i++) {
			if (this.get(i).equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size == list.length) {
			growArray();
		}
		
		
		for(int i = size; i > index; i--) {
			list[i] = list[i - 1];
		}
		
		
		list[index] = e;
		size += 1;
		
	}
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] list2 = (E[]) new Object[list.length * 2];
		for(int i = 0; i < size; i++) {
			list2[i] = list[i];
		}
		list = list2;
	}
	
	@Override
	public E get(int index) {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	@Override
	public E remove(int index) {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		E x = list[index];
		list[index] = null;
		size -= 1;
		for(int i = index; i < this.size; i++) {
			list[i] = list[i + 1];
		}
		list[size] = null;
		return x;
	}
	
	/**
	 * A list that is used to store the e by given index
	 * @param e the content that used to stored.
	 * @param index the index that is used to store the information.
	 * @return returns the list.
	 */
	public E set(int index, E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < this.size(); i++) {
			if (this.get(i).equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		E x = list[index];
		
		list[index] = e;
		
		return x;
	}
	
	
}