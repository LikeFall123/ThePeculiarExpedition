package map;
import java.io.FileWriter;
import java.io.IOException;

import static map.RandomNumber.randomNumber;

/**
 * Legeneral egy random mappet, ha nincsen bemeneti .txt fajl
 */

public class GenerateRandomMap {

    /**
     * A terkep felso fele tenger, a tobbi megadott % esellyel egy map elem
     * @param c hanyadik kuldetes
     */

    public static void genRanMap(char c){
        if(c<='5') {
            Show.lines=16;
            Show.columns=16;
            for (int i = 0; i < 3; i++) {
                Show.lvl[i] = new String("0000000000000000");
            }
            int h = randomNumber(0,Show.lines);
            String s = "";
            for(int i=0;i<h;i++){
                s = s+'0';
            }
            s=s+'h';
            for(int i=h+1;i<Show.lines;i++){
                s=s+'0';
            }
            Show.lvl[3]=s;
            for(int i=4;i<Show.lines; i++){
                String line ="";
                for(int j=0;j<Show.columns;j++){
                    if(i==4 && j==h){line = line +'i';}else{
                        int r = randomNumber(0,90);
                        if(0<=r && r<40){line=line+'1'; }
                        if(40<=r && r<50){line=line+'2'; }
                        if(50<=r && r<60){line=line+'3'; }
                        if(60<=r && r<63){line=line+'f'; }
                        if(63<=r && r<65){line=line+'o'; }
                        if(65<=r && r<68){line=line+'b'; }
                        if(68<=r && r<=100){line=line+'4'; }
                    }

                }
                Show.lvl[i]=line;
            }
            for(int i=4;i<Show.lines;i++){
                for(int j=0;j<Show.columns;j++){
                    if(Show.lvl[i].charAt(j)=='3'){
                        if(i-1>=4 && j-1>=0 && i+1<Show.lines && j+1<Show.lines){
                            for(int k=i-1;k<=i+1;k++){
                                StringBuilder sb = new StringBuilder(Show.lvl[k]);
                                for(int g=j-1;g<=j+1;g++){
                                    if(Show.lvl[k].charAt(g)=='1'){sb.setCharAt(g,'n');}
                                }
                                Show.lvl[k]=sb.toString();
                            }
                        }
                    }
                }
            }

            int px = randomNumber(0,Show.columns);
            int py = randomNumber(10,Show.lines);
            System.out.println("x:"+px+" ,py:"+py);
            StringBuilder sb = new StringBuilder(Show.lvl[py]);
            sb.setCharAt(px,'*');
            Show.lvl[py]=sb.toString();

            for(int i=0;i<Show.lines;i++){
                System.out.println(Show.lvl[i]);
            }

//            try {
//                FileWriter myWriter = new FileWriter("filename.txt");
//                for(int i=0;i<Show.lines;i++){
//                    myWriter.write(Show.lvl[i]+"\n");
//                }
//                myWriter.close();
//                System.out.println("Successfully wrote to the file.");
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
        }
    }
}
