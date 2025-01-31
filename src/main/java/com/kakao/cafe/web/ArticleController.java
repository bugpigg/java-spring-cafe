package com.kakao.cafe.web;

import com.kakao.cafe.CustomLogger;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.web.dto.ArticleDetailDto;
import com.kakao.cafe.web.dto.ArticleRegisterFormDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private static final CustomLogger log = new CustomLogger(ArticleController.class);
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions")
    public String create(@ModelAttribute ArticleRegisterFormDto articleRegisterFormDto) {
        log.info("post question : {}", articleRegisterFormDto);
        articleService.register(articleRegisterFormDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model) {
        log.info("show question's list");
        model.addAttribute("questions", articleService.showAll());
        return "index";
    }

    @GetMapping("/questions/{articleId}")
    public String show(@PathVariable String articleId, Model model) {
        log.info("show {}'s article", articleId);
        ArticleDetailDto articleDetailDto = articleService.showOne(articleId);
        model.addAttribute("writer", articleDetailDto.getWriter());
        model.addAttribute("title", articleDetailDto.getTitle());
        model.addAttribute("createdTime", articleDetailDto.getCreatedTime());
        model.addAttribute("contents", articleDetailDto.getContents());
        return "qna/show";
    }
}
