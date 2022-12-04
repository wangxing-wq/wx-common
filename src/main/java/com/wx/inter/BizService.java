package com.wx.inter;

import java.util.List;
import java.util.Objects;

/**
 * @author 22343
 * @version 1.0
 */
public class BizService<K,B>{
	
	private BaseDao<K,B> baseDao;

	public boolean isExist(K k){
		if (Objects.isNull(k)){
			return false;
		}
		return baseDao.findById(k) != null;
	}
	
	public boolean isExistByBean(B bean,boolean containYourself){
		if (Objects.isNull(bean)){
			return false;
		}
		List<B> byBeanSelective = baseDao.findByBeanSelective(bean);
		for (B b : byBeanSelective) {
			if (!containYourself && Objects.equals(bean,b )){
				return true;
			}
		}
		return byBeanSelective.size() > 0;
	}

}
