import java.io.*;
import java.net.*;

public class RecebeComplexo {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6789);
            Socket s = ss.accept();
            ObjectInputStream in = new ObjectInputStream(new DataInputStream(s.getInputStream()));
            Complexo c = (Complexo) in.readObject();
            
            // Extrair o arquivo do objeto Complexo
            byte[] fileData = c.getArquivo();
            
            // Escrever o arquivo no sistema de arquivos do servidor
            FileOutputStream fileOutputStream = new FileOutputStream("caminho_de_destino/arquivo_recebido.txt"); // Substitua com o caminho onde deseja salvar o arquivo
            fileOutputStream.write(fileData);
            fileOutputStream.close();
            
            System.out.println("Módulo = " + c.modulo());
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
