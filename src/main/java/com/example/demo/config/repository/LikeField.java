package com.example.demo.config.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alex
 * @date 5/13/2021 9:25 AM
 */
@Data
@AllArgsConstructor
public class LikeField {

    private String fieldName;

    private FuzzyConfig fuzzyConfig;

    public LikeField(String fieldName) {
        this.fieldName = fieldName;
        this.fuzzyConfig = FuzzyConfig.FUZZY_FULL;
    }


    public LikeField(String fieldName, String prefix, String suffix) {
        this.fieldName = fieldName;
        this.fuzzyConfig = new FuzzyConfig(prefix, suffix);
    }

    public static LikeField[] generator(String... fieldNames) {
        LikeField[] likeField = new LikeField[fieldNames.length];

        for (int i = 0; i < fieldNames.length; i++) {
            LikeField obj = new LikeField(fieldNames[i]);
            likeField[i] = obj;
        }

        return likeField;
    }
}
