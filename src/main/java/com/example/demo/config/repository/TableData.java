package com.example.demo.config.repository;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 5/12/2021 2:16 PM
 */
@Data
public class TableData<T> {

    private long count;

    private List<T> data = new ArrayList<>();

}
