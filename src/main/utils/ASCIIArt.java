package main.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIArt {

    //Start Application Sketches
    public static void printLogo() {

        System.out.println();
        System.out.println();

        printBall();

        System.out.println();
        System.out.println();

        printName();
    }

    public static void printName() {

        int width = 180;
        int height = 50;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 18));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("Champions League", 10, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
    }

    public static void printBall() {

        System.out.println("                                                                                   _,aaadP''''''Ybaaaa,,_                                      ");
        System.out.println("                                                                               ,adP,__,,,aaaadP'''''Y888888a,_                                 ");
        System.out.println("                                                                            ,a8888888P'''             'Y8888888b,                              ");
        System.out.println("                                                                         _a888888888'                   `Y88888888b,                           ");
        System.out.println("                                                                       ,d888888888P'                       '888888888b,                        ");
        System.out.println("                                                                     ,88888888P'Y8,                       ,P'   `''Y888b,                      ");
        System.out.println("                                                                   ,d8888P''     'Ya,                    ,P'         `Ya`b,                    ");
        System.out.println("                                                                  ,P88''           `Ya,                 ,P'            `b`Yi                   ");
        System.out.println("                                                                 d',P                `'Y,              ,P'              `Y 'i                  ");
        System.out.println("                                                               ,P' P'                   '888888888888888b                `b 'i                 ");
        System.out.println("                                                              ,P' d'                    d8888888888888888b                `b `b                ");
        System.out.println("                                                              d' d'                    ,888888888888888888b                I, Y,               ");
        System.out.println("                                                             ,f ,f                    ,88888888888888888888b               `b, b               ");
        System.out.println("                                                             d' d'                    d888888888888888888888b              ,88,I               ");
        System.out.println("                                                            ,P  8                    ,88888888888888888888888b,_          ,d8888               ");
        System.out.println("                                                            d'  8,                   d8888888888888888888888P'`'Ya,_     ,d88888               ");
        System.out.println("                                                            8  d88b,             ,adP''Y888888888888888888P'      `''Ya, d88888P               ");
        System.out.println("                                                            8 ,88888b,       ,adP''     `'Y8888888888888''             `'888888I               ");
        System.out.println("                                                            Y,88888888b, ,adP''             ''Y888888P'                  888888'               ");
        System.out.println("                                                            `888888888888P'                     ''YP'                    888888                ");
        System.out.println("                                                             I88888888888                          8                     88888I                ");
        System.out.println("                                                             `Y8888888888                          8                     88888'                ");
        System.out.println("                                                              `Y888888888                          8                     8888I                 ");
        System.out.println("                                                               `Y88888888                          8                     8P'8'                 ");
        System.out.println("                                                                `Y8888888,                         8                   ,d',d'                  ");
        System.out.println("                                                                 `b''''Y8b                         8                 ,d' ,d'                   ");
        System.out.println("                                                                   'b,   'Y,                       8               ,P' ,d'                     ");
        System.out.println("                                                                     'b,   'Ya,_                 ,d88ba,,___   _,aP' ,P'                       ");
        System.out.println("                                                                       'Ya_   ''Ya,_       _,,ad88888888888888P'' _,d'                         ");
        System.out.println("                                                                         `'Ya_    ''Yaaad88888888888888888888P _,d''                           ");
        System.out.println("                                                                             `'Ya,_     'Y888888888888888888P',d''                             ");
        System.out.println("                                                                                `''Ya,__`Y888888888888888P'''                                  ");
        System.out.println("                                                                                     ``'''''''''''''''                                         ");

    }

    //Navigation Sketches
    public static void  enter(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                         _____________                                           ");
        System.out.println("                                        |    __o__    |                                          ");
        System.out.println("                                   o    |     / \\     |                                         ");
        System.out.println("                                   ()                                                            ");
        System.out.println("                                   |\\,       o                                                  ");

        System.out.println("\n" + "************************************* Enter Data *****************************************");

    }

    public static void next(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                                  ______                                         ");
        System.out.println("                               o__        __o__  |      |\\                                      ");
        System.out.println("                              /|            |    |      |X\\                                     ");
        System.out.println("                              / > o        /|    |      |XX\\                                    ");

        System.out.println("\n" + "***************************** What would you like to do next? ****************************");
    }

    public static void fail(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                            _____________                                        ");
        System.out.println("                                           |   _         |    o                                  ");
        System.out.println("                                 /_o _\\    |  _o=--'_    |                                      ");
        System.out.println("                                   |                       /                                     ");
        System.out.println("                                  /|,    -   - -  -   -  -                                       ");

        System.out.println("\n" + "******************************************* FAILED ***************************************");
    }

    public static void success(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                            _____________                                        ");
        System.out.println("                                           |   _         |                                       ");
        System.out.println("                                 | o |     |  _o=--'_   o|                                       ");
        System.out.println("                                   |                   /                                         ");
        System.out.println("                                  /|,    -   - -  - -                                            ");

        System.out.println("\n" + "***************************************** SUCCEED ****************************************");
    }

    public static void penalties(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                         _____________                                           ");
        System.out.println("                                        |    __o__    |                                          ");
        System.out.println("                                        |     / \\     |                                         ");
        System.out.println("                               /   o       /_______/           /                                 ");
        System.out.println("                              /    ()                         /                                  ");
        System.out.println("                             /     |\\,      o                /                                  ");
        System.out.println("                            /_______________________________/                                    ");
        System.out.println("                                         '------'                                                ");

        System.out.println("\n" + "************************************* PENALTIES ******************************************");

    }

    public static void champion(){

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                       ___________                                               ");
        System.out.println("                                     _|           |_                                             ");
        System.out.println("                                   /  |           |  \\                                          ");
        System.out.println("                                   \\ _|           |_ /                                          ");
        System.out.println("                                      |           |                                             ");
        System.out.println("                                       \\         /                                              ");
        System.out.println("                                          '___'                                                  ");
        System.out.println("                                           | |                                                   ");
        System.out.println("                                          _|_|_                                                  ");
        System.out.println("                                         |_____|                                                 ");

        System.out.println("\n" + "*************************************** CHAMPION! ****************************************");

    }

    //End Application Sketches
    public static void end() {

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                                       -   \\O                                                   ");
        System.out.println("                                      -   / \\                                                   ");
        System.out.println("                                    -  __/ \\ `                                                  ");
        System.out.println("                                       `     \\, ()                                              ");
        System.out.println("                                  ^^^^^^^^^^^^^^^^^^^^^^                                         ");

        System.out.println("\n" + "******************************** You ended the Tournament ********************************" + "\n");

    }

    public static void complete() {

        System.out.println("\n" + "******************************************************************************************");

        System.out.println("                              ___________________________                                        ");
        System.out.println("                             |             |             |                                       ");
        System.out.println("                             |___          |          ___|                                       ");
        System.out.println("                             |_  |         |         |  _|                                       ");
        System.out.println("                            .| | |.       ,|.       .| | |.                                      ");
        System.out.println("                            || | | )     ( | )     ( | | ||                                      ");
        System.out.println("                            '|_| |'       `|'       `| |_|'                                      ");
        System.out.println("                             |___|         |         |___|                                       ");
        System.out.println("                             |             |             |                                       ");
        System.out.println("                             |_____________|_____________|                                       ");

        System.out.println("\n" + "***************************** You completed the Tournament *******************************" + "\n");
    }

}