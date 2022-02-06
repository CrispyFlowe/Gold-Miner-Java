// buttons


import java.awt.*;


public class item {
    
    
    static String path_item = "images/item/";

    
    

    item(){
        
    }

    static final String name = "item";

    // how many item do you have
    int count = 0;
    

    // price of item (in shop)
    

    int price = 10;
    int x = 100;
    int y = 100;
    Image img;

    int offset_x = 50;
    int offset_y = 90;

    boolean item_visible = true;

    static final int caption_level_item = 1;
    static final int caption_shop_item = 2;

    int caption = caption_level_item;

    
    

    int draw(Graphics graphic){
        if (window.window_state == window.state_game){
            item_visible = true;
            graphic.drawImage(img, x, y, null);
            background.show_text(String.format("x%d", count), x + offset_x, y + offset_y, graphic);

        } else if (window.window_state == window.state_shop){
            item_visible = false;

        }


    
        return 0;
    }


}

class dynamite extends item {
    dynamite(){
        // 663, 80
        
        this.x = 600;
        this.y = 169;
        this.offset_x = 50;
        this.offset_y = 90;

        this.img = Toolkit.getDefaultToolkit().getImage(path_item + "dynamite.png");

        this.count = 3;
        this.price = 100;
    }

    // do you use dynamite or not?
    static boolean explode_flag = false;
    static int dynamite_count = 3;
    static final String name = "dynamite";
    

    int use_dynamite(){
        if (count > 0 && explode_flag == false){
            count--;
            explode_flag = true;
        }
        return 0;
    }
}

class potion extends item {
    potion(){
        
        this.x = 200;//504, 197
        this.y = 158;
        this.offset_x = 40;
        this.offset_y = 70;

        this.img = Toolkit.getDefaultToolkit().getImage(path_item + "potion.png");

        this.count = 3;
        this.price = 100;
    }

    

    static boolean drink_flag = false;
    static final String name = "potion";

    int use_potion(){
        if (count > 0){
            count--;
            drink_flag = true;
        }
        return 0;
    }
}

class time_clock extends item {
    time_clock(){
        this.caption = item.caption_shop_item;
        
    }

    static final String name = "time_clock";
}

class four_leaf_clover extends item {
    four_leaf_clover(){
        this.caption = item.caption_shop_item;
        
    }
}





