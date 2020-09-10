package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
@Entity
@Table(name = "book")
public class BookModel {
	@Id
	private Long id;
	private String title;
	private String author;
	private Double price;
	private String language;
}
