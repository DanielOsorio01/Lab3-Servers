package TCP.Cliente;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Cliente {
    public static void main(String[] args) {
        try{
            String serverAddress = "localhost";
            int serverport = 23;

            // Se inicia el socket del servidor para poder conectarse
            Socket socket = new Socket(serverAddress, serverport);
            System.out.println("Conectando con el servidor " + serverAddress + ":" + serverport);


            InputStream inputStream = socket.getInputStream();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter outputWriter = new PrintWriter(outputStream, true);

            String fileName = "archivo.txt";
            outputWriter.println(fileName);

            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            System.out.println("Archivo " + fileName + " enviado");
            String response = inputReader.readLine();
            System.out.println(response);

            socket.close();
            System.out.println("Desconectado del servidor");

        }catch (Exception e) {e.printStackTrace();}

    }
}
