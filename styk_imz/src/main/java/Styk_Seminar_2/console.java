package Styk_Seminar_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class console {
public static Socket socketnewMessage;
static String[] partsout(String[] array, int
index)
{
String [] result = new String
[array.length-index];
for (int i=index; i<(array.length); i++)
{
result[i-index] = array[i];
}
return result;
}
@SuppressWarnings("null")
static void ping(Socket socket) throws
IOException
{
DataInputStream dis = new
DataInputStream(socket.getInputStream
());
DataOutputStream dos = new
DataOutputStream
(socket.getOutputStream());
dos.writeInt(1);
dos.writeByte(1);
byte[] nbm = new byte[dis.readInt()];
dis.readFully(nbm);
if(nbm.length == 1 && nbm[0] == 2) {
System.out.println("Ping successfull");
}
else System.out.println("Ping unsuccessfull");
}
static void echo(Socket socket, String
str) throws IOException
{
DataInputStream dis = new
DataInputStream(socket.getInputStream
());
DataOutputStream dos = new
DataOutputStream
(socket.getOutputStream());
byte[] BytesOfString = str.getBytes();
dos.writeInt(BytesOfString.length+1);
dos.writeByte(3);
dos.write(BytesOfString);
byte[] nbm = new byte[dis.readInt()];
dis.readFully(nbm);
if(nbm.length > 1) {
String str_received= new String(nbm, 0,
nbm.length);
System.out.println(str_received);
}
else System.out.println("Error "+nbm
[0]);
}
public static void main(String[] args){
try {
Socket socket = new Socket
(" lv.rst.uk.to ", 151);
socketnewMessage = new Socket
(" lv.rst.uk.to ", 151);
PrintWriter out = new PrintWriter
(socket.getOutputStream(),
true);
BufferedReader in = new BufferedReader
(new InputStreamReader(
socket.getInputStream()));
PrintWriter outnewMessage = new
PrintWriter
(socketnewMessage.getOutputStream
(),true);
BufferedReader innewMessage = new
BufferedReader(new InputStreamReader(
socketnewMessage.getInputStream()));
String[] parts;
InputStreamReader isr = new
InputStreamReader ( System.in );
BufferedReader br = new
BufferedReader ( isr );
String s = null;
System.out.printf("Enter String%n");
while ( (s = br.readLine ()) != null ) {
parts = s.split(" ");
switch (parts[0]) {
case "ping": ping(socket);
System.out.printf("Your command is ping%n");
break;
case "echo": echo(socket, String.join(" ", partsout(parts,1)));
System.out.printf("Your command is 'echo' with the parameter %s %n",String.join(" ", partsout(parts,1)));
break;
case "login": System.out.printf("Your command is 'login' with the parameters: name=%s, password=%s%n", parts[1], parts[2]);
break;
case "list": System.out.printf("Your command is 'list'%n");
break;
case "msg": System.out.printf("Your command is 'msg' with the name: %s, text of the msg is: %s%n",parts[1], String.join(" ", partsout(parts,2)));
break;
case "file": System.out.printf("Your command is 'file' with the parameters: username=%s, filename=%s%n", parts [1], parts[2]);
break;
case "exit": System.exit(0);
break;
default: System.out.println("Invalid command");
break;
}
}
}
catch ( IOException ioe ) {
// won't happen too often from the
keyboard
}
}
}