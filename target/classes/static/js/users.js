document.addEventListener("DOMContentLoaded", function(){
    getHousePage();
});
//
function findAllUsers(){
    fetch('/users')
        .then(response => response.json())
        .then(data => {
            const userList = document.getElementById("user-list");
            userList.innerHTML = "";
            data.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.uuid}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.balance}</td>
                    <td>${user.state}</td>
                `;
                userList.appendChild(row);
            });
        });
}

function getHousePage() {
    fetch('/users', {
        method: 'GET',
        headers: {
            'AUTHORIZATION': localStorage.getItem('accessToken')
        }
    }).then((response) => {
        return response.text()
    }).then(function (html) {
        let b = document.body
        b.innerHTML = ''
        b.innerHTML = html
    })
}