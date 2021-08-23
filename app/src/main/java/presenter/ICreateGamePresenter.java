package presenter;

public interface ICreateGamePresenter {
    void setGameName(String name);
    void setMaxPlayers(int number);
   /* void setPrivacy(Boolean isPrivate);
    void setPassword(String password);*/ //todo extra
    void confirmCreation();
}

//remote change! local conflict
