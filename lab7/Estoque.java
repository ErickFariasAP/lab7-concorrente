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
        for(long i = 0; i < 3; i++) {
          int quantidade = random.nextInt(100);
          estoque.put(i, quantidade);
          System.out.println("Estoque Abastecido com " + quantidade + " items do produto " + i);
          System.out.println();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
}
