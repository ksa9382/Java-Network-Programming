import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Server {
    static final int MAX_LENGTH = 1024;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("args = " + Arrays.toString(args) +
                    "\nUsage: java Server <Port>");
            System.exit(1);
        }

        try (DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]))) {
            DatagramPacket packet = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);

            System.out.println("socket is created..");
            System.out.println("LocalSocketAddress() = " + ds.getLocalSocketAddress() + ", " + "ReceiveBufferSize() = " + ds.getReceiveBufferSize());

            while (true) {
                System.out.println("Start listen..");

                ds.receive(packet);

                if (packet.getLength() == 0)
                    break;

                byte[] data = packet.getData();
                System.out.println("packet: " + Arrays.toString(data));

                ds.send(packet);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
