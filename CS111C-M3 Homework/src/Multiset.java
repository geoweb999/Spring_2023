
/**
 * An unordered collection that allows duplicates. 
 * @author Jessica Masters
 * @version 1.0
 */
public interface Multiset<T> {
	
	/**
	 * Adds a new element to this multiset.
	 * @param element element to be added to the multiset
	 * @return true if the element was successfully added and false otherwise.
	 */
	public boolean add(T element);
	
	
	/**
	 * Removes one unspecified element from the multiset, if possible.
	 * @return The removed element or null (when the multiset is empty)
	 */
	public T remove();

	
	/**
	 * Removes one occurrence of the specified element from the multiset, if possible.
	 * @param element element to be removed from the multiset
	 * @return true if the element was successfully removed and false otherwise.
	 */
	public boolean remove(T element);
	
	
	
	/**
	 * Counts the number of times the specified element appears in the multiset.
	 * @param element the element to be counted
	 * @return The number of times the element appears in the multiset.
	 */
	public int getOccurrencesOf(T element);
	
	
	/**
	 * Determines if the multiset contains the specified element.
	 * @param element the element to find
	 * @return true if the element is in the multiset and false otherwise.
	 */
	public boolean contains(T element);
	
	
	/**
	 * Determines if there are any elements in the multiset.
	 * @return true if there are no elements in the multiset and false otherwise.
	 */
	public boolean isEmpty();	
	
	
	/**
	 * Gets the number of elements in the multiset.
	 * @return The number of elements in the multiset.
	 */
	public int size();
	
	
	/**
	 * Removes all elements from the multiset.
	 */
	public void clear();
	

}
