package destp.net;

public class SimpleThread extends Thread{
    private Run run;

    public SimpleThread(Run run) {
        this.run = run;
    }

    @Override
    public void run() {
        run.ma();
    }
}
