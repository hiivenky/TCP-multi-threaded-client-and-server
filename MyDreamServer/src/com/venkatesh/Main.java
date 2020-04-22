package com.venkatesh;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(12000);

        while(true){
            Socket dataSocket = socket.accept();
            GameServer gameServer = new GameServer(dataSocket);
            gameServer.start();
        }
    }
}
