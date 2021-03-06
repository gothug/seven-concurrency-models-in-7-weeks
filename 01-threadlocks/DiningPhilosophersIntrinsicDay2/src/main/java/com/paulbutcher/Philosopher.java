/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/
package com.paulbutcher;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.Random;

class Philosopher extends Thread {

  private boolean eating;
  private Philosopher left;
  private Philosopher right;
//  private ReentrantLock table;
//  private Condition condition;
  private Random random;
  private int thinkCount;

  public Philosopher() {
    eating = false;
//    this.table = table;
//    condition = table.newCondition();
    random = new Random();
  }

  public void setLeft(Philosopher left) { this.left = left; }
  public void setRight(Philosopher right) { this.right = right; }

  public void run() {
    try {
      while (true) {
        think();
        eat();
      }
    } catch (InterruptedException e) {}
  }

  private void think() throws InterruptedException {
    synchronized (this) {
      eating = false;
    }

    synchronized (this.left) {
      notifyAll();
    }

    synchronized (this.right) {
      notifyAll();
    }

    ++thinkCount;

//    if (thinkCount % 10 == 0)
      System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");

    Thread.sleep(2000);
  }

  private synchronized void eat() throws InterruptedException {
    System.out.println(this + ": trying to eat..");
    while (left.eating || right.eating) {
      try {
        wait();
      } catch (InterruptedException e) {}
    }

    eating = true;

    Thread.sleep(100);
  }
}
