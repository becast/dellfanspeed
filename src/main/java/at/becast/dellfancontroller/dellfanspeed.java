package at.becast.dellfancontroller;

import at.becast.dellfancontroller.config.Settings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class dellfanspeed{
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    static ipmitool ipmi = new ipmitool();
    static Sensors temp = new Sensors();
    private static Checker check = new Checker();
    private static Settings config = Settings.getInstance();
    public static void main(String[] args) {
        //Main Code
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Shutting Down, resuming automatic control");
                    ipmi.switchmode(true);
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.scheduleAtFixedRate(check, 0, config.getInt("dellfanspeed.refresh"), TimeUnit.SECONDS);
    }
}

