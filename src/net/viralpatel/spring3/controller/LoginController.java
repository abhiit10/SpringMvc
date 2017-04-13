package net.viralpatel.spring3.controller;

import java.io.FileReader;
import java.net.BindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	/**
	 * 
	 * @param searchMap
	 * @return
	 */
	HashMap getJsonMap(HttpServletRequest request){
		JSONParser parser = new JSONParser();
		HashMap resultMap = new HashMap();
		try {
			Object obj = parser.parse(new FileReader("E:/postedJobs.json"));
	 
			JSONObject jsonObject = (JSONObject) obj;
			HashMap<Object, Object> map = new HashMap<Object, Object>(jsonObject);
			
			ArrayList<Object> allPostedJobs = (ArrayList<Object>)map.get("postedJobs");
			ArrayList<HashMap> allJobs = new ArrayList<HashMap>();
			ArrayList<Object> shortListedCdt = new ArrayList<Object>();
		
			Iterator<Object> itr = allPostedJobs.iterator();
			while(itr.hasNext()){
				HashMap eachMap = new HashMap();
				eachMap = (HashMap) itr.next();
				
				/*System.out.println("==========="+eachMap.get("title"));
				System.out.println("==========="+request.getParameter("title"));*/
				
				//System.out.println("------is empty---->"+request.getParameter("title").trim().isEmpty());
				
				if(request.getParameter("title").trim().isEmpty() || eachMap.get("title").toString().toLowerCase().contains(request.getParameter("title").trim().toLowerCase())){
					HashMap<String, Object> openJobs = new HashMap<String, Object>();
					if(request.getParameter("open")!= null && request.getParameter("open").equals("on") && eachMap.get("status").equals("Open")){
						openJobs.put("company", eachMap.get("company") );
						openJobs.put("title", eachMap.get("title") );
						openJobs.put("datePosted", eachMap.get("datePosted") );
						openJobs.put("status", eachMap.get("status") );
						allJobs.add(openJobs);
					}
					if(request.getParameter("close")!= null && request.getParameter("close").equals("on") && eachMap.get("status").equals("Close")){
						openJobs.put("company", eachMap.get("company") );
						openJobs.put("title", eachMap.get("title") );
						openJobs.put("datePosted", eachMap.get("datePosted") );
						openJobs.put("status", eachMap.get("status") );
						allJobs.add(openJobs);
					}
				}
				//All Jobs posted containing status open and closed both 
				
				resultMap.put("Searched", allJobs) ;
				
				//Fetching shortListed Candidates
				String filterCom = request.getParameter("company") != null ? request.getParameter("company").trim() : "" ;
				String title = request.getParameter("title") != null ? request.getParameter("title").trim() : "" ;
				
				//System.out.println("filterCom-------"+filterCom);
				if(eachMap.get("company").equals(filterCom) && eachMap.get("title").equals(title)){
					shortListedCdt.addAll((ArrayList)eachMap.get("ShortListed"));
					resultMap.put("ShortListed", shortListedCdt) ;
				}
				
				String reqName = request.getParameter("name")!= null ? request.getParameter("name").trim() : "" ;
				String reqWorkPlace = request.getParameter("worksAt")!= null ? request.getParameter("worksAt").trim() : "" ;
				
				HashMap interView = new HashMap();
				ArrayList interViews = null;
				//if( eachMap.get("ShortListed") !=null ){
					interViews = (ArrayList) eachMap.get("ShortListed");
					System.out.println("==interViews======"+interViews);
					if(interViews != null ){
						Iterator interViewItr = interViews.iterator();
						while(interViewItr.hasNext()){
							HashMap allShortListed = (HashMap) interViewItr.next();
							
							System.out.println("allShortListed------------"+allShortListed);
							if(allShortListed.get("name").equals(reqName) && allShortListed.get("worksAt").equals(reqWorkPlace)){
								resultMap.put("InterViews", allShortListed.get("interviews")) ;
								
							}
						}
					}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--resultMap------------->"+resultMap);
		return resultMap ;
	}
	
   @RequestMapping("/login")
   public ModelAndView login(HttpServletRequest request, HttpServletResponse response, BindException errors){
	   
	   String username = request.getParameter("username");
	   String pwd = request.getParameter("password");
	   return new ModelAndView("hello", "message", username + pwd);
   }
   
   @RequestMapping(value="/AddUser",method=RequestMethod.POST)
   public @ResponseBody String addUser(HttpServletRequest request, HttpServletResponse response, BindException errors){
       String returnText = "adsa";
       return returnText;
   }
   
   @RequestMapping(value="/searchPostedJobs",method=RequestMethod.POST)
   public ModelAndView searchPostedJobs(HttpServletRequest request, HttpServletResponse response, BindException errors){
	   
	   HashMap resultMap = getJsonMap(request);
       return new ModelAndView("postedJobs", "resultMap", resultMap);
   }

   @RequestMapping(value="/getShortlistedCandidate",method=RequestMethod.POST)
   public ModelAndView getShortlistedCandidate(HttpServletRequest request, HttpServletResponse response, BindException errors){
	   HashMap resultMap = getJsonMap(request);
       return new ModelAndView("shortListed", "resultMap", resultMap);
   }
   
   @RequestMapping(value="/getInterviewResults",method=RequestMethod.POST)
   public ModelAndView getInterviewResults(HttpServletRequest request, HttpServletResponse response, BindException errors){
	   HashMap resultMap = getJsonMap(request);
	   return new ModelAndView("interviews", "resultMap", resultMap);
   }
}
