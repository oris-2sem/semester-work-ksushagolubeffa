$(document).ready(function () {
    // Hide the dropdown menu by default
    $('.dropdown-menu').hide();

    // Show the dropdown menu on hover
    $('.dropdown').hover(function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
    }, function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });

    // Open the link on click
    $('.dropdown-toggle').click(function () {
        window.location.href = 'http://localhost:8080/profile';
    });
});
