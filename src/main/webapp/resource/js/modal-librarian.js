$(document).ready(function () {
    let table = $('#table').DataTable();

    let modalLibrarian = document.getElementById('modal-wrapper-librarian-insert');
    window.onclick = function (event) {
        if (event.target === modalLibrarian) {
            modalLibrarian.style.display = "none";
            $('.container input').val('');
        }
    };

    $('#saveLibrarianButton').on('click', function () {
        $('#modal-wrapper-librarian-insert').css('display', 'block');
    });

    $('#saveReaderButton').on('click', function () {
        $('#modal-wrapper-librarian-insert').css('display', 'block');
    });

    table.on('click', '.modalCross', function () { // for second page
        $('#modal-wrapper-librarian-insert').css('display', 'none');
        $('.container input').val('');
    });

    $('.modalCross').on('click', function () {
        $('#modal-wrapper-librarian-insert').css('display', 'none');
        $('.container input').val('');
    });

});

