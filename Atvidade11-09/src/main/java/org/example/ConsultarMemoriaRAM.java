package org.example;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class ConsultarMemoriaRAM {
    public static void main(String[] args) {
        // Obter a instância da MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // Obter informações sobre a memória
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

        // Exibir informações sobre a memória
        System.out.println("Memória Total: " + memoryUsage.getMax() / (1024 * 1024) + " MB");
        System.out.println("Memória Usada: " + memoryUsage.getUsed() / (1024 * 1024) + " MB");
        System.out.println("Memória Livre: " + (memoryUsage.getMax() - memoryUsage.getUsed()) / (1024 * 1024) + " MB");
    }
}
