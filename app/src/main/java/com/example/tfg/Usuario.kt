package com.example.tfg

data class Usuario(
    private var nombre: String,
    private var apellido: String,
    private var correo: String,


): java.io.Serializable{

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getApellido(): String {
        return apellido
    }

    fun setApellido(apellido: String) {
        this.apellido = apellido
    }

    fun getCorreo(): String {
        return correo
    }

    fun setCorreo(correo: String) {
        this.correo = correo
    }


    //creamos un constructor vacio
    constructor() : this("", "", "")

}