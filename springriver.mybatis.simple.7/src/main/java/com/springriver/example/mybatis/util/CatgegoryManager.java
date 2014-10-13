package com.springriver.example.mybatis.util;

import java.util.List;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.mapper.CategoryMapper;
import com.springriver.example.mybatis.mapper.ProductMapper;

public class CatgegoryManager {
	public static Category selectCategoryById(final int id) {

		return (Category)new DatabaseOperation() {
			
			@Override
			public Object performAction() {
				return executeMapperFunction(CategoryMapper.class, "selectCategoryById", id);
			}
		}.invoke();
		
	}
	
	public static Category selectCategoryDeepById(final int id) {
		return (Category)new DatabaseOperation() {
			
			@Override
			public Object performAction() {
				return executeMapperFunction(CategoryMapper.class, "selectCategoryDeepById", id);
			}
		}.invoke();
	}	
	
	public static Category selectCategoryDeepById2(final int id) {
		return (Category)new DatabaseOperation() {
			
			@Override
			public Object performAction() {
				return executeMapperFunction(CategoryMapper.class, "selectCategoryDeepById2", id);
			}
		}.invoke();
	}	
	
	public static void insertCategory(final Category category){
	
		new DatabaseOperation(true) {
			
			@Override
			public Object performAction() {
						
				executeMapperFunction(CategoryMapper.class, "insertCategory", category);
				List<Product> products = category.getProducts();
				if (products != null){
				
					for(Product product: products){
						executeMapperFunction(ProductMapper.class, "insertProduct", product);
						executeMapperFunction(CategoryMapper.class, "insertCategoryProduct", new Object[]{category.getCategoryId(), product.getProductId()});
					}
				}	
				return category.getCategoryId();
			}
		}.invoke();		
		
	}
}
