package MultyTasking;

/**
 * Created by Bogdan Kukharskiy on 10.09.2015.
 */
public class WorkWithThreads {
    public static void main(String[] args) {
        NewThread nc = new NewThread();
        NewThread2 nc2 = new NewThread2();
        nc.start();
        Thread thread = new Thread(nc2);
        thread.start();
    }

}
