package com.article.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.article.dto.Article;
import com.article.dto.ResultData;
import com.article.service.ArticleService;


@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String searchKeywordType, String searchKeyword) {
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}

		return articleService.getArticles(searchKeywordType, searchKeyword);
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(Map<String, Object>param) {
		
		
		return articleService.addArticle(param);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id+"번은 없는 게시물입니다.", "id",id);
		}
		return new ResultData("S-1", id+"번 글 입니다.", "article",article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		
		return articleService.deleteArticle(id);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {

		return articleService.modfiyArticle(id, title, body);
	}
	

}
