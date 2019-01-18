$(document).ready(function () {
    let modalBookInsert = document.getElementById('modal-wrapper-book-insert');
    let modalAuthor = document.getElementById('modal-wrapper-author');
    let modalGenre = document.getElementById('modal-wrapper-genre');
    window.onclick = function (event) {
        if (event.target === modalBookInsert) {
            modalBookInsert.style.display = "none";
            $('.container input[type!=hidden]').val('');
        }
        if (event.target === modalAuthor) {
            modalAuthor.style.display = "none";
            $('.container input[type!=hidden]').val('');
        }
        if (event.target === modalGenre) {
            modalGenre.style.display = "none";
            $('.container input[type!=hidden]').val('');
        }
    };


    let table = $('#table').DataTable();

   /* $('#table tbody').on('click', '.saveEditBookButton', function () {
        let data = table.row($(this).parents('tr')).data();
        let inputs = $('#modal-wrapper-book input');
        inputs[0].value = data[1]; // title
        inputs[1].value = data[5]; // count
        inputs[2].value = data[4]; // year
        // inputs[3].value = data[0]; // id
        $('#modal-wrapper-book').css('display', 'block');
    });*/

    $('#table tbody').on('click', '.saveEditBookButton', function () {
        let data = table.row($(this).parents('tr')).data();
        let inputs = $('#modal-wrapper-book-insert input');
        inputs[0].value = data[1]; // title
        inputs[1].value = data[5]; // count
        inputs[2].value = data[4]; // year
        $('#modal-wrapper-book-insert input[name="bookId"]')[0].value = this.value;
        $('#modal-wrapper-book-insert').css('display', 'block');
    });

    $('#saveBookButton').on('click', function () {
        /*let inputs = $('#modal-wrapper-book-insert input');
        inputs[3].value = null; // id*/
        $('#modal-wrapper-book-insert').css('display', 'block');
    });
    $('#addAuthorButton').on('click', function () {
        $('#modal-wrapper-author').css('display', 'block');
    });
    $('#addGenreButton').on('click', function () {
        $('#modal-wrapper-genre').css('display', 'block');
    });

    table.on('click', '.modalCross', function () { // for second page
        $('#modal-wrapper-book-insert').css('display', 'none');
        $('#modal-wrapper-author').css('display', 'none');
        $('#modal-wrapper-genre').css('display', 'none');
        $('.container input[type!=hidden]').val('');
    });

    $('.modalCross').on('click', function () {
        $('#modal-wrapper-book-insert').css('display', 'none');
        $('#modal-wrapper-author').css('display', 'none');
        $('#modal-wrapper-genre').css('display', 'none');
        $('.container input[type!=hidden]').val('');
    });

});
