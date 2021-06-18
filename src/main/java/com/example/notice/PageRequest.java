package com.example.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PageRequest {
    private int page;
    private int offset;
    private int size;
    private String direction;
    private String sort;


    public PageRequest(int page, int size, String direction, String sort) {
        this.page = page;
        this.offset = page * 10 - 10;
        this.size = size;
        this.direction = direction;
        this.sort = sort;
    }
}
