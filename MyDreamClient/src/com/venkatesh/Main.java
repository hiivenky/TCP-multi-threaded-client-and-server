package com.venkatesh;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            Socket socket = new Socket("localhost", 12000);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(),true);
            Scanner sc = new Scanner(System.in);
            String str;
            do{
                System.out.println("Enter the data to send to server");
                str = sc.nextLine();
                if(!str.equals("exit")){
                    ///System.out.println(str);
                    outToServer.println(str);
                    String receivedData;
                    receivedData=inFromServer.readLine();
                    while(!receivedData.equals("")) {
                        System.out.println(receivedData);
                        receivedData=inFromServer.readLine();
                    }
                }
            }while (!str.equals("exit"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
        }
    }
}
