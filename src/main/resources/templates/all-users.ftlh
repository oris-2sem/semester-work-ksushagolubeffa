<html>
<head>
    <title>Список пользователей</title>
</head>
<#include "new-menu.ftlh">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
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
    <table id="user-table" class="table">
        <thead>
        <tr>
            <th>ИМЯ</th>
            <th>ПОЧТА</th>
            <th>БАЛАНС</th>
            <th>РОЛЬ</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td><a href="/users/${user.uuid}">${user.username}</a></td>
                <td>${user.email}</td>
                <td>${user.balance}</td>
                <td>${user.role}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>