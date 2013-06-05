package net.gobro.plauen.service;

import net.gobro.plauen.model.Text;

public interface TextService {

	Text fetch(Integer id);

	Text store(Text text);

}
