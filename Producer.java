import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Producer extends Thread{
    public static final int MaxMessages = 7;
    public static double startTime;
    public static Queue<String> sender = new Queue<>();
    public static Stack<String> receiver = new Stack<>();
    public static String check = "0";

    @Override
    public void run() {
        try {
            while (true) {
                sendingMessage();
                sleep(100);
                StackProcess();
                sleep(100);
            }
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String inputMess(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a message:");
        String mess = input.nextLine();
        if (mess.length() > 250) {
            throw new IndexOutOfBoundsException("This message is over 250 characters");
        }
        if(mess.equals("")){
            throw new IllegalStateException("This message is null");
        }
        if(mess.contains("//")){
            throw new IllegalStateException("This message can't come with // consecutively");
        }
        return mess;
    }

    public synchronized void sendingMessage() throws InterruptedException {
//        BufferedReader reader = null;
        while (sender.size() == MaxMessages) {
            System.out.println("waiting");
            wait();
        }
        try{
            String mess = inputMess();
            startTime = System.currentTimeMillis();
            var messages = mess.split("/");
//            if(check.equals("0")) {
//                reader = new BufferedReader(new FileReader("D:\\studying\\data_structure\\Exercise\\Assignment\\src\\test.txt"));
//            }else{
//                reader = null;
//            }
//            if(reader!=null) {
//                var messages = reader.readLine();
//                while (messages != null) {
//                    sender.offer(messages.trim());
//                    check = messages;
//                    messages = reader.readLine();
//                }
//                System.out.println("check:" + check);
//            }
            for(int i = 0; i < messages.length; i++) {
                if(messages[i].equals("")){
                    throw new IllegalStateException("This small message is null");
                }
                try {
                    sender.offer(messages[i].trim());
                }catch (IllegalStateException ex){
                    System.out.println(ex.getMessage());
                    System.out.println("Please input a valid message");
                    sendingMessage();
                }
            }
//            System.out.println("The time executed in the Sending method: " + (endTime-startTime) + " ns");
        }catch (IndexOutOfBoundsException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Please input a valid message");
            sendingMessage();
        }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        notify();
    }

    public synchronized void StackProcess() throws InterruptedException {
        notify();
        while (sender.size() == 0) {
            wait();
        }
        double startTime = System.nanoTime();
        while (sender.size()>0){
            receiver.push(sender.poll());
        }
        double endTime = System.nanoTime();
//        System.out.println("The time executed in the stackProcess method: "+ (endTime - startTime)+ " ns");
    }


    public synchronized String receivingMess() throws InterruptedException{
        notify();
        while (receiver.size() == 0)
            wait();

        return receiver.pop();
    }

}
