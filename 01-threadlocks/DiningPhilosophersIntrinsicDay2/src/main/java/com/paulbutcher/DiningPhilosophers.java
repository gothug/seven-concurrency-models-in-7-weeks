/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/
package com.paulbutcher;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    int N = 3;

    Philosopher[] philosophers = new Philosopher[N];
//    ReentrantLock table = new ReentrantLock();
    
    for (int i = 0; i < N; ++i)
      philosophers[i] = new Philosopher();

//    System.out.println(philosophers[6]);
//    System.out.println(philosophers[7]);
    for (int i = 0; i < N; ++i) {
      philosophers[i].setLeft(philosophers[(i + (N - 1)) % N]);
      philosophers[i].setRight(philosophers[(i + 1) % N]);
      philosophers[i].start();
    }
    for (int i = 0; i < N; ++i)
      philosophers[i].join();
  }
}
