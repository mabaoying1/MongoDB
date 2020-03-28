/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */



package com.healthpay.modules.sys.utils;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.healthpay.common.service.BaseService;
import com.healthpay.common.sms.SMSUtils;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.common.utils.SpringContextHolder;
import com.healthpay.modules.sys.dao.AreaDao;
import com.healthpay.modules.sys.dao.MenuDao;
import com.healthpay.modules.sys.dao.OfficeDao;
import com.healthpay.modules.sys.dao.RoleDao;
import com.healthpay.modules.sys.dao.UserDao;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.Menu;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.Role;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * Class UserUtils
 *
 *
 * @version        1.0, 16/06/28
 * @author         gyp
 */
public class UserUtils {
    private static UserDao     userDao                       = SpringContextHolder.getBean(UserDao.class);
    private static RoleDao     roleDao                       = SpringContextHolder.getBean(RoleDao.class);
    private static MenuDao     menuDao                       = SpringContextHolder.getBean(MenuDao.class);
    private static AreaDao     areaDao                       = SpringContextHolder.getBean(AreaDao.class);
    private static OfficeDao   officeDao                     = SpringContextHolder.getBean(OfficeDao.class);
    public static final String USER_CACHE                    = "userCache";
    public static final String USER_CACHE_ID_                = "id_";
    public static final String USER_CACHE_LOGIN_NAME_        = "ln";
    public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
    public static final String CACHE_ROLE_LIST               = "roleList";
    public static final String CACHE_MENU_LIST               = "menuList";
    public static final String CACHE_AREA_LIST               = "areaList";
    public static final String CACHE_OFFICE_LIST             = "officeList";
    public static final String CACHE_OFFICE_ALL_LIST         = "officeAllList";
    public static final String CACHE_AREA_ALL_LIST           = "areaAllList";

