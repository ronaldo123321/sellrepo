<!DOCTYPE html>
<html lang="en">
<#--<head>-->
    <#--<meta charset="UTF-8">-->
    <#--<title>seller product list</title>-->
    <#--<link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">-->
    <#--<link rel="stylesheet" href="/sell/css/style.css">-->
<#--</head>-->
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <!--边栏sidebar-->
<#include "../common/nav.ftl">
    <!--主体内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>买家姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                   <#list orderDTOPage.content as orderDTO>
                   <tr class="success">
                       <td>${orderDTO.orderId}</td>
                       <td>${orderDTO.buyerName}</td>
                       <td>${orderDTO.buyerPhone}</td>
                       <td>${orderDTO.buyerAddress}</td>
                       <td>${orderDTO.orderAmount}</td>
                       <td>${orderDTO.getOrderStatusEnum().msg}</td>
                       <td>${orderDTO.getPayStatusEnum().msg}</td>
                       <td>${orderDTO.createTime}</td>
                       <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                       <td>
                           <#if orderDTO.getOrderStatusEnum().code == 0>
                               <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                           </#if>
                       </td>
                   </tr>
                   </#list>
                        </tbody>
                    </table>
                </div>
            <#--sort page-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                  <#if currentPage lte 1>
                       <li class="disabled"><a href="#">上一页</a></li>
                  <#else>
                      <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                  </#if>

                   <#list 1..orderDTOPage.getTotalPages() as index>
                       <#if currentPage == index>
                         <li class="disabled"><a href="#">${index}</a></li>
                       <#else>
                           <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                       </#if>
                   </#list>

                   <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                   <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                   </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>