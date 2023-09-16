package org.example;

import java.io.File;

    public class ConsultarHDD {
        public static void main(String[] args) {
            // Obter a lista de raízes de sistema de arquivos (discos)
            File[] roots = File.listRoots();

            // Iterar sobre os discos e exibir informações
            for (File root : roots) {
                System.out.println("Disco: " + root);
                System.out.println("Espaço Total: " + root.getTotalSpace() / (1024 * 1024 * 1024) + " GB");
                System.out.println("Espaço Livre: " + root.getFreeSpace() / (1024 * 1024 * 1024) + " GB");
                System.out.println("Espaço Usado: " + (root.getTotalSpace() - root.getFreeSpace()) / (1024 * 1024 * 1024) + " GB");
                System.out.println();
            }
        }
    }



