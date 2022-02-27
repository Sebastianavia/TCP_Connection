package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection extends Thread{
	
	private ServerSocket server;
	private Socket socket;
	private int puerto;
	
	public TimeServer timeServer;
	public Ip ip;
	public Interfacee interfacee;
	public Rtt rtt;
	public Speed speed;
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	@Override
	public void run() {
		try {
			
			while(true) {
				server = new ServerSocket(puerto);
				System.out.println("Esperando un cliente");
				socket = server.accept();
				System.out.println("Cliente conectado");
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(os));
				BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
				



				String msg = bReader.readLine();
				if(msg.contains("whatTimeIsIt")){
					String toPrintString = "";
					
					toPrintString = timeServer.timePrint();
					bwriter.write(toPrintString+"\n");
					bwriter.flush();
				}
				
				else if(msg.contains("remoteIpconfig")) {
					String toIpString = "";
					toIpString = ip.ipconfing();
					bwriter.write(toIpString+"\n");
					bwriter.flush();
				}
				else if(msg.contains("interface")) {
					String toInterfacePrint = "";
					toInterfacePrint=interfacee.interfacePrint();
					bwriter.write(toInterfacePrint+"\n");
					bwriter.flush();					
				}else if(msg.contains("RTT")) {
					String toRtt = bReader.readLine();
					bwriter.write(toRtt+"\n");
					bwriter.flush();	
				}else if(msg.contains("speed")) {
					String toSpeed = bReader.readLine();
					bwriter.write(toSpeed+"\n");
					bwriter.flush();
				}
				else {
					System.out.println("*******************************************");
					System.out.println("");
					System.out.println("         Parametro no encontardo");
					System.out.println("Error de la capa 8, reinicie el programa XD");
					System.out.println("");
					System.out.println("*******************************************");
				}
				
				server.close();
				
			}

		}catch(IOException ex) {

		}
	}

	

	public void setTimeServer(TimeServer timeServer) {
		this.timeServer = timeServer;
	}

	public interface TimeServer{
		public String timePrint();
	}
	
	public void setIp(Ip ip) {
		this.ip = ip;
	}

	
	public interface Ip{
		public String ipconfing();
	}
	
	public interface Interfacee{
		public String interfacePrint();
	}

	public void setInterfacee(Interfacee interfacee) {
		this.interfacee = interfacee;
	}
	public interface Rtt{
		public String rtt();
	}

	public void setRtt(Rtt rtt) {
		this.rtt = rtt;
	}

	public interface Speed{
		public String speed();
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}
	
	

}
