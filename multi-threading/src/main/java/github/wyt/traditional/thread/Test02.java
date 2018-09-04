package github.wyt.traditional.thread;

/**
 * 传统线程互斥技术
 *
 * <ol>
 *   <li>非静态同步方法用的都是同一把锁,即 实例对象本身.
 *   <li>静态同步方法用的也是同一把锁——类对象本身.
 *   <li>同步代码块可以用实例对象本身(this);也可以用类对象本身T.class
 *   <li>只有用同一把锁的方法或者代码块之间,才会相互构成锁竞争条件.
 * </ol>
 *
 * @author wangyongtao
 * @date 2018-9-4
 */
@SuppressWarnings("all")
public class Test02 {

  public static void main(String[] args) {
    new Test02().init();
  }

  private void init() {

    final Outputer outputer = new Outputer();

    new Thread(
            () -> {
              while (true) {
                try {
                  Thread.sleep(2000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                outputer.output2("How are you?");
              }
            })
        .start();

    new Thread(
            () -> {
              while (true) {
                try {
                  Thread.sleep(2000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                outputer.output("Fine,thank you.");
              }
            })
        .start();
  }

  static class Outputer {

    public void output(String name) {
      int len = name.length();
      // 当前实例对象
      synchronized (this) {
        for (int i = 0; i < len; i++) {
          System.out.print(name.charAt(i));
        }
        System.out.println();
      }
    }

    // 和output方法同一个锁监视器
    public synchronized void output2(String name) {
      int len = name.length();
      for (int i = 0; i < len; i++) {
        System.out.print(name.charAt(i));
      }
      System.out.println();
    }

    // output3使用Outputer.class,作为锁监视器.
    public static synchronized void output3(String name) {
      int len = name.length();
      for (int i = 0; i < len; i++) {
        System.out.print(name.charAt(i));
      }
      System.out.println();
    }
  }
}
