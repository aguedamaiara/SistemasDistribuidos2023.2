import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadedEchoServer {
    public static void main(String[] args) {
        int i = 1;
        List<PrintWriter> clientOutputs = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(8189);

            for (;;) {
                Socket incoming = serverSocket.accept();
                System.out.println("Spawning " + i);

                PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */);
                clientOutputs.add(out);

                new ThreadedEchoHandler(incoming, i, clientOutputs).start();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ThreadedEchoHandler extends Thread {
    private Socket incoming;
    private int counter;
    private List<PrintWriter> clientOutputs;

    public ThreadedEchoHandler(Socket incoming, int counter, List<PrintWriter> clientOutputs) {
        this.incoming = incoming;
        this.counter = counter;
        this.clientOutputs = clientOutputs;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */);

            out.println("Hello! Enter BYE to exit.");

            boolean done = false;
            while (!done) {
                String str = in.readLine();
                System.out.println(" - " + str);
                if (str == null) {
                    done = true;
                } else {
                    for (PrintWriter clientOut : clientOutputs) {
                        clientOut.println("Echo (" + counter + "): " + str);
                    }

                    if (str.trim().equals("BYE")) {
                        done = true;
                    }
                }
            }

            incoming.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
