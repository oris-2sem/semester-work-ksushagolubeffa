const likeButton = document.querySelector('#like-button');
if (likeButton) {
    likeButton.addEventListener('click', () => {
        const csrfToken = document.querySelector('input[name=_csrf]').value;
        const url = likeButton.classList.contains('like-button')
            ? `/media/${content.uuid}/addLike`
            : `/media/${content.uuid}/removeLike`;
        const method = likeButton.classList.contains('like-button') ? 'POST' : 'DELETE';
        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-CSRF-Token': csrfToken
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok');
            })
            .then(data => {
                // Update the UI to reflect the like/unlike status
                likeButton.classList.toggle('like-button');
                likeButton.classList.toggle('unlike-button');
            })
            .catch(error => {
                console.error('Error occurred while liking/unliking content', error);
            });
    });
}