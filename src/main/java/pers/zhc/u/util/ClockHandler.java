package pers.zhc.u.util;

public class ClockHandler<MsgType> {
    Thread clockThread;
    boolean start = true;
    private ParamReference<MsgType> paramReference;

    public ClockHandler(ClockHandlerCallback<MsgType> callback, long periodMillis) {
        clockThread = new Thread(() -> {
            while (start) {
                callback.callback(this.paramReference.param);
                try {
                    Thread.sleep(periodMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        paramReference = new ParamReference<>();
    }

    public void start() {
        this.clockThread.start();
    }

    public ParamReference<MsgType> getParamReference() {
        return this.paramReference;
    }

    public void stop() {
        this.start = false;
    }

    public static class ParamReference<T> {
        public T param;
    }
}
