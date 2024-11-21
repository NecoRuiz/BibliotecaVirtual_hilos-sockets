package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public void iniciar() {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("\nMenú:");
                System.out.println("1. Consultar libro por ISBN");
                System.out.println("2. Consultar libro por título");
                System.out.println("4. Consultar libros por autor");
                System.out.println("3. Salir de la aplicación");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextLine();
                salida.println(opcion);

                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese el ISBN del libro: ");
                        String isbn = scanner.nextLine();
                        salida.println(isbn);
                        System.out.println("Respuesta del servidor: " + entrada.readLine());
                        break;
                    case "2":
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        salida.println(titulo);
                        System.out.println("Respuesta del servidor: " + entrada.readLine());
                        break;
                    case "4":
                        System.out.print("Ingrese el nombre del autor: ");
                        String autor = scanner.nextLine();
                        salida.println(autor);

                        // Lee y muestra todos los libros del autor enviados por el servidor
                        String respuesta;
                        while (!(respuesta = entrada.readLine()).equals("FIN")) {
                            System.out.println(respuesta);
                        }
                        break;
                    case "3":
                        System.out.println("Saliendo de la aplicación.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (!opcion.equals("3"));

        } catch (IOException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciar();
    }
}
