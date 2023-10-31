package com.example.tiktokvideoplayer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GiftServer extends WebSocketServer{
    private static final Logger LOGGER= LoggerFactory.getLogger(GiftServer.class);
    public static BlockingQueue<ArrayList> arrayLists=new ArrayBlockingQueue<>(200);
    public GiftServer(int port){
        super(new InetSocketAddress(port));
    }
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        //broadcast("new connection: " + handshake.getResourceDescriptor()); //This method sends a message to all clients connected
        LOGGER.debug(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket conn, int i, String s, boolean b) {
        //broadcast(conn + " has left the room!");
        LOGGER.debug(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        //broadcast(message);
        //LOGGER.debug(conn + ": " + message);
    }
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        ArrayList<?> objects = deserializeByteArray(message.array());
        try {
            arrayLists.put(objects);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception exception) {
        LOGGER.error(exception.getMessage(),exception);
    }

    @Override
    public void onStart() {
        LOGGER.debug("Server started!");
        setConnectionLostTimeout(100);
    }

    public static ArrayList<?> deserializeByteArray(byte[] byteArray) {
        try {
            LOGGER.debug("Mesaj Deserialize oluyor");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            ArrayList<?> list = (ArrayList<?>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return null;
        }
    }



}
