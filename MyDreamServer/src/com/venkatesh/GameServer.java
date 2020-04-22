package com.venkatesh;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class GameServer extends Thread {
    private Socket socket;
    private static int count=0;
    private static Hashtable<String,String> coordinates= new Hashtable<>();
    private String clientName;

    public GameServer(Socket socket){
        this.socket=socket;
        this.clientName = "client"+count;
        count++;
        coordinates.put(clientName,"0,0");
    }

    @Override
    public void run() {
        try {
            this.setName(clientName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            String str;
            do{
                str = reader.readLine();
                System.out.println("Received from "+clientName+" "+ str);
                coordinates.put(clientName,str);
                writer.println(str.toUpperCase()+" From server");
                for(Map.Entry<String,String> entry: coordinates.entrySet()){
                    if(!entry.getKey().equals(clientName)){
                        writer.println(entry.getKey()+" is at location "+entry.getValue());
                    }
                }
                writer.println("");
            }while(!str.equals("exit"));

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
