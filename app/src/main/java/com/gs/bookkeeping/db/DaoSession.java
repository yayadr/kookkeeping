package com.gs.bookkeeping.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gs.bookkeeping.main.beans.AccountBean;
import com.gs.bookkeeping.main.beans.InfoBean;

import com.gs.bookkeeping.db.AccountBeanDao;
import com.gs.bookkeeping.db.InfoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accountBeanDaoConfig;
    private final DaoConfig infoBeanDaoConfig;

    private final AccountBeanDao accountBeanDao;
    private final InfoBeanDao infoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accountBeanDaoConfig = daoConfigMap.get(AccountBeanDao.class).clone();
        accountBeanDaoConfig.initIdentityScope(type);

        infoBeanDaoConfig = daoConfigMap.get(InfoBeanDao.class).clone();
        infoBeanDaoConfig.initIdentityScope(type);

        accountBeanDao = new AccountBeanDao(accountBeanDaoConfig, this);
        infoBeanDao = new InfoBeanDao(infoBeanDaoConfig, this);

        registerDao(AccountBean.class, accountBeanDao);
        registerDao(InfoBean.class, infoBeanDao);
    }
    
    public void clear() {
        accountBeanDaoConfig.clearIdentityScope();
        infoBeanDaoConfig.clearIdentityScope();
    }

    public AccountBeanDao getAccountBeanDao() {
        return accountBeanDao;
    }

    public InfoBeanDao getInfoBeanDao() {
        return infoBeanDao;
    }

}
