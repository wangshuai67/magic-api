package org.d8s.magicapi.modules;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.d8s.script.annotation.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mongo FindIterable 方法扩展
 */
public class MongoFindIterableExtension {

	@Comment("结果转为List")
	public List<Map<String, Object>> list(FindIterable<Document> iterable) {
		MongoCursor<Document> cursor = iterable.iterator();
		List<Map<String, Object>> result = new ArrayList<>();
		while (cursor.hasNext()) {
			result.add(cursor.next());
		}
		return result;
	}

}
