

//game

// class game window and level generator

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.List;

import java.io.*;


public class window extends JFrame {
    

    

    List<object> treasures = new ArrayList<>();
    

    static boolean debug = false;
    static boolean debug_render = false;

    static boolean running = true;
    static int mouse_x = 0;
    static int mouse_y = 0;

    static boolean auto_level_begin_flag = true;
    static boolean end_flag = false;

    public static String UNAME = "player";
    
    static int level = 0;
    
    
        
        
        int level_begin(){
            /**
             * new level begin
             * use to start new level
             * 
             */
            
            level_end_flag = false;

            level++;

        
            if (level > 1){
                bg_obj.switch_background(background.background_underground);
            }

            window_state = state_game;
            // 1 - clear list
            treasures.clear();

            // 2 - remove all object in list
            // for (object obj: treasures){
            //     publics.remove(obj);
            // }

            
            player.goal = level * 100;
            player.begin_count_down = publics.nowtime() + 30000;

            // 30 second countdown (current)
            player.count_down = 30;
            
        
            boolean place_flag = true;
            // create treasure
            for (int i = 0; i < 10; i++){
                int type = publics.random(1, 20);
                // int type2 = publics.random(1, 10);

                treasure current_t = new treasure(); // current treasure


                if (type < 3){current_t = new treasure_small();}
                else if (type < 8){current_t = new treasure();}
                else if (type < 12){current_t = new treasure_big();}
                else if (type < 15){current_t = new diamond();}
                else if (type < 16){current_t = new lucky_block();}
                else if (type < 19){current_t = new tnt();}

                for (object obj: treasures){
                    if (publics.distance(current_t.x, current_t.y, obj.x, obj.y) < treasure.density){
                        place_flag = false;

                    }
                }
                if (place_flag){
                    treasures.add(current_t);
                } else {
                    place_flag = true;
                    i--;
                }


            }
            // create rock 
            for (int j = 0; j < 10; j++){
                rock current_s = new rock();

                for (object obj: treasures){
                    if (publics.distance(current_s.x, current_s.y, obj.x, obj.y) < treasure.density){
                        place_flag = false;

                    }
                }
                if (place_flag){
                    treasures.add(current_s);
                } else {
                    place_flag = true;
                    j--;
                }
            // create lucky box
            for (int k = 0; k < 10; k++){
                int box_kind = publics.random(1, 10);
                lucky_block current_b = new lucky_block();
                tnt current_d = new tnt();

                // if (box_kind > 9){current_b = new lucky_block();}
                // publics.println("doing");

                



                for (object obj: treasures){
                    if (publics.distance(current_s.x, current_s.y, obj.x, obj.y) < treasure.density){
                        place_flag = false;

                    }
                }
                if (place_flag){
                    if (box_kind == 9){
                        treasures.add(current_b);
                        publics.println("added lb");
                    } else if (box_kind == 8){
                        treasures.add(current_d);
                        publics.println("added tnt");
                    }
                } else {
                    place_flag = true;
                    // k--;
                }

                

            }
                
            }
            return 0;
        }

    {
        level_begin();
    }

    background bg_obj = new background(this);
    player player_obj = new player();
    rope rope_obj = new rope(this);

    dynamite dynamite_obj = new dynamite();
    potion potion_obj = new potion();
    shop shop_obj = new shop(this);

    static String path_data = "data/";
    static String rep = ".";

    public static int var = 0;
    
    static int count;

    Image offscreen_image;

    static int win_width = 800;
    static int win_height = 600;

    final static int state_game = 1;
    final static int state_shop = 2;
    final static int state_begin = 3;

    
    int launch(){
        // draw window
    
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setTitle("gold miner beta by Cflowe");

    
        addMouseListener(new MouseAdapter(){
            //Override
            public void mouseClicked(MouseEvent event){
                super.mouseClicked(event);
                if (event.getButton() == 1){
                    mouse_x = event.getX();
                    mouse_y = event.getY();
                    rope_obj.state = rope.state_throw;
                    

                    System.out.println("mouse x:" + mouse_x);
                    System.out.println("mouse y:" + mouse_y);
                }
                
            }

        });

        addKeyListener(new KeyAdapter(){
            //Override
            public void keyPressed(KeyEvent event){
                super.keyPressed(event);
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE){
                    // exit full screen mode
                } else if (event.getKeyCode() == KeyEvent.VK_B){
                    // explode
                    dynamite_obj.use_dynamite();
            
                } else if (event.getKeyCode() == KeyEvent.VK_U){
                    // use potion of strength
                    potion_obj.use_potion();
                } else if (event.getKeyCode() == KeyEvent.VK_S){
                    // go to shop
                    if (debug){
                        shop_obj.go_to_shop();
                    }

                } else if (event.getKeyCode() == KeyEvent.VK_SPACE){
                    rope_obj.state = rope.state_throw;
                    
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (debug){
                        level_begin();
                    }
                    
                } else if (event.getKeyCode() == KeyEvent.VK_N){
                    window_state = state_shop;
                }


                // return 0;
            }
        });


