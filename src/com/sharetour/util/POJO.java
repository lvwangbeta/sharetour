package com.sharetour.util;

import java.io.Serializable;
import java.sql.*;
import java.util.Map;
import java.util.List;

/*
 * POJO类是实体关系的基类
 * 完成数据库相关操作
 */
public abstract class POJO implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id = 0;			
	private QueryHelper helper;
	
	private String tablename = "Unknown table"; 				
	public void setId(long id){this.id = id;}
	public long getId(){return id;}
	
	public POJO(String tablename)
	{
		setTablename(tablename);
	}
	
	/*
	 * POJO类及其子类要想访问数据库，必须为其设置一个
	 * QueryHelper对象，以保证数据库连接一致以及
	 * 事务处理
	 */
	public void setQueryHelper(QueryHelper helper)
	{
		this.helper = helper;
	}
	public QueryHelper getQueryHelper()
	{
		return this.helper;
	}
	
	public String getTablename()
	{
		return tablename;
	}
	public void setTablename(String tablename)
	{
		this.tablename = tablename;
	}
	
	
	/*
	 * @param {long} id 按照建立数据库对象映射
	 * @return {<T extends POJO>}
	 */
	@SuppressWarnings("unchecked")
	public <T extends POJO> T Get(long id)
	{
		String sql = "SELECT * FROM " + getTablename() + " WHERE id=?";
		T obj = (T) helper.get(getClass(), sql, new Object[]{id});
		return obj;
	}
	
	/*
	 * Get() without param
	 */
	@SuppressWarnings("unchecked")
	public <T extends POJO> T Get()
	{
		String sql = "SELECT * FROM " + getTablename() + " WHERE id=?";
		T obj =  (T) helper.get(getClass(), sql, new Object[]{id});
		return obj;
	}
	
	/*
	 * 保存对象到数据库中
	 * Save()
	 */
	public long Save()
	{
		/*
		if(getId() > 0)
		{
			InsertObject(this);
		}
		else
		{
			setId(InsertObject(this));
		}
		*/
		setId(InsertObject(this));
		return getId();
	}
	
	/*
	 * 插入对象到数据库(目前还没有借助QueryHelper)
	 * @param {POJO} obj
	 * @param {long} id 返回数据库主键
	 */
	public long InsertObject(POJO obj)
	{
		Map<String, Object> map = obj.ListInsertableFields();
		if(map == null ||map.size() == 0) return id;
		String[] fields = map.keySet().toArray(new String[map.size()]);
		StringBuilder sql = new StringBuilder("INSERT INTO ");
		sql.append(obj.getTablename());
		//fill in the fields name
		sql.append("(");
		for(int i=0; i<fields.length; i++)
		{
			if(i > 0)
				sql.append(",");
			sql.append(fields[i]);		
		}
		sql.append(") VALUES(");
		for(int i=0; i<fields.length; i++)
		{
			if(i > 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = helper.getConnection();
			//pstm = con.prepareStatement(sql.toString());
			pstm = con.prepareStatement(sql.toString(), 
					PreparedStatement.RETURN_GENERATED_KEYS);
			for(int i=0; i<fields.length; i++)
			{
				pstm.setObject(i+1, map.get(fields[i]));
			}
			pstm.executeUpdate();
			res = pstm.getGeneratedKeys();
			return res.next()?res.getLong(1):0;
			//get the pk of insert
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;		
	}
	/*
	 * 根据map中的 属性更新数据库
	 * 暂时还是执行sql语句吧
	 */
	public void Update(String sql, Object...objects)
	{
		helper.update(sql, objects);	
	}
	
	/*
	 * 删除该对象对应的数据库数据
	 */
	public void Delete()
	{
		if(getId() > 0)
		{			
			helper.delete("DELETE FROM " + getTablename() + " WHERE id=?", getId());
		}
		else
			return;
	}
	
	/*
	 * 分页显示数据
	 * @param page
	 * @param size
	 */
	public List<? extends POJO> List(int page, int size)
	{
		String sql = "SELECT * FROM " + getTablename()+" ORDER BY id DESC ";
		return helper.query_slice(getClass(), sql, page, size);
	}

	/*
	 * 分页显示数据,但可以设置order属性
	 * @param page
	 * @param size
	 */
	public List<? extends POJO> List(int page, int size, String order)
	{
		String sql = "SELECT * FROM " + getTablename()+" ORDER BY " + order + " DESC ";
		return helper.query_slice(getClass(), sql, page, size);
	}	
	
	/*
	 * 获得总记录条目数
	 */
	public int TotalCount()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		int count = 0;
		try {
			con = helper.getConnection();
			pstm = con.prepareStatement("SELECT COUNT(*) FROM " + getTablename());
			res = pstm.executeQuery();
			if(res.next())
			{
				count = res.getInt(1);
			}
		} catch (SQLException e) {
			count = 0;
		}
		return count;
	}
	
	
	/*
	 * ListInsertableFields()
	 * 子类必须实现该方法
	 * @return HashMap
	 */
	public abstract Map<String, Object> ListInsertableFields();
}