package rezolvate.threads.semafor;

import java.util.concurrent.Semaphore;

class AddThread extends MyThread {

    public AddThread(Semaphore sem, String threadName) {
        super(sem, threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    protected void doOperation() {
        Shared.count++;
    }

}
