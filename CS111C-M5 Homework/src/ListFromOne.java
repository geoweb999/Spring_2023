
/**
 * An ordered collection that allows duplicates. The list is indexed starting at position 1.
 * @author Jessica Masters
 * @version 1.0
 */
public interface ListFromOne<T> {
	
	/**
	 * Adds a new element to the end of the list.
	 * @param element element to be added to the list
	 * @return true if the element was successfully added and false otherwise.
	 */
	public boolean add(T element);
	
	
	/**
	 * Adds a new element to the list at the specified position. The method
	 * shifts the other elements to make room for the new element.
	 * @param position the position at which to add the new element
	 * @param element element to be added to the list
	 * @return true if the element was successfully added and false otherwise.
	 * @throws IndexOutOfBoundsException if the position is < 1 or > size()+1
	 */
	public boolean add(int position, T element);
	
	
	/**
	 * Determines if the list contains the specified element.
	 * @param element the element to find
	 * @return true if the element is in the list and false otherwise.
	 */
	public boolean contains(T element);
	
	
	/**
	 * Removes the element at the specified position. The method shifts the 
	 * other elements down to fill the position that has been removed.
	 * @param position the position from which to remove an element
	 * @return The removed element 
	 * @throws IndexOutOfBoundsException if the position is < 1 or > size()
	 */
	public T remove(int position);
	
	
	/**
	 * Removes the first occurrence of the specified element. The method shifts 
	 * the other elements down to fill the position that has been removed.
	 * @param element the element to be removed 
	 * @return true if the element is removed and false otherwise
	 */
	public boolean remove(T element);
	
	
	/**
	 * Replaces the element at the specified position with a new element. The size
	 * of the list does not change.
	 * @param position the position to replace
	 * @param element the new element to replace the original one
	 * @return The original element that was replaced
	 * @throws IndexOutOfBoundsException if the position is < 1 or > size()
	 */
	public T set(int position, T element);
	
	
	/**
	 * Gets the element at the specified position.
	 * @param position the position at which to get the element
	 * @return The object at the specified position
	 * @throws IndexOutOfBoundsException if the position is < 1 or > size()
	 */
	public T get(int position);
	
	
	/**
	 * Gets the number of elements in the list.
	 * @return The number of elements in the list.
	 */
	public int size();
	
	
	/**
	 * Determines if there are any elements in the list. 
	 * @return true if the list is empty and false otherwise.
	 */
	public boolean isEmpty();
	

	/**
	 * Removes all elements from the list.
	 */
	public void clear();

}
