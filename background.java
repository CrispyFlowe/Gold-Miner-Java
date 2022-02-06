


import java.awt.*;




public class background {

    

    window frame;

    background(window frame){
        this.frame = frame;
    }

    boolean False = false;
    boolean True = true;
    
    static String path_img = "images/background/";

    Graphics graph;
    
    long count_down = 0;

    Image background = Toolkit.getDefaultToolkit().getImage(path_img + "underground.png");

    Image underground = Toolkit.getDefaultToolkit().getImage(path_img + "underground.png");
    Image shop = Toolkit.getDefaultToolkit().getImage(path_img + "gold_shop_full_item.png");

    Image boss_angry = Toolkit.getDefaultToolkit().getImage(path_img + "boss_angry.png");
    Image boss_happy = Toolkit.getDefaultToolkit().getImage(path_img + "boss_happy.png");
    
    Image soil = Toolkit.getDefaultToolkit().getImage(path_img + "soil.png");

    final static int background_underground = 1;
    final static int background_shop = 2;
    final static int background_begin = 3;
    final static int background_boss_angry = 4;
    final static int background_boss_happy = 5;
    

    int switch_background(int name){
        switch (name){
            case background_underground:
                background = underground;
                break;

            case background_shop:
                background = shop;
                window.window_state = window.state_shop;
                break;

            case background_boss_angry:
                background = boss_angry;
                break;

            case background_boss_happy:
                background = boss_happy;
                break;
            
        }
        return 0;
    }

    int force_switch_background(int name, int ... args){

        int x = 0;
        int y = 0;
        int i = 0;

        Graphics graphic = graph;

        for (int arg: args){
            i++;
            if (i == 1){x = arg;}
            else if (i == 2){y = arg;}
        }

        
        switch_background(name);
        graphic.drawImage(background, x, y, null);
        
        return 0;
    }
    /**
    * int name, Graphics graphic, int x, int y
    */

    int draw(Graphics graphic){
        if (window.window_state == window.state_game){
            graphic.drawImage(underground, 0, 0, null);

            graph = graphic;

            // show score on screen
            show_text("space to throw, b to use dynamite, u to drink potion", 30, 60, graphic, 20);
            show_text(String.format("level %d", window.level), 30, 100, graphic);
            show_text(String.format("score: %d", player.money), 30, 150, graphic);
            show_text(String.format("goal: %d", player.goal), 30, 175, graphic, 20);


            count_down = Math.abs((publics.nowtime() - player.begin_count_down) / 1000);

            // get digits of level
            // for: show_text(String.format(" - Time: %d", (count_down)), 145, 100, graphic);
            int level_len = (String.valueOf(window.level)).length();
            show_text(String.format(" - Time: %d", (count_down)), 140 + (level_len * 17), 100, graphic);
            
        } else if (window.window_state == window.state_shop){

            // graphic.drawImage(soil, 0, 0, null);
            
            // if (background == shop){
            //     graphic.drawImage(background, -95, 45, null);
            // } else if (background == boss_angry){
            //     graphic.drawImage(boss_angry, -95, 45, null);
            // } else if (background == boss_happy){
            //     graphic.drawImage(boss_happy, -95, 45, null);

            
            graphic.drawImage(background, -95, 45, null);
            if (this.frame.shop_obj.switch_to_game_flag && this.frame.shop_obj.switch_to_game_time > publics.nowtime()
            && False){
                // switch to game
                publics.println("do switch");
                
                this.frame.bg_obj.switch_background(background_boss_angry);
            } else {
                // this.frame.shop_obj.switch_to_game_flag = false;
                // this.frame.level_begin();
                // window.window_state = window.state_game;
            }
        }
        

        
        return 0;
    }

    public static int show_text(String text, int x, int y, Graphics graphic, int ... args){
        /**
         * show_text method
         * format: text, x, y, graphic, args: (size, color)
         * @throws l
         */

        // Color color = Color.BLACK;
        int i = 0;
        int size = 30;
        for (int arg: args){
            i++;
            if (i == 1){size = arg;}
            
        }
        graphic.setColor(Color.BLACK);
        graphic.setFont(new Font("plain", Font.BOLD, size));
        graphic.drawString(text, x, y);
        return 0;
    }

}







