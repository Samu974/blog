package edu.ecm.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#getId()
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#setId(java.lang.Long)
	 */

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	private String name;

	@Column
	private String email;

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#getName()
	 */
	 
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#setName(java.lang.String)
	 */
	 
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#getEmail()
	 */
	 
	public String getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.domain.Author#setEmail(java.lang.String)
	 */

	public void setEmail(String email) {
		this.email = email;
	}

}