package org.exquisitus.server.domainmodel;

/**
 * Simple Message Entity 
 * 
 * @author muzero
 */

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE")
public class Message {
	
	@Id @GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="TEXT", nullable=false)
	private String text;
	
	@Column(name="USEROWNER", nullable=true)
	private User userOwner;
	
	@Column(name="DATE")
	private Timestamp date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
