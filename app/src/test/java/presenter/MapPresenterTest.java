package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

import org.junit.Test;

import java.util.ArrayList;

import cs340.tickettoride.MockMap;

public class MapPresenterTest {
    @Test
    public void citySelect(){
        MapPresenter test = new MapPresenter(new MockMap());
        ArrayList<Route> routes = test.getAdjRoutes("Seattle");

        System.out.print(routes);
    }
}