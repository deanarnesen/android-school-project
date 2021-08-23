package cs340.tickettoride;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    boolean firstLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firstLoad = true;
        System.out.println("onCreate\n");
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);

        // This handler only makes the screen go to sleep for 2 seconds before calling the LoginRegisterFragment
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = new LoginRegisterFragment();
                fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

            }
        }, 2000);   //2 seconds

    }


    @Override
    protected void onRestart(){
        super.onRestart();
        LoginRegisterFragment fragment = new LoginRegisterFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }


    /**
     * Creates a new activity that takes you to the Main Lobby
     */
    public void goToGameListLobby(){

        //CREATE AN ACTIVITY
        Intent intent2 = new Intent(this, ListGameActivity.class);
        this.startActivity(intent2);

    }
}














/* This block is just for easier access to reusable code - LEVI ----  I put it here on request of Ryan. :)

    //CREATION OF FRAGMENTS.
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = new SplashScreenFragment();
    fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();


    //CREATE AN ACTIVITY
    Intent intent2 = new Intent(this.getActivity(), ListGameActivity.class);
    this.startActivity(intent2);



    //DISPLAYING A TOAST
    Toast.makeText(this, "LOADing", Toast.LENGTH_SHORT).show();






 */




