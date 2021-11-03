package com.example.demo.config.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex
 * @date 5/12/2021 2:17 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {

    private int page;

    private int pageSize = 10;

    public int getStartPos() {
        return (page - 1) * pageSize;
    }
}
