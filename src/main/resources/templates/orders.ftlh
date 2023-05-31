<!DOCTYPE html>
<html lang="ru">
<#include "new-menu.ftlh">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
    <title>Заказы</title>
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
        .row{
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container bg-white rounded-top rounded-bottom mt-2">
    <div class="row d-flex justify-content-center mt-3">
        <div class="col-lg-10 col-12">
            <div class="d-flex">
                <div class="pt-1" style="margin-top: 15px"><h4>ЗАВЕРШЕННЫЕ ЗАКАЗЫ</h4></div>
            </div>
            <div class="d-flex  mt-3" id="heading">
                <div class="px-lg-5 mr-lg-5" id="product">ТОВАР</div>
                <div class="px-lg-5 ml-lg-5" id="price">ЦЕНА ₽</div>
                <div class="px-lg-5 ml-lg-1" id="usdPrice">ЦЕНА $</div>
                <div class="px-lg-5 ml-lg-3" id="eurPrice">ЦЕНА €</div>
                <div class="px-lg-5 ml-lg-3" id="total">ДАТА ЗАКАЗА</div>
            </div>
            <#list products as product>
                <div class="d-flex flex-row justify-content-between align-items-center pt-lg-6 pt-2 pb-4 border-bottom mobile">
                    <div class="d-flex flex-row align-items-center">
                        <div><img src="/products/${product.uuid}/image" alt="${product.name}" width="150" height="150"></div>
                        <div class="d-flex flex-column pl-md-3 pl-1">
                            <div><h6><a href="/products/${product.uuid}">${product.name}</a></h6></div>
                        </div>
                    </div>
                    <div class="pl-md-0 pl-1"><b>${product.price} ₽</b></div>
                    <div class="pl-md-0 pl-1"><b>${product.usdPrice} $</b></div>
                    <div class="pl-md-0 pl-1"><b>${product.eurPrice} €</b></div>
                    <div class="pl-md-0 pl-1"><b>${product.date} </b></div>
                </div>
            </#list>
        </div>
    </div>
</div>

</body>
</html>