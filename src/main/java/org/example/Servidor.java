package org.example;

import model.Libro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private static final int PUERTO = 12345;
    private List<Libro> listaLibros;

    public Servidor() {
        listaLibros = new ArrayList<>();
        cargarLibros(); // Llamada para cargar los libros al iniciar el servidor
    }

    //metodo para cargar libros
    public void cargarLibros() {
        listaLibros.add(new Libro("1", 30, "El Quijote", "Cervantes"));
        listaLibros.add(new Libro("2", 20, "El Principito", "Juan"));
        listaLibros.add(new Libro("3", 26, "1984", "Martin"));
        listaLibros.add(new Libro("4", 10, "Orgullo y Prejuicio", "Carlos"));
        listaLibros.add(new Libro("5", 42, "Rayuela", "Miguel"));
        listaLibros.add(new Libro("6", 36, "Crimen y castigo", "Mario"));
        listaLibros.add(new Libro("7", 73, "La Casa de Bernarda Alba", "Mario"));
    }

    // Buscar un libro por ISBN
    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    // Buscar un libro por título
    public Libro buscarPorTitulo(String titulo) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    // Buscar libros por autor
    private List<Libro> buscarPorAutor(String autor) {
        List<Libro> librosPorAutor = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                librosPorAutor.add(libro);
            }
        }
        return librosPorAutor;
    }

    // Iniciar servidor y esperar conexiones de clientes
    public void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor conectado correctamente...");

            while (true) {
                try {
                    Socket socket = serverSocket.accept();  // Esperar la conexión de un cliente
                    new Thread(new ManejadorCliente(socket)).start(); // Crear un hilo para manejar el cliente
                } catch (IOException e) {
                    System.out.println("Error al establecer comunicación con el cliente.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket socket;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String opcion;
                while ((opcion = entrada.readLine()) != null) {
                    if (opcion.equals("1")) {  // Consultar por ISBN
                        String isbn = entrada.readLine();
                        Libro libro = buscarPorIsbn(isbn);
                        salida.println(libro != null ? libro.toString() : "Libro no encontrado.");
                    } else if (opcion.equals("2")) {  // Consultar por título
                        String titulo = entrada.readLine();
                        Libro libro = buscarPorTitulo(titulo);
                        salida.println(libro != null ? libro.toString() : "Libro no encontrado.");
                    } else if (opcion.equals("4")) {  // Consultar por autor
                        String autor = entrada.readLine();
                        List<Libro> librosPorAutor = buscarPorAutor(autor);
                        if (!librosPorAutor.isEmpty()) {
                            salida.println("Libros encontrados:");
                            for (Libro libro : librosPorAutor) {
                                salida.println(libro.toString());
                            }
                        } else {
                            salida.println("No se encontraron libros de este autor.");
                        }
                        salida.println("FIN"); // Enviar "FIN" cuando termine de enviar los libros
                    } else if (opcion.equals("3")) {  // Salir
                        salida.println("Adiós.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }
}

