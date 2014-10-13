package com.springriver.example.mybatis;

import java.util.List;

import org.junit.Test;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.util.CatgegoryManager;

public class CategoryManagerTest {



	@Test
	public void testCategory() {
		Category category = new Category();
		category.setCategoryName("Shoes");
		category.addProduct(new Product("Nike"));
		category.addProduct(new Product("Adidas"));
		CatgegoryManager.insertCategory(category);
		Category insertedCategory = CatgegoryManager.selectCategoryDeepById(category.getCategoryId());
		
		assert(insertedCategory != null);
		assert(insertedCategory.getCategoryName().equals("Shoes"));
		
		List<Product> products = category.getProducts();
		assert(products != null);
		assert(products.size() == 2);
		
		
	}	
	
}
