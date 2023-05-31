<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
</head>
<link rel="stylesheet" href="/css/menu.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<#--<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
<script src="/js/menu.js"></script>
<nav class="navbar navbar-expand-lg navbar-light ">
    <a class="navbar-brand" href="/">Главная</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="new-menu" id="new-menu">
        <ul class="nav navbar-nav mr-auto" >
            <li class="nav-item">
                <a class="nav-link" href="/products">Товары</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/media">Медиаконтент</a>
            </li>
            <li class="nav-item">
                <form action="/media/search" class="input-group">
                    <input type="text" class="form-control mr-3 rounded-right no-border" placeholder="Введите название контента" aria-describedby="basic-addon2" id="search" name="search" style="font-family: Bahnschrift, sans-serif; ">
                    <button type="submit" href="/media/search" class="btn btn-primary">Поиск</button>
                </form>
            </li>
        </ul>

        <ul class="nav navbar-nav ml-auto">
            <li class="dropdown">
                <a href="/profile" class="dropdown-toggle" data-toggle="dropdown">Профиль<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/profile/notifications">Уведомления</a></li>
                    <li><a href="/profile/orders">Заказы</a></li>
                    <li><a href="/profile/likes">Понравилось</a></li>
                    <li><a href="/profile/media">Мой контент</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart">Корзина</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signIn">Войти</a>
            </li>
        </ul>
    </div>
</nav>
</html>
