/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SnakeGame;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class MainClass {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Snake Game");
        frame.setBounds(10,10,905,700);//top and down 10pixels, width 905 and height 700
        frame.setResizable(false);//user cannot resize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when we close the frame it should close it
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.DARK_GRAY);//setting background
        frame.add(panel);//adding game panel componenet to the frame
        frame.setVisible(true);//making frame visible
    }
    
}
