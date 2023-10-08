package org.example.at2out;

import java.io.*;
import java.net.*;

public class ClienteChat {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8189); // Conecta ao servidor na porta 12345

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String mensagem;
        while ((mensagem = reader.readLine()) != null) {
            out.println(mensagem);
            if (mensagem.equals("cambio")) {
                System.out.println("Você encerrou a comunicacão.");
                break;
            }
        }

        // Leia a mensagem do servidor antes de fechar o socket.
        String respostaServidor = in.readLine();
        if (respostaServidor.equals("Voce encerrou a comunicacão.")) {
            socket.close();
        }
    }
}
