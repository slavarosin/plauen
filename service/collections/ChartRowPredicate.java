/**
 * 
 */
package net.gobro.plauen.service.collections;

import net.gobro.plauen.model.ChartRow;

import org.apache.commons.collections.Predicate;


public class ChartRowPredicate implements Predicate {
	private String username;

	public ChartRowPredicate(String username) {
		this.username = username;
	}

	@Override
	public boolean evaluate(Object obj) {
		return username.equals((((ChartRow) obj).getUsername()));
	}
}