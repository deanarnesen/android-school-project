package Tests;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.RouteColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Vector;

public class JsonRoutesBuilder {
    Vector<Route> routes = new Vector<>();
    @Test
    public void buildRoutes(){
        //charleston !! moved to top to preserve vector indices
        Route r0 = new Route("Charleston", "Miami", RouteColor.PURPLE, 4, 0);
        routes.add(r0);
        //vancouver
        Route r1 = new Route("Vancouver", "Seattle", RouteColor.GRAY, 1, 1);
        routes.add(r1);
        Route r2 = new Route("Vancouver", "Seattle", RouteColor.GRAY, 1, 2);
        routes.add(r2);
        Route r3 = new Route("Vancouver", "Calgary", RouteColor.GRAY, 3, 3);
        routes.add(r3);
        //seattle
        Route r4 = new Route("Seattle", "Portland", RouteColor.GRAY, 1, 4);
        routes.add(r4);
        Route r5 = new Route("Seattle", "Portland", RouteColor.GRAY, 1, 5);
        routes.add(r5);
        Route r6 = new Route("Seattle", "Calgary", RouteColor.GRAY, 4, 6);
        routes.add(r6);
        Route r7 = new Route("Seattle", "Helena", RouteColor.YELLOW, 6, 7);
        routes.add(r7);
        //portland
        Route r8 = new Route("Portland", "San Francisco", RouteColor.GREEN, 5, 8);
        routes.add(r8);
        Route r9 = new Route("Portland", "San Francisco", RouteColor.PURPLE, 5, 9);
        routes.add(r9);
        Route r10 = new Route("Portland", "Salt Lake City", RouteColor.BLUE, 6, 10);
        routes.add(r10);
        //san fransisco
        Route r11 = new Route("San Francisco", "Salt Lake City", RouteColor.ORANGE, 5, 11);
        routes.add(r11);
        Route r12 = new Route("San Francisco", "Salt Lake City", RouteColor.WHITE, 5, 12);
        routes.add(r12);
        Route r13 = new Route("San Francisco", "Los Angeles", RouteColor.YELLOW, 3, 13);
        routes.add(r13);
        Route r14 = new Route("San Francisco", "Los Angeles", RouteColor.PURPLE, 3, 14);
        routes.add(r14);
        //los angeles
        Route r15 = new Route("Los Angeles", "Las Vegas", RouteColor.GRAY, 2, 15);
        routes.add(r15);
        Route r16 = new Route("Los Angeles", "Phoenix", RouteColor.GRAY, 3, 16);
        routes.add(r16);
        Route r17 = new Route("Los Angeles", "El Paso", RouteColor.BLACK, 6, 17);
        routes.add(r17);
        //Calgary
        Route r18 = new Route("Calgary", "Winnipeg", RouteColor.WHITE, 6, 18);
        routes.add(r18);
        Route r19 = new Route("Calgary", "Helena", RouteColor.GRAY, 4, 19);
        routes.add(r19);
        //helena
        Route r20 = new Route("Helena", "Winnipeg", RouteColor.BLUE, 4, 20);
        routes.add(r20);
        Route r21 = new Route("Helena", "Duluth", RouteColor.ORANGE, 6, 21);
        routes.add(r21);
        Route r22 = new Route("Helena", "Omaha", RouteColor.RED, 4, 22);
        routes.add(r22);
        Route r23 = new Route("Helena", "Denver", RouteColor.GREEN, 4, 23);
        routes.add(r23);
        Route r24 = new Route("Helena", "Salt Lake City", RouteColor.PURPLE, 3, 24);
        routes.add(r24);
        //salt lake city
        Route r25 = new Route("Salt Lake City", "Denver", RouteColor.RED, 3, 25);
        routes.add(r25);
        Route r26 = new Route("Salt Lake City", "Denver", RouteColor.YELLOW, 3, 26);
        routes.add(r26);
        Route r27 = new Route("Salt Lake City", "Las Vegas", RouteColor.ORANGE, 3, 27);
        routes.add(r27);
        //no remaining las vegas routes
        //phoenix
        Route r28 = new Route("Phoenix", "Denver", RouteColor.WHITE, 5, 28);
        routes.add(r28);
        Route r29 = new Route("Phoenix", "Santa Fe", RouteColor.GRAY, 3, 29);
        routes.add(r29);
        Route r30 = new Route("Phoenix", "El Paso", RouteColor.GRAY, 3, 30);
        routes.add(r30);
        //denver
        Route r31 = new Route("Denver", "Omaha", RouteColor.PURPLE, 4, 31);
        routes.add(r31);
        Route r32 = new Route("Denver", "Kansas City", RouteColor.BLACK, 4, 32);
        routes.add(r32);
        Route r33 = new Route("Denver", "Kansas City", RouteColor.ORANGE, 4, 33);
        routes.add(r33);
        Route r34 = new Route("Denver", "Oklahoma City", RouteColor.RED, 4, 34);
        routes.add(r34);
        Route r35 = new Route("Denver", "Santa Fe", RouteColor.GRAY, 2, 35);
        routes.add(r35);
        //santa fe
        Route r36 = new Route("Santa Fe", "Oklahoma City", RouteColor.GRAY, 2, 36);
        routes.add(r36);
        Route r37 = new Route("Santa Fe", "El Paso", RouteColor.GRAY, 2, 37);
        routes.add(r37);
        //el paso
        Route r38 = new Route("El Paso", "Oklahoma City", RouteColor.YELLOW, 5, 38);
        routes.add(r38);
        Route r39 = new Route("El Paso", "Dallas", RouteColor.RED, 4, 39);
        routes.add(r39);
        Route r40 = new Route("El Paso", "Houston", RouteColor.GREEN, 6, 40);
        routes.add(r40);
        //winnipeg
        Route r41 = new Route("Winnipeg", "Sault St. Marie", RouteColor.GRAY, 6, 41);
        routes.add(r41);
        Route r42 = new Route("Winnipeg", "Duluth", RouteColor.BLACK, 4, 42);
        routes.add(r42);
        //duluth
        Route r43 = new Route("Duluth", "Sault St. Marie", RouteColor.GRAY, 3, 43);
        routes.add(r43);
        Route r44 = new Route("Duluth", "Toronto", RouteColor.PURPLE, 6, 44);
        routes.add(r44);
        Route r45 = new Route("Duluth", "Chicago", RouteColor.RED, 3, 45);
        routes.add(r45);
        Route r46 = new Route("Duluth", "Omaha", RouteColor.GRAY, 2, 46);
        routes.add(r46);
        Route r47 = new Route("Duluth", "Omaha", RouteColor.GRAY, 2, 47);
        routes.add(r47);
        //omaha
        Route r48 = new Route("Omaha", "Chicago", RouteColor.BLUE, 4, 48);
        routes.add(r48);
        Route r49 = new Route("Omaha", "Kansas City", RouteColor.GRAY, 1, 49);
        routes.add(r49);
        Route r50 = new Route("Omaha", "Kansas City", RouteColor.GRAY, 1, 50);
        routes.add(r50);
        //kansas city
        Route r51 = new Route("Kansas City", "Saint Louis", RouteColor.BLUE, 2, 51);
        routes.add(r51);
        Route r52 = new Route("Kansas City", "Saint Louis", RouteColor.PURPLE, 2, 52);
        routes.add(r52);
        Route r53 = new Route("Kansas City", "Oklahoma City", RouteColor.GRAY, 2, 53);
        routes.add(r53);
        Route r54 = new Route("Kansas City", "Oklahoma City", RouteColor.GRAY, 2, 54);
        routes.add(r54);
        //oklahoma city
        Route r55 = new Route("Oklahoma City", "Little Rock", RouteColor.GRAY, 2, 55);
        routes.add(r55);
        Route r56 = new Route("Oklahoma City", "Dallas", RouteColor.GRAY, 2, 56);
        routes.add(r56);
        Route r57 = new Route("Oklahoma City", "Dallas", RouteColor.GRAY, 2, 57);
        routes.add(r57);
        //dallas
        Route r58 = new Route("Dallas", "Little Rock", RouteColor.GRAY, 2, 58);
        routes.add(r58);
        Route r59 = new Route("Dallas", "Houston", RouteColor.GRAY, 1, 59);
        routes.add(r59);
        Route r60 = new Route("Dallas", "Houston", RouteColor.GRAY, 1, 60);
        routes.add(r60);
        //houston
        Route r61 = new Route("Houston", "New Orleans", RouteColor.GRAY, 2, 61);
        routes.add(r61);
        //sault st marie
        Route r62 = new Route("Sault St. Marie", "Montreal", RouteColor.BLACK, 5, 62);
        routes.add(r62);
        Route r63 = new Route("Sault St. Marie", "Toronto", RouteColor.GRAY, 2, 63);
        routes.add(r63);
        //chicago
        Route r64 = new Route("Chicago", "Toronto", RouteColor.WHITE, 4, 64);
        routes.add(r64);
        Route r65 = new Route("Chicago", "Pittsburgh", RouteColor.ORANGE, 3, 65);
        routes.add(r65);
        Route r66 = new Route("Chicago", "Pittsburgh", RouteColor.BLACK, 3, 66);
        routes.add(r66);
        Route r67 = new Route("Chicago", "Saint Louis", RouteColor.GREEN, 2, 67);
        routes.add(r67);
        Route r68 = new Route("Chicago", "Saint Louis", RouteColor.WHITE, 2, 68);
        routes.add(r68);
        //saint louis
        Route r69 = new Route("Saint Louis", "Pittsburgh", RouteColor.GREEN, 5, 69);
        routes.add(r69);
        Route r70 = new Route("Saint Louis", "Nashville", RouteColor.GRAY, 2, 70);
        routes.add(r70);
        Route r71 = new Route("Saint Louis", "Little Rock", RouteColor.GRAY, 2, 71);
        routes.add(r71);
        //little rock
        Route r72 = new Route("Little Rock", "Nashville", RouteColor.WHITE, 3, 72);
        routes.add(r72);
        Route r73 = new Route("Little Rock", "New Orleans", RouteColor.GREEN, 3, 73);
        routes.add(r73);
        //new orleans
        Route r74 = new Route("New Orleans", "Atlanta", RouteColor.YELLOW, 4, 74);
        routes.add(r74);
        Route r75 = new Route("New Orleans", "Atlanta", RouteColor.ORANGE, 4, 75);
        routes.add(r75);
        Route r76 = new Route("New Orleans", "Miami", RouteColor.RED, 6, 76);
        routes.add(r76);
        //toronto
        Route r77 = new Route("Toronto", "Montreal", RouteColor.GRAY, 3, 77);
        routes.add(r77);
        Route r78 = new Route("Toronto", "Pittsburgh", RouteColor.GRAY, 2, 78);
        routes.add(r78);
        //pittsburgh
        Route r79 = new Route("Pittsburgh", "New York", RouteColor.WHITE, 2, 79);
        routes.add(r79);
        Route r80 = new Route("Pittsburgh", "New York", RouteColor.GREEN, 2, 80);
        routes.add(r80);
        Route r81 = new Route("Pittsburgh", "Washington", RouteColor.GRAY, 2, 81);
        routes.add(r81);
        Route r82 = new Route("Pittsburgh", "Raleigh", RouteColor.GRAY, 2, 82);
        routes.add(r82);
        Route r83 = new Route("Pittsburgh", "Nashville", RouteColor.YELLOW, 4, 83);
        routes.add(r83);
        //nashville
        Route r84 = new Route("Nashville", "Raleigh", RouteColor.BLACK, 3, 84);
        routes.add(r84);
        Route r85 = new Route("Nashville", "Atlanta", RouteColor.GRAY, 1, 85);
        routes.add(r85);
        //atlanta
        Route r86 = new Route("Atlanta", "Raleigh", RouteColor.GRAY, 2, 86);
        routes.add(r86);
        Route r87 = new Route("Atlanta", "Raleigh", RouteColor.GRAY, 2, 87);
        routes.add(r87);
        Route r88 = new Route("Atlanta", "Charleston", RouteColor.GRAY, 2, 88);
        routes.add(r88);
        Route r89 = new Route("Atlanta", "Miami", RouteColor.BLUE, 5, 89);
        routes.add(r89);
        //montreal
        Route r90 = new Route("Montreal", "Boston", RouteColor.GRAY, 2, 90);
        routes.add(r90);
        Route r91 = new Route("Montreal", "Boston", RouteColor.GRAY, 2, 91);
        routes.add(r91);
        Route r92 = new Route("Montreal", "New York", RouteColor.BLUE, 3, 92);
        routes.add(r92);
        //boston
        Route r93 = new Route("Boston", "New York", RouteColor.YELLOW, 2, 93);
        routes.add(r93);
        Route r94 = new Route("Boston", "New York", RouteColor.RED, 2, 94);
        routes.add(r94);
        //new york
        Route r95 = new Route("New York", "Washington", RouteColor.BLACK, 2, 95);
        routes.add(r95);
        Route r96 = new Route("New York", "Washington", RouteColor.ORANGE, 2, 96);
        routes.add(r96);
        //washington
        Route r97 = new Route("Washington", "Raleigh", RouteColor.GRAY, 2, 97);
        routes.add(r97);
        Route r98 = new Route("Washington", "Raleigh", RouteColor.GRAY, 2, 98);
        routes.add(r98);
        //raleigh
        Route r99 = new Route("Raleigh", "Charleston", RouteColor.GRAY, 2, 99);
        routes.add(r99);


        Gson gson = new Gson();
        String json = gson.toJson(routes);
        try{
            PrintWriter writer = new PrintWriter("routes_json.txt");
            writer.println(json);
            writer.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Type type = new TypeToken<Vector<Route>>(){}.getType();
        Vector<Route> test = gson.fromJson(json, type);
        System.out.print(test.size());


    }

}
