import java.net.*;
public class Main {
    public static void main(String[] args)
        throws Exception {
            int portnumber = 1333;
            if (args.length >= 1) {
                portnumber = Integer.parseInt(args[0]);
            }

            MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);
            System.out.println("Multicastsocket is created at port " + portnumber);

            InetAddress group = InetAddress.getByName("255.4.5.6");

            serverMulticastSocket.joinGroup(group);
            System.out.println("Joingroup method is called...");
            boolean infinite = true;

            while (infinite) {
                byte buf[] = new byte[1024];
                DatagramPacket data = new DatagramPacket(buf, buf.length);
                serverMulticastSocket.receive(data);
                String msg = new String(data.getData()).trim();
                System.out.println("Message recieved from client = " + msg);
            }
            serverMulticastSocket.close();
        }
    }