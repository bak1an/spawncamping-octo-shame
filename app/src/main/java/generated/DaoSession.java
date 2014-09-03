package generated;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import generated.Point;

import generated.PointDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pointDaoConfig;

    private final PointDao pointDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pointDaoConfig = daoConfigMap.get(PointDao.class).clone();
        pointDaoConfig.initIdentityScope(type);

        pointDao = new PointDao(pointDaoConfig, this);

        registerDao(Point.class, pointDao);
    }
    
    public void clear() {
        pointDaoConfig.getIdentityScope().clear();
    }

    public PointDao getPointDao() {
        return pointDao;
    }

}