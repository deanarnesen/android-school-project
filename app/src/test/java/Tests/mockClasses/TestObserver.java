package Tests.mockClasses;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {
    public TestObserver() {
    }

    @Override
    public void update(Observable observable, Object arg){
        System.out.println(observable.toString());
    }

}
