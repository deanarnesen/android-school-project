package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ClientGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

import java.util.ArrayList;
import java.util.Vector;

/**
 *  This AllRoutes class is designed to aid the client in selecting routes
 * @invariant vector size
 * @author Dean Arnesen
 */
public class AllRoutes {
    Vector<Route> routes;

    /**
     * @pre routes != null
     * @post routes are set to the passed in vector
     * @param routes routes to track
     */
    public AllRoutes(Vector<Route> routes){
        this.routes = routes;
    }

    /**
     * @pre routes != null
     * @post returns size of routes vector
     * @return number or routes stored
     */
    public int getSize(){
        return routes.size();
    }

    public Vector<Route> getRoutes() {
        return routes;
    }

    /**
     * Designed to provide the view with a method of selecting routes. A player selects a city
     * and this method provides the routes from that city that are available for taking
     *
     * @pre routes != null
     * @post vector unchanged
     * @param sourceCity city selected by the user in the process of claiming a route
     * @return a list of adjacent routes that the player is allowed to take
     */
    public ArrayList<Route> getAvaliableAdjacents(String sourceCity){
        ArrayList<Route> avaliableRoutes = new ArrayList<>();
        GameModel model = GameModel.getInstance();
        int numPlayers = GameLobbyModel.getInstance().getCurrentPlayers().size();
        for(Route route : routes){

            boolean thisRouteIsClaimedDOUBLE = false;
            Vector<Route> routeList = model.getClientPlayerInfo().getRoutes();
                for(Route playerRoute : routeList)
                {
                    if(route.getStartCity().equals(playerRoute.getStartCity()) && route.getEndCity().equals(playerRoute.getEndCity()))
                    {
                        thisRouteIsClaimedDOUBLE = true;
                        break;
                    }
                }
                Boolean doublerouterule = false;
            for(Route innerRoute: routes){
                if(innerRoute.getEndCity().equals(route.getEndCity()) && route.getStartCity().equals(innerRoute.getStartCity()) && innerRoute.isTaken() && numPlayers < 4){
                    doublerouterule = true;
                }
            }
            if(doublerouterule){
                continue;
            }
            if((route.getEndCity().equals(sourceCity) || route.getStartCity().equals(sourceCity)) && !route.isTaken() && !thisRouteIsClaimedDOUBLE){
                switch(route.getRouteColor()){
                    case RED:
                        if (model.getNumberRed() + model.getNumberLocomotive()>= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case BLUE:
                        if (model.getNumberBlue() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case GRAY:
                        if (model.getNumberBlue() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberRed() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberYellow() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberGreen() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberOrange() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberPurple() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberBlack() + model.getNumberLocomotive() >= route.getLength() ||
                                model.getNumberWhite() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case BLACK:
                        if(model.getNumberBlack() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case GREEN:
                        if(model.getNumberGreen() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case WHITE:
                        if(model.getNumberWhite() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case ORANGE:
                        if(model.getNumberOrange() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case PURPLE:
                        if(model.getNumberPurple() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                    case YELLOW:
                        if(model.getNumberYellow() + model.getNumberLocomotive() >= route.getLength()){
                            avaliableRoutes.add(route);
                        }
                        break;
                }

            }
        }
        return avaliableRoutes;
    }

    /**
     * @pre routes != null
     * @param id id of route to be returned
     * @return route with attribute id=id
     */
    public Route getRouteById(int id){
        return routes.elementAt(id);
    }

    /**
     * When the user selects the route out of the ones provided by 'getAvaliableAdjacents', this
     * function modifes the corresponding route to reflect the claim
     *
     * @pre routes != null
     *      GameModel.ActivePlayer != null
     *      GameModel.activePlayer exists in GameModel.gameMembers
     *      ActivePlayer.color != null
     * @post specified route 'taken' is now true
     *       "colorTakenBy" is same as ActivePlayer.color
     * @param id the route to be claimed
     */
    public void claimRoute(int id){
        Route route = routes.elementAt(id);
        route.setTaken(true);
        String activeUser = GameModel.getInstance().getActivePlayer();
        ClientGamePlayer activePlayer = GameModel.getInstance().getGameMembers().get(activeUser);
        route.setPlayerTakenBy(activePlayer.getColor());
    }

}
