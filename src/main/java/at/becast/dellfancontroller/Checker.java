package at.becast.dellfancontroller;

import at.becast.dellfancontroller.config.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Checker implements Runnable{
    private static Settings config = null;
    private static final Logger LOG = LoggerFactory.getLogger(Checker.class);
    List<Integer> temps;
    List<Integer> speed;
    int currentspeed = 0;
    public Checker(){
        config = Settings.getInstance();
        temps = config.getIntList("dellfanspeed.temperatures");
        speed = config.getIntList("dellfanspeed.speed");
    }

    private int speedCalc(Double temp){
        int rTemp = (int) Math.round(temp);
        int Speedkey = getClosestIndex(temps,rTemp);
        int wantedspeed = speed.get(Speedkey);
        LOG.debug("Temperature (rounded): {} Wanted speed: {}",rTemp,wantedspeed);
        return wantedspeed;
    }
    public void run() {
        int wantedspeed = speedCalc(dellfanspeed.temp.getAverageTemp());
        //int wantedspeed = speedCalc(35.3);
        if(wantedspeed == currentspeed){
            return;
        }else{
            dellfanspeed.ipmi.setspeed(wantedspeed);
            currentspeed = wantedspeed;
        }
    }

    protected int getClosestIndex(final List<Integer>values, int value) {
        class Closest {
            Integer dif;
            int index = -1;
        };
        Closest closest = new Closest();
        for (int i = 0; i < values.size(); ++i) {
            final int dif = Math.abs(value - values.get(i));
            if (closest.dif == null || dif < closest.dif) {
                closest.index = i;
                closest.dif = dif;
            }
        }
        return closest.index;
    }
}
