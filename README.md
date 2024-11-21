# Biblioteca Virtual - Aplicación Cliente-Servidor 📚

Este proyecto consiste en desarrollar una **aplicación cliente-servidor** que utiliza **sockets** e **hilos** para gestionar una **biblioteca virtual**.  
La aplicación **servidora** permite gestionar libros de forma remota, mientras que la aplicación **cliente** interactúa con el servidor para realizar operaciones sobre la biblioteca.

---

## Funcionalidades

### **Servidor**
La aplicación servidora se encarga de:
- Gestionar una lista de libros de una **biblioteca virtual**.
- Soportar múltiples clientes de forma concurrente mediante **hilos**.
- Proporcionar las siguientes operaciones:
  - **Consultar libros disponibles**: El cliente puede solicitar la lista de libros disponibles en el servidor.
  - **Añadir nuevos libros**: Permite al cliente agregar nuevos libros a la biblioteca.
  - **Eliminar libros existentes**: Permite al cliente eliminar libros de la biblioteca por su ID o nombre.

### **Cliente**
La aplicación cliente permite a los usuarios realizar solicitudes al servidor para interactuar con la biblioteca:
- **Ver el catálogo de libros**: El cliente solicita al servidor el catálogo de libros disponibles.
- **Agregar un libro**: El cliente puede enviar una solicitud para agregar un libro al catálogo.
- **Eliminar un libro**: El cliente puede solicitar al servidor que elimine un libro de la biblioteca.

---

## Ejemplo de flujo de trabajo

El cliente y el servidor interactúan a través de solicitudes y respuestas. El flujo básico es el siguiente:

1. **Conexión Cliente-Servidor**: El cliente se conecta al servidor usando sockets.
2. **Selección de operación**: El cliente selecciona la operación que desea realizar (consultar, agregar, eliminar).
3. **Interacción con el servidor**: El servidor procesa la solicitud y devuelve la respuesta al cliente.
4. **Finalización**: El cliente puede terminar la conexión o continuar con más solicitudes.

