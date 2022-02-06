


import java.awt.*;



public class player extends item {

    static String path_player = "images/player/";

    static int money = 0;
    static int goal = 0;
    static long count_down = 0;
    static long begin_count_down = publics.nowtime();

    int player_x = 370;
    int player_y = 135;

    int width = 120;
    int height = 122;

    String shown_message = "";
    long end_message = 0;

    boolean player_visible = true;

    // <>script
    // Image player = Toolkit.getDefaultToolkit().getImage(path_player + "walk1.png");
    Image player = Toolkit.getDefaultToolkit().getImage(path_player + "gold_miner_default.png");
    
    
    
    int draw(Graphics graphic){
        if (window.window_state == window.state_game){
            player_visible = true;
        } else if (window.window_state == window.state_shop){
            player_visible = false;
        }

        if (player_visible){
            graphic.drawImage(player, player_x, player_y, null);
            if (publics.nowtime() < end_message){
                background.show_text(shown_message, player_x - 20, player_y - 15, graphic);
            }
        }

        return 0;
    }


    int send_message(String text, Graphics graphic){
        
        shown_message = text;
        end_message = publics.nowtime() + 1000;
        // <>script
        // background.show_text(text, player_x, player_y - 50, graphic); 
        // background.show_text(text, player_x - Math.round(width / 2), player_y - Math.round(height / 2), graphic);

        return 0;
    }
    
}








