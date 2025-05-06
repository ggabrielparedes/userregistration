import { emailTest } from "./utils.js";

document.getElementById("registerform").addEventListener("submit", function(e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const confirmEmail = document.getElementById("confirmEmail").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    let alert = document.getElementById("alertMessage");
    if(!emailTest(email)) {
        console.log("teste");
        alert.style.color = "red";
        alert.textContent = "Insira formato de e-mail válido.";
    } else if(!(email === confirmEmail)) {
        alert.style.color = "red";
        alert.textContent = "E-mails não são iguais.";
    } else if(!(password === confirmPassword)) {
        alert.style.color = "red";
        alert.textContent = "Senhas não são iguais.";
    } else {
        fetch("register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, email, confirmEmail, password, confirmPassword})
        })
        .then(response => response.json())
        .then(data => {
            if(!data.response) {
                alert.style.color = "red";
                alert.textContent = "Este e-mail já esta cadastrado";
            } else {
                alert.style.color = "green";
                alert.textContent = "Conta cadastrada com sucesso";
            }
        }).catch(err => {
            alert.style.color = "red";
            alert.textContent = "Erro de conexão";
        })
    }
})