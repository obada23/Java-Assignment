
package com.mycompany.obada;

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Node {
    int info;
    Node link;

    public Node(int info) {
        this.info = info;
        this.link = null;
    }
}

class LinkedStack {
    private Node stackTop;

    public LinkedStack() {
        this.stackTop = null;
    }

    public void initializeStack() {
        Node temp;
        while (this.stackTop != null) {
            temp = this.stackTop;
            this.stackTop = this.stackTop.link;
            temp = null;
        }
    }

    public boolean isEmpty() {
        return (this.stackTop == null);
    }

    public void push(int item) {
        Node newNode = new Node(item);
        newNode.link = this.stackTop;
        this.stackTop = newNode;
    }

    public void pop() {
        if (this.stackTop == null)
            System.out.println("The Stack Is Empty ");
        else {
            Node temp = this.stackTop;
            this.stackTop = this.stackTop.link;
            temp = null;
        }
    }

    public int top() {
        if (this.stackTop != null)
            return this.stackTop.info;
        else
            return 0;
    }

    private void copy(LinkedStack o) {
        if (o.stackTop == null)
            this.stackTop = null;
        if (this.stackTop != null)
            initializeStack();
        else {
            Node newNode, current, last;
            current = o.stackTop;
            this.stackTop = new Node(current.info);
            this.stackTop.link = null;
            last = this.stackTop;
            current = current.link;

            while (current != null) {
                newNode = new Node(current.info);
                newNode.link = null;
                last.link = newNode;
                last = newNode;
                current = current.link;
            }
        }
    }

    public LinkedStack copyStack(LinkedStack o) {
        LinkedStack newStack = new LinkedStack();
        newStack.copy(o);
        return newStack;
    }

    public LinkedStack assignStack(LinkedStack o) {
        if (this != o)
            copy(o);
        return this;
    }

    public LinkedStack(LinkedStack o) {
        this.stackTop = null;
        copy(o);
    }

    public String convertToHexadecimal(int X) {
    LinkedStack stack = new LinkedStack();

    while (X != 0) {
        int remainder = X % 16;
        X /= 16;
        if (remainder < 10)
            stack.push(remainder);
        else
            stack.push(remainder + 55);
    }

    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
        if (stack.top() < 10) {
            result.append(stack.top());
            stack.pop();
        } else {
            result.append((char) (stack.top()));
            stack.pop();
        }
    }

    return result.toString();
}
}

public class obadaD extends JFrame implements ActionListener{
    
    private JButton exit=null,convert=null;
    private JLabel L1=null;
    private JTextField empty=null;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object T = e.getSource();
        if(T==exit)
        {
            dispose();
        }
        /*
        else if(T==convert)
        {
            String S = empty.getText();
            L1.setText("The number in Hex is ---> " + S.);
        }
        */
        else if (T == convert) {
    LinkedStack x = new LinkedStack();

    String S = empty.getText();
    int R = Integer.parseInt(S);
    if(R<0)
    {
        L1.setText("non valid number please try again");
    }
    else{
    String hexResult = x.convertToHexadecimal(R);
    L1.setText("The number in Hex is ---> " + hexResult);
    }
    }   
        System.out.println("pressed-->"+e.getActionCommand());
        
    }
    
    private void init()
    {
        
        L1 = new JLabel("The number in Hex is --->");
        empty = new JTextField(15);
        convert = new JButton("Convert");
        exit = new JButton("Exit");
        
        JPanel N=new JPanel();
        add(N,BorderLayout.NORTH);
        N.setBorder(BorderFactory.createTitledBorder("Enter a positive number :"));
        N.add(empty);
        
        
        
        JPanel C=new JPanel();
        add(C,BorderLayout.CENTER);
        C.setBorder(BorderFactory.createTitledBorder("The resualt "));
        C.add(L1);
        
        JPanel S=new JPanel();
        add(S,BorderLayout.SOUTH);
        S.add(convert);
        S.add(exit);
        convert.setToolTipText("press me to compute the number");

        
        
        
        
        /*
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                dispose();
            
            }
        
        }
        );
        */
        
        exit.addActionListener(this);
        convert.addActionListener(this);
        empty.addActionListener(this);
        
        C.setBorder(BorderFactory.createLineBorder(Color.gray));
        N.setBorder(BorderFactory.createLineBorder(Color.gray));
        S.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        
        
        
    }
    
    
    public obadaD()
    {
        super("Convert Dec to Hex");
        init();
        
        
        
        
       
        
        
        setSize(300,400);
        setLocation(200,300);
        setBounds(200,200,400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
       
        new obadaD();
        
    }

    
}
