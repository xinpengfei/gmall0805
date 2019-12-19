import java.util.Comparator;
import java.util.TreeSet;

public class JavaBa {
    //接口中只有一个抽象方法：comparable comparator runnaable
    //lambda表达式 stream api提供了一个接口好用的方法 aptionnal 解决空指针
    public static void main(String[] args) {

        TreeSet<Object> set = new TreeSet<>();
        set.add(new Object());
     /*   new Thread(()-> System.out.println("kkkk")).start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个匿名类不类");
            }
        });*/
    }
}
