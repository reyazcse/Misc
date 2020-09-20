//ctci 15.5 : first() then second() and then third() should be executed.
//We have three threads, first thread calls first(), second calls second() and so on
/*
	ISSUE: the order of the methods is not correct!!! Not sure why
 * */
package ctci;

import java.util.concurrent.Semaphore;

class SemaphoreFoo {
	public int pause = 1000;
	Semaphore sem1;
	Semaphore sem2;
	public SemaphoreFoo() {
		sem1 = new Semaphore(1);
		sem2 = new Semaphore(2);
		try {
			sem1.acquire();
			sem2.acquire();
		}catch(Exception e) {
			System.out.println("exception occurred in SemaphoreFoo cconstructor");
		}
	}
	public void first() {
		try {
			System.out.println("started executing 1");
			Thread.sleep(pause);
			System.out.println("finished executing 1");
			sem1.release();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void second() {
		try {
			sem1.acquire();
			sem1.release();
			System.out.println("started executing 2");
			Thread.sleep(pause);
			System.out.println("finished executing 2");
			sem2.release();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void third() {
		try {
			sem2.acquire();
			sem2.release();
			System.out.println("started executing 3");
			Thread.sleep(pause);
			System.out.println("finished executing 3");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
class SemaphoreThread extends Thread {
	String methodName;
	SemaphoreFoo obj;
	public SemaphoreThread(SemaphoreFoo foo, String name) {
		obj = foo;
		this.methodName = name;
		
	}
	public void run() {
		if(methodName.contentEquals("first")) {
			obj.first();
		}else if (methodName.equals("second")) {
			obj.second();
		}else  if (methodName.equals("third")) {
			obj.third();
		}
	}
}
public class SemaphorExample {
	

	public static void main(String[] args) {
		SemaphoreFoo obj = new SemaphoreFoo();
		SemaphoreThread th1 = new SemaphoreThread(obj, "first");
		SemaphoreThread th2 = new SemaphoreThread(obj, "second");
		SemaphoreThread th3 = new SemaphoreThread(obj, "third");
		th1.start();
		th2.start();
		th3.start();

	}

}
