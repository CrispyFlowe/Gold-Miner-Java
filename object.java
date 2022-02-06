

import java.awt.*;

import java.util.List;
import java.util.ArrayList;


public class object {



    double x;
    double y;

    int width;
    int height;

    Image img;

    boolean catch_flag;

    int mass;
    
    List<String> include = new ArrayList<>(); // maybe have(use random to choice one)
    // List<String> contain = {"", "", "", "", ""}; // must have

    String type = "";
    final static String lucky_block = "lucky";
    final static String dynamite_block = "dynamite";
    final static String normal = "normal";

    // object's worth
    int valued;

    boolean obj_visible = true;

    int draw(Graphics graphic){
        if (window.window_state == window.state_game){
            obj_visible = true;
        } else if (window.window_state == window.state_shop){
            obj_visible = false;
        }



        if (obj_visible){
            graphic.drawImage(img, (int)x, (int)y, null);
        }
        return 0;
    }

    public int getWidth(){
        return width;
    }

    int remove(){
        x = -1000;
        y = -1000;
        return 0;
    }
    
    int in_box(){
        return 0;
    }
}










