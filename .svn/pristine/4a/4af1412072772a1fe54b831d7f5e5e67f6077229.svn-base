package com.healthpay.modules.gen.service;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.BaseService;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.gen.dao.GenTemplateDao;
import com.healthpay.modules.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenTemplateService extends BaseService {

	@Autowired
	private GenTemplateDao genTemplateDao;

	public GenTemplate get(String id) {
		return (GenTemplate) this.genTemplateDao.get(id);
	}

	public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
		genTemplate.setPage(page);
		page.setList(this.genTemplateDao.findList(genTemplate));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(GenTemplate genTemplate) {
		if (genTemplate.getContent() != null) {
			genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
		}
		if (StringUtils.isBlank(genTemplate.getId())) {
			genTemplate.preInsert();
			this.genTemplateDao.insert(genTemplate);
			return;
		}
		genTemplate.preUpdate();
		this.genTemplateDao.update(genTemplate);
	}

	@Transactional(readOnly = false)
	public void delete(GenTemplate genTemplate) {
		this.genTemplateDao.delete(genTemplate);
	}
}