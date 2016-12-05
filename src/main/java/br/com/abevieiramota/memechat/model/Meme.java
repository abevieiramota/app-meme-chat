package br.com.abevieiramota.memechat.model;

import java.util.Objects;

public class Meme {

	private String filename;
	private long id;

	public Meme(long id, String filename) {
		this.id = id;
		this.filename = filename;
	}

	public String getFilename() {
		return this.filename;
	}

	public long getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Meme)) {
			return false;
		}

		Meme other = (Meme) obj;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

}
