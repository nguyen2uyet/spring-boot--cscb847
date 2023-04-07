package com.cscb847f89497.kursovarabota.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cscb847f89497.kursovarabota.dto.ArticleDTO;
import com.cscb847f89497.kursovarabota.entity.Article;
import com.cscb847f89497.kursovarabota.repository.ArticleRepository;
import com.cscb847f89497.kursovarabota.service.ArticleService;
import com.cscb847f89497.kursovarabota.utils.Utils;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleDTO findOneById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        return Utils.mapArticleToArticleDTO(article);
    }

    @Override
    public List<ArticleDTO> list() {
        return Utils.mapListArticleDTOs(articleRepository.findAll());
    }

    @Override
    public void add(ArticleDTO articleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(articleDTO, Article.class);
        articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void update(ArticleDTO articleDTO) {
        ModelMapper mapper = new ModelMapper();
        Article oldArticle = articleRepository.findById(articleDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + articleDTO.getId()));
        oldArticle = mapper.map(articleDTO, Article.class);
        articleRepository.save(oldArticle);
    }

    @Override
    public ArticleDTO findOneBySubdomain(String subdomain) {
        return Utils.mapArticleToArticleDTO(articleRepository.findOneBySubdomain(subdomain));

    }

    @Override
    public List<ArticleDTO> findByNavbar(String navbar) {
        return Utils.mapList(articleRepository.findByNavbar(navbar), ArticleDTO.class);
    }

    @Override
    public List<String> findAllNavbar() {
        return articleRepository.findDistinctNavbar();
    }

}
