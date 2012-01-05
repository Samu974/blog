package edu.ecm.blog.util;

import java.util.*;

public class TagCloud {

	private List<String> tags = new ArrayList<String>();

	public int size() {
		return tags.size();
	}

	public void add(String... tag) {
		if (tag == null) {
			return;
		}
		
		for (String t : tag) {
			if (this.tags.contains(t)==false){			
			this.tags.add(t);
			}
		}

	}
	
	public boolean contains(String tag){
		return tags.contains(tag);
	}	
}
