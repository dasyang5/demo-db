package com.example.demo.config.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alex
 * @date 5/13/2021 9:07 AM
 */
@Data
@AllArgsConstructor
public class FuzzyConfig {

    private String prefix;

    private String suffix;

    public static FuzzyConfig FUZZY_FULL = new FuzzyConfig("%", "%");

    public static FuzzyConfig FUZZY_WITH_PREFIX = new FuzzyConfig("%", null);

    public static FuzzyConfig FUZZY_WITH_SUFFIX = new FuzzyConfig(null, "%");

}
