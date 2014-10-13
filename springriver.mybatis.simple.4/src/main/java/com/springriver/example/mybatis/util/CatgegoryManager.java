package com.springriver.example.mybatis.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Product;
import com.springriver.example.mybatis.mapper.CategoryMapper;
import com.springriver.example.mybatis.mapper.ProductMapper;

public class CatgegoryManager {
	public static Category selectCategoryById(int id) {
		SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory()
				.openSession();
		try {
			CategoryMapper categoryMapper = sqlSession
					.getMapper(CategoryMapper.class);
			return categoryMapper.selectCategoryById(id);
		} finally {
			sqlSession.close();
		}
	}
	
	public static Category selectCategoryDeepById(int id) {
		SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory()
				.openSession();
		try {
			CategoryMapper categoryMapper = sqlSession
					.getMapper(CategoryMapper.class);
			return categoryMapper.selectCategoryDeepById(id);
		} finally {
			sqlSession.close();
		}
	}	
	
	public static void insertCategory(Category category){
		SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory()
				.openSession();
		try {
			CategoryMapper categoryMapper = sqlSession
					.getMapper(CategoryMapper.class);
			categoryMapper.insertCategory(category);
			List<Product> products = category.getProducts();
			if (products != null){
				ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
				for(Product product: products){
					productMapper.insertProduct(product);
					categoryMapper.insertCategoryProduct(category.getCategoryId(), product.getProductId());
				}
			}
			
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}		
	}
}
