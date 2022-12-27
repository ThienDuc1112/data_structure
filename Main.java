import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
        Consumer consumer = new Consumer(producer);
        consumer.start();

    }
}