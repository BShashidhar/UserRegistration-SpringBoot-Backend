
package com.cdac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.cdac.entity.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "emailId") })
public class User extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 1, max = 4)
	private String title;

	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String firstName;

	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String lastName;

	private Role role;

	@Column(nullable = false)
	private Gender gender;

	@Email
	@Column(unique = true, nullable = false, length = 45)
	private String emailId;

	@Column(nullable = false)
	private String password;

}
