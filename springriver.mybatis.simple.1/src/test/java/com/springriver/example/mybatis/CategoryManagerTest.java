package com.springriver.example.mybatis;

import org.junit.Test;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.util.CatgegoryManager;

public class CategoryManagerTest {

	
	@Test
	public void testUpdateUser() {
		Category category = CatgegoryManager.selectCategoryById(1);
		assert(category != null);
		assert(category.getCategoryName().equals("Apparel"));
		
	}

}
