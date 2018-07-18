package com.concretepage.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Article;
import com.concretepage.repository.ArticleRepository;
import com.concretepage.service.IArticleService;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


@Controller
@RequestMapping("user")
public class ArticleController {
    
     
    //private static final Logger log = LogManager.getLogger(ArticleController.class);
     
	@Autowired
	private IArticleService articleService;
        
        @Autowired
        ArticleRepository articleRepository;


	@GetMapping("article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
	
	    Article article = articleService.getArticleById(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

        
       
        

	@GetMapping("articles")
	public ResponseEntity<List<Article>> getAllArticles() {
         //   log.info(" getAllArticles  getAllArticles ");
         //   log.warn(" Warning getAllArticles");
            System.out.println(" ALLLLLLLLLLLLLLLLLLLLLL");
		   List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

        
        @GetMapping("articles1")
	public ResponseEntity<List<Article>> getAllArticles1() {
          //  log.info(" getAllArticles  getAllArticles11 ");
          //  log.warn(" Warning getAllArticles 111");
            System.out.println(" getAllArticles11");
            List<Article> list=articleRepository.findAll();
		//List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}


	@PostMapping("article")
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.addArticle(article);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}



	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@DeleteMapping("article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("article/test")
	public ResponseEntity<Article> getArticletesttest() {
           
		Article article = new Article();
		article.setArticleId(1);
		article.setTitle("TESTTITLE");
		article.setCategory("TESTCATEROGY");

		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}


	@GetMapping("article/test1")
	public ResponseEntity<Article> getArticletesttest1() {
		

		Article article = new Article();
		article.setArticleId(11);
		article.setTitle("TESTTITLE1");
		article.setCategory("TESTCATEROGY1");

		Article article1=	getDBConnection1();

		return new ResponseEntity<Article>(article1, HttpStatus.OK);
	}



	@GetMapping("article/test/{id}")
	public ResponseEntity<Article> getArticletestById(@PathVariable("id") Integer id) {
            
		Article article = new Article();
		article.setArticleId(1);
		article.setTitle("TESTTITLE");
		article.setCategory("TESTCATEROGY");
		Article article1=	getDBConnection();

		return new ResponseEntity<Article>(article1, HttpStatus.OK);
	}

	private Article getDBConnection(){
		
		List<Article> articleList=new ArrayList<>();
		Article article=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");


			String url ="jdbc:mysql://vinaymysql.mysql.database.azure.com:3306/test?useSSL=true&requireSSL=false";
			Connection	myDbConn = DriverManager.getConnection(url, "vinayadmin@vinaymysql", "Vinay@2018");


			Statement stmt=myDbConn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from articles");

			while(rs.next())
				article=new Article();
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			article.setArticleId(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setCategory(rs.getString(3));
			articleList.add(article);
			myDbConn.close();
		}catch(Exception e){
			
			System.out.println(e);
		}

		return article;
	}


	private Article getDBConnection1(){
		
		//List<Article> articleList=new ArrayList<>();
		Article article=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url ="jdbc:mysql://vinaymysql.mysql.database.azure.com:3306/test";
			Connection	myDbConn = DriverManager.getConnection(url, "vinayadmin@vinaymysql", "Vinay@2018");


			Statement stmt=myDbConn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from articles");

			while(rs.next())
				article=new Article();
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			article.setArticleId(100);
			article.setTitle("TEST");
			article.setCategory("TEST1222");
			//articleList.add(article);
			myDbConn.close();
		}catch(Exception e){
			
			System.out.println(e);}

		return article;
	}


} 