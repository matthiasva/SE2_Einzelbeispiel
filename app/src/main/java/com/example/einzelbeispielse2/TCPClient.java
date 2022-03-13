package com.example.einzelbeispielse2;

import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient extends AsyncTask<String,Void,Void> {

    final String domain = "se2-isys.aau.at";
    final int port = 53212;

     Socket socket;
     public String message;
     public String modifiedMessage;


    @Override
    protected Void doInBackground(String... strings) {
        try {

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(domain, port);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = inFromUser.readLine();
            outToServer.writeBytes(message + "\n");
            modifiedMessage = inFromServer.readLine();
            socket.close();

        }catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
    }
}
