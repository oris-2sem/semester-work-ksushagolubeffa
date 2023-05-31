<!DOCTYPE html>
<html>
<header>
    <title>Медиаконтент</title>
</header>
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
        <#list mediaList as content>
            <div class="col-md-4 mb-4 mt-3">
                <div class="card">
                    <img src="/media/${content.uuid}/image" class="card-img-top" width="300" height="300" alt="${content.name}">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/media/${content.uuid}">${content.name}</a></h5>
                        <p class="card-text">By <a href="/users/${content.user.uuid}">${content.user.username}</a></p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <a href="/media/add" class="btn btn-primary btn-lg rounded-circle text-center" style="position: fixed; bottom: 20px; right: 20px;">
        <span style="font-size: 32px;">+</span>
    </a>
</div>
</body>
</html>