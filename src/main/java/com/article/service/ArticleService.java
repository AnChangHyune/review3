package com.article.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.dao.ArticleDao;
import com.article.dto.Article;
import com.article.dto.ResultData;
import com.article.util.Util;


@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}
	
	public ResultData modfiyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id+"번은 없는 게시물입니다.", "id",id);
		}
		
		articleDao.modfiyArticle(id, title, body);
		
		return new ResultData("S-1", id+"번을 수정하였습니다.", "article",getArticleById(id));
	}
	
	public ResultData deleteArticle(int id) {
		Article article = getArticleById(id);
		if(article == null) {
			return new ResultData("F-1", id+"번은 없는 게시물입니다.", "id",id);
		}
		articleDao.deleteArticle(id);
		
		return new ResultData("S-1", id+"번 게시물을 삭제하였습니다.", "id",id);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public ResultData addArticle(Map<String, Object> param) {
		articleDao.addArticle(param);
		
		int id = Util.getAsInt(param.get("id"), 0);
		
		return new ResultData("S-1", id+"번 글이 등록되었습니다.", "id",id);
	}
	
}
