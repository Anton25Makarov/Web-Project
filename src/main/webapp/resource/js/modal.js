$(document).ready(function () {
    let modalBook = document.getElementById('modal-wrapper-book');
    let modalAuthor = document.getElementById('modal-wrapper-author');
    let modalGenre = document.getElementById('modal-wrapper-genre');
    window.onclick = function (event) {
        if (event.target === modalBook) {
            modalBook.style.display = "none";
            $('.container input').val('');
        }
        if (event.target === modalAuthor) {
            modalAuthor.style.display = "none";
            $('.container input').val('');
        }
        if (event.target === modalGenre) {
            modalGenre.style.display = "none";
            $('.container input').val('');
        }
    };


    let table = $('#bookTable').DataTable();

    $('#bookTable tbody').on('click', '.saveEditBookButton', function () {
        let data = table.row($(this).parents('tr')).data();
        let inputs = $('#modal-wrapper-book input');
        inputs[0].value = data[1]; // title
        inputs[1].value = data[4]; // year
        inputs[2].value = data[5]; // count
        inputs[3].value = data[0]; // id
        $('#modal-wrapper-book').css('display', 'block');
    });

    $('#saveBookButton').on('click', function () {
        let inputs = $('#modal-wrapper-book input');
        inputs[3].value = null; // id
        $('#modal-wrapper-book').css('display', 'block');
    });
    $('#addAuthorButton').on('click', function () {
        $('#modal-wrapper-author').css('display', 'block');
    });
    $('#addGenreButton').on('click', function () {
        $('#modal-wrapper-genre').css('display', 'block');
    });

    $('#bookTable').on('click', '.modalCross', function () { // for second page
        $('#modal-wrapper-book').css('display', 'none');
        $('#modal-wrapper-author').css('display', 'none');
        $('#modal-wrapper-genre').css('display', 'none');
        $('.container input').val('');
    });

    $('.modalCross').on('click', function () {
        alert("Cross");
        $('#modal-wrapper-book').css('display', 'none');
        $('#modal-wrapper-author').css('display', 'none');
        $('#modal-wrapper-genre').css('display', 'none');
        $('.container input').val('');
    });

});
