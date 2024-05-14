
package com.mycompany.oopsuii;

import java.util.Scanner;

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

    public void convertToBinary(int X) {
        int b;
        while (X != 0) {
            b = X % 2;
            X /= 2;
            push(b);
        }
        while (!isEmpty()) {
            System.out.print(top());
            pop();
        }
        System.out.println();
    }

    public void convertToOctal(int X) {
        int b;
        while (X != 0) {
            b = X % 8;
            X /= 8;
            push(b);
        }
        while (!isEmpty()) {
            System.out.print(top());
            pop();
        }
        System.out.println();
    }

    public void convertToHexadecimal(int X) {
        int b;
        while (X != 0) {
            b = X % 16;
            X /= 16;
            if (b < 10)
                push(b);
            else
                push(b + 55);
        }
        while (!isEmpty()) {
            if (top() < 10) {
                System.out.print(top());
                pop();
            } else {
                System.out.print((char) (top()));
                pop();
            }
        }
        System.out.println();
    }
}

public class oop {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------");
        LinkedStack x = new LinkedStack();
        int n;
        int X;
        Scanner in = new Scanner(System.in);

            System.out.println("-------------------------------------------------------------------");
            System.out.print("Enter a positive integer:  ");
            X = in.nextInt(); 
            in = null;
            System.out.print("The Result In Hexadecimal Num Is : ");
            x.convertToHexadecimal(X);
            System.out.println("-------------------------------------------------------------------\n");
            
       System.exit(0);
        
    }
}
