package com.example.model;

import java.util.*;

public class WebPage {
	String url;
	
	HashSet<WebPage> links;
	
	WebPage(String url)
	{
		this.url=url;
		this.links = new HashSet<WebPage>();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HashSet<WebPage> getLinks() {
		return links;
	}

	public void setLinks(HashSet<WebPage> links) {
		this.links = links;
	}
	
	public void addLink(WebPage newLink)
	{
		this.links.add(newLink);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebPage other = (WebPage) obj;
		
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	

}
