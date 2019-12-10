package com.example.elastic;

import lombok.Data;

@Data
public class RssData {
    private String title;
    private String contents;
    private String writer;
    private String createDateTime;
}
