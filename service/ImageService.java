package net.gobro.plauen.service;

import java.io.IOException;

import net.gobro.plauen.model.Image;


public interface ImageService {

	byte[] fetch(Integer id) throws IOException;

	void remove(Integer id);

	Image fetchObject(Integer id);

}
