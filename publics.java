

import java.util.*;



public class publics {
    static int random(int first, int second){
        int result = (int) (Math.random() * second) + first;
        return result;
    }

    

    static double distance(double da, double db, double dx, double dy){
        double ia = da - dx;
        double ib = db - dy;
        return Math.sqrt((ia * ia) + (ib * ib));
    }

    static int println(String text){
        System.out.println(text);
        return 0;
    }

    static int println(double text){
        System.out.println(text);
        return 0;
    }

    static int printb(boolean text){
        System.out.println(text);
        return 0;
    }

    static int printd(int text){
        System.out.println(text);
        return 0;
    }

    static long nowtime(){
        return System.currentTimeMillis();
    }

    static int remove(object obj){
        obj.x = -1000;
        obj.y = -1000;
        return 0;
    }

    // in test function / method
    static String format(String form, String ... str){
        String last_format = "";
        String final_format = "";
        List<String> format_list = new ArrayList<>();

        int count = 0;
        // add args need to format to a list
        for (int j = 0; j < str.length; j++){
            format_list.add(str[j]);
        }
        
        for (int i = 0; i < form.length(); i++){
            if (form.substring(i, i) == "{" && form.substring(i + 1, i + 1) == "}"){
                final_format = final_format + last_format;
                // clear last_format
                last_format = "";
                i++;
                final_format = final_format + format_list.get(count);
                count++;


            } else {
                last_format = last_format + form.substring(i, i);
            }
        }
        return final_format;
    }
}





