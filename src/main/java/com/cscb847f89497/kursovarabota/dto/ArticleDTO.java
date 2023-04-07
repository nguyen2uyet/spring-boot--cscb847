package com.cscb847f89497.kursovarabota.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private String subdomain;
    private String picture;
    private String navbar;
    private String title;
    private String abstractContent;
    private String content;
}
