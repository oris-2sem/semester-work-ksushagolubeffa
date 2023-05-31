<!doctype html>
<html lang="ru">
<head>
    <title>Вход</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="css/style.css">

</head>
<body class="img js-fullheight" style="background-image: url(images/back.jpg);">
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="login-wrap p-0">
                    <h3 class="mb-4 text-center">FANS DREAM</h3>
                    <form action="/signUp" method="post">
                        <div class="form-group">
                            <input type="text" id="email" name = "email" class="form-control" placeholder="Почта" required>
                        </div>
                        <div class="form-group">
                            <input id="username" type="text" class="form-control" name="username" placeholder="Имя пользователя" required>
                        </div>
                        <div class="form-group">
                            <input id="password" type="password" class="form-control" name="password" placeholder="Пароль" required>
                            <span toggle="#password-field" ></span>
                        </div>
                        <div class="form-group">
                            <input id="repeat" type="password" class="form-control" name="repeat" placeholder="Повторите пароль" required>
                            <span toggle="#password-field" ></span>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="form-control btn btn-primary submit px-3">Зарегистрироваться</button>
                        </div>
                    </form>
                    <p class="w-100 text-center">&mdash; Есть аккаунт? <a href="/signIn">Войти</a> &mdash;</p>

                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>

