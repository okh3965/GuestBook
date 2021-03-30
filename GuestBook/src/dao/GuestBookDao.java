package dao;

import java.util.List;
import vo.GuestBookVo;

public interface GuestBookDao {
	public List<GuestBookVo> getList();
	public int insert(GuestBookVo vo);
	public int delete(String password);
	
}
