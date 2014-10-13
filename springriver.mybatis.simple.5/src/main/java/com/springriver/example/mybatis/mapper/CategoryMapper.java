package com.springriver.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Product;

public interface CategoryMapper {
	public Category selectCategoryById(int id);
	public Category selectCategoryDeepById(int id);
	public Category selectCategoryDeepById2(int id);
	public void insertCategory(Category category);
	public void insertCategoryProduct(@Param("categoryId") int categoryId, @Param("productId") int productId);
}
