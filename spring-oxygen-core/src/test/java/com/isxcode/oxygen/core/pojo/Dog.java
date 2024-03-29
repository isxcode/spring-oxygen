package com.isxcode.oxygen.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isxcode.oxygen.core.excel.ExcelType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

@JsonIgnoreProperties({"location"})
public class Dog extends DefaultHandler {

	@ExcelType(cellName = "name")
	private String name;

	@ExcelType(cellName = "age")
	private Integer age;

	private String localName;

	public Dog(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public Dog() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		this.localName = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) {

		String date = new String(ch, start, length);
		if (!"\n".equals(date)) {
			switch (this.localName) {
				case "name":
					this.name = date;
					break;
				case "age":
					try {
						this.age = Integer.parseInt(date);
					} catch (NumberFormatException e) {
						this.age = 0;
					}
					break;
				default:
			}
		}
	}

	@Override
	public String toString() {
		return "Dog{"
				+ "name='"
				+ name
				+ '\''
				+ ", age="
				+ age
				+ ", localName='"
				+ localName
				+ '\''
				+ '}';
	}
}
