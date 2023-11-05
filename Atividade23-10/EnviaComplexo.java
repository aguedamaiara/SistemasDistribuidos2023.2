
import java.io.*;   import java.net.*;

public class EnviaComplexo {

public static void main (String args [])
 {
  try {
            Socket s = new Socket("localhost", 6789);
            ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(s.getOutputStream()));
            Complexo c = new Complexo();
            c.a = 3;
            c.b = 5;
            
            // Ler o arquivo e armazenar no objeto Complexo
            File fileToSend = new File("seu_arquivo.txt"); // Substitua com o caminho do arquivo que deseja enviar
            FileInputStream fileInputStream = new FileInputStream(fileToSend);
            byte[] fileData = new byte[(int) fileToSend.length()];
            fileInputStream.read(fileData);
            c.setArquivo(fileData);
            
            out.writeObject(c);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}