import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        //syzd.na random generator
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        //syzdavane na socket za podslushvane i vryzka
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and waiting for clients...");
        //priemane na konekciq i syzdavane na vhodno-izhodni potoci
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        //zadavane na status
        boolean gameover = false;
        //cikyl dokato igrata svyrshi
        while (!gameover){
            //prochitane na predpolojenieto ot inputa
            int guess = in.readInt();
            System.out.println("Client guessed: " + guess);

        // sravnenie na predpolojenieto s chisloto i obratna vryzka kym klienta
        if(guess == number){
            //klienta redpolaga pravilno i prikl
            out.writeUTF("You guessed it! The number was" + number + ".");
            out.writeBoolean(true);
            gameover = true;
        } else if (guess < number) {
            //klienta predp. po-malko,prati podskazka
            out.writeUTF("Too low.. Try higher number.");
            out.writeBoolean(false);
        }else {
            //klienta predp po-golqmo
            out.writeUTF("Too high. Try lower number.");
            out.writeBoolean(false);
        }
        //izchistva potoka
            out.flush();
        }
        //zatv.na soketa
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();

        }
}
