package com.starcode88.jutils;

import java.util.List;

/**
 * Helper class with convenience functions to check
 * conditions.
 */
public class ConditionChecker {

	/**
	 * Checks if a condition is true for all elements in a list.
	 * Please check the unit tests how this class can be used.
	 * 
	 * @param list       The list which contains the items
	 * @param condition  A condition which is expected to be true. The
	 *                   condition takes one list item as argument.
	 * @return           true if the condition is true for all list items.
	 */
    public static <T> boolean allConditionsAreTrue(List<T> list, Condition condition) {
         
    	for (Object obj : list) {
    		if (condition.isTrue(obj) == false) {
    			return false;
    		}
    	}
    	
    	return true;
    }

	/**
	 * Checks if at least one condition is true for any element in the list.
	 *
	 * @param  list       the list of elements to check
	 * @param  condition  the condition to check for each element
	 * @return            true if at least one condition is true, false otherwise
	 */
	public static <T> boolean atLeastOneConditionIsTrue(List<T> list, Condition condition) {
		
		for (Object obj : list) {
			if (condition.isTrue(obj)) {
				return true;
			}
		}
		
		return false;	
	}
}
