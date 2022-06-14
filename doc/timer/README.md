# timer

### Timer시작 종료
```java
public synchronized void setTimer(Message message) {
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            System.out.println("## Timeout " + message);
            
            int failCount = message.getFailCount();
            if(failCount >= queueProperties.getMaxFailCount()) {
                System.out.println("## message moved DLQ " + name + " " + message);
                messages.remove(message);
                deadLetterMessages.add(message);
            } else {
                failCount ++;
                message.setFailCount(failCount);
                message.setUsable(true);
            }
        }
        
    };
    
    
    QueueService.getInstance().getTimer().schedule(task, queueProperties.getProcessTimeout() * 1000);
    message.setTimerTask(task);
}

public synchronized void removeTimer(Message message) {
    TimerTask task = message.getTimerTask();
    if(task != null) {
        System.out.println("timer removed for " + message);
        task.cancel();
    }
}
```