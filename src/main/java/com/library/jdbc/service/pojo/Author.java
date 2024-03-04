package com.library.jdbc.service.pojo;

import java.util.Objects;

public class Author {
	
	private String authorId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fullNameRodit;
	private String exId;
	private String url;
	private String email;
	private String homePage;
	private Integer lvl;
	private Integer relation;
	private Integer subjectId;
	private String nickname;
	private String hubId;

	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getExId() {
		return exId;
	}
	public void setExId(String exId) {
		this.exId = exId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public Integer getRelation() {
		return relation;
	}
	public void setRelation(Integer relation) {
		this.relation = relation;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHubId() {
		return hubId;
	}
	public void setHubId(String hubId) {
		this.hubId = hubId;
	}	
	public String getFullNameRodit() {
		return fullNameRodit;
	}
	public void setFullNameRodit(String fullNameRodit) {
		this.fullNameRodit = fullNameRodit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, exId, firstName, fullNameRodit, homePage, hubId, authorId, lastName, lvl, middleName,
				nickname, relation, subjectId, url);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(email, other.email) && Objects.equals(exId, other.exId)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(fullNameRodit, other.fullNameRodit)
				&& Objects.equals(homePage, other.homePage) && Objects.equals(hubId, other.hubId)
				&& Objects.equals(authorId, other.authorId) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(lvl, other.lvl) && Objects.equals(middleName, other.middleName)
				&& Objects.equals(nickname, other.nickname) && Objects.equals(relation, other.relation)
				&& Objects.equals(subjectId, other.subjectId) && Objects.equals(url, other.url);
	}
	
	@Override
	public String toString() {
		return "Author [id=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", fullNameRodit=" + fullNameRodit + ", exId=" + exId + ", url=" + url + ", email=" + email
				+ ", homePage=" + homePage + ", lvl=" + lvl + ", relation=" + relation + ", subjectId=" + subjectId
				+ ", nickname=" + nickname + ", hubId=" + hubId + "]";
	}
	
	
}
