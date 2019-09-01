package com.personaldata.pdmdemo.good;

import org.springframework.web.multipart.MultipartFile;

public class AddItemForm {
	//String create_time;
    String id;
	String category;
    String people;
    String event_location;
    String historical_time;
    String description;
    String web_link;
    String importance;
    String tag;
    String priority;
    MultipartFile multipartFile;
    MultipartFile multipartFileNew;
    String newfilePath;
	//private String create_time;



	public String getNewfilePath() {
		return newfilePath;
	}

	public void setNewfilePath(String newfilePath) {
		this.newfilePath = newfilePath;
	}

	public AddItemForm() {
		super();
	}
	
/*
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
*/

	public String getOriginalFilename() {		
		String fileName = getMultipartFile().getOriginalFilename();
		return fileName;
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public MultipartFile getMultipartFileNew() {
		return multipartFileNew;
	}

	public void setMultipartFileNew(MultipartFile multipartFileNew) {
		this.multipartFileNew = multipartFileNew;
	}
	
    public String getId() { return id; }
    public void setId(String id) { this.id = id; } 
    
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getHistorical_time() {
		return historical_time;
	}

	public void setHistorical_time(String historical_time) {
		this.historical_time = historical_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb_link() {
		return web_link;
	}

	public void setWeb_link(String web_link) {
		this.web_link = web_link;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}	
}
