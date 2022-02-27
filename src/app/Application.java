package app;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Enumeration;

import comm.TCPConnection;

public class Application implements TCPConnection.TimeServer, TCPConnection.Ip, TCPConnection.Interfacee, TCPConnection.Rtt, TCPConnection.Speed{
	
	private TCPConnection connection;
	
	public Application() {
		connection = new TCPConnection();
		connection.setPuerto(6000);
		connection.setTimeServer(this);
		connection.setIp(this);
		connection.setInterfacee(this);
	}

	public void init() {
		
		connection.start();	
	
	}

	@Override
	public String timePrint() {
		Calendar c = Calendar.getInstance();
		String dateHour = c.getTime().toString();
		return dateHour;

	}

	@Override
	public String ipconfing() {
		String msg = " ";
		
        try {
            InetAddress ip = InetAddress.getLocalHost();
            msg = ip.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
	}

	@Override
	public String interfacePrint() {
		String msg=" ";
		  try {

	            Enumeration<NetworkInterface> interfaces;

	            interfaces = NetworkInterface.getNetworkInterfaces();
	            while(interfaces.hasMoreElements()) {
	                NetworkInterface netN = interfaces.nextElement();

	                if(netN.isUp()) {
	                	msg += netN.getName();
	                	msg += " -- ";
	                    if(netN.getHardwareAddress()!=null) {

	                        String mac = new BigInteger(1,netN.getHardwareAddress()).toString(16);
	                        msg += mac;
	                        msg += " -- ";

	                    }
	                }
	            }

	        } catch (SocketException e) {
	           
	            e.printStackTrace();
	        
	        }
		return msg;
		
	}

	@Override
	public String rtt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String speed() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
