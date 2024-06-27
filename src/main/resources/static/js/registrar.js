$(document).ready(function() {
});

async function registrarUsuario () {

    let datos = {}

    datos.nombre = document.getElementById('txtNombre').value
    datos.apellido = document.getElementById('txtApellido').value
    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtPassword').value
    datos.telefono = document.getElementById('txtTelefono').value
    // falta recibir los datos del formulario datos.nombre - datos.apellido, etc


    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })
    alert("Cuenta creada con éxito.")
    window.location.href = "login.html"

}
