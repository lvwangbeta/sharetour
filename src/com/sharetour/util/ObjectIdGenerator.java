package com.sharetour.util;

import org.bson.types.ObjectId;

public class ObjectIdGenerator {
	public synchronized static ObjectId generate(){
		return new ObjectId();
	}
}
