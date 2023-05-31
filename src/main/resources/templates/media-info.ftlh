<!DOCTYPE html>
<html>
<#include "new-menu.ftlh">
<head>
    <title>${content.name}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/media-info.css">
    <#--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"-->
    <#--          integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
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
<div class="container">
    <br>
    <h1>${content.name}</h1><br>
    <video width="1100" controls>
        <source src="/media/${content.uuid}/video" type="${content.video.contentType}"/>
    </video>
    <br><br>
    <div class="d-flex justify-content-between">
        <h3>Автор: <a href="/users/${content.user.uuid}" style="color:#864f39">${content.user.username}</a></h3>
        <p>${likes} лайков</p>
    </div>
    <div class="d-flex justify-content-between">
        <p>Дата создания: ${content.date}</p>
        <#if isAuth || isAdmin>
            <#if isLiked>
                            <form action="/media/${content.uuid}/removeLike" method="POST">
<#--                <form>-->
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="unlike-button"></button>
                </form>
            <#else>
<#--                <form>-->
                <form action="/media/${content.uuid}/addLike" method="POST">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="like-button"></button>
                </form>
            </#if>
        </#if>
    </div>
    <h5>${content.description}</h5>
    <div class="row list-group-horizontal">
        <#if isAuthor || isAdmin>
            <form method="get" action="/media/edit/${content.uuid}">
                <input type="submit" class="ml-3 btn btn-success mr-3" value="Редактировать">
            </form>
            <form method="post" action="/media/${content.uuid}/delete">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-danger">Удалить контент</button>
            </form>
        </#if>
    </div>
    <hr>
    <#if isAuth || isAdmin>
        <h3>Добавить комментарий</h3>
        <form action="/media/${content.uuid}/addComment" method="POST">
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
                                        <form action="/media/${content.uuid}/deleteComment" method="POST">
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
<script src="/js/add-like.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@widgetbot/crate@3/dist/crate.esm.js"></script>

</body>
</html>