package sp.utils;

import com.google.gson.GsonBuilder;

public class GsonUtil {
	public void stringToObject() {
//		QueueProperties queueProperties = new Gson().fromJson(contents, QueueProperties.class);
	}
	
	public String ExposeOnlyToString() {
		// @Expose
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this).toString();
	}
}
