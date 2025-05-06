import { emailTest } from "./utils.js"

document.getElementById("loginform").addEventListener("submit", function(e) {
    e.preventDefault();

    let error = document.getElementById("alert");
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if(!emailTest(email)) {
        error.style.color = "red";
        error.textContent = "Insira formato de e-mail válido.";
    } else {
        fetch("login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            if(data.response) {
                error.style.color = "green";
                error.textContent = "Logado!!"
            } else {
                error.style.color = "red";
                error.textContent = "Login incorreto. Tente novamente."
            }
        }).catch(err => {
            error.style.color = "red";
            error.textContent = "Erro de Conexão.";
        })
    }
})