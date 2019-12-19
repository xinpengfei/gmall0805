package com.example.demo;

import java.util.List;

//创建一个接口
@FunctionalInterface
interface Foo{
int slave(int x,int y);
default void lable(){

}
static int dele (){
    return 4;

}
}
/*
   拷贝小括号  写死右箭头  落实大括号
   @FunctionalInterface
   在函数接口中default 的普通方法可以有多个
   也可有静态代码块
   可有多个

 */

public class LambdaDamo {
    public static void main(String[] args) {
       /* Foo foo = new Foo() {
            @Override
            public int slave(int x, int y) {
                return 1;
            }
        };*/
        Foo foo=( x, y)->{
            return x+y;

        };

    }
}
