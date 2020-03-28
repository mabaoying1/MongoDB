package com.healthpay.modules.gen.service;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.BaseService;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.gen.dao.GenDataBaseDictDao;
import com.healthpay.modules.gen.dao.GenTableColumnDao;
import com.healthpay.modules.gen.dao.GenTableDao;
import com.healthpay.modules.gen.entity.GenTable;
import com.healthpay.modules.gen.entity.GenTableColumn;
import com.healthpay.modules.gen.util.GenUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenTableService extends BaseService {

	@Autowired
	private GenTableDao genTableDao;

	@Autowired
	private GenTableColumnDao genTableColumnDao;

	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;

	public GenTable get(String id) {
		GenTable genTable = (GenTable) this.genTableDao.get(id);
		GenTableColumn genTableColumn;
		(genTableColumn = new GenTableColumn()).setGenTable(new GenTable(genTable.getId()));
		genTable.setColumnList(this.genTableColumnDao.findList(genTableColumn));
		return genTable;
	}

	public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
		genTable.setPage(page);
		page.setList(this.genTableDao.findList(genTable));
		return page;
	}

	public List<GenTable> findAll() {
		return this.genTableDao.findAllList(new GenTable());
	}

	public List<GenTable> findTableListFormDb(GenTable genTable) {
		return this.genDataBaseDictDao.findTableList(genTable);
	}

	public boolean checkTableName(String tableName) {
		if (StringUtils.isBlank(tableName))
			return true;
		GenTable genTable = new GenTable();
		genTable.setName(tableName);

		return (this.genTableDao.findList(genTable)).size() == 0;
	}

	public boolean checkTableNameFromDB(String tableName) {
		if (StringUtils.isBlank(tableName))
			return true;
		GenTable genTable;
		(genTable = new GenTable()).setName(tableName);

		return (this.genDataBaseDictDao.findTableList(genTable)).size() == 0;
	}

	public GenTable getTableFormDb(GenTable genTable) {
		if (StringUtils.isNotBlank(genTable.getName())) {
			List<GenTable> list = this.genDataBaseDictDao.findTableList(genTable);
			if (list.size() > 0) {
				if (StringUtils.isBlank(genTable.getId())) {
					if (StringUtils.isBlank((genTable = (GenTable) list.get(0)).getComments())) {
						genTable.setComments(genTable.getName());
					}
					genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
				}
				List<GenTableColumn> columnList = this.genDataBaseDictDao.findTableColumnList(genTable);
				List<GenTableColumn> oldColumnList = genTable.getColumnList();
				for (GenTableColumn column : columnList) {
					boolean b = false;
					for (GenTableColumn genTableColumn : genTable.getColumnList()) {
						if (genTableColumn.getName() != null && genTableColumn.getName().equals(column.getName())) {
							b = true;
						}
					}
					if (!b) {
						genTable.getColumnList().add(column);
					}

				}

				for (GenTableColumn e : genTable.getColumnList()) {
					boolean b = false;
					for (GenTableColumn olde : oldColumnList) {
						if (olde.getName().equals(e.getName())) {
							b = true;
						}
					}
					if (!b) {
						e.setDelFlag("1");
					}

				}

				genTable.setPkList(this.genDataBaseDictDao.findTablePK(genTable));

				GenUtil.setColumnValue(genTable);
			}
		}

		return genTable;
	}

	@Transactional(readOnly = false)
	public void save(GenTable genTable) {
		boolean isSync = true;

		if (StringUtils.isBlank(genTable.getId())) {
			isSync = false;
		} else {
			GenTable oldTable = get(genTable.getId() );
			if ((oldTable.getColumnList().size() != genTable.getColumnList().size())
					|| (!oldTable.getName().equals(genTable.getName()))
					|| (!oldTable.getComments().equals(genTable.getComments())))
				isSync = false;
			else {
				for (GenTableColumn column : genTable.getColumnList()) {
					GenTableColumn oldColumn = (GenTableColumn) this.genTableColumnDao.get(column.getId()) ;
					if (!StringUtils.isBlank(column.getId())
							&& (oldColumn.getName().equals(column.getName()))
							&& (oldColumn.getJdbcType().equals(column.getJdbcType()))
							&& (oldColumn.getIsPk().equals(column.getIsPk()))
							&& (oldColumn.getComments().equals(column.getComments())))
						continue;
					isSync = false;
				}
			}

		}

		if (!isSync) {
			genTable.setIsSync("0");
		}

		if (StringUtils.isBlank(genTable.getId())) {
			genTable.preInsert();
			this.genTableDao.insert(genTable);
		} else {
			genTable.preUpdate();
			this.genTableDao.update(genTable);
		}

		this.genTableColumnDao.deleteByGenTable(genTable);
		
		for (GenTableColumn column : genTable.getColumnList()) {
			column.setGenTable(genTable);
			column.setId(null);
			column.preInsert();
			this.genTableColumnDao.insert(column);
		}
	}

	@Transactional(readOnly = false)
	public void syncSave(GenTable genTable) {
		genTable.setIsSync("1");
		this.genTableDao.update(genTable);
	}

	@Transactional(readOnly = false)
	public void saveFromDB(GenTable genTable) {
		genTable.preInsert();
		this.genTableDao.insert(genTable);
		List<GenTableColumn> columnList = genTable.getColumnList() ;
		for (GenTableColumn column : columnList) {
			column.setGenTable(genTable);
			column.setId(null);
			column.preInsert();
			this.genTableColumnDao.insert(column);
		}
	}

	@Transactional(readOnly = false)
	public void delete(GenTable genTable) {
		this.genTableDao.delete(genTable);
		this.genTableColumnDao.deleteByGenTable(genTable);
	}

	@Transactional(readOnly = false)
	public void buildTable(String sql) {
		this.genTableDao.buildTable(sql);
	}
}
