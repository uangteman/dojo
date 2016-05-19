package com.sample.an.sample.service.db.service;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.sample.an.sample.service.db.model.KnownSourceORMLiteModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cindy on 5/13/16.
 */
public class KnownSourceORMLiteService extends BaseORMLiteService {

	private final Dao<KnownSourceORMLiteModel, Integer> knownSourceDao;

	public KnownSourceORMLiteService(Context context) {
		super(context, KnownSourceORMLiteService.class);

		try{
			this.knownSourceDao = repositoryHelper.getKnownSourceDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int createKnownSourceModel(KnownSourceORMLiteModel bankModel){
		try{
			return this.knownSourceDao.create(bankModel);
		} catch(SQLException ex){
			ex.printStackTrace();
		}
		return 0;
	}
	
	public List<KnownSourceORMLiteModel> getAllKnownSourceByKnownId(long min, long max) throws SQLException{
		QueryBuilder<KnownSourceORMLiteModel, Integer> qb = this.knownSourceDao.queryBuilder();
				qb.offset(min).limit(max);
				qb.orderBy("know_id", true);
		return this.knownSourceDao.query(qb.prepare());
	}
	
	public List<KnownSourceORMLiteModel> getAllKnownSource(long min, long max) throws SQLException{
		QueryBuilder<KnownSourceORMLiteModel, Integer> qb = this.knownSourceDao.queryBuilder();
				qb.offset(min).limit(max);
		return this.knownSourceDao.query(qb.prepare());
	}

}