    /**
     * Method description
     *
     */
    public static void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_MENU_LIST);
        removeCache(CACHE_AREA_LIST);
        removeCache(CACHE_OFFICE_LIST);
        removeCache(CACHE_OFFICE_ALL_LIST);
        UserUtils.clearCache(getUser());
    }

    /**
     * Method description
     *
     *
     * @param user
     */
    public static void clearCache(User user) {
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());

        if ((user.getArea() != null) && (user.getArea().getId() != null)) {
            CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getArea().getId());
        }
    }

    /**
     * Method description
     *
     *
     * @param key
     * @param value
     */
    public static void putCache(String key, Object value) {

        // getCacheMap().put(key, value);
        getSession().setAttribute(key, value);
    }

    /**
     * Method description
     *
     *
     * @param key
     */
    public static void removeCache(String key) {

        // getCacheMap().remove(key);
        getSession().removeAttribute(key);
    }

    // 注册用户重置密码

    /**
     * Method description
     *
     *
     * @param uid
     * @param pwd
     * @param tel
     * @param password
     *
     * @return
     *
     * @throws IOException
     */
    public static String sendPass(String uid, String pwd, String tel, String password) throws IOException {

        // 发送内容
        String content = "您的新密码是：" + password + "，请登录系统，重新设置密码。";

        return SMSUtils.send(uid, pwd, tel, content);
    }

    // 发送注册码

    /**
     * Method description
     *
     *
     * @param uid
     * @param pwd
     * @param tel
     * @param randomCode
     *
     * @return
     *
     * @throws IOException
     */
    public static String sendRandomCode(String uid, String pwd, String tel, String randomCode) throws IOException {

        // 发送内容
        String content = "您的验证码是：" + randomCode + "，有效期30分钟，请在有效期内使用。";

        return SMSUtils.send(uid, pwd, tel, content);
    }

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    public static List<Area> getAreaAllList(String id) {
        @SuppressWarnings("unchecked")
        List<Area> areaList = null; //(List<Area>) getCache(CACHE_AREA_ALL_LIST);

        if (areaList == null) {
            Area area = new Area();

            area.setId(id);
            areaList = areaDao.findAllList(area);
            putCache(CACHE_AREA_ALL_LIST, areaList);
        }

        return areaList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Area> getAreaList(String id) {
        @SuppressWarnings("unchecked")
        List<Area> areaList = null;//(List<Area>) getCache(CACHE_AREA_LIST);

        if (areaList == null) {
            User user = getUser();
			Area area = new Area();
			area.setId(id);
            if (user.isAdmin()) {
                areaList = areaDao.findAllList(area);
            } else {
                area.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
                //area.setId(user.getArea().getId());
                areaList = areaDao.findList(area);
            }

            putCache(CACHE_AREA_LIST, areaList);
        }

        return areaList;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    public static Area getByAreaName(String name) {
        Area a = new Area();

        a.setName(name);

        List<Area> list = areaDao.findList(a);

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return new Area();
        }
    }

    /**
     * Method description
     *
     *
     * @param loginName
     *
     * @return
     */
    public static User getByLoginName(String loginName) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);

        if (user == null) {
            user = userDao.getByLoginName(new User(null, loginName));

            if (user == null) {
                return null;
            }

            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }

        return user;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    public static Office getByOfficeName(String name) {
        Office o = new Office();

        o.setName(name);

        List<Office> list = officeDao.findList(o);

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return new Office();
        }
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    public static User getByUserName(String name) {
        User u = new User();

        u.setName(name);

        List<User> list = userDao.findList(u);

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return new User();
        }
    }

    // ============== User Cache ==============

    /**
     * Method description
     *
     *
     * @param key
     *
     * @return
     */
    public static Object getCache(String key) {
        return getCache(key, null);
    }

    /**
     * Method description
     *
     *
     * @param key
     * @param defaultValue
     *
     * @return
     */
    public static Object getCache(String key, Object defaultValue) {

        // Object obj = getCacheMap().get(key);
        Object obj = getSession().getAttribute(key);

        return (obj == null)
               ? defaultValue
               : obj;
    }

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    public static User get(String id) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);

        if (user == null) {
            user = userDao.get(id);

            if (user == null) {
                return null;
            }

            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }

        return user;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Menu> getMenuList() {
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);

        if (menuList == null || menuList.size() == 0) {
            User user = getUser();

            if (user.isAdmin()) {
                menuList = menuDao.findAllList(new Menu());
            } else {
                Menu m = new Menu();

                m.setUserId(user.getId());
                menuList = menuDao.findByUserId(m);
            }

            putCache(CACHE_MENU_LIST, menuList);
        }

        return menuList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Office> getOfficeAllList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);

        if (officeList == null) {
            officeList = officeDao.findAllList(new Office());
        }
        putCache(CACHE_OFFICE_ALL_LIST,officeList);
        return officeList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Office> getOfficeList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);

        if (officeList == null) {
            User user = getUser();

            if (user.isAdmin()) {
                officeList = officeDao.findAllList(new Office());
            } else {
                Office office = new Office();

                office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "ar", ""));
                officeList = officeDao.findList(office);
            }

            putCache(CACHE_OFFICE_LIST, officeList);
        }

        return officeList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Principal getPrincipal() {
        try {
            Subject   subject   = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();

            if (principal != null) {
                return principal;
            }

            // subject.logout();
        } catch (UnavailableSecurityManagerException e) {}
        catch (InvalidSessionException e) {}

        return null;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Role> getRoleList() {
        @SuppressWarnings("unchecked")
        List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);

        if (roleList == null) {
            User user = getUser();

            if (user.isAdmin()) {
                roleList = roleDao.findAllList(new Role());
            } else {
                Role role = new Role();

                role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
                roleList = roleDao.findList(role);
            }

            putCache(CACHE_ROLE_LIST, roleList);
        }

        return roleList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);

            if (session == null) {
                session = subject.getSession();
            }

            if (session != null) {
                return session;
            }

            // subject.logout();
        } catch (InvalidSessionException e) {}

        return null;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * Method description
     *
     *
     * @param date
     *
     * @return
     */
    public static String getTime(Date date) {
        StringBuffer time  = new StringBuffer();
        Date         date2 = new Date();
        long         temp  = date2.getTime() - date.getTime();
        long         days  = temp / 1000 / 3600 / 24;    // 相差小时数

        if (days > 0) {
            time.append(days + "天");
        }

        long temp1 = temp % (1000 * 3600 * 24);
        long hours = temp1 / 1000 / 3600;    // 相差小时数

        if ((days > 0) || (hours > 0)) {
            time.append(hours + "小时");
        }

        long temp2 = temp1 % (1000 * 3600);
        long mins  = temp2 / 1000 / 60;    // 相差分钟数

        time.append(mins + "分钟");

        return time.toString();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Menu getTopMenu() {
        List<Menu> menuList = getMenuList();
        if(null != menuList && menuList.size() > 0)
            return menuList.get(0);
        return null;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static User getUser() {
        Principal principal = getPrincipal();

        if (principal != null) {
            User user = get(principal.getId());

            if (user != null) {
                return user;
            }

            return new User();
        }

        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
