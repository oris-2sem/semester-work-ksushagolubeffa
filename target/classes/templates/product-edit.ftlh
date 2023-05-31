<!DOCTYPE html>
<html>
<#include "new-menu.ftlh">
<link rel="stylesheet" href="/css/product-edit.css">
<head>
    <title>Редактировать информацию о товаре</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
        h2{
            margin-top: 20px;
            color: #3d3d3d;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Редактировать информацию о товаре "${product.name}"</h2>
    <form action="/products/edit/${product.uuid}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Название товара</label>
            <input type="text" class="form-control" id="name" name = "name" placeholder="Введите название товара">
        </div>
        <div class="form-group">
            <label for="price">Цена товара</label>
            <input type="number" class="form-control" id="price" name = "price" placeholder="Введите цену товара">
        </div>
        <div class="form-group">
            <label for="description">Описание товара</label>
            <textarea class="form-control" id="description" rows="3" name = "description" placeholder="Введите описание товара"></textarea>
        </div>
        <div class="form-group">
            <label for="image">Фото товара</label>
            <input type="file" class="form-control-file" name = "image" id="image">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
    </form>
</div>

<!-- Add the Bootstrap JavaScript files -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>