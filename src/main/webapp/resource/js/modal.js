/*
$(function () {
    let modal = document.getElementById('myModal');

    let btn = document.getElementById("myBtn");

    let span = document.getElementById("closeModal");

    btn.onclick = function () {
        modal.style.display = "block";
    };

    span.onclick = function () {
        modal.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
});*/

$(document).ready(function () {
    let modal = document.getElementById('modal-wrapper');
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
            $('.container input').val('');
        }
    };

    $('#addBookButton').on('click', function () {
        $('#modal-wrapper').css('display', 'block');
    });
    $('#modalCross').on('click', function () {
        $('#modal-wrapper').css('display', 'none');
        $('.container input').val('');
    });


});
