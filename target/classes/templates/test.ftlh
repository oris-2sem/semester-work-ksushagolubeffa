<html>
<body>
<button id="get-users">Get All Users</button>
</body>
<script>
    $(document).ready(function() {
        $('#get-users').click(function() {
            $.ajax({
                url: '/users',
                type: 'GET',
                success: function(data) {
                    var userList = '';
                    $.each(data, function(index, user) {
                        userList += '<p>' + user.name + '</p>';
                    });
                    $('#user-list').html(userList);
                }
            });
        });
    });
</script>
</html>