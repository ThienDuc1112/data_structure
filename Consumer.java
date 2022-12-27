public class Consumer extends Thread{
    public Producer producer;
    public Consumer(Producer producer){
        this.producer = producer;
    }

    @Override
    public void run(){
        try {
            while (true) {
               String mess = producer.receivingMess();
                System.out.println("You got a message: " + mess);
                if(Producer.receiver.size()==0) {
                    double endTime = System.currentTimeMillis();
                    System.out.println("The time to execute the program: " +(endTime - Producer.startTime) + "ms");
                }
            }

        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
