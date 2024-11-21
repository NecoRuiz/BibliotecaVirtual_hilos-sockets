# BibliotecaVirtual_hilos-sockets
Este proyecto consiste en desarrollar una aplicación cliente-servidor que utiliza sockets e hilos para gestionar una biblioteca virtual.
La aplicación servidora permite gestionar libros de forma remota, mientras que la aplicación cliente interactúa con el servidor para realizar operaciones sobre la biblioteca.
Funcionalidades
Servidor
Gestiona una lista de libros de una biblioteca virtual.
Soporta múltiples clientes de forma concurrente mediante hilos.
Proporciona operaciones como:
Consultar libros disponibles.
Añadir nuevos libros.
Eliminar libros existentes.
Cliente
Consume los servicios proporcionados por el servidor.
Permite a los usuarios realizar solicitudes al servidor, como:
Ver el catálogo de libros.
Agregar un libro.
Eliminar un libro.
