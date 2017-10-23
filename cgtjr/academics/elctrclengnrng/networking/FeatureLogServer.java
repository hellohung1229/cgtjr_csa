package cgtjr.academics.elctrclengnrng.networking;



import java.net.*;
import java.io.*;

public class FeatureLogServer {
    /*
     * Bind the socket to port 0. A random free port from 1024 to 65535 will be selected. 
     * You may retrieve the selected port with getsockname() right after bind().
     */
    private static String defaultServer = "localhost";
    private static String defaultPort = "4678";
    private FeatureLogWriter featureDataLogWriter;
    
    public static String getDefaultServer() {
        return defaultServer;
    }

    public static void setDefaultServer(String defaultServer) {
        FeatureLogServer.defaultServer = defaultServer;
    }

    public static String getDefaultPort() {
        return defaultPort;
    }

    public static void setDefaultPort(String defaultPort) {
        FeatureLogServer.defaultPort = defaultPort;
    }
    
    
    public static void main(String[] args) throws IOException {
        FeatureLogWriter featureDataLogWriter = new FeatureLogWriter();
    
        String portNmbrStr = "";
        if (args.length == 1) {
            //System.err.println("Usage: java EchoServer <port number>");
            //System.exit(1);
            portNmbrStr = args[0];
        }else{
            portNmbrStr = defaultPort;
        }
        
        int portNumber = Integer.parseInt(portNmbrStr);
        
        try {
            ServerSocket serverSocket =
                new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(""+inputLine);
                out.println("Server response:"+inputLine);
                featureDataLogWriter.append(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
