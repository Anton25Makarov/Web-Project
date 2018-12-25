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

    /* $('#userTable').on('click', 'tbody tr', function (event) {
         $(this).addClass('highlight').siblings().removeClass('highlight');
     });

     $('#btnRowClick').click(function (e) {
         var rows = getHighlightRow();
         if (rows != undefined) {
             alert(rows.attr('id'));
         }
     });

     var getHighlightRow = function () {
         return $('table > tbody > tr.highlight');
     };

     $('#saveEditBookButton').on('click', 'tbody tr', function () {
         $(this).addClass('highlight').siblings().removeClass('highlight');

         let rows = $('table > tbody > tr.highlight');
         if (rows !== undefined) {
             alert(rows.attr('id'));
         }

         $('#modal-wrapper-book input[name="bookTitle"]').val("Hello value");
         $('#modal-wrapper-book').css('display', 'block');
     });*/

    /*$(".saveEditBookButton").click(function () {
        let item = $(this).closest("tr")   // Finds the closest row <tr>
        // Gets a descendent with class="nr"
            .text();         // Retrieves the text within <td>

        alert(item);
    });*/

    /* let table = $('#example');

     $('table tbody').on('click', '.saveEditBookButton', function () {
         var data = table.row($(this).parents('tr')).data();
         alert(data[0] + "'s salary is: " + data[1]);
     });*/

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

    $('.modalCross').on('click', function () {
        $('#modal-wrapper-book').css('display', 'none');
        $('#modal-wrapper-author').css('display', 'none');
        $('#modal-wrapper-genre').css('display', 'none');
        $('.container input').val('');
    });
})
;
