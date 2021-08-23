package cs340.tickettoride.PathRouteFiles;

import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

import cs340.tickettoride.MapGameActivity;
import cs340.tickettoride.R;


/**
 * This class returns the imagePath that belongs to the Route given with the specefied color.
 */
public class PathRoute {
    public PathRoute(MapGameActivity view){
        this.mView=view;
    }


    MapGameActivity mView;
    Route mRoute;
    Color mColor;
    ImageView imageView;

    public PathRoute(Color color_in, Route route_in, MapGameActivity view){
        this.mColor=color_in;
        this.mRoute=route_in;
        this.mView = view;
    }


    public Route getRoute() {
        return mRoute;
    }

    public void setRoute(Route route) {
        mRoute = route;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public void displayPath(Color color, Route route){

        System.out.println("This is the id: " + route.getId() + "******************************hhhh\n\n\n");
        switch(color){
            case RED:
                displayImageRed(route);
                break;
            case YELLOW:
                displayImageYellow(route);
                break;
            case GREEN:
                displayImageGreen(route);
                break;
            case BLUE:
                displayImageBlue(route);
                break;
            case PINK:
                displayImagePink(route);
                break;
            default:
                displayImagePink(route);
        }

    }

    public void displayImageRed(Route route){

        System.out.println("Start City: " + route.getStartCity() + "END City : " + route.getEndCity() + "\n\n\n\n");
        switch(route.getId()) {
            case 0:
                imageView = mView.findViewById(R.id.frag_map_miami_charl);
                imageView.setImageResource(R.drawable.path_miami_charles_red);
                break;
            case 1:
                imageView = mView.findViewById(R.id.frag_map_van_seat);
                imageView.setImageResource(R.drawable.path_van_seat_red);
                break;
            case 2:
                imageView = mView.findViewById(R.id.frag_map_van_seat2);
                imageView.setImageResource(R.drawable.path_van_seat2_red);
                break;
            case 3:
                imageView = mView.findViewById(R.id.frag_map_van_cal);
                imageView.setImageResource(R.drawable.path_van_calg_red);
                break;
            case 4:
                imageView = mView.findViewById(R.id.frag_map_seat_port);
                imageView.setImageResource(R.drawable.path_seat_port_red);
                break;
            case 5:
                imageView = mView.findViewById(R.id.frag_map_seat_port2);
                imageView.setImageResource(R.drawable.path_seat_port2_red);
                break;

            case 6:
                imageView = mView.findViewById(R.id.frag_map_seat_cal);
                imageView.setImageResource(R.drawable.path_seat_calg_red);
                break;
            case 7:
                imageView = mView.findViewById(R.id.frag_map_seat_helen);
                imageView.setImageResource(R.drawable.path_seatt_helen_red);
                break;

            case 8:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra);
                imageView.setImageResource(R.drawable.path_sanfra_port_red);
                break;
            case 9:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra2);
                imageView.setImageResource(R.drawable.path_sanfra_port2_red);
                break;
            case 10:
                imageView = mView.findViewById(R.id.frag_map_port_salt);
                imageView.setImageResource(R.drawable.path_port_salt_red);
                break;
            case 11:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt);
                imageView.setImageResource(R.drawable.path_sanfra_salt_red);
                break;
            case 12:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt2);
                imageView.setImageResource(R.drawable.path_sanfra_salt2_red);
                break;
            case 13:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan);
                imageView.setImageResource(R.drawable.path_sanfra_losang_red);
                break;
            case 14:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan2);
                imageView.setImageResource(R.drawable.path_sanfra_losang2_red);
                break;
            case 15:
                imageView = mView.findViewById(R.id.frag_map_losan_lasveg);
                imageView.setImageResource(R.drawable.path_losan_lasveg_red);
                break;
            case 16:
                imageView = mView.findViewById(R.id.frag_map_losan_phoenix);
                imageView.setImageResource(R.drawable.path_loans_phoenix_red);
                break;
            case 17:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_red);
                break;
            case 18:
                imageView = mView.findViewById(R.id.frag_map_cal_wini);
                imageView.setImageResource(R.drawable.path_calg_winnni_red);
                break;
            case 19:
                imageView = mView.findViewById(R.id.frag_map_cal_helen);
                imageView.setImageResource(R.drawable.path_calg_helen_red);
                break;
            case 20:
                imageView = mView.findViewById(R.id.frag_map_helen_wini);
                imageView.setImageResource(R.drawable.path_helen_winnni_red);
                break;
            case 21:
                imageView = mView.findViewById(R.id.frag_map_helen_dulut);
                imageView.setImageResource(R.drawable.path_helen_dulut_red);
                break;
            case 22:
                imageView = mView.findViewById(R.id.frag_map_helen_oma);
                imageView.setImageResource(R.drawable.path_helen_oma_red);
                break;
            case 23:
                imageView = mView.findViewById(R.id.frag_map_denver_helen);
                imageView.setImageResource(R.drawable.path_denver_helen_red);
                break;
            case 24:
                imageView = mView.findViewById(R.id.frag_map_salt_helen);
                imageView.setImageResource(R.drawable.path_salt_helen_red);
                break;
            case 25:
                imageView = mView.findViewById(R.id.frag_map_salt_denver);
                imageView.setImageResource(R.drawable.path_salt_denver_red);
                break;
            case 26:
                imageView = mView.findViewById(R.id.frag_map_salt_denver2);
                imageView.setImageResource(R.drawable.path_salt_denver2_red);
                break;
            case 27:
                imageView = mView.findViewById(R.id.frag_map_lasveg_salt);
                imageView.setImageResource(R.drawable.path_lasveg_salt_red);
                break;
            case 28:
                imageView = mView.findViewById(R.id.frag_map_phoenix_denver);
                imageView.setImageResource(R.drawable.path_phoenix_denver_red);
                break;
            case 29:
                imageView = mView.findViewById(R.id.frag_map_phoenix_santaf);
                imageView.setImageResource(R.drawable.path_phoenix_santaf_red);
                break;
            case 30:
                imageView = mView.findViewById(R.id.frag_map_phoenix_elpas);
                imageView.setImageResource(R.drawable.path_phoenix_elpaso_red);
                break;
            case 31:
                imageView = mView.findViewById(R.id.frag_map_denver_oma);
                imageView.setImageResource(R.drawable.path_denver_oma_red);
                break;
            case 32:
                imageView = mView.findViewById(R.id.frag_map_denver_kan);
                imageView.setImageResource(R.drawable.path_denver_kan_red);
                break;
            case 33:
                imageView = mView.findViewById(R.id.frag_map_denver_kan2);
                imageView.setImageResource(R.drawable.path_denver_kan2_red);
                break;
            case 34:
                imageView = mView.findViewById(R.id.frag_map_denver_okla);
                imageView.setImageResource(R.drawable.path_denver_okla_red);
                break;
            case 35:
                imageView = mView.findViewById(R.id.frag_map_santaf_denver);
                imageView.setImageResource(R.drawable.path_santaf_denver_red);
                break;
            case 36:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_red);
                break;
            case 37:
                imageView = mView.findViewById(R.id.frag_map_elpaso_santaf);
                imageView.setImageResource(R.drawable.path_elpaso_santaf_red);
                break;
            case 38:
                imageView = mView.findViewById(R.id.frag_map_elpaso_okla);
                imageView.setImageResource(R.drawable.path_elpaso_okla_red);
                break;
            case 39:
                imageView = mView.findViewById(R.id.frag_map_elpaso_dallas);
                imageView.setImageResource(R.drawable.path_elpaso_dalla_red);
                break;
            case 40:
                imageView = mView.findViewById(R.id.frag_map_elpaso_houst);
                imageView.setImageResource(R.drawable.path_elpaso_houst_red);
                break;
            case 41:
                imageView = mView.findViewById(R.id.frag_map_winni_sault);
                imageView.setImageResource(R.drawable.path_winnni_sault_red);
                break;
            case 42:
                imageView = mView.findViewById(R.id.frag_map_wini_dulut);
                imageView.setImageResource(R.drawable.path_winnni_dulut_red);
                break;
            case 43:
                imageView = mView.findViewById(R.id.frag_map_dulut_sault);
                imageView.setImageResource(R.drawable.path_dulut_sault_red);
                break;
            case 44:
                imageView = mView.findViewById(R.id.frag_map_dulut_toron);
                imageView.setImageResource(R.drawable.path_dulut_toron_red);
                break;
            case 45:
                imageView = mView.findViewById(R.id.frag_map_chica_dulut);
                imageView.setImageResource(R.drawable.path_chica_dulut_red);
                break;
            case 46:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma);
                imageView.setImageResource(R.drawable.path_dulut_oma_red);
                break;
            case 47:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma2);
                imageView.setImageResource(R.drawable.path_dulut_oma2_red);
                break;
            case 48:
                imageView = mView.findViewById(R.id.frag_map_chica_oma);
                imageView.setImageResource(R.drawable.path_chica_oma_red);
                break;
            case 49:
                imageView = mView.findViewById(R.id.frag_map_oma_kan);
                imageView.setImageResource(R.drawable.path_oma_kan_red);
                break;
            case 50:
                imageView = mView.findViewById(R.id.frag_map_oma_kan2);
                imageView.setImageResource(R.drawable.path_oma_kan2_red);
                break;
            case 51:
                imageView = mView.findViewById(R.id.frag_map_kan_saint);
                imageView.setImageResource(R.drawable.path_kan_saint_red);
                break;
            case 52:
                imageView = mView.findViewById(R.id.frag_map_kan_saint2);
                imageView.setImageResource(R.drawable.path_kan_saint2_red);
                break;
            case 53:
                imageView = mView.findViewById(R.id.frag_map_okla_kan);
                imageView.setImageResource(R.drawable.path_okla_kan_red);
                break;
            case 54:
                imageView = mView.findViewById(R.id.frag_map_okla_kan2);
                imageView.setImageResource(R.drawable.path_okla_kan2_red);
                break;
            case 55:
                imageView = mView.findViewById(R.id.frag_map_okla_litter);
                imageView.setImageResource(R.drawable.path_okla_littler_red);
                break;
            case 56:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla);
                imageView.setImageResource(R.drawable.path_dallas_okla_red);
                break;
            case 57:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla2);
                imageView.setImageResource(R.drawable.path_dallas_okla_red); //todo duplicate?
                break;
            case 58:
                imageView = mView.findViewById(R.id.frag_map_dallas_litter);
                imageView.setImageResource(R.drawable.path_dalla_littler_red);
                break;
            case 59:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst);
                imageView.setImageResource(R.drawable.path_dallas_houst_red);
                break;
            case 60:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst2);
                imageView.setImageResource(R.drawable.path_dallas_houst2_red);
                break;
            case 61:
                imageView = mView.findViewById(R.id.frag_map_newo_houst);
                imageView.setImageResource(R.drawable.path_newo_houst_red);
                break;
            case 62:
                imageView = mView.findViewById(R.id.frag_map_sault_montr);
                imageView.setImageResource(R.drawable.path_sault_montr_red);
                break;
            case 63:
                imageView = mView.findViewById(R.id.frag_map_sault_toron);
                imageView.setImageResource(R.drawable.path_sault_toron_red);
                break;
            case 64:
                imageView = mView.findViewById(R.id.frag_map_chica_toron);
                imageView.setImageResource(R.drawable.path_chica_toron_red);
                break;
            case 65:
                imageView = mView.findViewById(R.id.frag_map_chica_pits);
                imageView.setImageResource(R.drawable.path_chica_pits_red);
                break;
            case 66:
                imageView = mView.findViewById(R.id.frag_map_chica_pits2);
                imageView.setImageResource(R.drawable.path_chica_pits2_red);
                break;
            case 67:
                imageView = mView.findViewById(R.id.frag_map_chica_saint);
                imageView.setImageResource(R.drawable.path_chicago_saint_red);
                break;
            case 68:
                imageView = mView.findViewById(R.id.frag_map_chica_saint2);
                imageView.setImageResource(R.drawable.path_chicago_saint2_red);
                break;
            case 69:
                imageView = mView.findViewById(R.id.frag_map_pits_saint);
                imageView.setImageResource(R.drawable.path_pits_saint_red);
                break;
            case 70:
                imageView = mView.findViewById(R.id.frag_map_nash_saint);
                imageView.setImageResource(R.drawable.path_nash_saint_red);
                break;
            case 71:
                imageView = mView.findViewById(R.id.frag_map_litter_saint);
                imageView.setImageResource(R.drawable.path_littler_saint_red);
                break;
            case 72:
                imageView = mView.findViewById(R.id.frag_map_litter_nash);
                imageView.setImageResource(R.drawable.path_littler_nash_red);
                break;
            case 73:
                imageView = mView.findViewById(R.id.frag_map_newo_litter);
                imageView.setImageResource(R.drawable.path_newo_littler_red);
                break;
            case 74:
                imageView = mView.findViewById(R.id.frag_map_atla_newo);
                imageView.setImageResource(R.drawable.path_atla_newo_red);
                break;
            case 75:
                imageView = mView.findViewById(R.id.frag_map_atla_newo2);
                imageView.setImageResource(R.drawable.path_atla_newo2_red);
                break;
            case 76:
                imageView = mView.findViewById(R.id.frag_map_miami_newo);
                imageView.setImageResource(R.drawable.path_miami_newo_red);
                break;
            case 77:
                imageView = mView.findViewById(R.id.frag_map_montr_toron);
                imageView.setImageResource(R.drawable.path_motr_toron_red);
                break;
            case 78:
                imageView = mView.findViewById(R.id.frag_map_toron_pits);
                imageView.setImageResource(R.drawable.path_toron_pits_red);
                break;
            case 79:
                imageView = mView.findViewById(R.id.frag_map_newy_pits);
                imageView.setImageResource(R.drawable.path_newy_pits_red);
                break;
            case 80:
                imageView = mView.findViewById(R.id.frag_map_newy_pits2);
                imageView.setImageResource(R.drawable.path_newy_pits2_red);
                break;
            case 81:
                imageView = mView.findViewById(R.id.frag_map_pits_wash);
                imageView.setImageResource(R.drawable.path_pits_wash_red);
                break;
            case 82:
                imageView = mView.findViewById(R.id.frag_map_pits_raleig);
                imageView.setImageResource(R.drawable.path_pits_raleig_red);
                break;
            case 83:
                imageView = mView.findViewById(R.id.frag_map_nash_pits);
                imageView.setImageResource(R.drawable.path_nash_pits_red);
                break;
            case 84:
                imageView = mView.findViewById(R.id.frag_map_nash_raleig);
                imageView.setImageResource(R.drawable.path_nash_raleig_red);
                break;
            case 85:
                imageView = mView.findViewById(R.id.frag_map_atla_nash);
                imageView.setImageResource(R.drawable.path_atla_nashv_red);
                break;
            case 86:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig);
                imageView.setImageResource(R.drawable.path_atla_raleig_red);
                break;
            case 87:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig2);
                imageView.setImageResource(R.drawable.path_atla_raleig2_red);
                break;
            case 88:
                imageView = mView.findViewById(R.id.frag_map_atla_charl);
                imageView.setImageResource(R.drawable.path_atla_charles_red);
                break;
            case 89:
                imageView = mView.findViewById(R.id.frag_map_atla_miami);
                imageView.setImageResource(R.drawable.path_atla_miami_red);
                break;
            case 90:
                imageView = mView.findViewById(R.id.frag_map_bost_montr);
                imageView.setImageResource(R.drawable.path_bost_motr_red);
                break;
            case 91:
                imageView = mView.findViewById(R.id.frag_map_bost_montr2);
                imageView.setImageResource(R.drawable.path_bost_motr2_red);
                break;
            case 92:
                imageView = mView.findViewById(R.id.frag_map_montr_newy);
                imageView.setImageResource(R.drawable.path_motr_newy_red);
                break;
            case 93:
                imageView = mView.findViewById(R.id.frag_map_boston_newy);
                imageView.setImageResource(R.drawable.path_bost_newy_red);
                break;
            case 94:
                imageView = mView.findViewById(R.id.frag_map_boston_newy2);
                imageView.setImageResource(R.drawable.path_bost_newy2_red);
                break;
            case 95:
                imageView = mView.findViewById(R.id.frag_map_newy_wash);
                imageView.setImageResource(R.drawable.path_newy_wash_red);
                break;
            case 96:
                imageView = mView.findViewById(R.id.frag_map_newy_wash2);
                imageView.setImageResource(R.drawable.path_newy_wash2_red);
                break;
            case 97:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig);
                imageView.setImageResource(R.drawable.path_wash_raleig_red);
                break;
            case 98:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig2);
                imageView.setImageResource(R.drawable.path_wash_raleig2_red);
                break;
            case 99:
                imageView = mView.findViewById(R.id.frag_map_charl_raleig);
                imageView.setImageResource(R.drawable.path_charl_raleig_red);
                break;

        }

        imageView.setVisibility(View.VISIBLE);
    }

    //blue
    public void displayImageBlue(Route route){

        System.out.println("Start City: " + route.getStartCity() + "END City : " + route.getEndCity() + "\n\n\n\n");
        switch(route.getId()) {
            case 0:
                imageView = mView.findViewById(R.id.frag_map_miami_charl);
                imageView.setImageResource(R.drawable.path_miami_charles_blue);
                break;
            case 1:
                imageView = mView.findViewById(R.id.frag_map_van_seat);
                imageView.setImageResource(R.drawable.path_van_seat_blue);
                break;
            case 2:
                imageView = mView.findViewById(R.id.frag_map_van_seat2);
                imageView.setImageResource(R.drawable.path_van_seat2_blue);
                break;
            case 3:
                imageView = mView.findViewById(R.id.frag_map_van_cal);
                imageView.setImageResource(R.drawable.path_van_calg_blue);
                break;
            case 4:
                imageView = mView.findViewById(R.id.frag_map_seat_port);
                imageView.setImageResource(R.drawable.path_seat_port_blue);
                break;
            case 5:
                imageView = mView.findViewById(R.id.frag_map_seat_port2);
                imageView.setImageResource(R.drawable.path_seat_port2_blue);
                break;

            case 6:
                imageView = mView.findViewById(R.id.frag_map_seat_cal);
                imageView.setImageResource(R.drawable.path_seat_calg_blue);
                break;
            case 7:
                imageView = mView.findViewById(R.id.frag_map_seat_helen);
                imageView.setImageResource(R.drawable.path_seatt_helen_blue);
                break;

            case 8:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra);
                imageView.setImageResource(R.drawable.path_sanfra_port_blue);
                break;
            case 9:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra2);
                imageView.setImageResource(R.drawable.path_sanfra_port2_blue);
                break;
            case 10:
                imageView = mView.findViewById(R.id.frag_map_port_salt);
                imageView.setImageResource(R.drawable.path_port_salt_blue);
                break;
            case 11:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt);
                imageView.setImageResource(R.drawable.path_sanfra_salt_blue);
                break;
            case 12:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt2);
                imageView.setImageResource(R.drawable.path_sanfra_salt2_blue);
                break;
            case 13:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan);
                imageView.setImageResource(R.drawable.path_sanfra_losang_blue);
                break;
            case 14:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan2);
                imageView.setImageResource(R.drawable.path_sanfra_losang2_blue);
                break;
            case 15:
                imageView = mView.findViewById(R.id.frag_map_losan_lasveg);
                imageView.setImageResource(R.drawable.path_losan_lasveg_blue);
                break;
            case 16:
                imageView = mView.findViewById(R.id.frag_map_losan_phoenix);
                imageView.setImageResource(R.drawable.path_loans_phoenix_blue);
                break;
            case 17:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_blue);
                break;
            case 18:
                imageView = mView.findViewById(R.id.frag_map_cal_wini);
                imageView.setImageResource(R.drawable.path_calg_winnni_blue);
                break;
            case 19:
                imageView = mView.findViewById(R.id.frag_map_cal_helen);
                imageView.setImageResource(R.drawable.path_calg_helen_blue);
                break;
            case 20:
                imageView = mView.findViewById(R.id.frag_map_helen_wini);
                imageView.setImageResource(R.drawable.path_helen_winnni_blue);
                break;
            case 21:
                imageView = mView.findViewById(R.id.frag_map_helen_dulut);
                imageView.setImageResource(R.drawable.path_helen_dulut_blue);
                break;
            case 22:
                imageView = mView.findViewById(R.id.frag_map_helen_oma);
                imageView.setImageResource(R.drawable.path_helen_oma_blue);
                break;
            case 23:
                imageView = mView.findViewById(R.id.frag_map_denver_helen);
                imageView.setImageResource(R.drawable.path_denver_helen_blue);
                break;
            case 24:
                imageView = mView.findViewById(R.id.frag_map_salt_helen);
                imageView.setImageResource(R.drawable.path_salt_helen_blue);
                break;
            case 25:
                imageView = mView.findViewById(R.id.frag_map_salt_denver);
                imageView.setImageResource(R.drawable.path_salt_denver_blue);
                break;
            case 26:
                imageView = mView.findViewById(R.id.frag_map_salt_denver2);
                imageView.setImageResource(R.drawable.path_salt_denver2_blue);
                break;
            case 27:
                imageView = mView.findViewById(R.id.frag_map_lasveg_salt);
                imageView.setImageResource(R.drawable.path_lasveg_salt_blue);
                break;
            case 28:
                imageView = mView.findViewById(R.id.frag_map_phoenix_denver);
                imageView.setImageResource(R.drawable.path_phoenix_denver_blue);
                break;
            case 29:
                imageView = mView.findViewById(R.id.frag_map_phoenix_santaf);
                imageView.setImageResource(R.drawable.path_phoenix_santaf_blue);
                break;
            case 30:
                imageView = mView.findViewById(R.id.frag_map_phoenix_elpas);
                imageView.setImageResource(R.drawable.path_phoenix_elpaso_blue);
                break;
            case 31:
                imageView = mView.findViewById(R.id.frag_map_denver_oma);
                imageView.setImageResource(R.drawable.path_denver_oma_blue);
                break;
            case 32:
                imageView = mView.findViewById(R.id.frag_map_denver_kan);
                imageView.setImageResource(R.drawable.path_denver_kan_blue);
                break;
            case 33:
                imageView = mView.findViewById(R.id.frag_map_denver_kan2);
                imageView.setImageResource(R.drawable.path_denver_kan2_blue);
                break;
            case 34:
                imageView = mView.findViewById(R.id.frag_map_denver_okla);
                imageView.setImageResource(R.drawable.path_denver_okla_blue);
                break;
            case 35:
                imageView = mView.findViewById(R.id.frag_map_santaf_denver);
                imageView.setImageResource(R.drawable.path_santaf_denver_blue);
                break;
            case 36:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_blue);
                break;
            case 37:
                imageView = mView.findViewById(R.id.frag_map_elpaso_santaf);
                imageView.setImageResource(R.drawable.path_elpaso_santaf_blue);
                break;
            case 38:
                imageView = mView.findViewById(R.id.frag_map_elpaso_okla);
                imageView.setImageResource(R.drawable.path_elpaso_okla_blue);
                break;
            case 39:
                imageView = mView.findViewById(R.id.frag_map_elpaso_dallas);
                imageView.setImageResource(R.drawable.path_elpaso_dalla_blue);
                break;
            case 40:
                imageView = mView.findViewById(R.id.frag_map_elpaso_houst);
                imageView.setImageResource(R.drawable.path_elpaso_houst_blue);
                break;
            case 41:
                imageView = mView.findViewById(R.id.frag_map_winni_sault);
                imageView.setImageResource(R.drawable.path_winnni_sault_blue);
                break;
            case 42:
                imageView = mView.findViewById(R.id.frag_map_wini_dulut);
                imageView.setImageResource(R.drawable.path_winnni_dulut_blue);
                break;
            case 43:
                imageView = mView.findViewById(R.id.frag_map_dulut_sault);
                imageView.setImageResource(R.drawable.path_dulut_sault_blue);
                break;
            case 44:
                imageView = mView.findViewById(R.id.frag_map_dulut_toron);
                imageView.setImageResource(R.drawable.path_dulut_toron_blue);
                break;
            case 45:
                imageView = mView.findViewById(R.id.frag_map_chica_dulut);
                imageView.setImageResource(R.drawable.path_chica_dulut_blue);
                break;
            case 46:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma);
                imageView.setImageResource(R.drawable.path_dulut_oma_blue);
                break;
            case 47:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma2);
                imageView.setImageResource(R.drawable.path_dulut_oma2_blue);
                break;
            case 48:
                imageView = mView.findViewById(R.id.frag_map_chica_oma);
                imageView.setImageResource(R.drawable.path_chica_oma_blue);
                break;
            case 49:
                imageView = mView.findViewById(R.id.frag_map_oma_kan);
                imageView.setImageResource(R.drawable.path_oma_kan_blue);
                break;
            case 50:
                imageView = mView.findViewById(R.id.frag_map_oma_kan2);
                imageView.setImageResource(R.drawable.path_oma_kan2_blue);
                break;
            case 51:
                imageView = mView.findViewById(R.id.frag_map_kan_saint);
                imageView.setImageResource(R.drawable.path_kan_saint_blue);
                break;
            case 52:
                imageView = mView.findViewById(R.id.frag_map_kan_saint2);
                imageView.setImageResource(R.drawable.path_kan_saint2_blue);
                break;
            case 53:
                imageView = mView.findViewById(R.id.frag_map_okla_kan);
                imageView.setImageResource(R.drawable.path_okla_kan_blue);
                break;
            case 54:
                imageView = mView.findViewById(R.id.frag_map_okla_kan2);
                imageView.setImageResource(R.drawable.path_okla_kan2_blue);
                break;
            case 55:
                imageView = mView.findViewById(R.id.frag_map_okla_litter);
                imageView.setImageResource(R.drawable.path_okla_littler_blue);
                break;
            case 56:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla);
                imageView.setImageResource(R.drawable.path_dallas_okla_blue);
                break;
            case 57:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla2);
                imageView.setImageResource(R.drawable.path_dallas_okla_blue); //todo duplicate??
                break;
            case 58:
                imageView = mView.findViewById(R.id.frag_map_dallas_litter);
                imageView.setImageResource(R.drawable.path_dalla_littler_blue);
                break;
            case 59:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst);
                imageView.setImageResource(R.drawable.path_dallas_houst_blue);
                break;
            case 60:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst2);
                imageView.setImageResource(R.drawable.path_dallas_houst2_blue);
                break;
            case 61:
                imageView = mView.findViewById(R.id.frag_map_newo_houst);
                imageView.setImageResource(R.drawable.path_newo_houst_blue);
                break;
            case 62:
                imageView = mView.findViewById(R.id.frag_map_sault_montr);
                imageView.setImageResource(R.drawable.path_sault_montr_blue);
                break;
            case 63:
                imageView = mView.findViewById(R.id.frag_map_sault_toron);
                imageView.setImageResource(R.drawable.path_sault_toron_blue);
                break;
            case 64:
                imageView = mView.findViewById(R.id.frag_map_chica_toron);
                imageView.setImageResource(R.drawable.path_chica_toron_blue);
                break;
            case 65:
                imageView = mView.findViewById(R.id.frag_map_chica_pits);
                imageView.setImageResource(R.drawable.path_chica_pits_blue);
                break;
            case 66:
                imageView = mView.findViewById(R.id.frag_map_chica_pits2);
                imageView.setImageResource(R.drawable.path_chica_pits2_blue);
                break;
            case 67:
                imageView = mView.findViewById(R.id.frag_map_chica_saint);
                imageView.setImageResource(R.drawable.path_chicago_saint_blue);
                break;
            case 68:
                imageView = mView.findViewById(R.id.frag_map_chica_saint2);
                imageView.setImageResource(R.drawable.path_chicago_saint2_blue);
                break;
            case 69:
                imageView = mView.findViewById(R.id.frag_map_pits_saint);
                imageView.setImageResource(R.drawable.path_pits_saint_blue);
                break;
            case 70:
                imageView = mView.findViewById(R.id.frag_map_nash_saint);
                imageView.setImageResource(R.drawable.path_nash_saint_blue);
                break;
            case 71:
                imageView = mView.findViewById(R.id.frag_map_litter_saint);
                imageView.setImageResource(R.drawable.path_littler_saint_blue);
                break;
            case 72:
                imageView = mView.findViewById(R.id.frag_map_litter_nash);
                imageView.setImageResource(R.drawable.path_littler_nash_blue);
                break;
            case 73:
                imageView = mView.findViewById(R.id.frag_map_newo_litter);
                imageView.setImageResource(R.drawable.path_newo_littler_blue);
                break;
            case 74:
                imageView = mView.findViewById(R.id.frag_map_atla_newo);
                imageView.setImageResource(R.drawable.path_atla_newo_blue);
                break;
            case 75:
                imageView = mView.findViewById(R.id.frag_map_atla_newo2);
                imageView.setImageResource(R.drawable.path_atla_newo2_blue);
                break;
            case 76:
                imageView = mView.findViewById(R.id.frag_map_miami_newo);
                imageView.setImageResource(R.drawable.path_miami_newo_blue);
                break;
            case 77:
                imageView = mView.findViewById(R.id.frag_map_montr_toron);
                imageView.setImageResource(R.drawable.path_motr_toron_blue);
                break;
            case 78:
                imageView = mView.findViewById(R.id.frag_map_toron_pits);
                imageView.setImageResource(R.drawable.path_toron_pits_blue);
                break;
            case 79:
                imageView = mView.findViewById(R.id.frag_map_newy_pits);
                imageView.setImageResource(R.drawable.path_newy_pits_blue);
                break;
            case 80:
                imageView = mView.findViewById(R.id.frag_map_newy_pits2);
                imageView.setImageResource(R.drawable.path_newy_pits2_blue);
                break;
            case 81:
                imageView = mView.findViewById(R.id.frag_map_pits_wash);
                imageView.setImageResource(R.drawable.path_pits_wash_blue);
                break;
            case 82:
                imageView = mView.findViewById(R.id.frag_map_pits_raleig);
                imageView.setImageResource(R.drawable.path_pits_raleig_blue);
                break;
            case 83:
                imageView = mView.findViewById(R.id.frag_map_nash_pits);
                imageView.setImageResource(R.drawable.path_nash_pits_blue);
                break;
            case 84:
                imageView = mView.findViewById(R.id.frag_map_nash_raleig);
                imageView.setImageResource(R.drawable.path_nash_raleig_blue);
                break;
            case 85:
                imageView = mView.findViewById(R.id.frag_map_atla_nash);
                imageView.setImageResource(R.drawable.path_atla_nashv_blue);
                break;
            case 86:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig);
                imageView.setImageResource(R.drawable.path_atla_raleig_blue);
                break;
            case 87:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig2);
                imageView.setImageResource(R.drawable.path_atla_raleig2_blue);
                break;
            case 88:
                imageView = mView.findViewById(R.id.frag_map_atla_charl);
                imageView.setImageResource(R.drawable.path_atla_charles_blue);
                break;
            case 89:
                imageView = mView.findViewById(R.id.frag_map_atla_miami);
                imageView.setImageResource(R.drawable.path_atla_miami_blue);
                break;
            case 90:
                imageView = mView.findViewById(R.id.frag_map_bost_montr);
                imageView.setImageResource(R.drawable.path_bost_motr_blue);
                break;
            case 91:
                imageView = mView.findViewById(R.id.frag_map_bost_montr2);
                imageView.setImageResource(R.drawable.path_bost_motr2_blue);
                break;
            case 92:
                imageView = mView.findViewById(R.id.frag_map_montr_newy);
                imageView.setImageResource(R.drawable.path_motr_newy_blue);
                break;
            case 93:
                imageView = mView.findViewById(R.id.frag_map_boston_newy);
                imageView.setImageResource(R.drawable.path_bost_newy_blue);
                break;
            case 94:
                imageView = mView.findViewById(R.id.frag_map_boston_newy2);
                imageView.setImageResource(R.drawable.path_bost_newy2_blue);
                break;
            case 95:
                imageView = mView.findViewById(R.id.frag_map_newy_wash);
                imageView.setImageResource(R.drawable.path_newy_wash_blue);
                break;
            case 96:
                imageView = mView.findViewById(R.id.frag_map_newy_wash2);
                imageView.setImageResource(R.drawable.path_newy_wash2_blue);
                break;
            case 97:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig);
                imageView.setImageResource(R.drawable.path_wash_raleig_blue);
                break;
            case 98:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig2);
                imageView.setImageResource(R.drawable.path_wash_raleig2_blue);
                break;
            case 99:
                imageView = mView.findViewById(R.id.frag_map_charl_raleig);
                imageView.setImageResource(R.drawable.path_charl_raleig_blue);
                break;

        }

        imageView.setVisibility(View.VISIBLE);
    }

    //yellow
    public void displayImageYellow(Route route){

        System.out.println("Start City: " + route.getStartCity() + "END City : " + route.getEndCity() + "\n\n\n\n");
        switch(route.getId()) {
            case 0:
                imageView = mView.findViewById(R.id.frag_map_miami_charl);
                imageView.setImageResource(R.drawable.path_miami_charles_yellow);
                break;
            case 1:
                imageView = mView.findViewById(R.id.frag_map_van_seat);
                imageView.setImageResource(R.drawable.path_van_seat_yellow);
                break;
            case 2:
                imageView = mView.findViewById(R.id.frag_map_van_seat2);
                imageView.setImageResource(R.drawable.path_van_seat2_yellow);
                break;
            case 3:
                imageView = mView.findViewById(R.id.frag_map_van_cal);
                imageView.setImageResource(R.drawable.path_van_calg_yellow);
                break;
            case 4:
                imageView = mView.findViewById(R.id.frag_map_seat_port);
                imageView.setImageResource(R.drawable.path_seat_port_yellow);
                break;
            case 5:
                imageView = mView.findViewById(R.id.frag_map_seat_port2);
                imageView.setImageResource(R.drawable.path_seat_port2_yellow);
                break;

            case 6:
                imageView = mView.findViewById(R.id.frag_map_seat_cal);
                imageView.setImageResource(R.drawable.path_seat_calg_yellow);
                break;
            case 7:
                imageView = mView.findViewById(R.id.frag_map_seat_helen);
                imageView.setImageResource(R.drawable.path_seatt_helen_yellow);
                break;

            case 8:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra);
                imageView.setImageResource(R.drawable.path_sanfra_port_yellow);
                break;
            case 9:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra2);
                imageView.setImageResource(R.drawable.path_sanfra_port2_yellow);
                break;
            case 10:
                imageView = mView.findViewById(R.id.frag_map_port_salt);
                imageView.setImageResource(R.drawable.path_port_salt_yellow);
                break;
            case 11:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt);
                imageView.setImageResource(R.drawable.path_sanfra_salt_yellow);
                break;
            case 12:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt2);
                imageView.setImageResource(R.drawable.path_sanfra_salt2_yellow);
                break;
            case 13:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan);
                imageView.setImageResource(R.drawable.path_sanfra_losang_yellow);
                break;
            case 14:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan2);
                imageView.setImageResource(R.drawable.path_sanfra_losang2_yellow);
                break;
            case 15:
                imageView = mView.findViewById(R.id.frag_map_losan_lasveg);
                imageView.setImageResource(R.drawable.path_losan_lasveg_yellow);
                break;
            case 16:
                imageView = mView.findViewById(R.id.frag_map_losan_phoenix);
                imageView.setImageResource(R.drawable.path_loans_phoenix_yellow);
                break;
            case 17:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_yellow);
                break;
            case 18:
                imageView = mView.findViewById(R.id.frag_map_cal_wini);
                imageView.setImageResource(R.drawable.path_calg_winnni_yellow);
                break;
            case 19:
                imageView = mView.findViewById(R.id.frag_map_cal_helen);
                imageView.setImageResource(R.drawable.path_calg_helen_yellow);
                break;
            case 20:
                imageView = mView.findViewById(R.id.frag_map_helen_wini);
                imageView.setImageResource(R.drawable.path_helen_winnni_yellow);
                break;
            case 21:
                imageView = mView.findViewById(R.id.frag_map_helen_dulut);
                imageView.setImageResource(R.drawable.path_helen_dulut_yellow);
                break;
            case 22:
                imageView = mView.findViewById(R.id.frag_map_helen_oma);
                imageView.setImageResource(R.drawable.path_helen_oma_yellow);
                break;
            case 23:
                imageView = mView.findViewById(R.id.frag_map_denver_helen);
                imageView.setImageResource(R.drawable.path_denver_helen_yellow);
                break;
            case 24:
                imageView = mView.findViewById(R.id.frag_map_salt_helen);
                imageView.setImageResource(R.drawable.path_salt_helen_yellow);
                break;
            case 25:
                imageView = mView.findViewById(R.id.frag_map_salt_denver);
                imageView.setImageResource(R.drawable.path_salt_denver_yellow);
                break;
            case 26:
                imageView = mView.findViewById(R.id.frag_map_salt_denver2);
                imageView.setImageResource(R.drawable.path_salt_denver2_yellow);
                break;
            case 27:
                imageView = mView.findViewById(R.id.frag_map_lasveg_salt);
                imageView.setImageResource(R.drawable.path_lasveg_salt_yellow);
                break;
            case 28:
                imageView = mView.findViewById(R.id.frag_map_phoenix_denver);
                imageView.setImageResource(R.drawable.path_phoenix_denver_yellow);
                break;
            case 29:
                imageView = mView.findViewById(R.id.frag_map_phoenix_santaf);
                imageView.setImageResource(R.drawable.path_phoenix_santaf_yellow);
                break;
            case 30:
                imageView = mView.findViewById(R.id.frag_map_phoenix_elpas);
                imageView.setImageResource(R.drawable.path_phoenix_elpaso_yellow);
                break;
            case 31:
                imageView = mView.findViewById(R.id.frag_map_denver_oma);
                imageView.setImageResource(R.drawable.path_denver_oma_yellow);
                break;
            case 32:
                imageView = mView.findViewById(R.id.frag_map_denver_kan);
                imageView.setImageResource(R.drawable.path_denver_kan_yellow);
                break;
            case 33:
                imageView = mView.findViewById(R.id.frag_map_denver_kan2);
                imageView.setImageResource(R.drawable.path_denver_kan2_yellow);
                break;
            case 34:
                imageView = mView.findViewById(R.id.frag_map_denver_okla);
                imageView.setImageResource(R.drawable.path_denver_okla_yellow);
                break;
            case 35:
                imageView = mView.findViewById(R.id.frag_map_santaf_denver);
                imageView.setImageResource(R.drawable.path_santaf_denver_yellow);
                break;
            case 36:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_yellow);
                break;
            case 37:
                imageView = mView.findViewById(R.id.frag_map_elpaso_santaf);
                imageView.setImageResource(R.drawable.path_elpaso_santaf_yellow);
                break;
            case 38:
                imageView = mView.findViewById(R.id.frag_map_elpaso_okla);
                imageView.setImageResource(R.drawable.path_elpaso_okla_yellow);
                break;
            case 39:
                imageView = mView.findViewById(R.id.frag_map_elpaso_dallas);
                imageView.setImageResource(R.drawable.path_elpaso_dalla_yellow);
                break;
            case 40:
                imageView = mView.findViewById(R.id.frag_map_elpaso_houst);
                imageView.setImageResource(R.drawable.path_elpaso_houst_yellow);
                break;
            case 41:
                imageView = mView.findViewById(R.id.frag_map_winni_sault);
                imageView.setImageResource(R.drawable.path_winnni_sault_yellow);
                break;
            case 42:
                imageView = mView.findViewById(R.id.frag_map_wini_dulut);
                imageView.setImageResource(R.drawable.path_winnni_dulut_yellow);
                break;
            case 43:
                imageView = mView.findViewById(R.id.frag_map_dulut_sault);
                imageView.setImageResource(R.drawable.path_dulut_sault_yellow);
                break;
            case 44:
                imageView = mView.findViewById(R.id.frag_map_dulut_toron);
                imageView.setImageResource(R.drawable.path_dulut_toron_yellow);
                break;
            case 45:
                imageView = mView.findViewById(R.id.frag_map_chica_dulut);
                imageView.setImageResource(R.drawable.path_chica_dulut_yellow);
                break;
            case 46:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma);
                imageView.setImageResource(R.drawable.path_dulut_oma_yellow);
                break;
            case 47:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma2);
                imageView.setImageResource(R.drawable.path_dulut_oma2_yellow);
                break;
            case 48:
                imageView = mView.findViewById(R.id.frag_map_chica_oma);
                imageView.setImageResource(R.drawable.path_chica_oma_yellow);
                break;
            case 49:
                imageView = mView.findViewById(R.id.frag_map_oma_kan);
                imageView.setImageResource(R.drawable.path_oma_kan_yellow);
                break;
            case 50:
                imageView = mView.findViewById(R.id.frag_map_oma_kan2);
                imageView.setImageResource(R.drawable.path_oma_kan2_yellow);
                break;
            case 51:
                imageView = mView.findViewById(R.id.frag_map_kan_saint);
                imageView.setImageResource(R.drawable.path_kan_saint_yellow);
                break;
            case 52:
                imageView = mView.findViewById(R.id.frag_map_kan_saint2);
                imageView.setImageResource(R.drawable.path_kan_saint2_yellow);
                break;
            case 53:
                imageView = mView.findViewById(R.id.frag_map_okla_kan);
                imageView.setImageResource(R.drawable.path_okla_kan_yellow);
                break;
            case 54:
                imageView = mView.findViewById(R.id.frag_map_okla_kan2);
                imageView.setImageResource(R.drawable.path_okla_kan2_yellow);
                break;
            case 55:
                imageView = mView.findViewById(R.id.frag_map_okla_litter);
                imageView.setImageResource(R.drawable.path_okla_littler_yellow);
                break;
            case 56:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla);
                imageView.setImageResource(R.drawable.path_dallas_okla_yellow);
                break;
            case 57:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla2);
                imageView.setImageResource(R.drawable.path_dallas_okla_yellow); //todo duplicate?
                break;
            case 58:
                imageView = mView.findViewById(R.id.frag_map_dallas_litter);
                imageView.setImageResource(R.drawable.path_dalla_littler_yellow);
                break;
            case 59:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst);
                imageView.setImageResource(R.drawable.path_dallas_houst_yellow);
                break;
            case 60:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst2);
                imageView.setImageResource(R.drawable.path_dallas_houst2_yellow);
                break;
            case 61:
                imageView = mView.findViewById(R.id.frag_map_newo_houst);
                imageView.setImageResource(R.drawable.path_newo_houst_yellow);
                break;
            case 62:
                imageView = mView.findViewById(R.id.frag_map_sault_montr);
                imageView.setImageResource(R.drawable.path_sault_montr_yellow);
                break;
            case 63:
                imageView = mView.findViewById(R.id.frag_map_sault_toron);
                imageView.setImageResource(R.drawable.path_sault_toron_yellow);
                break;
            case 64:
                imageView = mView.findViewById(R.id.frag_map_chica_toron);
                imageView.setImageResource(R.drawable.path_chica_toron_yellow);
                break;
            case 65:
                imageView = mView.findViewById(R.id.frag_map_chica_pits);
                imageView.setImageResource(R.drawable.path_chica_pits_yellow);
                break;
            case 66:
                imageView = mView.findViewById(R.id.frag_map_chica_pits2);
                imageView.setImageResource(R.drawable.path_chica_pits2_yellow);
                break;
            case 67:
                imageView = mView.findViewById(R.id.frag_map_chica_saint);
                imageView.setImageResource(R.drawable.path_chicago_saint_yellow);
                break;
            case 68:
                imageView = mView.findViewById(R.id.frag_map_chica_saint2);
                imageView.setImageResource(R.drawable.path_chicago_saint2_yellow);
                break;
            case 69:
                imageView = mView.findViewById(R.id.frag_map_pits_saint);
                imageView.setImageResource(R.drawable.path_pits_saint_yellow);
                break;
            case 70:
                imageView = mView.findViewById(R.id.frag_map_nash_saint);
                imageView.setImageResource(R.drawable.path_nash_saint_yellow);
                break;
            case 71:
                imageView = mView.findViewById(R.id.frag_map_litter_saint);
                imageView.setImageResource(R.drawable.path_littler_saint_yellow);
                break;
            case 72:
                imageView = mView.findViewById(R.id.frag_map_litter_nash);
                imageView.setImageResource(R.drawable.path_littler_nash_yellow);
                break;
            case 73:
                imageView = mView.findViewById(R.id.frag_map_newo_litter);
                imageView.setImageResource(R.drawable.path_newo_littler_yellow);
                break;
            case 74:
                imageView = mView.findViewById(R.id.frag_map_atla_newo);
                imageView.setImageResource(R.drawable.path_atla_newo_yellow);
                break;
            case 75:
                imageView = mView.findViewById(R.id.frag_map_atla_newo2);
                imageView.setImageResource(R.drawable.path_atla_newo2_yellow);
                break;
            case 76:
                imageView = mView.findViewById(R.id.frag_map_miami_newo);
                imageView.setImageResource(R.drawable.path_miami_newo_yellow);
                break;
            case 77:
                imageView = mView.findViewById(R.id.frag_map_montr_toron);
                imageView.setImageResource(R.drawable.path_motr_toron_yellow);
                break;
            case 78:
                imageView = mView.findViewById(R.id.frag_map_toron_pits);
                imageView.setImageResource(R.drawable.path_toron_pits_yellow);
                break;
            case 79:
                imageView = mView.findViewById(R.id.frag_map_newy_pits);
                imageView.setImageResource(R.drawable.path_newy_pits_yellow);
                break;
            case 80:
                imageView = mView.findViewById(R.id.frag_map_newy_pits2);
                imageView.setImageResource(R.drawable.path_newy_pits2_yellow);
                break;
            case 81:
                imageView = mView.findViewById(R.id.frag_map_pits_wash);
                imageView.setImageResource(R.drawable.path_pits_wash_yellow);
                break;
            case 82:
                imageView = mView.findViewById(R.id.frag_map_pits_raleig);
                imageView.setImageResource(R.drawable.path_pits_raleig_yellow);
                break;
            case 83:
                imageView = mView.findViewById(R.id.frag_map_nash_pits);
                imageView.setImageResource(R.drawable.path_nash_pits_yellow);
                break;
            case 84:
                imageView = mView.findViewById(R.id.frag_map_nash_raleig);
                imageView.setImageResource(R.drawable.path_nash_raleig_yellow);
                break;
            case 85:
                imageView = mView.findViewById(R.id.frag_map_atla_nash);
                imageView.setImageResource(R.drawable.path_atla_nashv_yellow);
                break;
            case 86:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig);
                imageView.setImageResource(R.drawable.path_atla_raleig_yellow);
                break;
            case 87:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig2);
                imageView.setImageResource(R.drawable.path_atla_raleig2_yellow);
                break;
            case 88:
                imageView = mView.findViewById(R.id.frag_map_atla_charl);
                imageView.setImageResource(R.drawable.path_atla_charles_yellow);
                break;
            case 89:
                imageView = mView.findViewById(R.id.frag_map_atla_miami);
                imageView.setImageResource(R.drawable.path_atla_miami_yellow);
                break;
            case 90:
                imageView = mView.findViewById(R.id.frag_map_bost_montr);
                imageView.setImageResource(R.drawable.path_bost_motr_yellow);
                break;
            case 91:
                imageView = mView.findViewById(R.id.frag_map_bost_montr2);
                imageView.setImageResource(R.drawable.path_bost_motr2_yellow);
                break;
            case 92:
                imageView = mView.findViewById(R.id.frag_map_montr_newy);
                imageView.setImageResource(R.drawable.path_motr_newy_yellow);
                break;
            case 93:
                imageView = mView.findViewById(R.id.frag_map_boston_newy);
                imageView.setImageResource(R.drawable.path_bost_newy_yellow);
                break;
            case 94:
                imageView = mView.findViewById(R.id.frag_map_boston_newy2);
                imageView.setImageResource(R.drawable.path_bost_newy2_yellow);
                break;
            case 95:
                imageView = mView.findViewById(R.id.frag_map_newy_wash);
                imageView.setImageResource(R.drawable.path_newy_wash_yellow);
                break;
            case 96:
                imageView = mView.findViewById(R.id.frag_map_newy_wash2);
                imageView.setImageResource(R.drawable.path_newy_wash2_yellow);
                break;
            case 97:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig);
                imageView.setImageResource(R.drawable.path_wash_raleig_yellow);
                break;
            case 98:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig2);
                imageView.setImageResource(R.drawable.path_wash_raleig2_yellow);
                break;
            case 99:
                imageView = mView.findViewById(R.id.frag_map_charl_raleig);
                imageView.setImageResource(R.drawable.path_charl_raleig_yellow);
                break;

        }

        imageView.setVisibility(View.VISIBLE);
    }

    //green
    public void displayImageGreen(Route route){

        System.out.println("Start City: " + route.getStartCity() + "END City : " + route.getEndCity() + "\n\n\n\n");
        switch(route.getId()) {
            case 0:
                imageView = mView.findViewById(R.id.frag_map_miami_charl);
                imageView.setImageResource(R.drawable.path_miami_charles_green);
                break;
            case 1:
                imageView = mView.findViewById(R.id.frag_map_van_seat);
                imageView.setImageResource(R.drawable.path_van_seat_green);
                break;
            case 2:
                imageView = mView.findViewById(R.id.frag_map_van_seat2);
                imageView.setImageResource(R.drawable.path_van_seat2_green);
                break;
            case 3:
                imageView = mView.findViewById(R.id.frag_map_van_cal);
                imageView.setImageResource(R.drawable.path_van_calg_green);
                break;
            case 4:
                imageView = mView.findViewById(R.id.frag_map_seat_port);
                imageView.setImageResource(R.drawable.path_seat_port_green);
                break;
            case 5:
                imageView = mView.findViewById(R.id.frag_map_seat_port2);
                imageView.setImageResource(R.drawable.path_seat_port2_green);
                break;

            case 6:
                imageView = mView.findViewById(R.id.frag_map_seat_cal);
                imageView.setImageResource(R.drawable.path_seat_calg_green);
                break;
            case 7:
                imageView = mView.findViewById(R.id.frag_map_seat_helen);
                imageView.setImageResource(R.drawable.path_seatt_helen_green);
                break;

            case 8:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra);
                imageView.setImageResource(R.drawable.path_sanfra_port_green);
                break;
            case 9:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra2);
                imageView.setImageResource(R.drawable.path_sanfra_port2_green);
                break;
            case 10:
                imageView = mView.findViewById(R.id.frag_map_port_salt);
                imageView.setImageResource(R.drawable.path_port_salt_green);
                break;
            case 11:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt);
                imageView.setImageResource(R.drawable.path_sanfra_salt_green);
                break;
            case 12:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt2);
                imageView.setImageResource(R.drawable.path_sanfra_salt2_green);
                break;
            case 13:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan);
                imageView.setImageResource(R.drawable.path_sanfra_losang_green);
                break;
            case 14:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan2);
                imageView.setImageResource(R.drawable.path_sanfra_losang2_green);
                break;
            case 15:
                imageView = mView.findViewById(R.id.frag_map_losan_lasveg);
                imageView.setImageResource(R.drawable.path_losan_lasveg_green);
                break;
            case 16:
                imageView = mView.findViewById(R.id.frag_map_losan_phoenix);
                imageView.setImageResource(R.drawable.path_loans_phoenix_green);
                break;
            case 17:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_green);
                break;
            case 18:
                imageView = mView.findViewById(R.id.frag_map_cal_wini);
                imageView.setImageResource(R.drawable.path_calg_winnni_green);
                break;
            case 19:
                imageView = mView.findViewById(R.id.frag_map_cal_helen);
                imageView.setImageResource(R.drawable.path_calg_helen_green);
                break;
            case 20:
                imageView = mView.findViewById(R.id.frag_map_helen_wini);
                imageView.setImageResource(R.drawable.path_helen_winnni_green);
                break;
            case 21:
                imageView = mView.findViewById(R.id.frag_map_helen_dulut);
                imageView.setImageResource(R.drawable.path_helen_dulut_green);
                break;
            case 22:
                imageView = mView.findViewById(R.id.frag_map_helen_oma);
                imageView.setImageResource(R.drawable.path_helen_oma_green);
                break;
            case 23:
                imageView = mView.findViewById(R.id.frag_map_denver_helen);
                imageView.setImageResource(R.drawable.path_denver_helen_green);
                break;
            case 24:
                imageView = mView.findViewById(R.id.frag_map_salt_helen);
                imageView.setImageResource(R.drawable.path_salt_helen_green);
                break;
            case 25:
                imageView = mView.findViewById(R.id.frag_map_salt_denver);
                imageView.setImageResource(R.drawable.path_salt_denver_green);
                break;
            case 26:
                imageView = mView.findViewById(R.id.frag_map_salt_denver2);
                imageView.setImageResource(R.drawable.path_salt_denver2_green);
                break;
            case 27:
                imageView = mView.findViewById(R.id.frag_map_lasveg_salt);
                imageView.setImageResource(R.drawable.path_lasveg_salt_green);
                break;
            case 28:
                imageView = mView.findViewById(R.id.frag_map_phoenix_denver);
                imageView.setImageResource(R.drawable.path_phoenix_denver_green);
                break;
            case 29:
                imageView = mView.findViewById(R.id.frag_map_phoenix_santaf);
                imageView.setImageResource(R.drawable.path_phoenix_santaf_green);
                break;
            case 30:
                imageView = mView.findViewById(R.id.frag_map_phoenix_elpas);
                imageView.setImageResource(R.drawable.path_phoenix_elpaso_green);
                break;
            case 31:
                imageView = mView.findViewById(R.id.frag_map_denver_oma);
                imageView.setImageResource(R.drawable.path_denver_oma_green);
                break;
            case 32:
                imageView = mView.findViewById(R.id.frag_map_denver_kan);
                imageView.setImageResource(R.drawable.path_denver_kan_green);
                break;
            case 33:
                imageView = mView.findViewById(R.id.frag_map_denver_kan2);
                imageView.setImageResource(R.drawable.path_denver_kan2_green);
                break;
            case 34:
                imageView = mView.findViewById(R.id.frag_map_denver_okla);
                imageView.setImageResource(R.drawable.path_denver_okla_green);
                break;
            case 35:
                imageView = mView.findViewById(R.id.frag_map_santaf_denver);
                imageView.setImageResource(R.drawable.path_santaf_denver_green);
                break;
            case 36:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_green);
                break;
            case 37:
                imageView = mView.findViewById(R.id.frag_map_elpaso_santaf);
                imageView.setImageResource(R.drawable.path_elpaso_santaf_green);
                break;
            case 38:
                imageView = mView.findViewById(R.id.frag_map_elpaso_okla);
                imageView.setImageResource(R.drawable.path_elpaso_okla_green);
                break;
            case 39:
                imageView = mView.findViewById(R.id.frag_map_elpaso_dallas);
                imageView.setImageResource(R.drawable.path_elpaso_dalla_green);
                break;
            case 40:
                imageView = mView.findViewById(R.id.frag_map_elpaso_houst);
                imageView.setImageResource(R.drawable.path_elpaso_houst_green);
                break;
            case 41:
                imageView = mView.findViewById(R.id.frag_map_winni_sault);
                imageView.setImageResource(R.drawable.path_winnni_sault_green);
                break;
            case 42:
                imageView = mView.findViewById(R.id.frag_map_wini_dulut);
                imageView.setImageResource(R.drawable.path_winnni_dulut_green);
                break;
            case 43:
                imageView = mView.findViewById(R.id.frag_map_dulut_sault);
                imageView.setImageResource(R.drawable.path_dulut_sault_green);
                break;
            case 44:
                imageView = mView.findViewById(R.id.frag_map_dulut_toron);
                imageView.setImageResource(R.drawable.path_dulut_toron_green);
                break;
            case 45:
                imageView = mView.findViewById(R.id.frag_map_chica_dulut);
                imageView.setImageResource(R.drawable.path_chica_dulut_green);
                break;
            case 46:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma);
                imageView.setImageResource(R.drawable.path_dulut_oma_green);
                break;
            case 47:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma2);
                imageView.setImageResource(R.drawable.path_dulut_oma2_green);
                break;
            case 48:
                imageView = mView.findViewById(R.id.frag_map_chica_oma);
                imageView.setImageResource(R.drawable.path_chica_oma_green);
                break;
            case 49:
                imageView = mView.findViewById(R.id.frag_map_oma_kan);
                imageView.setImageResource(R.drawable.path_oma_kan_green);
                break;
            case 50:
                imageView = mView.findViewById(R.id.frag_map_oma_kan2);
                imageView.setImageResource(R.drawable.path_oma_kan2_green);
                break;
            case 51:
                imageView = mView.findViewById(R.id.frag_map_kan_saint);
                imageView.setImageResource(R.drawable.path_kan_saint_green);
                break;
            case 52:
                imageView = mView.findViewById(R.id.frag_map_kan_saint2);
                imageView.setImageResource(R.drawable.path_kan_saint2_green);
                break;
            case 53:
                imageView = mView.findViewById(R.id.frag_map_okla_kan);
                imageView.setImageResource(R.drawable.path_okla_kan_green);
                break;
            case 54:
                imageView = mView.findViewById(R.id.frag_map_okla_kan2);
                imageView.setImageResource(R.drawable.path_okla_kan2_green);
                break;
            case 55:
                imageView = mView.findViewById(R.id.frag_map_okla_litter);
                imageView.setImageResource(R.drawable.path_okla_littler_green);
                break;
            case 56:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla);
                imageView.setImageResource(R.drawable.path_dallas_okla_green);
                break;
            case 57:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla2);
                imageView.setImageResource(R.drawable.path_dallas_okla_green); //todo duplicate?
                break;
            case 58:
                imageView = mView.findViewById(R.id.frag_map_dallas_litter);
                imageView.setImageResource(R.drawable.path_dalla_littler_green);
                break;
            case 59:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst);
                imageView.setImageResource(R.drawable.path_dallas_houst_green);
                break;
            case 60:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst2);
                imageView.setImageResource(R.drawable.path_dallas_houst2_green);
                break;
            case 61:
                imageView = mView.findViewById(R.id.frag_map_newo_houst);
                imageView.setImageResource(R.drawable.path_newo_houst_green);
                break;
            case 62:
                imageView = mView.findViewById(R.id.frag_map_sault_montr);
                imageView.setImageResource(R.drawable.path_sault_montr_green);
                break;
            case 63:
                imageView = mView.findViewById(R.id.frag_map_sault_toron);
                imageView.setImageResource(R.drawable.path_sault_toron_green);
                break;
            case 64:
                imageView = mView.findViewById(R.id.frag_map_chica_toron);
                imageView.setImageResource(R.drawable.path_chica_toron_green);
                break;
            case 65:
                imageView = mView.findViewById(R.id.frag_map_chica_pits);
                imageView.setImageResource(R.drawable.path_chica_pits_green);
                break;
            case 66:
                imageView = mView.findViewById(R.id.frag_map_chica_pits2);
                imageView.setImageResource(R.drawable.path_chica_pits2_green);
                break;
            case 67:
                imageView = mView.findViewById(R.id.frag_map_chica_saint);
                imageView.setImageResource(R.drawable.path_chicago_saint_green);
                break;
            case 68:
                imageView = mView.findViewById(R.id.frag_map_chica_saint2);
                imageView.setImageResource(R.drawable.path_chicago_saint2_green);
                break;
            case 69:
                imageView = mView.findViewById(R.id.frag_map_pits_saint);
                imageView.setImageResource(R.drawable.path_pits_saint_green);
                break;
            case 70:
                imageView = mView.findViewById(R.id.frag_map_nash_saint);
                imageView.setImageResource(R.drawable.path_nash_saint_green);
                break;
            case 71:
                imageView = mView.findViewById(R.id.frag_map_litter_saint);
                imageView.setImageResource(R.drawable.path_littler_saint_green);
                break;
            case 72:
                imageView = mView.findViewById(R.id.frag_map_litter_nash);
                imageView.setImageResource(R.drawable.path_littler_nash_green);
                break;
            case 73:
                imageView = mView.findViewById(R.id.frag_map_newo_litter);
                imageView.setImageResource(R.drawable.path_newo_littler_green);
                break;
            case 74:
                imageView = mView.findViewById(R.id.frag_map_atla_newo);
                imageView.setImageResource(R.drawable.path_atla_newo_green);
                break;
            case 75:
                imageView = mView.findViewById(R.id.frag_map_atla_newo2);
                imageView.setImageResource(R.drawable.path_atla_newo2_green);
                break;
            case 76:
                imageView = mView.findViewById(R.id.frag_map_miami_newo);
                imageView.setImageResource(R.drawable.path_miami_newo_green);
                break;
            case 77:
                imageView = mView.findViewById(R.id.frag_map_montr_toron);
                imageView.setImageResource(R.drawable.path_motr_toron_green);
                break;
            case 78:
                imageView = mView.findViewById(R.id.frag_map_toron_pits);
                imageView.setImageResource(R.drawable.path_toron_pits_green);
                break;
            case 79:
                imageView = mView.findViewById(R.id.frag_map_newy_pits);
                imageView.setImageResource(R.drawable.path_newy_pits_green);
                break;
            case 80:
                imageView = mView.findViewById(R.id.frag_map_newy_pits2);
                imageView.setImageResource(R.drawable.path_newy_pits2_green);
                break;
            case 81:
                imageView = mView.findViewById(R.id.frag_map_pits_wash);
                imageView.setImageResource(R.drawable.path_pits_wash_green);
                break;
            case 82:
                imageView = mView.findViewById(R.id.frag_map_pits_raleig);
                imageView.setImageResource(R.drawable.path_pits_raleig_green);
                break;
            case 83:
                imageView = mView.findViewById(R.id.frag_map_nash_pits);
                imageView.setImageResource(R.drawable.path_nash_pits_green);
                break;
            case 84:
                imageView = mView.findViewById(R.id.frag_map_nash_raleig);
                imageView.setImageResource(R.drawable.path_nash_raleig_green);
                break;
            case 85:
                imageView = mView.findViewById(R.id.frag_map_atla_nash);
                imageView.setImageResource(R.drawable.path_atla_nashv_green);
                break;
            case 86:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig);
                imageView.setImageResource(R.drawable.path_atla_raleig_green);
                break;
            case 87:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig2);
                imageView.setImageResource(R.drawable.path_atla_raleig2_green);
                break;
            case 88:
                imageView = mView.findViewById(R.id.frag_map_atla_charl);
                imageView.setImageResource(R.drawable.path_atla_charles_green);
                break;
            case 89:
                imageView = mView.findViewById(R.id.frag_map_atla_miami);
                imageView.setImageResource(R.drawable.path_atla_miami_green);
                break;
            case 90:
                imageView = mView.findViewById(R.id.frag_map_bost_montr);
                imageView.setImageResource(R.drawable.path_bost_motr_green);
                break;
            case 91:
                imageView = mView.findViewById(R.id.frag_map_bost_montr2);
                imageView.setImageResource(R.drawable.path_bost_motr2_green);
                break;
            case 92:
                imageView = mView.findViewById(R.id.frag_map_montr_newy);
                imageView.setImageResource(R.drawable.path_motr_newy_green);
                break;
            case 93:
                imageView = mView.findViewById(R.id.frag_map_boston_newy);
                imageView.setImageResource(R.drawable.path_bost_newy_green);
                break;
            case 94:
                imageView = mView.findViewById(R.id.frag_map_boston_newy2);
                imageView.setImageResource(R.drawable.path_bost_newy2_green);
                break;
            case 95:
                imageView = mView.findViewById(R.id.frag_map_newy_wash);
                imageView.setImageResource(R.drawable.path_newy_wash_green);
                break;
            case 96:
                imageView = mView.findViewById(R.id.frag_map_newy_wash2);
                imageView.setImageResource(R.drawable.path_newy_wash2_green);
                break;
            case 97:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig);
                imageView.setImageResource(R.drawable.path_wash_raleig_green);
                break;
            case 98:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig2);
                imageView.setImageResource(R.drawable.path_wash_raleig2_green);
                break;
            case 99:
                imageView = mView.findViewById(R.id.frag_map_charl_raleig);
                imageView.setImageResource(R.drawable.path_charl_raleig_green);
                break;

        }

        imageView.setVisibility(View.VISIBLE);
    }


    //pink
    public void displayImagePink(Route route) {

        System.out.println("Start City: " + route.getStartCity() + "END City : " + route.getEndCity() + "\n\n\n\n");
        switch (route.getId()) {
            case 0:
                imageView = mView.findViewById(R.id.frag_map_miami_charl);
                imageView.setImageResource(R.drawable.path_miami_charles_pink);
                break;
            case 1:
                imageView = mView.findViewById(R.id.frag_map_van_seat);
                imageView.setImageResource(R.drawable.path_van_seat_pink);
                break;
            case 2:
                imageView = mView.findViewById(R.id.frag_map_van_seat2);
                imageView.setImageResource(R.drawable.path_van_seat2_pink);
                break;
            case 3:
                imageView = mView.findViewById(R.id.frag_map_van_cal);
                imageView.setImageResource(R.drawable.path_van_calg_pink);
                break;
            case 4:
                imageView = mView.findViewById(R.id.frag_map_seat_port);
                imageView.setImageResource(R.drawable.path_seat_port_pink);
                break;
            case 5:
                imageView = mView.findViewById(R.id.frag_map_seat_port2);
                imageView.setImageResource(R.drawable.path_seat_port2_pink);
                break;

            case 6:
                imageView = mView.findViewById(R.id.frag_map_seat_cal);
                imageView.setImageResource(R.drawable.path_seat_calg_pink);
                break;
            case 7:
                imageView = mView.findViewById(R.id.frag_map_seat_helen);
                imageView.setImageResource(R.drawable.path_seatt_helen_pink);
                break;

            case 8:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra);
                imageView.setImageResource(R.drawable.path_sanfra_port_pink);
                break;
            case 9:
                imageView = mView.findViewById(R.id.frag_map_port_sanfra2);
                imageView.setImageResource(R.drawable.path_sanfra_port2_pink);
                break;
            case 10:
                imageView = mView.findViewById(R.id.frag_map_port_salt);
                imageView.setImageResource(R.drawable.path_port_salt_pink);
                break;
            case 11:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt);
                imageView.setImageResource(R.drawable.path_sanfra_salt_pink);
                break;
            case 12:
                imageView = mView.findViewById(R.id.frag_map_sanfra_salt2);
                imageView.setImageResource(R.drawable.path_sanfra_salt2_pink);
                break;
            case 13:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan);
                imageView.setImageResource(R.drawable.path_sanfra_losang_pink);
                break;
            case 14:
                imageView = mView.findViewById(R.id.frag_map_sanfra_losan2);
                imageView.setImageResource(R.drawable.path_sanfra_losang2_pink);
                break;
            case 15:
                imageView = mView.findViewById(R.id.frag_map_losan_lasveg);
                imageView.setImageResource(R.drawable.path_losan_lasveg_pink);
                break;
            case 16:
                imageView = mView.findViewById(R.id.frag_map_losan_phoenix);
                imageView.setImageResource(R.drawable.path_loans_phoenix_pink);
                break;
            case 17:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_pink);
                break;
            case 18:
                imageView = mView.findViewById(R.id.frag_map_cal_wini);
                imageView.setImageResource(R.drawable.path_calg_winnni_pink);
                break;
            case 19:
                imageView = mView.findViewById(R.id.frag_map_cal_helen);
                imageView.setImageResource(R.drawable.path_calg_helen_pink);
                break;
            case 20:
                imageView = mView.findViewById(R.id.frag_map_helen_wini);
                imageView.setImageResource(R.drawable.path_helen_winnni_pink);
                break;
            case 21:
                imageView = mView.findViewById(R.id.frag_map_helen_dulut);
                imageView.setImageResource(R.drawable.path_helen_dulut_pink);
                break;
            case 22:
                imageView = mView.findViewById(R.id.frag_map_helen_oma);
                imageView.setImageResource(R.drawable.path_helen_oma_pink);
                break;
            case 23:
                imageView = mView.findViewById(R.id.frag_map_denver_helen);
                imageView.setImageResource(R.drawable.path_denver_helen_pink);
                break;
            case 24:
                imageView = mView.findViewById(R.id.frag_map_salt_helen);
                imageView.setImageResource(R.drawable.path_salt_helen_pink);
                break;
            case 25:
                imageView = mView.findViewById(R.id.frag_map_salt_denver);
                imageView.setImageResource(R.drawable.path_salt_denver_pink);
                break;
            case 26:
                imageView = mView.findViewById(R.id.frag_map_salt_denver2);
                imageView.setImageResource(R.drawable.path_salt_denver2_pink);
                break;
            case 27:
                imageView = mView.findViewById(R.id.frag_map_lasveg_salt);
                imageView.setImageResource(R.drawable.path_lasveg_salt_pink);
                break;
            case 28:
                imageView = mView.findViewById(R.id.frag_map_phoenix_denver);
                imageView.setImageResource(R.drawable.path_phoenix_denver_pink);
                break;
            case 29:
                imageView = mView.findViewById(R.id.frag_map_phoenix_santaf);
                imageView.setImageResource(R.drawable.path_phoenix_santaf_pink);
                break;
            case 30:
                imageView = mView.findViewById(R.id.frag_map_phoenix_elpas);
                imageView.setImageResource(R.drawable.path_phoenix_elpaso_pink);
                break;
            case 31:
                imageView = mView.findViewById(R.id.frag_map_denver_oma);
                imageView.setImageResource(R.drawable.path_denver_oma_pink);
                break;
            case 32:
                imageView = mView.findViewById(R.id.frag_map_denver_kan);
                imageView.setImageResource(R.drawable.path_denver_kan_pink);
                break;
            case 33:
                imageView = mView.findViewById(R.id.frag_map_denver_kan2);
                imageView.setImageResource(R.drawable.path_denver_kan2_pink);
                break;
            case 34:
                imageView = mView.findViewById(R.id.frag_map_denver_okla);
                imageView.setImageResource(R.drawable.path_denver_okla_pink);
                break;
            case 35:
                imageView = mView.findViewById(R.id.frag_map_santaf_denver);
                imageView.setImageResource(R.drawable.path_santaf_denver_pink);
                break;
            case 36:
                imageView = mView.findViewById(R.id.frag_map_losan_elpaso);
                imageView.setImageResource(R.drawable.path_loans_elpaso_pink);
                break;
            case 37:
                imageView = mView.findViewById(R.id.frag_map_elpaso_santaf);
                imageView.setImageResource(R.drawable.path_elpaso_santaf_pink);
                break;
            case 38:
                imageView = mView.findViewById(R.id.frag_map_elpaso_okla);
                imageView.setImageResource(R.drawable.path_elpaso_okla_pink);
                break;
            case 39:
                imageView = mView.findViewById(R.id.frag_map_elpaso_dallas);
                imageView.setImageResource(R.drawable.path_elpaso_dalla_pink);
                break;
            case 40:
                imageView = mView.findViewById(R.id.frag_map_elpaso_houst);
                imageView.setImageResource(R.drawable.path_elpaso_houst_pink);
                break;
            case 41:
                imageView = mView.findViewById(R.id.frag_map_winni_sault);
                imageView.setImageResource(R.drawable.path_winnni_sault_pink);
                break;
            case 42:
                imageView = mView.findViewById(R.id.frag_map_wini_dulut);
                imageView.setImageResource(R.drawable.path_winnni_dulut_pink);
                break;
            case 43:
                imageView = mView.findViewById(R.id.frag_map_dulut_sault);
                imageView.setImageResource(R.drawable.path_dulut_sault_pink);
                break;
            case 44:
                imageView = mView.findViewById(R.id.frag_map_dulut_toron);
                imageView.setImageResource(R.drawable.path_dulut_toron_pink);
                break;
            case 45:
                imageView = mView.findViewById(R.id.frag_map_chica_dulut);
                imageView.setImageResource(R.drawable.path_chica_dulut_pink);
                break;
            case 46:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma);
                imageView.setImageResource(R.drawable.path_dulut_oma_pink);
                break;
            case 47:
                imageView = mView.findViewById(R.id.frag_map_dulut_oma2);
                imageView.setImageResource(R.drawable.path_dulut_oma2_pink);
                break;
            case 48:
                imageView = mView.findViewById(R.id.frag_map_chica_oma);
                imageView.setImageResource(R.drawable.path_chica_oma_pink);
                break;
            case 49:
                imageView = mView.findViewById(R.id.frag_map_oma_kan);
                imageView.setImageResource(R.drawable.path_oma_kan_pink);
                break;
            case 50:
                imageView = mView.findViewById(R.id.frag_map_oma_kan2);
                imageView.setImageResource(R.drawable.path_oma_kan2_pink);
                break;
            case 51:
                imageView = mView.findViewById(R.id.frag_map_kan_saint);
                imageView.setImageResource(R.drawable.path_kan_saint_pink);
                break;
            case 52:
                imageView = mView.findViewById(R.id.frag_map_kan_saint2);
                imageView.setImageResource(R.drawable.path_kan_saint2_pink);
                break;
            case 53:
                imageView = mView.findViewById(R.id.frag_map_okla_kan);
                imageView.setImageResource(R.drawable.path_okla_kan_pink);
                break;
            case 54:
                imageView = mView.findViewById(R.id.frag_map_okla_kan2);
                imageView.setImageResource(R.drawable.path_okla_kan2_pink);
                break;
            case 55:
                imageView = mView.findViewById(R.id.frag_map_okla_litter);
                imageView.setImageResource(R.drawable.path_okla_littler_pink);
                break;
            case 56:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla);
                imageView.setImageResource(R.drawable.path_dallas_okla_pink);
                break;
            case 57:
                imageView = mView.findViewById(R.id.frag_map_dallas_okla2);
                imageView.setImageResource(R.drawable.path_dallas_okla_pink); //todo duplicate?
                break;
            case 58:
                imageView = mView.findViewById(R.id.frag_map_dallas_litter);
                imageView.setImageResource(R.drawable.path_dalla_littler_pink);
                break;
            case 59:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst);
                imageView.setImageResource(R.drawable.path_dallas_houst_pink);
                break;
            case 60:
                imageView = mView.findViewById(R.id.frag_map_dallas_houst2);
                imageView.setImageResource(R.drawable.path_dallas_houst2_pink);
                break;
            case 61:
                imageView = mView.findViewById(R.id.frag_map_newo_houst);
                imageView.setImageResource(R.drawable.path_newo_houst_pink);
                break;
            case 62:
                imageView = mView.findViewById(R.id.frag_map_sault_montr);
                imageView.setImageResource(R.drawable.path_sault_montr_pink);
                break;
            case 63:
                imageView = mView.findViewById(R.id.frag_map_sault_toron);
                imageView.setImageResource(R.drawable.path_sault_toron_pink);
                break;
            case 64:
                imageView = mView.findViewById(R.id.frag_map_chica_toron);
                imageView.setImageResource(R.drawable.path_chica_toron_pink);
                break;
            case 65:
                imageView = mView.findViewById(R.id.frag_map_chica_pits);
                imageView.setImageResource(R.drawable.path_chica_pits_pink);
                break;
            case 66:
                imageView = mView.findViewById(R.id.frag_map_chica_pits2);
                imageView.setImageResource(R.drawable.path_chica_pits2_pink);
                break;
            case 67:
                imageView = mView.findViewById(R.id.frag_map_chica_saint);
                imageView.setImageResource(R.drawable.path_chicago_saint_pink);
                break;
            case 68:
                imageView = mView.findViewById(R.id.frag_map_chica_saint2);
                imageView.setImageResource(R.drawable.path_chicago_saint2_pink);
                break;
            case 69:
                imageView = mView.findViewById(R.id.frag_map_pits_saint);
                imageView.setImageResource(R.drawable.path_pits_saint_pink);
                break;
            case 70:
                imageView = mView.findViewById(R.id.frag_map_nash_saint);
                imageView.setImageResource(R.drawable.path_nash_saint_pink);
                break;
            case 71:
                imageView = mView.findViewById(R.id.frag_map_litter_saint);
                imageView.setImageResource(R.drawable.path_littler_saint_pink);
                break;
            case 72:
                imageView = mView.findViewById(R.id.frag_map_litter_nash);
                imageView.setImageResource(R.drawable.path_littler_nash_pink);
                break;
            case 73:
                imageView = mView.findViewById(R.id.frag_map_newo_litter);
                imageView.setImageResource(R.drawable.path_newo_littler_pink);
                break;
            case 74:
                imageView = mView.findViewById(R.id.frag_map_atla_newo);
                imageView.setImageResource(R.drawable.path_atla_newo_pink);
                break;
            case 75:
                imageView = mView.findViewById(R.id.frag_map_atla_newo2);
                imageView.setImageResource(R.drawable.path_atla_newo2_pink);
                break;
            case 76:
                imageView = mView.findViewById(R.id.frag_map_miami_newo);
                imageView.setImageResource(R.drawable.path_miami_newo_pink);
                break;
            case 77:
                imageView = mView.findViewById(R.id.frag_map_montr_toron);
                imageView.setImageResource(R.drawable.path_motr_toron_pink);
                break;
            case 78:
                imageView = mView.findViewById(R.id.frag_map_toron_pits);
                imageView.setImageResource(R.drawable.path_toron_pits_pink);
                break;
            case 79:
                imageView = mView.findViewById(R.id.frag_map_newy_pits);
                imageView.setImageResource(R.drawable.path_newy_pits_pink);
                break;
            case 80:
                imageView = mView.findViewById(R.id.frag_map_newy_pits2);
                imageView.setImageResource(R.drawable.path_newy_pits2_pink);
                break;
            case 81:
                imageView = mView.findViewById(R.id.frag_map_pits_wash);
                imageView.setImageResource(R.drawable.path_pits_wash_pink);
                break;
            case 82:
                imageView = mView.findViewById(R.id.frag_map_pits_raleig);
                imageView.setImageResource(R.drawable.path_pits_raleig_pink);
                break;
            case 83:
                imageView = mView.findViewById(R.id.frag_map_nash_pits);
                imageView.setImageResource(R.drawable.path_nash_pits_pink);
                break;
            case 84:
                imageView = mView.findViewById(R.id.frag_map_nash_raleig);
                imageView.setImageResource(R.drawable.path_nash_raleig_pink);
                break;
            case 85:
                imageView = mView.findViewById(R.id.frag_map_atla_nash);
                imageView.setImageResource(R.drawable.path_atla_nashv_pink);
                break;
            case 86:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig);
                imageView.setImageResource(R.drawable.path_atla_raleig_pink);
                break;
            case 87:
                imageView = mView.findViewById(R.id.frag_map_atla_raleig2);
                imageView.setImageResource(R.drawable.path_atla_raleig2_pink);
                break;
            case 88:
                imageView = mView.findViewById(R.id.frag_map_atla_charl);
                imageView.setImageResource(R.drawable.path_atla_charles_pink);
                break;
            case 89:
                imageView = mView.findViewById(R.id.frag_map_atla_miami);
                imageView.setImageResource(R.drawable.path_atla_miami_pink);
                break;
            case 90:
                imageView = mView.findViewById(R.id.frag_map_bost_montr);
                imageView.setImageResource(R.drawable.path_bost_motr_pink);
                break;
            case 91:
                imageView = mView.findViewById(R.id.frag_map_bost_montr2);
                imageView.setImageResource(R.drawable.path_bost_motr2_pink);
                break;
            case 92:
                imageView = mView.findViewById(R.id.frag_map_montr_newy);
                imageView.setImageResource(R.drawable.path_motr_newy_pink);
                break;
            case 93:
                imageView = mView.findViewById(R.id.frag_map_boston_newy);
                imageView.setImageResource(R.drawable.path_bost_newy_pink);
                break;
            case 94:
                imageView = mView.findViewById(R.id.frag_map_boston_newy2);
                imageView.setImageResource(R.drawable.path_bost_newy2_pink);
                break;
            case 95:
                imageView = mView.findViewById(R.id.frag_map_newy_wash);
                imageView.setImageResource(R.drawable.path_newy_wash_pink);
                break;
            case 96:
                imageView = mView.findViewById(R.id.frag_map_newy_wash2);
                imageView.setImageResource(R.drawable.path_newy_wash2_pink);
                break;
            case 97:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig);
                imageView.setImageResource(R.drawable.path_wash_raleig_pink);
                break;
            case 98:
                imageView = mView.findViewById(R.id.frag_map_wash_raleig2);
                imageView.setImageResource(R.drawable.path_wash_raleig2_pink);
                break;
            case 99:
                imageView = mView.findViewById(R.id.frag_map_charl_raleig);
                imageView.setImageResource(R.drawable.path_charl_raleig_pink);
                break;

        }

        imageView.setVisibility(View.VISIBLE);
    }

}
