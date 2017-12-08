package com.daoImpl;

import com.core.page.Pagination;
import com.dao.AdminDao;
import com.model.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Resource
    private SessionFactory sessionFactory;

    public Admin findOne(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Admin admin = (Admin) session.load(Admin.class, id);
        return admin;
    }

    public Admin findOne(String id) {
        Session session = sessionFactory.getCurrentSession();
        Admin admin = (Admin) session.load(Admin.class, id);
        return admin;
    }

    public Admin findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "From Admin admin where admin.username =:username";
        Query query = session.createQuery(hql);

        return (Admin) query.list().get(0);
    }

    public void add(Admin entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(Admin entity) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Admin admin set userpwd = :entity.userpwd, login_times=:entity.login_times," +
                " last_login=:entity.last_login,status=:entity.status,  jurisdiction=:entity.jurisdiction, role=:entity.role WHERE id = :entity.id";
        session.createQuery(hql).executeUpdate();
    }

    public void delete(Admin entity) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "delete from Admin admin " + "where admin.id=:entity.id";
        session.createQuery(sql);
    }

    public List<Admin> pagination(Pagination<Admin> pagination) {
        Integer start = pagination.getStart();
        Integer pageSize = pagination.getPageSize();
        String hql = "From Admin";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public List<Admin> findAll() {
        String hql = "FROM Admin";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        return query.list();
    }

    public void save(Admin entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }
}
