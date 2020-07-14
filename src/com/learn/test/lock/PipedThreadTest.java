package com.learn.test.lock;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Description: 管道输入输出流 ： 对于Piped类型的流，必须先镜像绑定。也就是调用connect方法如果没有绑定就会抛出异常
 * @author: zhongxp
 * @Date: 7/14/2020 3:29 PM
 */
public class PipedThreadTest {
    public static void main(String[] args) throws IOException {

        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();
        in.connect(out);
        Thread t1 = new Thread(new Consummer(in), "PrintThread");
        t1.start();
        Thread t2 = new Thread(new Proudcer(out), "ProducerThread");
        t2.start();
    }
}

class Consummer implements Runnable {
    PipedReader in;
    Consummer(PipedReader in) {
        this.in = in;
    }
    @Override
    public void run() {
        int read = 0;
        try {
            while ((read = in.read()) != -1) {
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Proudcer implements Runnable {
    PipedWriter out;

    Proudcer(PipedWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        int write = 0;
        try {
            while ((write = System.in.read()) != -1) {
                out.write(write);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
