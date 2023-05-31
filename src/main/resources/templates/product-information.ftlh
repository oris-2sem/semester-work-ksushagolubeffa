<!DOCTYPE html>
<html>
<#include "new-menu.ftlh">
<head>
    <meta charset="UTF-8">
    <title>${product.name}</title>
    <link rel="stylesheet" href="/css/product-info.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<style>
    :root {
        --main-color-start: #ffffff;
        --main-color-end: #ceab8f;
    }

    html {
        background-image: linear-gradient(to bottom, var(--main-color-start), var(--main-color-end));
        height: 100%;
    }

    body {
        background-image: linear-gradient(to bottom, var(--main-color-start), var(--main-color-end));
        background-attachment: fixed;
        background-size: 100% 100vh;
        font-family: Bahnschrift, sans-serif;
    }
</style>
<body>
<div class="container rounded-top rounded-bottom">
    <div class="row">
        <div class="col-md-6">
            <img src="/products/${product.uuid}/image" alt="Product Image" class="img-fluid" width="400">
        </div>
        <div class="col-md-6">
            <br><br><br><br><br>
            <h2>${product.name}</h2>
            <p>${product.description}</p>
            <p>Цена: ${product.price}₽/${product.usdPrice}$/${product.eurPrice}€</p>
            <p>Дата создания: ${product.date}</p>
            <div class="row list-group-horizontal">
                <#if isAdmin || isAuth>
                    <form method="post" action="/products/${product.uuid}/addToCart">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-primary mr-3">Добавить в корзину</button>
                    </form>
                </#if>
                <#if isAdmin>
                    <form method="get" action="/products/edit/${product.uuid}">
                        <input type="submit" class="btn btn-success mr-3" value="Редактировать">
                    </form>
                    <form method="post" action="/products/${product.uuid}/delete">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-danger">Удалить товар</button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
    <hr>
    <#if isAuth || isAdmin>
        <h2>Добавить комментарий</h2>
        <form action="/products/${product.uuid}/addComment" method="POST">
            <div class="form-group">
                <textarea class="form-control" id="comment" name="comment"></textarea>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Добавить комментарий</button>
        </form>
    </#if>
    <#if count>
        <div class="row lower">
            <div class="col-md-12">
                <h3>Комментарии</h3>
                <#list comments as comment>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="d-flex justify-content-between">
                                <h5>${comment.username}</h5>
                                <p>${comment.date}</p>
                            </div>
                            <div class="d-flex justify-content-between">
                                <p>${comment.text}</p>
                                <div>
                                    <#if (isAuth || isAdmin) && currentUser.username == comment.username>
                                        <form action="/products/${product.uuid}/deleteComment" method="POST">
                                            <input type="hidden" name="commentId" value="${comment.uuid}">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <input type="submit" class="close" value="&times;">
                                        </form>
                                    </#if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </#list>
            </div>
        </div>
    </#if>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>