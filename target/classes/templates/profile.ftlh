<html>
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <#include "new-menu.ftlh">
    <link rel="stylesheet" href="/css/profile.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<style>
    body{
        font-family: Bahnschrift, sans-serif;
        background: linear-gradient(to bottom, #ffffff, #ceab8f);
    }
    a{
        font-size: 20px;
        color: #b2826e;
    }
    a:hover{
        font-size: 20px;
        color: #864f39;
    }
    p{
        font-size: 20px;
    }

</style>
<body>
<div class="container my-5 rounded-top rounded-bottom">
    <div class="row">
        <div class="col-md-6">
            <img src="/users/${user.uuid}/image" class="img-fluid">
        </div>
        <div class="col-md-6">
            <h3>${user.username}</h3>
            <p>Баланс: ${user.balance}₽</p>
            <a href="/profile/edit">Редактировать</a><br><br>
            <a href="/profile/media">Медиаконтент</a><br><br>
            <#if isAdmin>
            <a href="/users">Список пользователей</a><br><br>
            <a href="/profile/check">Заявки на публикацию</a><br><br>
            </#if>
            <form action="/logout" method="get">
                <button type="submit" class="btn btn-primary">Выйти</button>
            </form>
            <form action="/profile" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-danger">Удалить аккаунт</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>