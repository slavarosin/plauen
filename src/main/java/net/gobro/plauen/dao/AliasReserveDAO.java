package net.gobro.plauen.dao;

public interface AliasReserveDAO {
	/**
	 * Generate alias.
	 * @param gameId game id to generate alias for
	 * @return alias generated
	 */
	Integer getAlias(Integer gameId);
	
	/**
	 * Clear old reserves from database. Currently rows will be deleted.
	 * Think about storing them for history.
	 * 
	 * @param gameDuration game duration in milliseconds
	 */
	void freeReserves(Long gameDuration);

}
