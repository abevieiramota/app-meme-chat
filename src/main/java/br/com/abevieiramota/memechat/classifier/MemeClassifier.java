package br.com.abevieiramota.memechat.classifier;

import org.springframework.stereotype.Component;

import br.com.abevieiramota.memechat.model.Meme;

@Component
public class MemeClassifier {

	public Meme classify(String content) {
		Meme meme = new Meme();
		
		return meme;
	}

}
