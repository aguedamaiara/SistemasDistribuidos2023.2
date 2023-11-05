import java.net.*;
import java.util.*;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(8189);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String command = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from " + clientAddress + ":" + clientPort + ": " + command);

                String response = processCommand(command);

                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static String processCommand(String command) {
        switch (command) {
            case "GET_RAM":
                return consultarMemoriaRAM();
            case "GET_HD":
                return consultarHDD();
            case "GET_RUNNING_PROGRAMS":
                return ConsultarProgramasEmExecucao();
            default:
                return "Invalid command. Try again.";
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
