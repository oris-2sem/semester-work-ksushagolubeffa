<!DOCTYPE html>
<html>
<head>
    <title>Список продуктов</title>
</head>
<#include "new-menu.ftlh">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<body>
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
<div class="container">
    <div class="row">
        <#list products as product>
            <div class="col-md-4 mb-4 mt-3">
                <div class="card">
                    <img src="/products/${product.uuid}/image" class="card-img-top" width="300" height="300" alt="${product.name}">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/products/${product.uuid}">${product.name}</a></h5>
                        <p class="card-text">Price: ${product.price} рублей</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <#if isAdmin>
    <a href="/products/add" class="btn btn-primary btn-lg rounded-circle text-center" style="position: fixed; bottom: 20px; right: 20px;">
        <span style="font-size: 32px;">+</span>
    </a>
    </#if>
</div>
</body>
</html>