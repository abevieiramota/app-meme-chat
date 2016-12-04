package br.com.abevieiramota.memechat.dao;

import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.abevieiramota.memechat.model.Meme;

@Repository
@Transactional
public class MemeDao {
	
	@Autowired
	private ServletContext ctx;
	
	@PersistenceContext
	private EntityManager manager;

	public void save(Meme contentMeme) {
		this.manager.persist(contentMeme);
	}

	public InputStream getImg(long id) {
		// TODO: gambs
		return this.ctx.getResourceAsStream("/images/lememe.jpg");
	}

}
