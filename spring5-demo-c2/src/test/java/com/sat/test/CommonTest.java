package com.sat.test;

import org.junit.Test;

import com.sat.bean.Ingredient;

public class CommonTest {
	
	@Test
	public void toStringTest() {
		Ingredient ingre1 = new Ingredient("1", "name1", Ingredient.Type.CHEESE);
		System.out.println(ingre1.toString());
	}
}
