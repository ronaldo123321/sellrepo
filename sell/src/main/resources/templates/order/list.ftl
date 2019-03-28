<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>seller product list</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
   <#--Hi freeMarker-->
<#--<h1>${orderDTOPage.getTotalPages()}</h1>-->
<#--<h3>${orderDTOPage.totalPages}</h3>-->
<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}<br/>-->
<#--</#list>-->
   <div class="container">
       <div class="row clearfix">
           <div class="col-md-12 column">
               <table class="table">
                   <thead>
                   <tr>
                       <th>
                           订单编号
                       </th>
                       <th>
                           买家姓名
                       </th>
                       <th>
                           手机号
                       </th>
                       <th>
                          地址
                       </th>
                       <th>
                          金额
                       </th>
                       <th>
                          订单状态
                       </th>
                       <th>
                           支付状态
                       </th>
                       <th>
                           创建时间
                       </th>
                       <th colspan="2">
                           操作
                       </th>
                   </tr>
                   </thead>
                   <tbody>
                   <#list orderDTOPage.content as orderDTO>
                   <tr class="success">
                       <td>
                           ${orderDTO.orderId}
                       </td>
                       <td>
                           ${orderDTO.buyerName}
                       </td>
                       <td>
                           ${orderDTO.buyerPhone}
                       </td>
                       <td>
                           ${orderDTO.buyerAddress}
                       </td>
                       <td>
                           ${orderDTO.orderAmount}
                       </td>
                       <td>
                           ${orderDTO.orderStatus}
                       </td>
                       <td>
                           ${orderDTO.payStatus}
                       </td>
                       <td>
                           ${orderDTO.createTime}
                       </td>
                       <td>
                          <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                       </td>
                       <td>
                           取消
                       </td>
                   </tr>

                   </#list>


                   </tbody>
               </table>
           </div>
       </div>
   </div>
</body>
</html>