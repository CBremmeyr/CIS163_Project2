package Project2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public static void main(String arg[]){
    JFrame gui = new JFrame();

    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setTitle("Mine Sweeper (May the clicks be with you");

    gui.setContentPane(new MineSweeperPanel());
    gui.setSize(400,400)
    gui.setVisibile(true);





}
