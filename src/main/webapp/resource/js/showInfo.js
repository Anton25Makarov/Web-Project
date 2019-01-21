$(document).ready(function () {
    let infos = document.getElementsByClassName("infos");

    for (let i = 0; i < infos.length; i++) {
        if (infos[i].innerHTML.length !== 0) { // not empty
            alert(infos[i].innerHTML);
        }
    }
});

