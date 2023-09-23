package org.example;
/* Descrição: servidor de eco (Echo Server) simples em Java.
Esse servidor fica esperando por conexões de clientes e responde a
comandos específicos que os clientes enviam , sendo SOMAR um deles onde
o cliente digita 3 numeros e o sevridor retorna a soma deles.
Data: 23/09/2023 */

import java.io.*;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.*;


public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(8189);
            //Socket incoming = s.accept();


            while (true) {
                Socket incoming = s.accept();
                System.out.println("Client connected: " + incoming);

                try {
                    InputStream inStream = incoming.getInputStream();
                    OutputStream outStream = incoming.getOutputStream();

                    Scanner in = new Scanner(inStream);
                    PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
                    //out.println("Hello! Enter BYE to exit.");

                    // Enviar mensagem de boas-vindas ao cliente
                    out.println("Welcome to the Simple Server!");
                    out.println("Available commands: SOMAR and BYE");


//                // echo client input
//                boolean done = false;
//                while (!done && in.hasNextLine()) {
//                    String line = in.nextLine();
//                    out.println("Echo: " + line);
//                    if (line.trim().equals("BYE")) {
//                        done = true;
//                    }
//                }

                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine().trim();
                        System.out.println("Received: " + line);

                        // Processar comandos
                        switch (line) {
                            case "SOMAR":
                                int sum = 0;
                                int numbersReceived = 0;
                                out.println("Enter three integers one by one:");

                                while (numbersReceived < 3) {
                                    String input = in.nextLine().trim();

                                    try {
                                        int num = Integer.parseInt(input);
                                        sum += num;
                                        numbersReceived++;
                                    } catch (NumberFormatException e) {
                                        out.println("Invalid input. Please enter a valid integer.");
                                    }
                                }

                                out.println("Sum: " + sum);
                                break;
                            case "BYE":
                                done = true;
                                break;
                            default:
                                out.println("Invalid command. Try again.");
                                break;
                        }
                    }

                } finally {
                    incoming.close();
                    System.out.println("Client disconnected.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//    // Função para obter informações da RAM
//        public static int somarNumeros ( int num1, int num2, int num3){
//            return num1 + num2 + num3;
//        }
//    }
