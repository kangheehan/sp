# sp

#### 콘솔입력
```java
try (Scanner scanner = new Scanner(System.in)) {
  while (true) {
    String[] tokens = scanner.nextLine().split(" ");

    String id = tokens[0];
    String passwd = tokens[1];

    System.out.println("id:" + id);
    System.out.println("password:" + passwd);
  }
}
```

#### HTTP Client
```java
public class MyHttpClient {
  private HttpClient httpClient;
    
  public MyHttpClient() {
    httpClient = new HttpClient();
  }
  
  public void send(String uri) throws InterruptedException {
    Logger.print("Sending " + uri + " ...");
    httpClient.newRequest(uri)
      .method(HttpMethod.GET)
      .timeout(5, TimeUnit.SECONDS)
      .send(new BufferingResponseListener() {

        @Override
        public void onComplete(Result result) {
          if (result.isSucceeded()) {
            Logger.print(getContentAsString());
          } else {
            Logger.print("Failed");
            Logger.print(result);
          }
        }
      });
  }
  
  public HttpClient getHttpClient() {
    return httpClient;
  }
  
  public void start() throws Exception {
    httpClient.start();
  }
  
  public void stop() throws Exception {
    httpClient.stop();
  }
  
  public static void main(String[] args) throws Exception {
    MyHttpClient client = new MyHttpClient();
    client.start();
    for(int i=0; i<3; i++) {
      client.send("http://127.0.0.1:8080/mypath");
      client.send("http://127.0.0.1:8080/mypath2");
    }
//    client.stop();
  }
}
```

#### Thread
```java
public class CardServerLauncher {  
  public static void main(String[] args) throws Exception {
    CardSocketServer cardServer = new CardSocketServer();    
    Thread thread = new Thread(cardServer);
    thread.start();

    CardHttpServer cardHttpServer = new CardHttpServer();
    cardHttpServer.start();

    try(Scanner scanner = new Scanner(System.in)) {
      String line;
      while ((line = scanner.nextLine()) != null) {
        if (line.equals("QUIT")) {
          cardServer.close();
          cardHttpServer.stop();
          break;
        }
      }
    }
  }
}
```

#### 유틸리티
```java
public static String passwordEncryption(String input) throws NoSuchAlgorithmException {
  MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
  byte[] result = mDigest.digest(input.getBytes());
  StringBuffer sb = new StringBuffer();
  for (int i = 0; i < result.length; i++) {
    sb.append(Integer.toString((result[i] & 0xFF) + 0x100, 16).substring(1).toUpperCase());
  }

  return sb.toString();
}

public static long hourDiff(String strTime2, String strTime1) throws ParseException {
  SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");

  java.util.Date date1 = transFormat.parse(strTime1);
  java.util.Date date2 = transFormat.parse(strTime2);

  long gap = date2.getTime() - date1.getTime();

  return gap / 60 / 60 / 1000;
}

public static void intToByte(byte[] buffer, int offset, int num) {
  buffer[offset + 3] = (byte) (num >> 24);
  buffer[offset + 2] = (byte) (num >> 16);
  buffer[offset + 1] = (byte) (num >> 8);
  buffer[offset + 0] = (byte) (num);
}

public static int byteToInt(byte[] buffer, int offset) {
  int nRet = ((((int) buffer[offset + 3] & 0xff) << 24)
      | (((int) buffer[offset + 2] & 0xff) << 16)
      | (((int) buffer[offset + 1] & 0xff) << 8)
      | (((int) buffer[offset] & 0xff)));
  return nRet;
}

public static String getCurrentDateTimeString() {
  long time = System.currentTimeMillis();
  SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");
  String strTime = dayTime.format(new Date(time));
  return strTime;
}

public static String getCurrentDateString() {
  long time = System.currentTimeMillis();
  SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
  String strToday = today.format(new Date(time));
  return strToday;
}
```
