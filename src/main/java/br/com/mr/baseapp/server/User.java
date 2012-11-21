package br.com.mr.baseapp.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
@SequenceGenerator(name = "USER_SEQUENCY", sequenceName = "USER_SEQUENCY")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCY")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "pass")
	private String pass;

	public User() {
	}

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
