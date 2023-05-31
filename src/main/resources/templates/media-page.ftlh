<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/searching.css">
    <meta charset="utf-8">
    <#include "new-menu.ftlh">
    <title>Список медиа</title>
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
    body{
        background-image: linear-gradient(to bottom, var(--main-color-start), var(--main-color-end));
        background-attachment: fixed;
        background-size: 100% 100vh;
        font-family: Bahnschrift, sans-serif;
    }
</style>
<body>
<div class="container">
    <h1>Список медиа по вашему запросу</h1>
    <div class="row">
        <#list listMedia as content>
            <div class="column">
                <div><img src="/media/${content.uuid}/image" alt="${content.name}" width="150" height="150"></div><br>
                <div><a href="/media/${content.uuid}">${content.name}</a></div>
                <div><a href="/users/${content.user.uuid}">${content.user.username}</a></div>
            </div>
        </#list>
    </div>
</div>
</body>
</html>
