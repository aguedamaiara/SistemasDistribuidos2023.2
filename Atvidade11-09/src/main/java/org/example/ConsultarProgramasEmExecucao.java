package org.example;
//
//import java.lang.management.ManagementFactory;
//import java.lang.management.RuntimeMXBean;
//import java.util.List;
//
//public class ConsultarProgramasEmExecucao {
//    public static void main(String[] args) {
//        // Obter a instância da RuntimeMXBean
//        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//
//        // Obter a lista de argumentos da linha de comando
//        List<String> argumentosLinhaDeComando = runtimeMXBean.getInputArguments();
//
//        // Exibir informações sobre os programas em execução
//        System.out.println("Programas em Execução:");
//
//        for (String argumento : argumentosLinhaDeComando) {
//            System.out.println(argumento);
//        }
//    }
//}
//

// Função para obter informações dos programas em execução
// public static String ConsultarProgramasEmExecucao() {
//            // Obter a instância da RuntimeMXBean
//            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//
//            // Obter a lista de argumentos da linha de comando
//            List<String> argumentosLinhaDeComando = runtimeMXBean.getInputArguments();
//
//            // Exibir informações sobre os programas em execução
//            System.out.println("Programas em Execução:");
//
//            for (String argumento : argumentosLinhaDeComando) {
//                System.out.println(argumento);
//            }
//        return "Informações dos programas em execução";
//        }
//
//}


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ConsultarProgramasEmExecucao {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 0);

        System.out.println("Programas em execução:");
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("ID: " + threadInfo.getThreadId());
            System.out.println("Nome: " + threadInfo.getThreadName());
            System.out.println("Estado: " + threadInfo.getThreadState());
            System.out.println("=============================");
        }
    }
}
