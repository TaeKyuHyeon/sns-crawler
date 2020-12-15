package com.example.springtest.controller;

import com.example.springtest.model.Article;
import com.example.springtest.repository.ArticleMongoRepository;
import com.example.springtest.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleMongoRepository articleMongoRepository;

    @GetMapping("/items")
    public List<Article> getArgs(@RequestParam long from){
        return articleMongoRepository.findByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(from);
    }

    @PostMapping("/items")
    public Map<String, Object> postArgs(@RequestBody Article article) {
        Map<String, Object> resultMap = new HashMap<>();
        articleMongoRepository.save(article);
        resultMap.put("article", article);
        return resultMap;
    }
}
