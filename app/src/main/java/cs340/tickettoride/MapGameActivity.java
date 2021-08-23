package cs340.tickettoride;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.RouteColor;

import java.util.ArrayList;
import java.util.Vector;

import cs340.tickettoride.PathRouteFiles.PathRoute;
import cs340.tickettoride.betweenFragments.CommunBetweenFragm;
import presenter.MapPresenter;


/**
 *
 *
 *  CommunBetweenFrag : class to communicate data between fragments.
 *  MapPresenter : class that will be in charge of moving to different views (fragments)
 *  list : list of Routes >= 0
 *  selected item : when the user selects the route, the selected item saves the last index of the route selected
 *  toReturnDestList : list of destination cards selected
 *  cardsToDisplay : list of destination cards that will be display for the user to choose
 *  fragmentMap : the fragment object used to display the map view
 *  result :  list of destination cards that were not selected by the user
 *  message : message given by the user to be send through chat
 *  chatFragment : fragment that holds the view for the chat interface
 *  usernames : list of the username of all users in the game.
 *
 * @invariant usernames > 0
 *
 */
public class MapGameActivity extends AppCompatActivity implements  IMapInterface, PopupMenu.OnMenuItemClickListener {



    private CommunBetweenFragm communication = CommunBetweenFragm.getInstance();
    private MapPresenter mPresenter;
    private ArrayList<Route> list;
    private int selectedItem;
    private Vector<DestinationCard> toReturnDestList = new Vector<>();
    private Vector<DestinationCard> cardsToDisplay;
    private MapFragmentGame fragmentMap = new MapFragmentGame();
    private Vector<DestinationCard> result = new Vector<>();
    private EditText message;
    private ChatFragment chatFragment;
    private ArrayList<String> usernames; //ordered!
    private Drawable routeIconColor;

    /**
     * Method already design by the AppCompatActivity class from Android
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.claim_route_menu, menu);
    }

    /**
     * Sets the activity to be display on the device.
     * @param savedInstanceState - package given to the method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_game);
        mPresenter = new MapPresenter(this);
        openMap();
        usernames = mPresenter.getUsers();
        communication.setUsernames(usernames);
    }


    /**
     * Method that creates an fragment and adds it to the stack. This object should display the DestinationCards to select from the user.
     *
     * @pre Have MapGameActivity be the main activity at this moment with xml attribute id=fragment_containter_chat
     * @post There will one more fragment in the fragment stack
     *
     */
    public void openDestCardSelect(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new CarSelectFragment();
        fm.beginTransaction().add(R.id.fragment_container_chat, fragment).commit();
    }


