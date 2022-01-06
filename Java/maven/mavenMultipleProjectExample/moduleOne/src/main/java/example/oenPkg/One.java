package example.oenPkg;

import example.twoPkg.Two;


public class One {

    public static void main(String[] args) {
        System.out.println( Two.echo("msgFromOne") );
    }
}
