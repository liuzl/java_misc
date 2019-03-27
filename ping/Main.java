import java.io.IOException;
import java.net.Socket;

public class Main {
	public static boolean scan(String host, int port) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		boolean ret = scan("185.133.193.119", 5173);
		System.out.println(ret);
		ret = scan("185.133.193.119", 5174);
		System.out.println(ret);
		ret = scan("142.93.152.104", 7451);
		System.out.println(ret);
	}

}
