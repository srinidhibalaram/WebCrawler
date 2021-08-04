package com.example.model;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WebCrawler {
	
	int maxDepth;
	Map<String,WebPage> pages;
	
	public WebCrawler(int d)
	{
		this.maxDepth=d;
		pages = new HashMap<String,WebPage>();
	}
	
	public WebPage addWebPage(String url)
	{
		
		WebPage page = pages.getOrDefault(url, new WebPage(url));
		pages.putIfAbsent(url,page);
		
		return page;
		
	}
	
	public void connect(WebPage page,String url)
	{
		WebPage newPage = this.addWebPage(url);
		page.addLink(newPage);
		
	}
	
	
	
	public void crawl(String url)
	{
		int d=0;
		Document doc;
		Map<WebPage,Integer> visited = new HashMap<WebPage,Integer>();
		WebPage page = pages.getOrDefault(url, new WebPage(url));
		pages.putIfAbsent(url, page);
		visited.put(page, 2);
		Queue<WebPage> currentLevel = new LinkedList<WebPage>();
		Queue<WebPage> nextLevel = new LinkedList<WebPage>();
		currentLevel.add(page);
	    try {
           while(!nextLevel.isEmpty())
           {
	    	WebPage currentPage = currentLevel.poll();
	    	visited.put(page, 1);
	        doc = Jsoup.connect(currentPage.getUrl()).get();

	        // get all links
	        Elements links = doc.select("a[href]");
	        for (Element link : links) {

	            // get the value from href attribute
	           String newUrl = link.attr("href");
	           WebPage newPage = pages.getOrDefault(newUrl, new WebPage(newUrl));
	           pages.putIfAbsent(newUrl, newPage);
	           if(visited.getOrDefault(newPage,0)==0)
	           {
	           currentPage.addLink(newPage);
	           nextLevel.add(newPage);
	           visited.putIfAbsent(newPage, 2);
	           }
	           
	            
	        }
	        
	        if(currentLevel.isEmpty())
	        {
	        	 d++;
	        	 Queue<WebPage> temp = new LinkedList<WebPage>();
	        	 currentLevel = nextLevel;
	        	 nextLevel = temp;
	        	 
	        	 
	        }
	           if(d==this.maxDepth)
	        	   break;
	        
           }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
