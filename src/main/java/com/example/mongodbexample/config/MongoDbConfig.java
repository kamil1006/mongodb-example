package com.example.mongodbexample.config;

import com.example.mongodbexample.entity.Article;
import com.example.mongodbexample.entity.Comment;
import com.example.mongodbexample.repository.ArticleRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Component
public class MongoDbConfig {

    private MongoTemplate mongoTemplate;

    private ArticleRepo articleRepo;

    public MongoDbConfig(MongoTemplate mongoTemplate, ArticleRepo articleRepo) {
        this.mongoTemplate = mongoTemplate;
        this.articleRepo = articleRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        //return "";
        System.out.println("na starcie2");

        int index = 0;

        while (index < 10) {
            Faker faker = new Faker();
            //faker.

            Article article = null;
            article = new Article();
            //article.setAuthor("Pemek");
            //article.setTitle("juj start");
            article.setAuthor(faker.gameOfThrones().character());
            article.setTitle(faker.book().title());


            Comment comment1 = new Comment();
           // comment1.setText("siuper art");
            comment1.setText(faker.chuckNorris().fact());
            Comment comment2 = new Comment();
            //comment2.setText("do bani art");
            comment2.setText(faker.chuckNorris().fact());

            article.setCommentList(Arrays.asList(comment1, comment2));


            mongoTemplate.insert(article);




            index++;
        }

        Article article = null;
        article = new Article();
        article.setAuthor("Pemek");
        article.setTitle("juj start2");
        Comment comment1 = new Comment();
        comment1.setText("siuper art");
        Comment comment2 = new Comment();
        comment2.setText("do bani art");
        article.setCommentList(Arrays.asList(comment1, comment2));
        mongoTemplate.insert(article);
        articleRepo.save(article);








        List<Article> all = mongoTemplate.findAll(Article.class);
        System.out.println("wszystkie");
        System.out.println(all);
        Query query = new Query((Criteria.where("author").is("Pemek")));
        List<Article> articles = mongoTemplate.find(query, Article.class, "article");
        System.out.println("jeden po authorze");
        System.out.println(articles);

        System.out.println("po tytule");
        Article do_bani_art = articleRepo.findArticleByTitle("juj start2").get();
        System.out.println(do_bani_art);

    }


    @Transactional
    public void tryAddElement(){

        Article article = null;
        article = new Article();
        article.setAuthor("Pemek");
        article.setTitle("juj start2");
        Comment comment1 = new Comment();
        comment1.setText("siuper artyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        Comment comment2 = new Comment();
        comment2.setText("do bani art");
        article.setCommentList(Arrays.asList(comment1, comment2));
        articleRepo.save(article);
      //  System.out.println(10/0);

    }
//--------------------------------------------------------------------------------------------------------


}
