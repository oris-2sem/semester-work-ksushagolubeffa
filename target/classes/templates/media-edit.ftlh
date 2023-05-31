<!DOCTYPE html>
<html>
<#include "new-menu.ftlh">
<head>
    <title>Редактировать медиаконтент</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/media-add.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        h2{
            margin-top: 20px;
            color: #3d3d3d;
        }
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
<div class="container">
    <h2>Редактировать медиаконтент "${content.name}"</h2>
    <form method="POST" action="/media/edit/${content.uuid}" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Название медиаконтента</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Введите название медиаконтента" required>
        </div>
        <div class="form-group">
            <label for="description">Описание медиаконтента</label>
            <textarea class="form-control" id="description" name="description" rows="3" placeholder="Введите описание медиаконтента" required></textarea>
        </div>
        <div class="form-group">
            <label for="previewImage">Фото для превью</label>
            <input type="file" class="form-control-file" id="previewImage" name="previewImage" required>
        </div>
        <#if isAdmin>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Применить изменения</button>
        <#else>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Отправить на рассмотрение</button>
    </form>
    </#if>
</div>
<!-- Add the Bootstrap JavaScript files -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>