package com.anytec.sell.controller;

import com.anytec.sell.dataobject.ProductCategroy;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.form.CategoryForm;
import com.anytec.sell.form.ProductForm;
import com.anytec.sell.service.ProductCategroyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {


    @Autowired
    private ProductCategroyService categroyService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategroy> categroyList = categroyService.findAll();
        map.put("categroyList",categroyList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId != null){
            ProductCategroy productCategroy = categroyService.findOne(categoryId);
            map.put("category",productCategroy);
        }
        return new ModelAndView("category/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult, Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategroy productCategroy = new ProductCategroy();
       try {
           if(form.getCategoryId() != null){
               productCategroy = categroyService.findOne(form.getCategoryId());
           }
           BeanUtils.copyProperties(form,productCategroy);
           categroyService.save(productCategroy);
       } catch (SellException e){
           map.put("msg",e.getMessage());
           map.put("url","/sell/seller/category/index");
           return new ModelAndView("common/error",map);
       }
       map.put("url","/sell/seller/category/list");
       return new ModelAndView("common/success",map);

    }

}
