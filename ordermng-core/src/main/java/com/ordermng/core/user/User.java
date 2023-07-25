package com.ordermng.core.user;

public class User {
	protected String email;
	protected String name;
	protected Boolean active = true;

	boolean isValid() {
		return email != null && !email.isEmpty() &&
			name != null && !name.isEmpty() &&
			active != null;
	}

	public User() {
	}
	public User(String email, String name, Boolean active) {
		this.email = email;
		this.name = name;
		this.active = active;
	}
	public User(User user) {
		this.email = user.email;
		this.name = user.name;
		this.active = user.active;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("User [email=").append(email).append(", name=").append(name).append(", active=").append(active).append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		return true;
	}

}
