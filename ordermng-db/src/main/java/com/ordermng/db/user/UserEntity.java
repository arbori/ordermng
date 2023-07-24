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
	public UserEntity(Long id, String email, String name, Boolean active) {
		super(email, name, active);

		this.id = id;
	}
	public UserEntity(UserEntity user) {
		super(user);

		this.id = user.getId();
	}

	@Id
	@Column(name = "user_id")
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_email", length = 32)
	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Column(name = "user_name", length = 40)
	@Override
	public String getName() {
		return super.getName();
	}

	@Column(name = "user_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
	
		return true;
	}
}
