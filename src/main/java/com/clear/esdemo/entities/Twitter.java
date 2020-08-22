package com.clear.esdemo.entities;

import lombok.Data;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.Date;
import java.util.Map;

/**
 * @program: esdemo
 * @description
 * @author: liuhui
 * @create: 2020-05-08 12:45
 **/
@Data
public class Twitter {

    private Long id;

    private String user;

    private Date postDate;

    private String message;

    private Map<String, HighlightField> highlight;


}
