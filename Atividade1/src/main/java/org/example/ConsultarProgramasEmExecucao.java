package org.example;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class ConsultarProgramasEmExecucao {
    public static void main(String[] args) {
        // Obter a instância da RuntimeMXBean
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        // Obter a lista de argumentos da linha de comando
        List<String> argumentosLinhaDeComando = runtimeMXBean.getInputArguments();

        // Exibir informações sobre os programas em execução
        System.out.println("Programas em Execução:");

        for (String argumento : argumentosLinhaDeComando) {
            System.out.println(argumento);
        }
    }
}

