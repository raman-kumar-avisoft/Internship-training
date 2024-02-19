// drawback of eagersingleton is it will create an object automatically not by our call.
class EagerSingleton{
//    create a private constructor
    private static EagerSingleton obj = new EagerSingleton();
    private EagerSingleton(){
        System.out.println("I am Singleton class constructor");
    }
    static EagerSingleton getObject(){
        obj = new EagerSingleton();
        return obj;
    }
}

//If we want to use multithreading than it's a drawback as multiple threads can be creating multiple objects at same time.
class LazySingleton{
    private static LazySingleton obj;
    private LazySingleton(){
        System.out.println("I am Singleton class constructor");
    }
    static LazySingleton getObject(){
        if(obj == null) {
            obj = new LazySingleton();
        }
        return obj;
    }
}

// WE CAN ALSO USE DOUBLE-CHECKED LOCKING INSTEAD OF USING SYNCHRONIZED.
class ThreadSingleton{
    private static ThreadSingleton obj;
    private ThreadSingleton(){
        System.out.println("I am Singleton class constructor");
    }
    static synchronized ThreadSingleton getInstance(){
        if(obj == null){
            obj = new ThreadSingleton();
        }
        return obj;
    }
}

class DoubleCheckedLocking{
    private static DoubleCheckedLocking obj;
    private DoubleCheckedLocking(){
        System.out.println("Singleton class constructor called");
    }
    static DoubleCheckedLocking getInstance(){
        if(obj == null){
            synchronized(DoubleCheckedLocking.class){
                if(obj == null){
                    obj = new DoubleCheckedLocking();
                }
            }
        }
        return obj;
    }
}

public class SingletonDesignPattern {
    public static void main(String[] args) {

//        EAGER INSTANTIATION
//        object creation of the singleton class
//        EagerSingleton obj1 = EagerSingleton.getObject();
//        EagerSingleton obj2 = EagerSingleton.getObject();

//        LAZY INSTANTIATION
//        LazySingleton obj1 = LazySingleton.getObject();
//        LazySingleton obj2 = LazySingleton.getObject();

//        Using Threads - using synchronized method
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ThreadSingleton obj1 = ThreadSingleton.getInstance();
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ThreadSingleton obj2 = ThreadSingleton.getInstance();
//            }
//        });
//
//        t1.start();
//        t2.start();
//        we can use thread with some delay as well but it won't be very effective as this will only result in making longer execution time.

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking obj1 = DoubleCheckedLocking.getInstance();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking obj2 = DoubleCheckedLocking.getInstance();
            }
        });

        t1.start();
        t2.start();
    }
}