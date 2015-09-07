package kr.berryz.nonthreadchatclient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Berryzed
 *
 */
public class NonThreadChatClient
{
	public static void main(String[] args)
	{
		BufferedReader in = null;
		BufferedReader stin = null;
		BufferedWriter out = null;
		Socket socket = null;
		
		try
		{
			socket = new Socket("127.0.0.1", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stin = new BufferedReader(new InputStreamReader(System.in));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String outputMessage;
			
			while (true)
			{
				outputMessage = stin.readLine();
				if (outputMessage.equalsIgnoreCase("bye"))
				{
					out.write(outputMessage);
					out.flush();
					break;
				}
				out.write("Client>" + outputMessage + "\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println(inputMessage);
			}
		}
		catch (IOException e)
		{
			System.out.println("error:" + e.getMessage());
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				System.out.println("error:" + e.getMessage());
			}
		}
	}
}
