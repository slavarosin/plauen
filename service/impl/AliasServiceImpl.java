package net.gobro.plauen.service.impl;

import net.gobro.plauen.dao.AliasReserveDAO;
import net.gobro.plauen.service.AliasService;
import net.gobro.plauen.util.AliasUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AliasServiceImpl implements AliasService {
	private static final Logger LOG = LoggerFactory
			.getLogger(AliasServiceImpl.class);

	@Autowired
	private AliasReserveDAO aliasReserveDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String getAlias(Integer gameId) {
		Integer result = getAliasReserveDAO().getAlias(gameId);

		String alias = AliasUtil.convertNumberToAlias(6, result);

		if (LOG.isDebugEnabled()) {
			LOG.debug("New alias [" + alias + "] generated for game ID = "
					+ gameId);
		}

		return alias;
	}

	@Override
	public void freeReserves(Long gameDuration) {
		getAliasReserveDAO().freeReserves(gameDuration);
	}

	public AliasReserveDAO getAliasReserveDAO() {
		return aliasReserveDAO;
	}

	public void setAliasReserveDAO(AliasReserveDAO aliasReserveDAO) {
		this.aliasReserveDAO = aliasReserveDAO;
	}
}
