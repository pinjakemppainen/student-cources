package com.student.model;

public class Course {
	private String id;
	private String name;
	private String description;

	public Course() {

	}

	public Course(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format(
                "Course [id=%s, name=%s, description=%s]", id, name,description);
	}
}