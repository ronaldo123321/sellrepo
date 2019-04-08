package com.anytec.sell.controller;

import com.anytec.sell.dataobject.ProductCategroy;
import com.anytec.sell.dataobject.ProductInfo;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.form.ProductForm;
import com.anytec.sell.service.ProductCategroyService;
import com.anytec.sell.service.ProductService;
import com.anytec.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategroyService categroyService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
       try {
           productService.onSale(productId);
       }catch (SellException e){
           map.put("msg",e.getMessage());
           map.put("url","/sell/seller/product/list");
           return new ModelAndView("common/error",map);
       }
        map.put("url","/sell/seller/product/list");
       return new ModelAndView("common/success",map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查所有类目
        List<ProductCategroy> productCategroyList = categroyService.findAll();
        map.put("categoryList",productCategroyList);

        return new ModelAndView("product/index",map);

    }


    @PostMapping("/save")
    @CacheEvict(value = "product",key = "123")//执行方法完毕后删除缓存
    //@CachePut(value = "product",key = "123")//每次执行完毕后 都会更新缓存
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            if(!StringUtils.isEmpty(productInfo.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtils.getUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }  catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }

}
