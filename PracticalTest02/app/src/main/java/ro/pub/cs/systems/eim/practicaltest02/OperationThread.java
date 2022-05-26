package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class OperationThread extends Thread{
    private Socket socket;

    private int res;
//    private HashMap<String, GenericResults> data;

    public OperationThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String clientResponse = "";

        if (this.socket != null) {
            BufferedReader bufferReader = null;
            try {
                bufferReader = Utils.getReader(this.socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String request_data = null;
            try {
                request_data = bufferReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] parts = request_data.split(",");
            String s_operand = parts[0];
            String s_op1 = parts[1];
            String s_op2 = parts[2];

            int op1 = Integer.valueOf(s_op1);
            int op2 = Integer.valueOf(s_op2);

            if (s_operand.equals("add")) {
                res = op1 + op2;
            } else if (s_operand.equals("mul")) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                res = op1 * op2;
            }

            String result_data = String.valueOf(res);


//            GenericResults newRes = new GenericResults();
//            newRes.setRes(result_data.toString());
//            this.data.put(result_data, newRes);
//            clientResponse = "Your operation result is : " + data.get(result_data).getRes();

            clientResponse = "Your operation result is : " + result_data;



            /* Write to client socket the reponse */
            PrintWriter printWriter = null;
            try {
                printWriter = Utils.getWriter(this.socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.println(clientResponse);

            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Erroare", "Null client socket");
        }
    }
}
