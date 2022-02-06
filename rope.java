import java.awt.*;


public class rope {
    
    static final int state_swing = 1;
    static final int state_throw = 2;
    static final int state_back = 3;
    static final int state_pull_back = 4;
    
    // rope throw speed
    int throw_speed = 10; //default value is 10, 30 after potion

    int default_pull_back_speed = throw_speed;
    int pull_back_speed = throw_speed;


    double swing_speed = 0.007; //0.001 default

    int startx = 395; // player x + 10 380 180
    int starty = 232; // player y + 45


    int endx = 600;
    int endy = 600;

    int state = 1;

    boolean rope_visible = true;

    window frame;

    rope(window frame){
        this.frame = frame;
    }

    int determine(){
        // use to detect the collision between rope and treasure
        // if collide, take treasure
        for (object obj: this.frame.treasures){
            // if (endx > obj.x && endx < obj.y
            //     + obj.width && endy > obj.y
            //     && endy < obj.y + obj.height){
            
                if (publics.distance(endx, endy, obj.x + (obj.width / 2), obj.y + (obj.height / 2)) < 30){
                    state = state_pull_back;
                    obj.catch_flag = true;
                    
            }
        }
        return 0;
    }

    int remove(object obj){
        obj.x = -1000;
        obj.y = -1000;
        return 0;
    }

    // length
    double length = 100;

    final double min_length = 100;
    final double max_length = 750;


    double degree = 0;
    int dir = 1;
    long potion_effect_end = 0;

    

    int draw(Graphics graphic){

        if (window.window_state == window.state_game){
            rope_visible = true;
        } else if (window.window_state == window.state_shop){
            rope_visible = false;
        }

        if (rope_visible == false){
            return 0;
        }
        
        
        
    
        // state: 1 - swing, 2 - throw, 3 - back
    
        determine();
        if (degree < 0){
            dir = 1;
        } else if (degree > 1){
            dir = -1;
        }
        switch (state){
            case state_swing:
                degree = degree + (swing_speed * dir);
                break;
            case state_throw:
                if (length < max_length){
                    length = length + throw_speed;
                } else {
                    state = state_back;
                }
                break;
            case state_back:
                if (length > min_length){
                    length = length - pull_back_speed;
                } else {
                    state = state_swing;
                }
                break;
            case state_pull_back:
                int mass = 1;

                // when touched
                if (length > min_length){
                    
                    length = length - pull_back_speed;
                    for (object obj: this.frame.treasures){
                        if (obj.catch_flag){
                            
                            if (obj.type == object.dynamite_block){
                                // iterate list again and check for distance
                                // use to detect range of tnt explode
                                for (object trea: this.frame.treasures){
                                    double half_x = obj.x + obj.width / 2;
                                    double half_y = obj.x + obj.height / 2;
                                    // double half_trea_x = trea.x + trea.width / 2;
                                    // double half_trea_y = trea.y + trea.height / 2;
                                    if (publics.distance(half_x, half_y, trea.x, trea.y) < tnt.explode_range){
                                        remove(trea);
                                    }
                                }
                                
                                
                                remove(obj);
                                state = state_back;
                                obj.type = object.normal;
                                break;
                            }

                            mass = obj.mass;
                            obj.x = Math.round(endx - obj.getWidth() / 2);
                            obj.y = endy;
                            if (length <= min_length){
                                // move away treasure while catched
                                
                                remove(obj);
                                
                                obj.catch_flag = false;

                                // if catched a lucky block item
                                if (obj.type == object.lucky_block){
                                    // choice random item from choice list
                                    String next_item = obj.include.get(publics.random(0, obj.include.size()));
                                    obj.mass = publics.random(1, 10) * 10;
                                    // give item to player
                                    if (next_item == dynamite.name){
                                        this.frame.player_obj.send_message("dynamite + 1", graphic);
                                        this.frame.dynamite_obj.count++;

                                    } else if (next_item == potion.name){
                                        this.frame.player_obj.send_message("potion + 1", graphic);
                                        this.frame.potion_obj.count++;

                                    } else {
                                        // item is money, then give player money
                                        this.frame.player_obj.send_message("money + " + next_item, graphic);
                                        player.money += Integer.parseInt(next_item);
                                    }
                                    
                                } 


                                    // <>shrink
                                /*
                                this.frame.player_obj.send_message(String.format("money + %d", obj.valued), graphic);
                                */
                                

                                    player.money += obj.valued; 
                                    state = state_swing;
                                
                            }


                            //BEGIN
                            // use dynamite
                            if (dynamite.explode_flag){
                                
                                dynamite.explode_flag = false;
                                state = state_back;
                                remove(obj);
                            }

                            // use potion
                            if (potion.drink_flag){
                                // potion.drink_flag = false;
                                // mass of treasure will be 70% lighter
                                potion_effect_end = publics.nowtime() + 5000;
                                potion.drink_flag = false;

                            if (publics.nowtime() < potion_effect_end){
                                
                                obj.mass = 1;
                            }
                             
                                // mass = (mass / 100) * 70;
                                // mass -= 10;
                                
                            
                            }
                            //END


                        }
                    
                    }

                }
                try {
                    Thread.sleep(mass);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                

        }
        

        endx = (int) (startx + length * Math.cos(degree * Math.PI));
        endy = (int) (starty + length * Math.sin(degree * Math.PI));
        graphic.setColor(Color.red);
        // draw rope
        graphic.drawLine(startx - 1, starty, endx, endy);
        graphic.drawLine(startx, starty, endx, endy);
        graphic.drawLine(startx + 1, starty, endx, endy);

        return 0;
    }

}









