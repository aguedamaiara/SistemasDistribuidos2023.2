//package org.example;

import java.io.*;
import java.lang.management.*;
import java.net.*;
import java.util.*;

//lib da ram

//lib do hdd
import java.io.File;

//lib dos programa em exe
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

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
                    out.println("Available commands: GET_RAM, GET_HD, GET_RUNNING_PROGRAMS, BYE");


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
                            case "GET_RAM":
                                String ramInfo = consultarMemoriaRAM();
                                out.println(ramInfo);
                                break;
                            case "GET_HD":
                                String hdInfo = consultarHDD();
                                out.println(hdInfo);
                                break;
                            case "GET_RUNNING_PROGRAMS":
                                String runningProgramsInfo = ConsultarProgramasEmExecucao();
                                out.println(runningProgramsInfo);
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

    // Função para obter informações da RAM
    public static String consultarMemoriaRAM() {
        // Obter a instância da MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // Obter informações sobre a memória
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

        // Construir uma string com informações sobre a memória
        String ramInfo = "Memória Total: " + memoryUsage.getMax() / (1024 * 1024) + " MB\n" +
                "Memória Usada: " + memoryUsage.getUsed() / (1024 * 1024) + " MB\n" +
                "Memória Livre: " + (memoryUsage.getMax() - memoryUsage.getUsed()) / (1024 * 1024) + " MB";

        return ramInfo;
    }


    // Função para obter informações do disco rígido
    public static String consultarHDD() {
        File[] roots = File.listRoots();
        StringBuilder hddInfo = new StringBuilder();

        for (File root : roots) {
            hddInfo.append("Disco: ").append(root).append("\n")
                    .append("Espaço Total: ").append(root.getTotalSpace() / (1024 * 1024 * 1024)).append(" GB\n")
                    .append("Espaço Livre: ").append(root.getFreeSpace() / (1024 * 1024 * 1024)).append(" GB\n")
                    .append("Espaço Usado: ").append((root.getTotalSpace() - root.getFreeSpace()) / (1024 * 1024 * 1024)).append(" GB\n\n");
        }

        return hddInfo.toString();
    }



    public static String ConsultarProgramasEmExecucao() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 0);

        StringBuilder result = new StringBuilder("Programas em execução:\n");
        for (ThreadInfo threadInfo : threadInfos) {
            result.append("ID: ").append(threadInfo.getThreadId()).append("\n");
            result.append("Nome: ").append(threadInfo.getThreadName()).append("\n");
            result.append("Estado: ").append(threadInfo.getThreadState()).append("\n");
            result.append("=============================\n");
        }

        return result.toString();
    }
}

