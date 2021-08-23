package cs340.tickettoride;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;

import java.util.Vector;

import cs340.tickettoride.betweenFragments.CommunBetweenFragm;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarSelectFragment extends Fragment {
    ImageView mImageView1;
    ImageView mImageView2;
    ImageView mImageView3;

    CommunBetweenFragm communication = CommunBetweenFragm.getInstance();

    public CarSelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_car_select, container, false);
        mImageView1 = (ImageView) v.findViewById(R.id.fragment_car_select_image1);
        mImageView2 = (ImageView) v.findViewById(R.id.fragment_car_select_image2);
        mImageView3 = (ImageView) v.findViewById(R.id.fragment_car_select_image3);
        displayCards();
        return v;
    }

    public void displayCards(){
        int i = 0;
        Vector<DestinationCard> list = communication.getCardsToChoose();
        for(DestinationCard card : list){
            if(i == 0){
                mathcImageWithDestCard(card.getStartCity(), card.getEndCity(), mImageView1);
            }else if (i == 1){
                mathcImageWithDestCard(card.getStartCity(), card.getEndCity(), mImageView2);
            }else{
                mathcImageWithDestCard(card.getStartCity(), card.getEndCity(), mImageView3);
            }
            i++;
        }


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
                System.out.println("Error, no default in CardSelectFragment-MatchImageWithCard()\n\n");
        }
    }
}