    /**
     * Method that puts a MapFragmentGame to the stack of the fragments. This changes the UI to the specefied fragment.
     *
     * @pre Have MapGameActivity be the main activity at this moment with xml attribute id=fragment_containter_chat
     * @post There will one more fragment in the fragment stack
     */
    public void openMap(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container_chat, fragmentMap).commit();
    }


    /**
     *
     * Method that puts a ChatFragment to the stack of the fragments. This changes the UI to the specefied fragment.
     *
     * @pre Have MapGameActivity be the main activity at this moment with xml attribute id=fragment_containter_chat
     * @post There will one more fragment in the fragment stack
     */
    public void openChat(){

        FragmentManager fm = getSupportFragmentManager();
        chatFragment = new ChatFragment();
        fm.beginTransaction().add(R.id.fragment_container_chat, chatFragment).commit();

    }

    /**
     * Method that puts a ShowCardsFragment to the stack of the fragments. This changes the UI to the specefied fragment.
     *
     * @pre Have MapGameActivity be the main activity at this moment with xml attribute id=fragment_containter_chat
     * @post There will one more fragment in the fragment stack
     *
     */
    public void openPlayerCards(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new ShowCardsFragment();
        fm.beginTransaction().add(R.id.fragment_container_chat, fragment).commit();
    }


    /**
     * This claims the route with the given color.
     * @param color The color the route will be
     * @param route The route being claimed.
     *
     * @pre The MapView fragment should be loaded before calling this function. Color has to be of the right format. The Route can't be null.
     * @post The selected route will change to inactive and no longer visible in the routes menu.
     *
     */
    @Override
    public void claimRoute(Color color, Route route) {
        displayPathSelected(route, color);
    }

    /**
     * It sets the number of cards for the specific player on the view.
     *
     * @param username
     * @param numCars
     *
     * @pre username has to be not null and has to belong to a player of the game The numCars has to be greater than 0.
     * @post The view will change the number of cars left for the specific player.
     */
    @Override
    public void setPlayerCarsLeft(final String username, final int numCars) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.setPlayerCarsLeftfrag(username, numCars);
            }
        });
    }

    /**
     *
     *Updates the score boards on the view by the given score number
     *
     * @param username
     * @param score
     *
     * @pre The MapFragment has to be loaded and be the active view.
     * @post the score for the given username will be changed to the one given
     *
     *
     */
    public void setPlayerScore(final String username, final int score) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.setPlayerScoreFrag(username, score);
            }
        });
    }

    /**
     * Method that will organize the usernames to have the active player at the top
     * @param turnOrder list of usernames (strings)
     *
     * @pre  The MapFragment has to be loaded and be the active view.
     * @post
     *
     */
    @Override
    public void setTurnOrder(ArrayList<String> turnOrder) {

    }

    @Override
    public void setActivePlayer(final String username) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.setActivePlayerfrag(username);

            }
        });
    }

    @Override
    public void setTrainCardNumber(final String username, final int cards) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.setTrainCardNumberfrag(username, cards);

            }
        });
    }

    @Override
    public void setDestCardNumber(final String username, final int cards) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.setDestCardNumberfrag(username, cards);

            }
        });

    }

    @Override
    public void showToast(final String toast) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.showToast(toast);

            }
        });

    }

    @Override
    public void updateChat(Vector<ChatMessage> currentChat) {
        final Vector<ChatMessage> messages = currentChat;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatFragment.updateMessages(new ArrayList<ChatMessage>(messages));
            }
        });

    }

    @Override
    public void updateDestDeck(final int cardsRemaining) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.updateDestDeckfrag(cardsRemaining);
            }
        });

    }

    public void grayRouteColorSelector(ArrayList<String> avaliableColors){
        ListView colorSelector;
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.gray_route_color_selector);
        dialog.setTitle("Use which color?");
        colorSelector = (ListView) dialog.findViewById(R.id.color_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.color_string, avaliableColors);
        colorSelector.setAdapter(adapter);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });

    }


    @Override
    public void updateTrainDeck(final int cardsRemaining) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.updateTrainDeckfrag(cardsRemaining);
            }
        });

    }

    @Override
    public void setFaceupTrainCards(Vector<TrainCarCard> trainCards) {
        communication.setTrainCarCards(trainCards);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentMap.updateTrainCardsfrag();
            }
        });
    }

    @Override
    public void setPlayerTrainCards(Vector<TrainCarCard> trainCards) {
        communication.setTrainCards(trainCards);
    }

    @Override
    public void setPlayerDestCards(Vector<DestinationCard> destCards) {
        communication.setDestCards(destCards);

    }

    @Override
    public void runUpdateTest() {
    }

    public void promptDestCards(Vector<DestinationCard> cards){
        cardsToDisplay = cards;
        communication.setCardsToChoose(cards);
        openDestCardSelect();
    }

    /**
     * Pre :
     * Post :
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        selectedItem = item.getItemId();
        Integer routeID = Integer.valueOf(list.get(selectedItem).getId());
        //TODO: get the color of the player who claimed the route.
        //TODO: only display path based on server response

        mPresenter.claimRoute(routeID); //will do nothing if not active player
        list.clear();
        return false;
    }


    public void displayPathSelected(final Route route, final Color color){

        final PathRoute pathRoute = new PathRoute(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pathRoute.displayPath(color, route);
            }
        });





//        ImageView imageView = findViewById(R.id.frag_map_lasveg_salt);
//        imageView.setImageResource(R.drawable.path_lasveg_salt_pink);
//        imageView.setVisibility(View.VISIBLE);


    }




    public void displayLastRoundNotificaiton()
    {
        showToast("Last Round");
    }




    public void sendSelectedDestinationCards(View view){
        for(DestinationCard card : cardsToDisplay){
            boolean returnCard = false;
            for(DestinationCard card1 : toReturnDestList){
                if(card.equals(card1)){
                    returnCard = true;
                }
            }
            if(!returnCard){
                result.add(card);
            }
        }
        if(toReturnDestList.size() <2){
            showToast("You need at least two cards");
        }else {
            //openMap();
            new SendDestinationCards().execute();
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_chat);
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }
    }


    public void cardSelectedfromView(View view) {
        ImageView imageView = (ImageView) view.findViewById(view.getId());
        switch  (view.getId()){
            case R.id.fragment_car_select_image1:
                toReturnDestList.add(cardsToDisplay.elementAt(0));
                imageView.setVisibility(View.GONE);
                break;
            case R.id.fragment_car_select_image2:
                toReturnDestList.add(cardsToDisplay.elementAt(1));
                imageView.setVisibility(View.GONE);
                break;
            case R.id.fragment_car_select_image3:
                toReturnDestList.add(cardsToDisplay.elementAt(2));
                imageView.setVisibility(View.GONE);
                break;
            default:
                System.out.println("ERROR ON DISPLAYING THE CARDS ON MapGameActivity -> listenToThis");
                break;
        }
    }

    /**
     * Creates the popup menu based on the city clicked.
     * @param view
     */
    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);


        switch (view.getId()) {
            case R.id.map_atlanta_button:
                setPopupMenu("Atlanta", popup);
                break;
            case R.id.map_boston_button:
                setPopupMenu("Boston", popup);
                break;
            case R.id.map_calgary_button:
                setPopupMenu("Calgary", popup);
                break;
            case R.id.map_charleston_button:
                setPopupMenu("Charleston", popup);
                break;
            case R.id.map_chicago_button:
                setPopupMenu("Chicago", popup);
                break;
            case R.id.map_dallas_button:
                setPopupMenu("Dallas", popup);
                break;
            case R.id.map_denver_button:
                setPopupMenu("Denver", popup);
                break;
            case R.id.map_duluth_button:
                setPopupMenu("Duluth", popup);
                break;
            case R.id.map_elpaso_button:
                setPopupMenu("El Paso", popup);
                break;
            case R.id.map_helena_button:
                setPopupMenu("Helena", popup);
                break;
            case R.id.map_houston_button:
                setPopupMenu("Houston", popup);
                break;
            case R.id.map_kansas_city_button:
                setPopupMenu("Kansas City", popup);
                break;
            case R.id.map_las_vegas_button:
                setPopupMenu("Las Vegas", popup);
                break;
            case R.id.map_little_rock_button:
                setPopupMenu("Little Rock", popup);
                break;
            case R.id.map_los_angeles_button:
                setPopupMenu("Los Angeles", popup);
                break;
            case R.id.map_miami_button:
                setPopupMenu("Miami", popup);
                break;
            case R.id.map_montreal_button:
                setPopupMenu("Montreal", popup);
                break;
            case R.id.map_nashville_button:
                setPopupMenu("Nashville", popup);
                break;
            case R.id.map_new_orleans_button:
                setPopupMenu("New Orleans", popup);
                break;
            case R.id.map_new_york_button:
                setPopupMenu("New York", popup);
                break;
            case R.id.map_oklahoma_city_button:
                setPopupMenu("Oklahoma City", popup);
                break;
            case R.id.map_omaha_button:
                setPopupMenu("Omaha", popup);
                break;
            case R.id.map_phoenix_button:
                setPopupMenu("Phoenix", popup);
                break;
            case R.id.map_pitsburgh_button:
                setPopupMenu("Pittsburgh", popup);
                break;
            case R.id.map_portland_button:
                setPopupMenu("Portland", popup);
                break;
            case R.id.map_raleigh_button:
                setPopupMenu("Raleigh", popup);
                break;
            case R.id.map_saint_louis_button:
                setPopupMenu("Saint Louis", popup);
                break;
            case R.id.map_salt_lake_city_button:
                setPopupMenu("Salt Lake City", popup);
                break;
            case R.id.map_san_francisco_button:
                setPopupMenu("San Francisco", popup);
                break;
            case R.id.map_santa_fe_button:
                setPopupMenu("Santa Fe", popup);
                break;
            case R.id.map_sault_st_marie_button:
                setPopupMenu("Sault St. Marie", popup);
                break;
            case R.id.map_seattle_button:
                setPopupMenu("Seattle", popup);
                break;
            case R.id.map_toronto_button:
                setPopupMenu("Toronto", popup);
                break;
            case R.id.map_vancouver_button:
                setPopupMenu("Vancouver", popup);
                popup.show();
                break;
            case R.id.map_washington_button:
                setPopupMenu("Washington", popup);
                break;
            case R.id.map_winnipeg_button:
                setPopupMenu("Winnipeg", popup);
                break;
        }
    }

    /**
     *
     * @param name_in
     * @param popup
     */
    public void setPopupMenu (String name_in, PopupMenu popup){
        list = mPresenter.getAdjRoutes(name_in);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.claim_route_menu);
        addToPopupList(list, popup, name_in);
        popup.show();
    }

    public void addToPopupList(ArrayList<Route> list, PopupMenu popupMenu_in, String name_in){
        if(list.size() != 0) {
            int i = 0;
            for (Route r : list) {
                RouteColor color = r.getRouteColor();
                String stringColor = "(gray)";
                switch (color)
                {
                    case BLUE:
                        stringColor = "(blue)";
                        break;
                    case RED:
                        stringColor = "(red)";
                        break;
                    case YELLOW:
                        stringColor = "(yellow)";
                        break;
                    case WHITE:
                        stringColor = "(white)";
                        break;
                    case ORANGE:
                        stringColor = "(orange)";
                        break;
                    case PURPLE:
                        stringColor = "(purple)";
                        break;
                    case GRAY:
                        stringColor = "(gray)";
                        break;
                    case GREEN:
                        stringColor = "(green)";
                        break;
                    case BLACK:
                        stringColor = "(black)";
                        break;
                }
                if(!(r.getEndCity().equals(name_in))) {
                    popupMenu_in.getMenu().add(0, i, 0, r.getEndCity()+stringColor);
                }else{
                    popupMenu_in.getMenu().add(0, i, 0, r.getStartCity()+stringColor);
                }
                i++;
            }
        }else{
            popupMenu_in.getMenu().add("No routes available");
        }
    }


    public void toEndScreen() {
        Intent intent = new Intent(this, EndGameActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToCards(View view) {

        openPlayerCards();
    }

    public void goBack(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_chat);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void goBackFromChat(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_chat);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void goToChat(View view) {
        openChat();
    }

    public void sendChatMessage(View view) {
        message = view.findViewById(R.id.fragment_chat_text_tosend);
        new SendChatMessageAsync().execute();
    }

    //draw train cards by image position
    public void drawCard1(View view) { new SendTrainCardRequest(0).execute(); }
    public void drawCard2(View view){
        new SendTrainCardRequest(1).execute();
    }
    public void drawCard3(View view){
        new SendTrainCardRequest(2).execute();
    }
    public void drawCard4(View view){
        new SendTrainCardRequest(3).execute();
    }
    public void drawCard5(View view){
        new SendTrainCardRequest(4).execute();
    }
    public void drawCardDeck(View view) { new SendTrainCardRequest(6).execute();}

    public void drawDestCard(View view) {
        new DrawDestCard().execute();
    }

    public void toEndScreen(View view) {
        Toast.makeText(this, "CA::ED", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EndGameActivity.class);
        startActivity(intent);
        this.finish();
    }


    private class SendDestinationCards extends AsyncTask<GameLobbyActivity, String, Void> {
        @Override
        protected Void doInBackground(GameLobbyActivity... voids) {
            mPresenter.destCardsChosen(toReturnDestList, result);
            return null;
        }
    }

    private class SendChatMessageAsync extends AsyncTask<MapGameActivity, String, Void> {
        @Override
        protected Void doInBackground(MapGameActivity... voids) {
            new MapPresenter().sendChat(message.getText().toString());
            return null;
        }
    }

    private class SendTrainCardRequest extends AsyncTask<Void, Void, Void>{
        int pos = 0;
        public SendTrainCardRequest(int pos){
            super();
            this.pos = pos;
        }
        protected Void doInBackground(Void... voids){
            mPresenter.drawTrainCard(pos);
            return null;
        }
    }


    private class DrawDestCard extends AsyncTask<Void, Void, Void>{

        protected Void doInBackground(Void... voids){
            mPresenter.getNewDests();
            return null;
        }
    }
}
