package com.starcode88.jutils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestConditionChecker {

	@Test
	public void test() {
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertTrue(ConditionChecker.allConditionsAreTrue(list, (listElement) -> { return true;}));
		assertTrue(ConditionChecker.allConditionsAreTrue(list, (listElement) -> { Integer i = (Integer)listElement; return i.intValue() < 10;}));
		assertTrue(ConditionChecker.allConditionsAreTrue(list, (listElement) -> { Integer i = (Integer)listElement; return i.intValue() > 0;}));
	}
}
