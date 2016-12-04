package br.com.abevieiramota.memechat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meme {
	
	@Id
	private Long id;

	public void generateImg() {
	}

	public Long getId() {
		return this.id;
	}
}
