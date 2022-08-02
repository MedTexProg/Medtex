package Listeners;

public class OnlineUserCounter {
    // Define static members and methods.
    private static int onlineUserNumber = 0;

    public static int getOnlineUserNumber() {
        return onlineUserNumber;
    }

    //
    public static void raise(){
        onlineUserNumber++;
        System.out.println("User counter adds 1!");
    }

    public static void reduce(){
        onlineUserNumber--;
        System.out.println("User counter deducts 1!");
    }

}
