
// import java.awt.*;
import javax.swing.*;

import java.util.*;


public class shop {

    List<String> shop_items = new ArrayList<>();

    int[] shop_item_pos = {139, 406, 251, 407, 436, 406}; // storage each item's position in shop menu

    boolean in_shop_flag = false;

    boolean switch_to_game_flag = false;
    long switch_to_game_time = publics.nowtime();
    int target_image = background.background_underground;

    int shop_init(){
        for (int i = 0; i < 4; i++){

            int next_shop_item = publics.random(1, 16);

            if (next_shop_item < 3){shop_items.add(dynamite.name);}
            else if (next_shop_item < 6){shop_items.add(potion.name);}
            else if (next_shop_item < 9){shop_items.add(time_clock.name);}
            else if (next_shop_item < 12){shop_items.add(four_leaf_clover.name);}
        }
        return 0;
    }
    
    static String path_item = "images/item/";

    window frame;
    shop(window frame){
        this.frame = frame;
    }

    shop(){

    }

    static boolean shop_flag = false;

    int go_to_shop() {

        if (in_shop_flag){
            return 0;
        }
        in_shop_flag = true;
        
        
        // switch_to_game_flag = false;
        // switch to shop background image
        // this.frame.bg_obj.switch_background(background.background_shop);



        window.window_state = window.state_shop;

        String want_to_buy = "";
        try {
            want_to_buy = JOptionPane.showInputDialog("""
            What do you want to buy?
            (d)dynamite: 100
            (p)potion of strength: 100
            (c)clock_time: 130 gives you extea time
            (f)four-leaf-clover: 500 let the chance of lucky_block be higher
            (r)rock book: 300 let stone worth more
            (w)washer for diamonad: 1000 let diamond worth more
            """);
            
        } catch (Exception e) {
            // do nothing
        }
        
        // publics.println(want_to_buy.substring(0, 1));

        int cost = 0;




        if (want_to_buy == null){} else
        if (want_to_buy.length() < 1){

            // exit shop
        
        } else {

        switch(want_to_buy.substring(0, 1)){
            // empty
            
            case "d":
                if (player.money > 100 - 1){
                    cost = 100;
                    this.frame.dynamite_obj.count++;
                }
                break;
            // potion
            case "p":
                if (player.money > 100 - 1){
                    cost = 100;
                    this.frame.potion_obj.count++;
                }
                break;
            case "r":
                if (player.money > 130 - 1){
                    cost = 130;

                }
        }
        }

        player.money -= cost;

        
        
        
        this.frame.bg_obj.switch_background(background.background_boss_angry);

        /*
        // 0 money, boss angry
        if (cost > 1){
            this.frame.bg_obj.switch_background(background.background_boss_angry);
            // this.frame.bg_obj.force_switch_background(background.background_boss_angry, -95, 45);
            target_image = background.background_boss_angry;
            
        // player buy something, boss happy
        } else if (cost > 0){
            this.frame.bg_obj.switch_background(background.background_boss_happy);
            // this.frame.bg_obj.force_switch_background(background.background_boss_happy, -95, 45);
            target_image = background.background_boss_happy;
        }
        */
        
        // try {
        //     Thread.sleep(2000);
        // } catch (InterruptedException e) {
            
        //     e.printStackTrace();
        // }
         // thread sleep

        
        // shrink<> script
        // switch_to_game_flag = true;
        // switch_to_game_time = publics.nowtime() + 2000;


        

        return 0;

    }



    
    
    
}










