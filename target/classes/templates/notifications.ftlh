<!DOCTYPE html>
<html lang="ru">
<#include "new-menu.ftlh">
<link rel="stylesheet" href="/css/notification.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<head>
    <meta charset="UTF-8">
    <title>Уведомления</title>
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
<body >
<div class="row notification-container rounded-top rounded-bottom">
    <h2 class="text-center">Уведомления</h2>
    <#list notifications as notification>
                <table>
                    <#if notification.message??>

                        <div class="card-body border-bottom justify-content-between">
                            <td ><div class="card-title" style="border: black">
                                    <img src="/images/notification.png" alt="Image" class="mr-2" width="30">
                                    Статус вашего контента изменен на ${notification.message}
                                </div></td>
                            <td> <div class="card-body d-flex justify-content-end">
                                <a href="/media/${notification.content.uuid}" class="btn btn-primary">Посмотреть</a>
                                <form method="POST" action="/profile/notifications/delete/${notification.uuid}" >
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="submit" class="btn btn-danger dismiss-notification ml-3" value="Прочитано">
                                </form>
                                </div>
                            </td>
                        </div>
                    <#else>
                        <div class="card-body">
                            <td><div class="card-title">
                                    <img src="/images/like.jpg" alt="Image" class="mr-2" width="30">
                                    <a href="/users/${notification.author.uuid}"><b>${notification.author.username}</b> </a>: ${notification.comment.text}</div></td>
                            <td><div class="card-body d-flex justify-content-end">
                                <a href="/media/${notification.content.uuid}" class="btn btn-primary">Посмотреть</a>
                                <form method="POST" action="/profile/notifications/delete/${notification.uuid}" >
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="submit" class="btn btn-danger dismiss-notification ml-3" value="Прочитано">
                                </form>
                                </div>
                            </td>
                        </div>
                    </#if>
                </table>

    </#list>
    <#if count>
    <form method="POST" action="/profile/notifications" >
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-danger dismiss-notification" value="Очистить уведомления">
    </form>
    </#if>
</div>
</body>
<script src="/js/notification.js"></script>
</html>