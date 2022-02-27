package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import comm.TCPConnection.Rtt;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("Bienvenido, escriba los siguintes comandos:" +"\n remoteIpconfig, interface, whatTimeIsIt, RTT, speed");
			while(true) {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine()+"\n";
			Socket socket = new Socket("127.0.0.1", 6000);
			

			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
			bwriter.write(line);
			bwriter.flush();
			if(line.contains("RTT")) {
				
				String msg=new String(new byte[1024]);
				long timeInit = System.currentTimeMillis();
				
				bwriter.write(msg+"\n");
				bwriter.flush();
				String recibe=bReader.readLine();
				long finalTime = System.currentTimeMillis();
				long totalTime = finalTime-timeInit;
				System.out.println("Tiempo tomado por la operacion " + totalTime + " milisegundos");
				
			}else if(line.contains("speed")) {

				String msg=new String(new byte[1024]);
				long timeInit = System.currentTimeMillis();
				
				bwriter.write(msg+"\n");
				bwriter.flush();
				String recibe=bReader.readLine();
				long finalTime = System.currentTimeMillis();
				long totalTime = finalTime-timeInit;
				double timeConverte = totalTime * 0.001;
				double speed = 8192/timeConverte;
				System.out.println("Velocidad de transmicion " + speed + " bytes/s");
				
				
			}
			else {
				
				String recibe=bReader.readLine();
				System.out.println(recibe);
				
			}
			
			

			socket.close();
			}

		}catch(IOException ex) {

		}

	}

}
