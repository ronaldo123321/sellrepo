package com.anytec.sell.dataobject.mapper;

import com.anytec.sell.dataobject.ProductCategroy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategroy productCategroy);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({   //将数据库中的字段映射成对象object中的内容
         @Result(column = "category_id",property = "categoryId"),
         @Result(column = "category_type",property = "categoryType"),
         @Result(column = "category_name",property = "categoryName")
    })
    ProductCategroy findByCategoryType(Integer categoryType);


}
