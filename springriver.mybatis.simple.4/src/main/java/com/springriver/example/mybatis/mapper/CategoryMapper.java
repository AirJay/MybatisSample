package com.springriver.example.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.springriver.example.mybatis.bean.Category;

public interface CategoryMapper {
	public Category selectCategoryById(int id);
	public Category selectCategoryDeepById(int id);
	public void insertCategory(Category category);
	public void insertCategoryProduct(@Param("categoryId") int categoryId, @Param("productId") int productId);
}
