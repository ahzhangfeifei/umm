package com.umm.wfm.security;

import com.umm.finance.base.security.service.KeyInfo;
import com.umm.finance.base.security.service.KeysLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class WfmKeysLoader implements KeysLoader {

	@Resource
	private SecurityKeysMapper securityKeysMapper;
	
	@Override
	public List<KeyInfo> getKeys() {
		return securityKeysMapper.select();
	}

}
