import java.io.*;
public class Complexo implements Serializable{

  double a, b;
    byte[] arquivo; // Adiciona um atributo byte[] para armazenar o arquivo

    public Complexo() {
    }

    public double modulo() {
        return Math.sqrt(a * a + b * b);
    }

    // Adicione métodos getters e setters para o atributo "arquivo"
    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}