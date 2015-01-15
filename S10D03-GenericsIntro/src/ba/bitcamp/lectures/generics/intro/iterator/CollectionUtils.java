package ba.bitcamp.lectures.generics.intro.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Utility methods that demonstrate generic programming on Collection interface.
 * 
 * @author emir
 *
 */
public class CollectionUtils {
	
	/**
	 * Removes nulls from provided collection.
	 * 
	 * @param c to remove nulls from
	 * @return number of removed elements
	 */
	@SuppressWarnings("rawtypes")
	public static int removeNulls(Collection c) {
		int removed = 0;
		Iterator iterator = c.iterator();
		while(iterator.hasNext()) {
			if (iterator.next() == null) {
				iterator.remove();
				removed++;
			}
		}
		return removed;
	}
	
	/**
	 * Creates new collection that does not contain nulls. Does not change input collection.
	 * It uses raw collection - not parameterized. 
	 * {@link SuppressWarnings} annotation is added to avoid IDE to report rawtypes and unchecked warnings.
	 * 
	 * @param c raw collection to create cleaned collection from. 
	 * @return collection without nulls
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection toCleanedRaw(Collection c) {
		ArrayList cleaned = new ArrayList();
		Iterator iterator = c.iterator();
		while(iterator.hasNext()) {
			Object e = iterator.next();
			if (e != null) {
				cleaned.add(e);
			}
		}
		return cleaned;
	}
	
	/**
	 * Creates new collection that does not contain nulls. Does not change input collection.
	 * It is parameterized.
	 * 
	 * @param c raw collection to create cleaned collection from. 
	 * @return collection without nulls
	 */
	public static <E> Collection<E> toCleaned(Collection<E> c) {
		ArrayList<E> cleaned = new ArrayList<E>();
		Iterator<E> iterator = c.iterator();
		while(iterator.hasNext()) {
			E e = iterator.next();
			if (e != null) {
				cleaned.add(e);
			}
		}
		return cleaned;
	}
	
	/**
	 * Creates new collection that contains only element whose string representation starts with prefix
	 * It uses raw collection - not parameterized.
	 * There is no {@link SuppressWarnings} annotation so IDE will probably report rawtype and/or unchecked warnings.
	 * 
	 * @param c raw collection to create prefixed collection from
	 * @param prefix to use as filter
	 * @return collection without nulls
	 */
	public static Collection withPrefixRaw(Collection c, String prefix) {
		Collection prefixed = new ArrayList();
		Iterator iterator = c.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (o != null && o.toString().startsWith(prefix)) {
				prefixed.add(o);
			}
		}
		return prefixed;
	}
	
	/**
	 * Creates new collection that contains only element whose string representation starts with prefix
	 * It is parameterized.

	 * @param c raw collection to create prefixed collection from
	 * @param prefix to use as filter
	 * @return collection without nulls
	 */
	public static <T> Collection<T> withPrefix(Collection<T> c, String prefix) {
		Collection<T> prefixed = new ArrayList<T>();
		Iterator<T> iterator = c.iterator();
		while (iterator.hasNext()) {
			T o = iterator.next();
			if (o != null && o.toString().startsWith(prefix)) {
				prefixed.add(o);
			}
		}
		return prefixed;
	}
	
	/**
	 * Check if collection has duplicates. Two null values are also duplicates.
	 * 
	 * @param c to check for duplicates
	 * @return true if collection has duplicates.
	 */
	public static <E> boolean hasDuplicates(Collection<E> c) {
		Iterator<E> iterator = c.iterator();
		int idx = 0;
		while(iterator.hasNext()) {
			idx++;
			E e = iterator.next();
			// do not check last
			if (iterator.hasNext()) {
				Iterator<E> subiterator = c.iterator();
				// move subiterator to index
				int sub = 0;
				while (subiterator.hasNext() && sub < idx) {
					sub++;
					subiterator.next();
				}
				// check if others are duplicates
				while (subiterator.hasNext()) {
					E s = subiterator.next();
					if (e == s || (e != null && e.equals(s))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Return first duplicate value that is not null, or null if there is no such value.
	 * Even logic is similar to hasDuplicateValues, code had to be duplicated because of different handling of null.
	 * 
	 * @param c to search for duplicate value
	 * @return first duplicate value
	 */
	public static <E> E firstDuplicateValue(Collection<E> c) {
		Iterator<E> iterator = c.iterator();
		int idx = 0;
		while(iterator.hasNext()) {
			idx++;
			E e = iterator.next();
			if (e != null && iterator.hasNext()) {
				Iterator<E> subiterator = c.iterator();
				// move subiterator to index
				int sub = 0;
				while (subiterator.hasNext() && sub < idx) {
					sub++;
					subiterator.next();
				}
				// check if others are duplicates
				while (subiterator.hasNext()) {
					E s = subiterator.next();
					if (e.equals(s)) {
						return e;
					}
				}
			}
		}
		return null;
	}
}
