package com.article.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.article.dto.Article;


@Mapper
public interface ArticleDao {

	public List<Article> getArticles(@Param(value = "searchKeywordType")String searchKeywordType, @Param(value = "searchKeyword")String searchKeyword);

	public void deleteArticle(@Param(value = "id")int id);

	public Article getArticleById(@Param(value = "id")int id);

	public void addArticle(Map<String, Object> param);

	public void modfiyArticle(@Param(value = "id")int id, @Param(value = "title")String title, @Param(value = "body")String body);

	
}
