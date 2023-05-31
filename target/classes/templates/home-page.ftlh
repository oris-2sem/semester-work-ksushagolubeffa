<!DOCTYPE html>
<html lang="en">
<#include "new-menu.ftlh">
<link rel="stylesheet" href="/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<head>
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
        p{
            /*text-shadow: #815842;*/
            /*color: #a97b60;*/
            text-align: center;
            margin-left: 20px;
            margin-right: 20px;
            font-size: 20px;
        }
    </style>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<br>
<p>Добро пожаловать на фанатский сайт по Гарри Поттеру. Тут Вы сможете найти мерч фандома, а также посмотреть и загрузить связанный с ним контент.</p>
<div class="container">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="images/slide2.jpg" alt="First slide" height="550">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/back.jpg" alt="Second slide" height="550">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/slide3.jpg" alt="Third slide" height="550">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <br><br>
    <p>Контент недели:</p>
    <div class="row">
        <#list contents as content>
            <div class="column ml-3">
                <div><img src="/media/${content.uuid}/image" alt="${content.name}" width="150" height="150"></div>
                <a href="/media/${content.uuid}">${content.name}</a>
                <div>${content.date}</div>
                <a href="/users/${content.user.uuid}">${content.user.username}</a>
            </div>
        </#list>
    </div>
</div>
</body>
<script>
    const carouselElement = document.querySelector('#carouselExampleIndicators');
    carouselElement.addEventListener('slid.bs.carousel', (event) => {
        console.log('Carousel slid to index', event.to);
    });
</script>
</html>