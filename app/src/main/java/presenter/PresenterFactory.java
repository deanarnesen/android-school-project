package presenter;

public class PresenterFactory {
    private IPresenter currentPresenter = null;

    public IPresenter getCurrentPresenter(){
        if(currentPresenter == null){
            return null;
        }
        return  currentPresenter;
    }

    public void setCurrentPresenter(IPresenter presenter){
        currentPresenter = presenter;
    }
}
