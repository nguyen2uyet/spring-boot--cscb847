package com.cscb847f89497.kursovarabota.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cscb847f89497.kursovarabota.dto.ArticleDTO;

@Service
public interface ArticleService {

    public ArticleDTO findOneBySubdomain(String subdomain);

    public List<ArticleDTO> findByNavbar(String navbar);

    public ArticleDTO findOneById(Long id);

    public List<String> findAllNavbar();

    public List<ArticleDTO> list();

    public void add(ArticleDTO ArticleDTO);

    public void delete(Long id);

    public void update(ArticleDTO ArticleDTO);

}
