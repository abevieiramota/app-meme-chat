package br.com.abevieiramota.memechat.dao;

import java.util.Arrays;
import java.util.List;

import br.com.abevieiramota.memechat.model.Message;

public class MessageDao {

	public List<Message> all() {
		return Arrays.asList(new Message("Hello World!"), new Message("Tudo bem?"));
	}
}
