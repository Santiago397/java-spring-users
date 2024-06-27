$(document).ready(function() {
});

async function iniciarSesion () {

    let datos = {}

    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtPassword').value


    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })

    const res = await request.text()
    // En caso de error, debería devolver un error 401, debido a que hace referencia a error de auth
    if (res != 'Error') {
        localStorage.token = res
        localStorage.email = datos.email
        window.location.href = "usuarios.html"
    } else {
        alert('Datos incorrectos')
    }

}