        if (UNAME == "cflowe1357"){
            debug = true;
            debug_render = true;
        }

        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        String local_load_file = "sss";
        // local_load_file = JOptionPane.showInputDialog("Do you want to keep your game? (Y/N)");

        /* if (local_load_file == null){} else */ if (local_load_file.length() < 1){}
        else {

            // publics.println(load_file_flag);
            local_load_file = (local_load_file.substring(0, 1)).toLowerCase();

            if (local_load_file.contains("y")){
                load_level();
            } else {
                // <>temp
                // start a new game 
                String local_player_name = JOptionPane.showInputDialog("What's your name?");
                if (local_player_name == null){}
                else {
                    UNAME = local_player_name;
                    load_level();
                }
            }
        }
        
        // game loop
        while (running){
            
                
            repaint();

                


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        }
        return 0;
    }

    boolean level_end_flag = false;

    int save_level(){

        int player_level = level; // 100100
        int player_money = player.money; // 100200
        int next_goal = player.goal;
        int player_dynamite_count = dynamite_obj.count; // 100300
        int player_potion_count = potion_obj.count; // 100400
        int[] data = {player_level, player_money, next_goal, player_dynamite_count, player_potion_count};
        
        
        try {
            // write to data file

            PrintStream local_last_data = new PrintStream(path_data + "player_data.txt");

            PrintStream data_file = new PrintStream(path_data + UNAME + "_data.txt");
            data_file.write(player_level);
            for (int i = 0; i < data.length; i++){
                data_file.append(String.valueOf(data[i])); 
                local_last_data.append(String.valueOf(data[i]));
                data_file.append(rep); 
                local_last_data.append(rep);
                if (debug_render){
                    publics.printd(data[i]);
                }
            }
            
            local_last_data.close();
            data_file.close();
        } catch (IOException e) {
            // exception
        }
        return 0;
    }

    

    int load_level(){
        // read file and load
        String data = "";
        try {
            BufferedReader data_file = new BufferedReader(new FileReader(path_data + UNAME + "_data.txt"));
            data = data_file.readLine();
            publics.println(data);
            data_file.close();

            if (data == null){
                return 0;
            }
            
            int d = 0;
            String current_letter = "";
            String result = "";

            for (int s = 1; s < data.length(); s++){
                current_letter = String.valueOf(data.substring(s, s + 1));
                
                if (debug_render){
                    publics.println(current_letter);
                }

                if (current_letter.contains(rep)){
                    // switch
                    d++;
                    // level, money, goal, dynamite, potion
                    if (debug_render){
                        publics.println(result);
                        publics.printb((result == ""));
                    }
                    if ((result == "") == false){ 
                        int int_result = Integer.parseInt(result);
                        if (debug_render){
                            publics.printd(int_result);
                        }
                        switch (d){
                            case 1:
                                level = int_result;
                                break;
                            case 2:
                                player.money = int_result;
                                break;
                            case 3:
                                player.goal = int_result;
                                break;
                            case 4:
                                dynamite_obj.count = int_result;
                                break;
                            case 5:
                                potion_obj.count = int_result;
                                break;
                        }
                            result = "";
                    } 
                    
                    
                        
                } else {
                    result = result + current_letter;
                    if (debug_render){
                        publics.println(result);
                    }
                }
            }
        } catch (IOException e) {
            // exception
        }
        return 0;
    }
    

    static int window_state = state_game;

    //Override
    public void paint(Graphics graphic){
        // switch(window_state){
            
                offscreen_image = this.createImage(800, 600);
                Graphics g_image = offscreen_image.getGraphics();

                bg_obj.draw(g_image);
                player_obj.draw(g_image);
                rope_obj.draw(g_image);
                dynamite_obj.draw(g_image);
                potion_obj.draw(g_image);

                for (object obj: treasures){
                    obj.draw(g_image);
                }

                // when time countdown end, if player's score made to nextlevel, re-generate
                // if not, throw a message to player
                
                if (auto_level_begin_flag && bg_obj.count_down < 1 && level_end_flag == false
                /*&& shop_obj.switch_to_game_flag == false*/){
                    // new level begin, win
                    if (save_level() == 1){
                        publics.println("failed to save");
                    }
                    
                    if (player.money > player.goal - 1){
                        level_end_flag = true;
                        bg_obj.switch_background(background.background_shop);
                        window.window_state = window.state_shop;
                        shop_obj.go_to_shop();
                        shop_obj.in_shop_flag = false;
                        level_begin();
                    } else {
                        // level end, lose
                        if (end_flag == false){
                            end_flag = true;
                            player_obj.send_message("your money is not enough!!!", graphic);
                            JOptionPane.showInputDialog("GAME OVER - TRY AGAIN? (Y / N)");
                            // forever for loop
                            // for (;;){
                            //     // do nothing until player exit game
                            // }
                        }
                    }
                }

            


                graphic.drawImage(offscreen_image, 0, 0, null);
            
        //}
    }

    


    

}








