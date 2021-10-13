function changeVisibility() {
    const input = document.getElementById("senha");
    if (input.type === 'password') {
        input.type = 'text';
    } else {
        input.type = 'password';
    }
}

function showModalOnLoad(id) {
    $(document).ready(function () {
        $(id).modal('show');
    });
}

function voltar() {
    window.history.back();
}