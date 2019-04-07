package com.anytec.sell.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryForm implements Serializable {

    private Integer categoryId;

    private String  categoryName;

    private Integer categoryType;
}
