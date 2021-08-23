package presenter;


import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;

import java.util.Observable;
import java.util.Observer;

import cs340.tickettoride.LoginRegisterFragment;
import cs340.tickettoride.MainActivity;
import service.LoginService;
import service.RegisterService;
import service.ServerProxy;

public class LoginRegisterPresenter implements ILoginRegisterPresenter, IPresenter, Observer {
    private MainActivity mainActivity = null;
    private LoginRegisterFragment frag = null;
    private LoginService loginService;
    private RegisterService registerService;
    private ClientModelRoot data;

    public LoginRegisterPresenter(){
        this.loginService = new LoginService();
        this.registerService = new RegisterService();
        data = ClientModelRoot.getInstance();
        data.setCurrentScreen(PlayerGameState.LOGIN);
        data.addObserver(this);
    }

    public void setMainActivity(MainActivity main){
        mainActivity = main;
    }
    public void setFragment(LoginRegisterFragment frag){
        this.frag = frag;
    }

    /*will respond by calling error msg function or change screen function*/
    @Override
    public String login(String username, String password, String ip, String port) {
        //Set info on singleton Client
        data.setClientUserName(username);

        //Temporal for IP ADDRESS
        ServerProxy serve = ServerProxy.getInstance();
        serve.setPortAndIP(port, ip);
        serve.startPoller();
        //calling the server
        String returnMessage = loginService.login(username, password);
        /*if(loginService.login(username, password).equals("Loading")){
            //       moveOn();
            return "Loading";
        }*/
        return returnMessage;
    }
    /*will respond by calling error msg function or change screen function
    * checks for same pass/confPass*/
    @Override
    public String register(String username, String password, String confPass, String ip, String port) {
        String message = null;
        data.setClientUserName(username);


        //Temporal for IP ADDRESS
        ServerProxy serve = ServerProxy.getInstance();
        serve.setPortAndIP(port, ip);
        serve.startPoller();


        if(password.equals(confPass)){
            message = registerService.register(username, password, confPass);
        }
        else{
            message = "Password must match";
        }
        return message;

    }
    public void login(){
        if(frag != null){
            frag.createLogIn();
        }

    }

    public void registered(){
    }
    private void moveOn(){
        if(mainActivity != null){
            mainActivity.goToGameListLobby();
        }
    }

    @Override
    public void displayError(String error) {
        //view.displayError(error);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == UpdateType.SCREEN_STATE) {
            if (ClientModelRoot.getInstance().getCurrentScreen() == PlayerGameState.JOIN_GAME_LOBBY) {
                moveOn();
            }
            else{
                //frag.displayError(ClientModelRoot.getInstance().getCurrentError()); todo

            }
        }
        else if(arg == UpdateType.DISPLAY_ERROR){
            ClientModelRoot.getInstance().getCurrentError();
        }
    }
}

