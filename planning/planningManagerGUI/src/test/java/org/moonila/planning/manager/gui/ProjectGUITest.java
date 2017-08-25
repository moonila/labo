package org.moonila.planning.manager.gui;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.moonila.planning.manager.core.to.ProjectTO;
import org.moonila.planning.manager.gui.admin.ProjectGUI;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProjectGUITest extends Assert{
	
	@Test
	public void homePage() throws Exception {
	    final WebClient webClient = new WebClient();
	    try {
	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	        final String pageAsXml = page.asXml();
	        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	        final String pageAsText = page.asText();
	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	    }catch(Exception e){
	    	
	    }
	}
	
	@Test
	public void toto() throws Exception {
		 final WebClient webClient = new WebClient();
		ProjectGUI pG = new ProjectGUI();
		
		List<ProjectTO> allProjects =  pG.getAllProjects();
		assertEquals(allProjects.size(), 5);
	}
}
