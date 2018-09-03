package github.wyt.traditional.thread;

/**
 * 创建一个线程并启动。
 *
 * @author wangyongtao
 * @date 2018/9/3
 */
public class Test01 {

  public static void main(String[] args) {

    Runnable runnable =
        () -> {
          while (true) {
            try {
              Thread.sleep(500);
              System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };

    Thread thd = new Thread(runnable);
    thd.start();

    System.out.println(Thread.currentThread().getName());
  }
}
