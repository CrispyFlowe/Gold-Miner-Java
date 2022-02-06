
import java.awt.*;


/**
 * java treasure method
 * 
 * gold - gold give you money
 * rock and diamond - give you more money, but stone worth less
 * lucky box - randomally give you item
 * tnt will explode when your rope touch it
 *
 */
//











public class treasure extends object {


    static String path_tile = "images/tile/";

    static int density = 50;

    treasure(){
        this.catch_flag = false;

        this.x = publics.random(0, window.win_width);
        this.y = publics.random(345, 500);
        this.width = 40;
        this.height = 40;
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "gold.png");

        this.mass = 30;
        this.valued = 40;
    }

    
}

class treasure_small extends treasure {
    treasure_small(){
        this.width = 20;
        this.height = 20;
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "gold_small.png");


        this.mass = 15;
        this.valued = 20;
        
    }
}

class treasure_big extends treasure {
    treasure_big(){
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "gold_big.png");

        this.mass = 60;
        this.valued = 100;
    }
    
}


class rock extends treasure {
    rock(){
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "rock.png");

        this.mass = 50;
        this.valued = 5;
    }
}



class diamond extends treasure {
    diamond(){
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "diamond.png");

        this.mass = 60;
        this.valued = 500;
    }
}




class lucky_block extends treasure {
    

    lucky_block(){
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "lucky_block.png");

        this.mass = 40;
        this.valued = 0;
        this.type = lucky_block;

        this.include.add(potion.name);
        this.include.add(dynamite.name);
        this.include.add("800");
        this.include.add("400");
        this.include.add("185");

    }


    int in_box(){
        
        return 0;
    }

}

class tnt extends treasure {
    tnt(){
        this.img = Toolkit.getDefaultToolkit().getImage(path_tile + "tnt.png");
        
        this.mass = 0;
        this.valued = 1;
        this.type = dynamite_block;
    }

    static int explode_range = 300; // 300

    static int tnt_explode(){
    
        return 0;
    }
}








