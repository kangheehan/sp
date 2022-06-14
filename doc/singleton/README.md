# sp

#### 싱글톤 패턴
```java
public class QueueService {

	private QueueService() {
		restore();
	}

	private static class QueueServiceHolder {
		private QueueServiceHolder() {}
		private static final QueueService INSTANCE = new QueueService();
	}

	public static QueueService getInstance() {
		synchronized (QueueServiceHolder.class) {
			return QueueServiceHolder.INSTANCE;
		}
	}
}
```
