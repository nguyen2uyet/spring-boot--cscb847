package com.cscb847f89497.kursovarabota.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cscb847f89497.kursovarabota.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findOneBySubdomain(String subdomain);

    List<Article> findByNavbar(String navbar);
}
