package cs340.tickettoride;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import java.util.Vector;

import cs340.tickettoride.betweenFragments.CommunBetweenFragm;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowCardsFragment extends Fragment {

    CommunBetweenFragm communication = CommunBetweenFragm.getInstance();
    LinearLayout mLinearLayout;
    TextView mRedCards;
    TextView mYellowCards;
    TextView mOrangeCards;
    TextView mGreenCards;
    TextView mBlueCards;
    TextView mBlackCards;
    TextView mWhiteCards;
    TextView mPurpleCards;
    TextView mLocomotiveCards;


    public ShowCardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_cards, container, false);
        mLinearLayout = v.findViewById(R.id.fragment_show_cards_linearlayout);
        mRedCards = v.findViewById(R.id.red_train_card_count);
        mOrangeCards= v.findViewById(R.id.orange_train_card_count);
        mYellowCards = v.findViewById(R.id.yellow_train_card_count);
        mGreenCards = v.findViewById(R.id.green_train_card_count);
        mBlackCards = v.findViewById(R.id.black_train_card_count);
        mBlueCards = v.findViewById(R.id.blue_train_card_count);
        mWhiteCards = v.findViewById(R.id.white_train_card_count);
        mPurpleCards = v.findViewById(R.id.purple_train_card_count);
        mLocomotiveCards = v.findViewById(R.id.locomotive_train_card_count);
        displayCards();
        setCardNumbers();

        return v;
    }

    public void displayCards(){
        Vector<DestinationCard> destCards = communication.getDestCards();
        for(DestinationCard dest : destCards){
            ImageView image = new ImageView(this.getContext());
            mathcImageWithDestCard(dest.getStartCity(), dest.getEndCity(), image);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
            image.setLayoutParams(layoutParams);
            mLinearLayout.addView(image);



        }



    }

    public void setCardNumbers(){
        GameModel model = GameModel.getInstance();
        mRedCards.setText(Integer.toString(model.getNumberRed()));
        mYellowCards.setText(Integer.toString(model.getNumberYellow()));
        mOrangeCards.setText(Integer.toString(model.getNumberOrange()));
        mGreenCards.setText(Integer.toString(model.getNumberGreen()));
        mBlueCards.setText(Integer.toString(model.getNumberBlue()));
        mPurpleCards.setText(Integer.toString(model.getNumberPurple()));
        mBlackCards.setText(Integer.toString(model.getNumberBlack()));
        mWhiteCards.setText(Integer.toString (model.getNumberWhite()));
        mLocomotiveCards.setText(Integer.toString(model.getNumberLocomotive()));
    }



    public void mathcImageWithDestCard(String name, String dest, ImageView imageView){
        switch(name){
            case "Los Angeles":
                if(dest.equals("New York")){
                    imageView.setImageResource(R.drawable.losangeles_newyork);
                }else if(dest.equals("Miami")){
                    imageView.setImageResource(R.drawable.losangeles_miami);
                }else if (dest.equals("Chicago")){
                    imageView.setImageResource(R.drawable.losangeles_chicago);
                }
                break;
            case "Duluth":
                if(dest.equals("Houston")){
                    imageView.setImageResource(R.drawable.duluth_houston);
                }else if (dest.equals("El Paso")){
                    imageView.setImageResource(R.drawable.duluth_elpaso);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "New York":
                imageView.setImageResource(R.drawable.newyork_atlanta);
                break;
            case "Sault St. Marie":
                if(dest.equals("Nashville")){
                    imageView.setImageResource(R.drawable.saultstmarie_nashville);
                }else if (dest.equals("Oklahoma")){
                    imageView.setImageResource(R.drawable.saultstmarie_oklahoma);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Portland":
                if(dest.equals("Nashville")) {
                    imageView.setImageResource(R.drawable.portland_nashville);
                }else if(dest.equals("Phoenix")){
                    imageView.setImageResource(R.drawable.portland_phoenix);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Vancouver":
                if(dest.equals("Montreal")) {
                    imageView.setImageResource(R.drawable.vancouver_montreal);
                }else if (dest.equals("Santa Fe")){
                    imageView.setImageResource(R.drawable.vancouver_santafe);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Toronto":
                imageView.setImageResource(R.drawable.toronto_miami);
                break;
            case "Dallas":
                imageView.setImageResource(R.drawable.dallas_newyork);
                break;
            case "Calgary":
                if(dest.equals("Salt Lake City")) {
                    imageView.setImageResource(R.drawable.calgary_saltlakecity);
                }else if (dest.equals("Phoenix")){
                    imageView.setImageResource(R.drawable.calgary_phoenix);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Winnipeg":
                if(dest.equals("Houston")){
                    imageView.setImageResource(R.drawable.winnipeg_houston);
                }else if (dest.equals("Little Rock")){
                    imageView.setImageResource(R.drawable.winnipeg_littlerock);
                }
                else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "San Francisco":
                imageView.setImageResource(R.drawable.sanfrancisco_atlanta);
                break;
            case "Kansas City":
                imageView.setImageResource(R.drawable.kansascity_houston);
                break;
            case "Denver":
                if(dest.equals("El Paso")){
                    imageView.setImageResource(R.drawable.denver_elpaso);
                }else if (dest.equals("Pittsburgh")){
                    imageView.setImageResource(R.drawable.denver_pittsburgh);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Chicago":
                if(dest.equals("New Orleans")) {
                    imageView.setImageResource(R.drawable.chicago_neworleans);
                }else if (dest.equals("Santa Fe")){
                    imageView.setImageResource(R.drawable.chicago_santafe);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Boston":
                imageView.setImageResource(R.drawable.boston_miami);
                break;
            case "Montreal":
                if(dest.equals("Atlanta")) {
                    imageView.setImageResource(R.drawable.montreal_atlanta);
                }else if (dest.equals("New Orleans")){
                    imageView.setImageResource(R.drawable.montreal_neworleans);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Seattle":
                if(dest.equals("New York")) {
                    imageView.setImageResource(R.drawable.seattle_newyork);
                }else if (dest.equals("Los Angeles")){
                    imageView.setImageResource(R.drawable.seattle_losangeles);
                }else{
                    System.out.println("Problem with matchImageWithDestCard IN CarSelect Fragment\n\n");
                }
                break;
            case "Helena":
                imageView.setImageResource(R.drawable.helena_losangeles);
                break;
            default:
                System.out.println("Error, no default in CarSelecFragment-MatchImageWithCard()\n\n");
        }

    }


}
