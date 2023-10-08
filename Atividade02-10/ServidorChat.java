package org.example.at2out;
import java.io.*;
import java.net.*;

    public class ServidorChat {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(8189); // Porta do servidor

            System.out.println("Servidor pronto para receber conexoes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                Thread thread = new Thread(new ClienteHandler(clientSocket));
                thread.start();
            }
        }
    }

    class ClienteHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClienteHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run() {
            try {
                String mensagem;
                while ((mensagem = in.readLine()) != null) {
                    System.out.println("Cliente diz: " + mensagem);
                    if (mensagem.equals("cambio")) {
                        out.println("Voce encerrou a comunicacão.");
                        break;
                    }
                }
                System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


