package cctv.bean;

import java.io.Serializable;

public class User extends BasePageBean implements Serializable {
    	private Integer userId;
	private String username;
	private String password;
	private String description;
	public Integer getUserId(){
		return this.userId;
	}
	public User setUserId(Integer userId){
		this.userId=userId;
		return this;
	}
	public String getUsername(){
		return this.username;
	}
	public User setUsername(String username){
		this.username=username;
		return this;
	}
	public String getPassword(){
		return this.password;
	}
	public User setPassword(String password){
		this.password=password;
		return this;
	}
	public String getDescription(){
		return this.description;
	}
	public User setDescription(String description){
		this.description=description;
		return this;
	}
;
}