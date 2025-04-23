package com.mycompany.app;

public class Sqrt
{
   double delta=0.00000001;
   double arg;

   public Sqrt(double arg) {
      this.arg=arg;
   }
   public double average(double x,double y) {
      return (x+y)/2.0;
   }
   public boolean good(double guess,double x) {
      return Math.abs(guess*guess-x)<delta;
   }
   public double improve(double guess,double x) {
      // При x = 0 избегаем деление на ноль
      if (x == 0) {
         return 0;
      }
      return average(guess,x/guess);
   }
   public double iter(double guess, double x) {
      // Специальная обработка для x = 0
      if (x == 0) {
         return 0;
      }
      
      if(good(guess,x))
         return guess;
      else
         return iter(improve(guess,x),x);
   }
   public double calc() {
      if (arg < 0) {
         throw new ArithmeticException("Невозможно вычислить квадратный корень отрицательного числа");
      }
      // Специальная обработка для arg = 0
      if (arg == 0) {
         return 0;
      }
      return iter(1.0,arg);
   }
} 