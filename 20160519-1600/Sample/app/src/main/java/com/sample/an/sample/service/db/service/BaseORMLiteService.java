package com.sample.an.sample.service.db.service;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sample.an.sample.service.db.helper.OrmLiteRepositoryHelper;

/**
 * Created by cindy on 5/13/16.
 */
public abstract class BaseORMLiteService {

    public final String tag;
    public final Context context;
    public final OrmLiteRepositoryHelper repositoryHelper;
	
	public BaseORMLiteService(Context context, Class<? extends BaseORMLiteService> instanceClass){
		this.context = context;
		this.repositoryHelper = OpenHelperManager.getHelper(context, OrmLiteRepositoryHelper.class);
		this.tag = instanceClass.getName();
	}

}
