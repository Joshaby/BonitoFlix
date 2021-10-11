function validatePassword() {
    const input = document.getElementById("senha");
    const error = document.getElementById("senha-label");
    if (input.value.length <= 5) {
        error.innerHTML = "Digite uma senha maior";
        input.classList.add("is-invalid");
        error.classList.add("text-danger");
    }
    else {
        error.innerHTML = "Senha";
        error.classList.remove("text-danger");
        input.classList.remove("is-invalid");
    }
}

function validateUsername() {
    const input = document.getElementById("nome");
    const error = document.getElementById("nome-label");
    if (input.value.length <= 3) {
        error.innerHTML = "Digite um nome maior";
        input.classList.add("is-invalid");
        error.classList.add("text-danger");
    }
    else {
        error.innerHTML = "Nome";
        error.classList.remove("text-danger");
        input.classList.remove("is-invalid");
    }
}

function validateValuesBeforeSend() {
    const inputNome = document.getElementById("nome");
    const inputSenha = document.getElementById("senha");
    return inputSenha.value.length > 5 && inputNome.value.length > 3;
}