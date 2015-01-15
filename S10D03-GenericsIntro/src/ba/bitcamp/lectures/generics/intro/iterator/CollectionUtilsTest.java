package ba.bitcamp.lectures.generics.intro.iterator;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtilsTest {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		names.add("test");
		names.add(null);
		names.add("another test");
		
		// before cleanup
		System.out.println("Before cleanup: " + names);
		int removed = CollectionUtils.removeNulls(names);
		System.out.println("Removed: " + removed);
		System.out.println("After cleanup: " + names);
		
		Collection<String> prefixedRaw = CollectionUtils.withPrefixRaw(names, "te");
		System.out.println("Prefixed raw: " + prefixedRaw);
		Collection<String> prefixed = CollectionUtils.withPrefix(names, "te");		
		System.out.println("Prefixed: " + prefixed);
		
		names.add(null);
		System.out.println("\nAdded null: " + names);
		//need to cast
		Collection<String> cleanedNames = (Collection<String>)CollectionUtils.toCleanedRaw(names);
		System.out.println("Cleaned raw: " + cleanedNames);
		
		System.out.println("\nStill have null: " + names);
		cleanedNames = CollectionUtils.toCleaned(names);
		System.out.println("Cleaned parameterized: " + cleanedNames);
		
		// check duplicates
		System.out.println("\nHas duplicates names: " + CollectionUtils.hasDuplicates(names));
		System.out.println("Has duplicates cleaned: " + CollectionUtils.hasDuplicates(cleanedNames));
		
		names.add(null);
		System.out.println("\nDuplicate null: " + names);
		System.out.println("Has duplicates names: " + CollectionUtils.hasDuplicates(names));

		cleanedNames.add("test");
		System.out.println("\nDuplicate test: " + cleanedNames);
		System.out.println("Has duplicates cleaned: " + CollectionUtils.hasDuplicates(cleanedNames));
		
		System.out.println("\nFirst duplicate value: " + CollectionUtils.firstDuplicateValue(cleanedNames));
	}
}
