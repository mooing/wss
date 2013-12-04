package com.mooing.wss.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mooing.wss.common.dao.GenericBaseDAO;

public class BaseService {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;
}
