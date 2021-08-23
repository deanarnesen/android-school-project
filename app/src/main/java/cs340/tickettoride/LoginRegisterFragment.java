package cs340.tickettoride;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import presenter.LoginRegisterPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginRegisterFragment extends Fragment {


    private Button registerButton;
    private Button loginButton;
    private EditText mUsernameLogin;
    private EditText mPasswordLogin;
    private EditText mUsernameRegisterIn;
    private EditText mPasswordRegisterIn;
    private EditText mPasswordConfirmIn;
    private LoginRegisterPresenter presenter;
    private EditText mIp;
    private EditText mPort;


    boolean usernameSignInProvided = false;
    boolean usernameRegisterProvided = false;
    boolean passwordSignInPorvided = false;
    boolean passwordRegisterProvided = false;
    boolean passwordConfirmProvided = false;



    public LoginRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login_register, container, false);

        registerButton = (Button) v.findViewById(R.id.register_button);
        loginButton = (Button) v.findViewById(R.id.login_button);
        mUsernameLogin = v.findViewById(R.id.username_login_in);
        mPasswordLogin = v.findViewById(R.id.password_login_in);
        mUsernameRegisterIn = v.findViewById(R.id.username_register_in);
        mPasswordRegisterIn = v.findViewById(R.id.password_register_in);
        mPasswordConfirmIn = v.findViewById(R.id.password_confirm_in);
        mPort = v.findViewById(R.id.port_address_info);
        mIp = v.findViewById(R.id.ip_address_info);
        presenter = new LoginRegisterPresenter();
        presenter.setFragment(this);
        presenter.setMainActivity((MainActivity)getActivity());


        registerButton.setEnabled(false);
        loginButton.setEnabled(true);

        //Set the text views.
        setAllTextViews();

        //Listener for REGISTER BUTTON
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new RegisterTask().execute();
            }
        });


        // Listener for LOGIN BUTTON
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginTask().execute();
            }
        });


        return v;

    }



    /**
     * Checks that all text fields are filled before the signIn Buttons are enable.
     */
    public void setAllTextViews(){
        mUsernameLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()> 0){
                    enableButtons(true, "usernameLogIn");
                }else{
                    enableButtons(false, "usernameLogIn");
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });


        mPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()> 0){
                    enableButtons(true, "passwordLogIn");
                }else{
                    enableButtons(false, "passwordLogIn");
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        mUsernameRegisterIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    enableButtons(true, "usernameRegister");
                }else{
                    enableButtons(false, "usernameRegister");
                }

            }
        });

        mPasswordRegisterIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    enableButtons(true, "passwordRegister");
                }else{
                    enableButtons(false, "passwordRegister");
                }
            }
        });

        mPasswordConfirmIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    enableButtons(true, "confirmPassword");
                }else{
                    enableButtons(false, "confirmPassword");
                }
            }
        });

    }



    /**
     * Will enable the Buttons after the corresponding fields are enable.
     * @param value
     * @param field
     */
    public void enableButtons(boolean value, String field){
        switch(field){
            case "usernameLogIn":
                if(value){
                    usernameSignInProvided = true;
                }else{
                    usernameSignInProvided = false;
                }
                break;
            case "passwordLogIn":
                if(value){
                    passwordSignInPorvided = true;
                }else{
                    passwordSignInPorvided = false;
                }
                break;
            case "usernameRegister":
                if(value){
                    usernameRegisterProvided = true;
                }else{
                    usernameRegisterProvided = false;
                }
                break;
            case "passwordRegister":
                if(value){
                    passwordRegisterProvided = true;
                }else{
                    passwordRegisterProvided = false;
                }
                break;
            case "confirmPassword":
                if(value){
                    passwordConfirmProvided = true;
                }else{
                    passwordConfirmProvided = false;
                }
                break;
        }

        if(usernameSignInProvided && passwordSignInPorvided){
            loginButton.setEnabled(true);
        }else{
            loginButton.setEnabled(false);
        }

        if(usernameRegisterProvided && passwordRegisterProvided && passwordConfirmProvided){
            registerButton.setEnabled(true);
        }else{
            registerButton.setEnabled(false);
        }

    }


    /**
     *  It is called when the user clicks the register button.
     *  This functions creates a RegisterObject with the username and password provided.
     *
     * @pre username and password has to be given
     *
     * @post If      it is successfull, the LoginRegisterFragment is updated by LobbyFragment.
     *      *  Else    an detailed error message will be displayed.
     */
    public void createRegister() {

//        //CREATE A FRAGMENT LOADING
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        LoadingFragment fragment = new LoadingFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//

    }

    /**
     *
     * It is called when the user clicks the Sign In button.
     * This function creates a LoginObject with the username and the password provided.
     *
     * @pre username and password has to be given. The password must match the confirmPassword.
     *
     * @post If      it is successfull, the LoginRegisterFragment is updated by LobbyFragment.
     *      *      *  Else    an detailed error message will be displayed.
     *
     */
    public void createLogIn (){
//        Toast.makeText(getActivity(), "Logging In", Toast.LENGTH_SHORT).show();
        //CREATE A FRAGMENT LOADING
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        LoadingFragment fragment = new LoadingFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

    }

    /**
     * Display the message passed if any. If the message is null, it displays nothing.
     * @param message
     */
    public void displayError(String message){
        if(message != null) {
            Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Login AsyncTask
     */
    private class LoginTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            return presenter.login(mUsernameLogin.getText().toString(), mPasswordLogin.getText().toString(), mIp.getText().toString(), mPort.getText().toString());
        }
        @Override
        protected void onPostExecute(String message){
            displayError(message);
        }

    }

    /**
     * Register AsyncTask
     */
    private class RegisterTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            return presenter.register(mUsernameRegisterIn.getText().toString(), mPasswordRegisterIn.getText().toString(), mPasswordConfirmIn.getText().toString(), mIp.getText().toString(), mPort.getText().toString());
        }
        @Override
        protected void onPostExecute(String message){
            displayError(message);
        }
    }
}
