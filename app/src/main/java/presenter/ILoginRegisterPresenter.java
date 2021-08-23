package presenter;

public interface ILoginRegisterPresenter {
    String login(String username, String password, String ip, String port);
    String register(String username, String password, String confPass, String ip, String port);
}
