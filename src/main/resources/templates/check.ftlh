<!DOCTYPE html>
<html lang="ru">
<#include "new-menu.ftlh">
<link rel="stylesheet" href="/css/check.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
    <title>Список контента</title>
    <meta charset="utf-8">
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
<div class="container bg-white rounded-top mt-2" id="zero-pad">
    <div class="row d-flex justify-content-center mt-3">
        <div class="col-lg-10 col-12">
            <div class="d-flex">
                <div class="pt-1"><h4>СПИСОК МЕДИАКОНТЕНТА</h4></div>
            </div>
            <div class="d-flex mt-3" id="heading" >
                <div class="px-lg-5 ml-lg-2" id="image"></div>
                <div class="px-lg-5 ml-lg-2" id="newimage"></div>
                <div class="px-lg-5 mr-lg-2" id="name">НАЗВАНИЕ</div>
                <div class="px-lg-5 ml-lg-1" id="user">АВТОР</div>
                <div class="px-lg-5 ml-lg-3" id="actions"></div>
            </div>
            <#list contentList as content>
                <div class="d-flex flex-row justify-content-between align-items-center pt-lg-6 pt-2 pb-4 border-bottom mobile mt-3">
                    <div class="d-flex flex-row align-items-center">
                        <div><img src="/media/${content.uuid}/image" alt="${content.name}" width="150" height="150"></div>
                    </div>
                    <div class="pl-md-0 pl-1 justify-content-end"><a href = "/media/${content.uuid}"><b>${content.name}</b></a> </div>
                    <form action="/users/${content.user.uuid}">
                        <div class="pl-md-0 pl-1 justify-content-end"><a href = "/users/${content.user.uuid}"><b>${content.user.username}</b></a> </div>
                    </form>
                    <form action="/profile/check/${content.uuid}/accept" method="POST">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-primary" value = "ПРИНЯТЬ">
                    </form>
                    <form action="/profile/check/${content.uuid}/reject" method="POST">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-danger dismiss-notification" value = "ОТКЛОНИТЬ">
                    </form>
                </div>
            </#list>
        </div>
    </div>

</div>

</body>
</html>