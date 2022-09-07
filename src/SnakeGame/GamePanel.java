/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author HP
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener{
    private int[] xposition = new int[750];
    private int[] yposition = new int[750];
    private int snake_size = 3;
    private int[] xpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] ypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,620};
    private Random random = new Random();
    private int enemyX, enemyY;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private int move =0;
    private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy.png"));
    private Timer gametimer;
    private int delay = 100;
    private int score = 0;
    private boolean gameover = false;
    GamePanel()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        gametimer = new Timer(delay,this);
        gametimer.start();
        newEnemy();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);//drawing white border for title
        g.drawRect(24,74,851,576);//drawing white border for game board
        snaketitle.paintIcon(this, g, 25, 11);//adding snake title
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        //when game will start the snake size is three and it grows as it eat apple
        if(move==0)
        {
            xposition[0] = 100;//head of snake
            xposition[1] = 75;//body of snake
            xposition[2] = 50;//body of snake
            yposition[0] = 100;
            yposition[1] = 100;
            yposition[2] = 100;
            //move++;
        }
        if(left)//if left direction then draw snake head accordingly
        {
            leftmouth.paintIcon(this, g,xposition[0], yposition[0]);
        }
         if(right) //if right direction then draw snake head accordingly
        {
            rightmouth.paintIcon(this, g,xposition[0], yposition[0]);
        }
         if(up)////if up direction then draw snake head accordingly
        {
            upmouth.paintIcon(this, g,xposition[0], yposition[0]);
        }
          if(down)//if down direction then draw snake head accordingly
        {
            downmouth.paintIcon(this, g,xposition[0], yposition[0]);
        }
         //drawing snake body
        for(int i=0;i<snake_size;i++)
        {
            snakeimage.paintIcon(this, g, xposition[i],yposition[i]);
        }
        enemy.paintIcon(this, g,enemyX, enemyY);
        if(gameover)
        {
            g.setColor(Color.red);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);
            
            g.setFont(new Font("Arial",Font.PLAIN,50));
            g.drawString("Press Space to Restart",320,350);
            
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,14));
        g.drawString("Score : "+score,750,30);
        g.drawString("Length : "+snake_size,750,50);
        g.dispose();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       //moving the snake body
       for(int i=snake_size-1;i>0;i--)
       {
           xposition[i] = xposition[i-1];
           yposition[i] = yposition[i-1];
       }
        //moving the snake in x and y direction
       if(left)
        {
            xposition[0] = xposition[0]-25;
           
        }
        if(right)
        {
            xposition[0] = xposition[0] + 25;
        }
        if(up)
        {
            yposition[0] = yposition[0] - 25;
        }
        if(down)
        {
            yposition[0] = yposition[0] + 25;
        }
        if(xposition[0]>850)
        {
            xposition[0] = 25;
        }
        if(xposition[0]<25)
        {
            xposition[0] = 850;
        }
        if(yposition[0] > 625)
        {
            yposition[0] = 75;
        }
        if(yposition[0] < 75)
        {
            yposition[0] = 625;
        }
        collideswithenemy();
        collideswithbody();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
           restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT &&(!right))
        {
            left = true;
            right = false;
            up = false;
            down = false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT &&(!left))
        {
            right = true;
            left = false;
            up = false;
            down = false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down))
        {
            up = true;
            down = false;
            left = false;
            right = false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN &&(!up))
        {
            down = true;
            up = false;
            left = false;
            right = false;
            move++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void newEnemy() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        enemyX = xpos[random.nextInt(34)];
        enemyY = ypos[random.nextInt(23)];
        for(int i=snake_size-1;i>0;i--)
        {
            if(xposition[i] == enemyX && yposition[i]==enemyY)
            {
                newEnemy();
            }
        }
    }

    private void collideswithenemy() {
      if(xposition[0] == enemyX && yposition[0]==enemyY)
      {
          newEnemy();
          snake_size++;
           score++;
      }
    }

    private void collideswithbody() {
       for(int i=snake_size-1;i>0;i--)
       {
           if(xposition[i]==xposition[0] && yposition[i]==yposition[0])
           {
               gametimer.stop();
               gameover = true;
           }
       }
    }

    private void restart() {
        
        gameover = false;
        move =0;
        score = 0;
        snake_size = 3;
        right = true;
        left = false;
        up = false;
        down = false;
        gametimer.start();
        repaint();
    }
    
    
    
    
}
