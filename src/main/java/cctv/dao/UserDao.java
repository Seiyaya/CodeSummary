package cctv.dao;

import java.util.List;

import cctv.bean.User;

public interface UserDao{

    /**
     * 添加
     * @param user
     */
    Integer addUser(User user);

    /**
     * 根据id查找
     * @param id
     */
    User getUserById(Integer id);

    /**
     * 根据多个id查找实体
     * @param idList
     */
    List<User> getUsersByIds(List<Integer> idList);

    /**
     * 根据id删除
     * @param id
     */
    Integer delUserById(Integer id);

    /**
     * 根据多个id删除
     * @param idList
     */
    Integer delUsersByIds(List<Integer> idList);

    /**
     * 根据id更新
     * @param user
     */
    Integer updateUserById(User user);

    /**
     * 获取记录总数
     * @param user
     */
    int getUserListCount(User user);

    /**
     * 分页查找
     * @param user
     */
    List<User> getUserListWithPage(User user);
}