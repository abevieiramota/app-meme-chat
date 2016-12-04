package br.com.abevieiramota.memechat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meme {
	
	@Id
	private long id;

	public void generateImg() {
	}

	public long getId() {
		return this.id;
	}
}
