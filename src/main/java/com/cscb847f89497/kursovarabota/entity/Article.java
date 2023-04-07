package com.cscb847f89497.kursovarabota.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subdomain", nullable = false)
    private String subdomain;

    @Column(name = "navbar", nullable = false)
    private String navbar;

    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "abstract", nullable = false)
    private String abstractContent;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

}
