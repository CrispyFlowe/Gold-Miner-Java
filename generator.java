


// public class generator extends window {

//     int generate()
//     {
//         boolean place_flag = true;

//         // create treasure
//         for (int i = 0; i < 10; i++){
//             int type = publics.random(1, 10);
//             int type2 = publics.random(1, 10);

//             treasure current_t = new treasure(); // current treasure


//             if (type < 3){current_t = new treasure_small();}
//             else if (type < 7){current_t = new treasure();}
//             else if (type < 9){current_t = new treasure_big();}
//             else if (type < 10){current_t = new diamond();}

//             for (object obj: treasures){
//                 if (publics.distance(current_t.x, current_t.y, obj.x, obj.y) < treasure.density){
//                     place_flag = false;

//                 }
//             }
//             if (place_flag){
//                 treasures.add(current_t);
//             } else {
//                 place_flag = true;
//                 i--;
//             }


//         }
//         // create rock 
//         for (int i = 0; i < 10; i++){
//             rock current_s = new rock();

//             for (object obj: treasures){
//                 if (publics.distance(current_s.x, current_s.y, obj.x, obj.y) < 50){
//                     place_flag = false;

//                 }
//             }
//             if (place_flag){
//                 treasures.add(current_s);
//             } else {
//                 place_flag = true;
//                 i--;
//             }
//         }
    
    
//     }

//     static object[] level_begin(){
//         generator level_generate = new generator();
//         level_generate.generate();
//         // todo
//         return 0;
//     }
// }










