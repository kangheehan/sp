# gson

#### Gson typetoken
```java
public void backup() {
    try (FileWriter writer = new FileWriter("queuestatus.json")) {
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(messageQeuueMap, writer);	
    } catch(Exception e) {
        e.printStackTrace();
    }
}

public void restore() {
    try(FileReader reader = new FileReader("queuestatus.json")) {
        Type type = new TypeToken<Map<String, MessageQueue>>(){}.getType(); 
        messageQeuueMap = new Gson().fromJson(new JsonReader(reader), type);
    } catch(Exception e) {
        e.printStackTrace();
    }
    
}
```