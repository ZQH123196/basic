package example.oenPkg;

import example.twoPkg.Two;


/**
 * @author ZQH12
 */
public class One {

    public static void main(String[] args) {
        System.out.println( Two.echo("msgFromOne") );
    }
}
