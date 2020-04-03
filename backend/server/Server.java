package Server;

import java.io.*;
import java.util.concurrent.*;
import java.net.*;

public class Server {

    private ServerSocket m_serverSocket;
    private Socket m_socket;
    private ExecutorService m_pool;

    private DatabaseManager m_db;

    private ObjectOutputStream m_sendObject;

    private ObjectInputStream m_readObject;

    private BufferedReader m_readString;
    private PrinterWriter m_sendString;

    private boolean m_running;

    public Server(int port) {
        try {
            m_serverSocket = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
            System.out.println("Server: Server is running on port " + port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        m_running = true;

        try {
            while (m_running) {
                // listen for clients 
                m_socket = m_serverSocket.accept();

                m_readObject = new ObjectInputStream(m_socket.getInputStream());

                m_readString = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
                m_sendString = new PrinterWriter(m_socket.getOutputStream(), true);
                m_sendObject = new ObjectOutputStream(socket.getOutputStream());
                m_sendObject.flush();

                 threadPool.execute(new StudentRunnable());
            }
        } catch (Exception e) {
            System.out.println("Exception in Server::run");
            e.printStackTrace();
            shutdown();
        }
    }


    public static void main(String[] args) {
        try {
            Server server = new Server(8099);
            server.communicateWithClient();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
