package com.fastcampus.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SearchParam {
    private String account;
    private String email;
    private int page;


}
