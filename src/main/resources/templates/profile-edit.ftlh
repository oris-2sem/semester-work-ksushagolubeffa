<!DOCTYPE html>
<html lang="ru">
<head>
    <#include "new-menu.ftlh">
    <meta charset="utf-8">
</head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/edit_profile.css">
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

    <form method="POST" action="/profile/edit" enctype="multipart/form-data">
        <div class="row">
            <!-- left column -->
            <div class="col-md-4">
                <div class="text-center">
                    <img src="/users/${user.uuid}/image" height="350" class="mb-3"  alt="Аватар" required>
                    <h6>Загрузите другое фото</h6>

                    <input type="file" class="form-control" id="image" name="image">
                </div>
            </div>

            <!-- edit form column -->
            <div class="col-md-8 personal-info">
                <div class="alert alert-info alert-dismissable">
                    <a class="panel-close close" data-dismiss="alert">×</a>
                    <i class="fa fa-coffee"></i>
                    После редактирования нажмите <strong>Подтвердить изменения</strong>. Иначе информация не сохранится.
                </div>
                <h3>Информация о пользователе</h3>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label">Username:</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" id="username" name="username" value="${user.username}"required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Старый пароль:</label>
                        <div class="col-md-8">
                            <input class="form-control" type="password" id="password" name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Новый пароль:</label>
                        <div class="col-md-8">
                            <input class="form-control" type="password" id="newPassword"  name="newPassword"required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="submit" class="btn btn-primary" value="Подтвердить изменения">
                            <span></span>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="button" class="btn btn-default" href = "/profile" value="Отменить">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </form>
</div>
<hr>
</body>
</html>