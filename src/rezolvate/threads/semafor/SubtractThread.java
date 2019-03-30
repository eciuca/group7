package rezolvate.threads.semafor;

import java.util.concurrent.Semaphore;

class SubtractThread extends MyThread {

    public SubtractThread(Semaphore sem, String threadName) {
        super(sem, threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    protected void doOperation() {
        Shared.count--;
    }
}
