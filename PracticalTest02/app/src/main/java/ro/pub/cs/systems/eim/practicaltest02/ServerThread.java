package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread{

    private int port;
    private ServerSocket serverSocket;
    private HashMap<String, GenericResults> data;

    private int res;

    public ServerThread(int port) {
        this.port = port;
        try {

            this.serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            Log.e("Eroare", "E busit serverul " + port);
            e.printStackTrace();
        }

        this.data = new HashMap<>();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket client_socket = serverSocket.accept();

                /* TODO, Try to run the following code on a separate thread */

                Thread operationThread = new OperationThread(client_socket);
                operationThread.start();


//                String clientResponse = "";
//
//                if (client_socket != null) {
//                    BufferedReader bufferReader = Utils.getReader(client_socket);
//                    String request_data = bufferReader.readLine();
//
//                    String[] parts = request_data.split(",");
//                    String s_operand = parts[0];
//                    String s_op1 = parts[1];
//                    String s_op2 = parts[2];
//
//                    int op1 = Integer.valueOf(s_op1);
//                    int op2 = Integer.valueOf(s_op2);
//
//                    if(s_operand.equals("add")){
//                        res = op1 + op2;
//                    } else if(s_operand.equals("mul")) {
//                        res = op1 * op2;
//                    }
//
//                    String result_data = String.valueOf(res);
//
//
//                    GenericResults newRes = new GenericResults();
//                    newRes.setRes(result_data.toString());
//                    this.data.put(result_data, newRes);
//                    clientResponse = "Your operation result is : " + data.get(result_data).getRes();
//
//
//
//                    /* Write to client socket the reponse */
//                    PrintWriter printWriter = Utils.getWriter(client_socket);
//                    printWriter.println(clientResponse);
//
//                    client_socket.close();
//
//
//                } else {
//                    Log.e("Erroare", "Null client socket");
//                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
