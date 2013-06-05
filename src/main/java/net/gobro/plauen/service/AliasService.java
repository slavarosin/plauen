package net.gobro.plauen.service;


public interface AliasService {

	String getAlias(Integer gameId);

	public void freeReserves(Long gameDuration);
}
