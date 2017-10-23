package cgtjr.academics.elctrclengnrng.networking;

import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.AnnotateFeatureVar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.AccessControlException;
import java.util.HashMap;

public class FeatureLogClient {

    private Socket dataSocket;
    private String hostName;
    private int portNumber;

    public FeatureLogClient() {
        //echoSocket = new Socket(hostName, portNumber);
    }

    public static void main(String[] args) throws IOException {

        String hostName = null;
        int portNumber = 0;

        if (args.length == 2) {
            //System.err.println("Usage: java EchoClient <host name> <port number>");
            //System.exit(1);
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        } else {
            hostName = FeatureLogServer.getDefaultServer();
            portNumber = Integer.parseInt(FeatureLogServer.getDefaultPort());
        }

        try {
            Socket echoSocket = new Socket(hostName, portNumber);

            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }

    public void post(String myURL, HashMap myHashMap) {
        transmit(myURL, null, myHashMap);
    }

    public void transmit(String myURL, String myPortNmbr, HashMap myHashMap) {



        //if (args.length == 2) {
        //System.out.println("FeatureLogClient : test 8");
        //System.err.println("Usage: java EchoClient <host name> <port number>");

        if (myURL != null) {
            hostName = myURL;
        } else {
            hostName = FeatureLogServer.getDefaultServer();
        }
        if (myPortNmbr != null) {
            portNumber = Integer.parseInt(myPortNmbr);
        } else {
            portNumber = Integer.parseInt(FeatureLogServer.getDefaultPort());
        }
        if (dataSocket == null) {
            try {
                dataSocket = new Socket(hostName, portNumber);
            } catch (UnknownHostException ex) {
                //Logger.getLogger(FeatureLogClient.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("FeatureLogClient : unknown host exception!");
            }catch(java.net.ConnectException ce){
                System.err.println("FeatureLogClient : connection exception!");
            }catch(AccessControlException ace){
                System.err.println("FeatureLogClient: access control exception!");
            }catch (IOException ex) {
                //Logger.getLogger(FeatureLogClient.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("FeatureLogClient : io exception!");
            }
        }
        try {

            PrintWriter out = new PrintWriter(dataSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            String featureOutput = retrieveStr(myHashMap);

            //while ((userInput = stdIn.readLine()) != null) {
            //out.println(userInput);
            out.println(featureOutput);
            //System.out.println("echo: " + in.readLine());
            //}
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            //System.exit(1);
        } catch (NullPointerException npe){
            System.err.println("FeatureLogClient: null pointer exception");            
        }
    }

    public String retrieveStr(HashMap myHashMap) {
        String output = "";
        //output += myHashMap.get(AnnotateFeatureVar.getUserLogonIdKey())+",";//, theAnnotationBoxVar.getUserLogonIdValue());
        output += myHashMap.get(AnnotateFeatureVar.getDateTimeKey()) + ",";//, theAnnotationBoxVar.getDateTimeValue());
        output += myHashMap.get(AnnotateFeatureVar.getFrameNumberKey()) + ",";//, theAnnotationBoxVar.getFrameNumberValue());
        output += myHashMap.get(AnnotateFeatureVar.getUrlKey()) + ",";//, theAnnotationBoxVar.getUrlValue());  
        output += myHashMap.get(AnnotateFeatureVar.getTrackNumberKey()) + ",";//, theAnnotationBoxVar.getTrackNumberValue());
        output += myHashMap.get(AnnotateFeatureVar.getWidthKey()) + ",";//, theAnnotationBoxVar.getWidthValue());        
        output += myHashMap.get(AnnotateFeatureVar.getHeightKey()) + ",";//, theAnnotationBoxVar.getHeightValue());
        output += myHashMap.get(AnnotateFeatureVar.getXMinCoordKey()) + ",";//, theAnnotationBoxVar.getXMinCoordValue());        
        output += myHashMap.get(AnnotateFeatureVar.getYMinCoordKey()) + ",";//, theAnnotationBoxVar.getYMinCoordValue());        
        output += myHashMap.get(AnnotateFeatureVar.getZMinCoordKey()) + ",";//, "0");                
        output += myHashMap.get(AnnotateFeatureVar.getXMaxCoordKey()) + ",";//, theAnnotationBoxVar.getXMaxCoordValue());                
        output += myHashMap.get(AnnotateFeatureVar.getYMaxCoordKey()) + ",";//, theAnnotationBoxVar.getYMaxCoordValue());                
        output += myHashMap.get(AnnotateFeatureVar.getZMaxCoordKey()) + ",";//, "0");                        
        output += myHashMap.get(AnnotateFeatureVar.getDescriptionKey()) + "\n";//, theAnnotationBoxVar.getDescriptionValue());    
        return output;
    }
}