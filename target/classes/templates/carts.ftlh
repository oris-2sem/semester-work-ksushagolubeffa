<!DOCTYPE html>
<html lang="ru">
<#include "new-menu.ftlh">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<head>
    <title>Корзина товаров</title>
    <meta charset="utf-8">
    <style>
        :root {
            --main-color-start: #ffffff;
            --main-color-end: #ceab8f;
        }
        html {
            background-image: linear-gradient(to bottom, var(--main-color-start), var(--main-color-end));
            height: 100%;
        }
        body{
            background-image: linear-gradient(to bottom, var(--main-color-start), var(--main-color-end));
            background-attachment: fixed;
            background-size: 100% 100vh;
            font-family: Bahnschrift, sans-serif;
        }
    </style>
</head>
<body>
<div class="container bg-white rounded-top rounded-bottom mt-2" id="zero-pad">
    <div class="row d-flex justify-content-center mt-3">
        <div class="col-lg-10 col-12">
            <div class="d-flex">
                <div class="pt-1" style="margin-top: 10px"><h4>КОРЗИНА</h4></div>
            </div>
            <div class="d-flex mt-3" id="heading" >
                <div class="px-lg-5 mr-lg-5" id="new"></div>
                <div class="px-lg-5 mr-lg-5" id="product">ТОВАР</div>
                <div class="px-lg-5 ml-lg-5" id="price">ЦЕНА ₽</div>
                <div class="px-lg-5 ml-lg-1" id="usdPrice">ЦЕНА $</div>
                <div class="px-lg-5 ml-lg-3" id="eurPrice">ЦЕНА €</div>
                <div class="px-lg-5 ml-lg-3" id="total">УДАЛИТЬ</div>
            </div>
            <#list orders as order>
                <div class="d-flex flex-row justify-content-between align-items-center pt-lg-6 pt-2 pb-4 border-bottom mobile mt-3">
                    <div class="d-flex flex-row align-items-center">
                        <div><img src="/products/${order.product.uuid}/image" alt="${order.product.name}" width="150" height="150"></div>
                        <div class="d-flex flex-column pl-md-3 pl-1">
                            <div><h6><a href="/products/${order.product.uuid}">${order.product.name}</a></h6></div>
                        </div>
                    </div>
                    <div class="pl-md-0 pl-1"><b>${order.product.price}₽</b></div>
                    <div class="pl-md-0 pl-1"><b>${order.product.usdPrice}$</b></div>
                    <div class="pl-md-0 pl-1"><b>${order.product.eurPrice}€</b></div>
                    <form action="/cart/${order.uuid}" method="POST">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="close" value = "&times;">
                    </form>
                </div>
            </#list>

        </div>
    </div>
</div>
<div class="container rounded-bottom py-4" id="zero-pad">
    <div class="row d-flex justify-content-center">
        <div class="col-lg-10 col-12">
            <div class="d-flex justify-content-between align-items-center">
                <#if count>
                    <div>
                        <form action="/cart/deleteAll" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-lg px-lg-4 px-3" style="background-color: #bb7278; color:  #ffffff; border-color: #bb7278;">УДАЛИТЬ ВСЕ</button>
                        </form>
                    </div>
                    <div class="px-md-0 px-1" id="footer-font" style="font-size: 20px">
                        <a class="pl-md-4">Итоговая сумма:<span class="pl-md-4">${total}₽/ ${usd}$/ ${eur}€</span></a>
                    </div>
                    <div>
                        <form action="/cart" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-lg px-lg-5 px-3" style="background-color: #b06d42; color: #ffffff;">КУПИТЬ</button>
                        </form>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>