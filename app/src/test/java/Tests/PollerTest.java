package Tests;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import clientSource.Poller;

import static org.junit.Assert.*;

public class PollerTest {

    /*@Test
    public void run() {
        long delay = 1000L;
        long period = 500L;
        TimerTask myPoller = new Poller();
        Timer myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new Poller(), delay, period);
        try {
            Thread.sleep(delay * 10);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        myTimer.cancel();
    }*/
}