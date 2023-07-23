package com.ordermng.db.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ordermng.core.user.User;

import javax.persistence.Column;

@Entity
@Table(name =  "ordermng_user")
public class UserEntity extends User {
	public UserEntity() {
		super();
	}
	public UserEntity(String email, String name, Boolean active) {
		super(email, name, active);
	}
	public UserEntity(UserEntity user) {
		super(user);
	}

	@Id
	@Column(name = "user_email")
	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Column(name = "user_name")
	@Override
	public String getName() {
		return super.getName();
	}

	@Column(name = "user_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}
}
