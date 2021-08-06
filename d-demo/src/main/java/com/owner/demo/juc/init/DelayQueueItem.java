package com.owner.demo.juc.init;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author lukewei
 * @date 2021/8/6 15:52
 */
@Data
@ToString
public class DelayQueueItem implements Delayed {

    private long time;

    private String name;

    public DelayQueueItem(String name,Long time, TimeUnit unit){
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time):0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayQueueItem item = (DelayQueueItem) o;
        long diff = this.time - item.time;
        if(diff <= 0){
            return -1;
        }
        return 1;
    }
}
