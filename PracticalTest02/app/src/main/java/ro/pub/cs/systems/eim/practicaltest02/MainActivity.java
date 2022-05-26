package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button startService;
    private ServerThread serverThread;
    Button clientStartRequest;

    private EditText serverPort;
    private EditText clientPort;
    private EditText address;
    private EditText data;

    TextView responseTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = findViewById(R.id.resultEditText);
        startService = findViewById(R.id.StarServerButton);

        serverPort = (EditText) findViewById(R.id.ServerPortEditText);
        clientPort = (EditText) findViewById(R.id.ClientConnectPortEditText);

        data = (EditText) findViewById(R.id.ClientRequestDataEditText);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int port = Integer.valueOf(serverPort.getText().toString());
                serverThread = new ServerThread(port);
                serverThread.start();

            }
        });

        clientStartRequest = findViewById(R.id.SendRequestButton);
        address = (EditText)findViewById(R.id.ClientConnectAddressEditText);

        clientStartRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int port = Integer.valueOf(clientPort.getText().toString());
               String adr = String.valueOf(address.getText().toString());
                String dataa = String.valueOf(data.getText().toString());

                ClientThread clientThread = new ClientThread(port, adr, dataa, responseTextView);
                clientThread.start();
            }
        });
    }
}