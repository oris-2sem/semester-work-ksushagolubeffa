
<html>
<#--<#include "new-menu.ftlh">-->
<head>
    <title>User List</title>
    <script src = "/js/users.js"></script>
<#--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">-->
<#--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>-->
<#--    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
<#--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>-->
<#--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>-->
<#--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->

</head>
<body>
<h1>Users</h1>
<ul id="user-list"></ul>

<#--<script>-->
<#--    fetch('/users')-->
<#--        .then(response => response.json())-->
<#--        .then(users => {-->
<#--            const userList = document.getElementById('user-list');-->
<#--            users.forEach(user => {-->
<#--                const li = document.createElement('li');-->
<#--                li.textContent = user.name + ' (' + user.email + ')';-->
<#--                userList.appendChild(li);-->
<#--            });-->
<#--        });-->
<#--</script>-->
</body>
<#--<body>-->
<div class="container">
    <h1>User List</h1>
    <table>
        <thead>
        <tr>
            <th>UUID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Balance</th>
            <th>State</th>
        </tr>
        </thead>
        <tbody id="user-list">

        </tbody>
    </table>
<#--        <table id="user-table" class="table">-->
<#--            <thead>-->
<#--            <tr>-->
<#--                <th>ID</th>-->
<#--                <th>Name</th>-->
<#--                <th>Email</th>-->
<#--            </tr>-->
<#--            </thead>-->
<#--            <tbody></tbody>-->
<#--        </table>-->
<#--</div>-->
<#--<script src="/static/js/users.js"></script>-->
<#--<script>-->
<#--    $(document).ready(function() {-->
<#--        $.ajax({-->
<#--            url: "/users",-->
<#--            type: "GET",-->
<#--            dataType: 'json',-->
<#--            success: function(data) {-->
<#--                console.log("Error fetching users: " + data);-->
<#--                let tableBody = $("#user-table");-->
<#--                $.each(data, function(index, user) {-->
<#--                    let row = $("<tr>");-->
<#--                    $("<td>").text(user.uuid).appendTo(row);-->
<#--                    $("<td>").text(user.username).appendTo(row);-->
<#--                    $("<td>").text(user.email).appendTo(row);-->
<#--                    $("<td>").text(user.role).appendTo(row);-->
<#--                    $("<td>").text(user.balance).appendTo(row);-->
<#--                    $("<td>").text(user.state).appendTo(row);-->
<#--                    row.appendTo(tableBody);-->
<#--                    console.log("Error fetching users: " + tableBody);-->
<#--                });-->
<#--            },-->
<#--            error: function(xhr, status, error) {-->
<#--                console.log("Error fetching users: " + error);-->
<#--            }-->
<#--        });-->
<#--    });-->
<#--</script>-->

<#--&lt;#&ndash;<script>&ndash;&gt;-->
<#--&lt;#&ndash;    fetch('/users', {&ndash;&gt;-->
<#--&lt;#&ndash;        method: 'get',&ndash;&gt;-->
<#--&lt;#&ndash;    })&ndash;&gt;-->
<#--&lt;#&ndash;        .then(response => response.json())&ndash;&gt;-->
<#--&lt;#&ndash;        .then(users => {&ndash;&gt;-->
<#--&lt;#&ndash;            let table = document.getElementById('user-list');&ndash;&gt;-->
<#--&lt;#&ndash;            table.innerHTML = ''&ndash;&gt;-->

<#--&lt;#&ndash;            table.innerHTML = '    <tr>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>id</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>username</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>email</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>role</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>balance</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '        <th>state</th>\n' +&ndash;&gt;-->
<#--&lt;#&ndash;                '    </tr>';&ndash;&gt;-->

<#--&lt;#&ndash;            for (let i = 0; i < users.length; i++) {&ndash;&gt;-->
<#--&lt;#&ndash;                let row = table.insertRow(-1)&ndash;&gt;-->
<#--&lt;#&ndash;                let id = row.insertCell(0)&ndash;&gt;-->
<#--&lt;#&ndash;                let username = row.insertCell(1)&ndash;&gt;-->
<#--&lt;#&ndash;                let email = row.insertCell(2)&ndash;&gt;-->
<#--&lt;#&ndash;                let role = row.insertCell(3)&ndash;&gt;-->
<#--&lt;#&ndash;                let balance = row.insertCell(4)&ndash;&gt;-->
<#--&lt;#&ndash;                let state = row.insertCell(5)&ndash;&gt;-->

<#--&lt;#&ndash;                id.innerHTML = users[i].uuid&ndash;&gt;-->

<#--&lt;#&ndash;                username.innerHTML = users[i].username&ndash;&gt;-->
<#--&lt;#&ndash;                email.innerHTML = users[i].email&ndash;&gt;-->
<#--&lt;#&ndash;                role.innerHTML = users[i].role&ndash;&gt;-->
<#--&lt;#&ndash;                balance.innerHTML = users[i].balance&ndash;&gt;-->
<#--&lt;#&ndash;                state.innerHTML = users[i].state&ndash;&gt;-->
<#--&lt;#&ndash;                console.log("Error fetching users: " + table);&ndash;&gt;-->
<#--&lt;#&ndash;            }&ndash;&gt;-->


<#--&lt;#&ndash;        });&ndash;&gt;-->
<#--&lt;#&ndash;</script>&ndash;&gt;-->
<#--</body>-->
<#--<script>-->
<#--    $(document).ready(function() {-->
<#--    $.ajax({-->
<#--        url: '/users',-->
<#--        method: 'get',-->
<#--        dataType: 'json',-->
<#--        success: function(data){-->
<#--            alert(data.text);    /* выведет "Текст" */-->
<#--            alert(data.error);   /* выведет "Ошибка" */-->
<#--        }-->
<#--        })-->
<#--    });-->
<#--</script>-->
</html>