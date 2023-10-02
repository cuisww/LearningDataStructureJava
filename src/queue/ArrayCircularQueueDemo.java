package queue;

import java.util.Scanner;

/*
* 1.front变量的含义做一个调整, fron就指向队列的第一个元素, 也就是arr[front]就是队列的第一个元素.
* front的初始值设置为0.
* 2.rear变量的含义做一个调整: rear指向队列的最后一个元素的后一个位置. 并且在数组中会空出一个位置.
* rear的初始值为0.
* 3.当队列满时, 条件是(rear + 1)%maxSize = front. 加1是因为预留了一个位置.
* 举个例子, 第一次满时, rear为maxSize-1, 这个地方没有数据. 这时候如果前面空出来了, 而且再进来一个, rear应该等于0.
* 4.当队列空时, rear = front
* 5.这种情况下, 队列中有效数据的个数(rear+maxSize-front)%maxSize*/
public class ArrayCircularQueueDemo {
    public static void main(String[] args){
        ArrayCircularQueue myq = new ArrayCircularQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){

            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加数据到队列");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 查看队列的头数据");
            key = scanner.next().charAt(0);

            switch (key){
                case 's':
                    myq.showQueue();
                    break;

                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    try {
                        myq.addQueue(value);

                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case 'g':
                    try{
                        int res = myq.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = myq.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }

        }
        System.out.println("程序退出");
    }
}

class ArrayCircularQueue{
    private int maxSize;//数组的最大容量
    private int front; //队列头, 指向的是队列的第一个数据的前一个位置.
    private int rear; //队列尾, 指向的是队列的最后一个数据.
    //创建数组存放队列
    private int[] queue;

    //创建队列的构造器
    public ArrayCircularQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        queue = new int[maxSize];
    }
    //判断队列是否满
    public boolean isFull() {
//        return (rear + 1) % maxSize == front;
        if(rear < front){
            return rear + 1 == front;
        } else{
            return rear + 1 - maxSize == front;
        }
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            throw new RuntimeException("数组满了!");//要throw new
        }
        queue[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据, 出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("数组是空的!");
        }

        int res = queue[front];
        front = (front + 1) % maxSize;
        return res;
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的, 没有数据");
            return;
        }

        for (int i = front; i < front + size(); i++){
            System.out.printf("第%d个 : %d\n", i - front, queue[i % maxSize]);
        }
    }

    public int size(){
//        return (rear + maxSize - front) % maxSize;
        if (rear < front) {
            return rear + maxSize - front;
        } else {
            return rear - front;
        }
    }

    //显示队列的头数据, 注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的");
        }
        return queue[front];
    }
}