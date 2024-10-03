import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Estoque implements Runnable {

    ConcurrentHashMap<Long, Integer> estoque;
    Random random = new Random();

    public Estoque(ConcurrentHashMap<Long, Integer> estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
      try {
        while (true) {
            estoque.put(0L, random.nextInt(30));
            estoque.put(1L, random.nextInt(30));
            Thread.sleep(1000);
        }  
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
}
