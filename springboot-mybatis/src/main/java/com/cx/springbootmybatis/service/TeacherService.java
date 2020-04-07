package com.cx.springbootmybatis.service;

import com.cx.springbootmybatis.dao.TeacherDao;
import com.cx.springbootmybatis.dao.UserDao;
import com.cx.springbootmybatis.entity.Teacher;
import com.cx.springbootmybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public Teacher getById(int id){
        return teacherDao.getById(id);
    }

    public void insert(Teacher teacher){
        int i = teacherDao.insert(teacher);
        System.out.println(i+"***");
    }

   /*如果配置都为Propagation.REQUIRED 如果当前要运行的事务不在一个事务里，那么就新启动一个事务，就像usercontroller调用userservice的update方法，这一阶段是没有事务的，那么userservice
    的update方法就会新启动一个事务。     如果当前运行的事务方法已经在一个事务里，那么就不会重新启动一个新的事务，直接在userservice的update方法的事务里运行，所以即使teacherservice的
    update方法执行完了，userservice的upadate又抛出错误，teacher的update里面执行的操作也会回滚*/
//    @Transactional(propagation=Propagation.REQUIRED)

    /***
     *userservice.update方法的事务传播行为使用 Propagation.REQUIRED,   teacherservice.update的方法的事务传播行为Propagation.REQUIRES_NEW
     *Propagation.REQUIRED已经在上面描述了,此时teacherservice的update方法不会使用userservice的update方法启动的事务，而是将之前的事务挂起,然后再启动一个新的事务
     * 这样如果teacherservice的update方法已经提交了,userservice的update方法抛出异常,那teacherservice里的update方法也不会再回滚。
     * 如果是teacherservice里的update方法抛出了异常,那么teacherservice里的update方法会回滚, 如果在userservice的update方法调用此方法,如果捕获了异常,那么userservice的update
     * 方法里面的会成功执行,如果没有捕获异常,那么将会回滚
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)

    /***
     * Propagation.SUPPORTS表示如果这个方法被一个有事务的方法调用,那么就具有事务,如果不开启事务,那么也就没有事务
     * 当前设置为Propagation.SUPPORTS,如果调用这个方法的Userservice的update方法开启了事务,那么这里也就有了事务,当出现错会回滚
     * 如果没有开启事务，那么这里就也没有事务,出错不会回滚
     * @param teacher
     */
   // @Transactional(propagation = Propagation.SUPPORTS)

    /***
     *Propagation.MANDATORY表示必须要在事务中执行,如果调用此方法的方法没有开启事务,那么将抛出异常
     * @param teacher
     */
    //@Transactional(propagation = Propagation.MANDATORY)

    /***
     * 表示当前方法不支持事务
     *如果调用此方法的方法开启了事务,那么执行这个方法使会将事务挂起,此方法不被事务包括,也就是后面出现了异常（指在调用该方法的方法抛出异常），这里也不会回滚
     *
     * 如果在该方法抛出异常,如果没有trycatch那么事务会回滚，如果有那么不会回滚
     * @param teacher
     */
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
/***
 * PROPAGATION_REQUIRES_NEW 启动一个新的, 不依赖于环境的 "内部" 事务. 这个事务将被完全 commited 或 rolled back 而不依赖于外部事务,
 * 它拥有自己的隔离范围, 自己的锁, 等等. 当内部事务开始执行时, 外部事务将被挂起, 内务事务结束时, 外部事务将继续执行. 
 *     另一方面, PROPAGATION_NESTED 开始一个 "嵌套的" 事务,  它是已经存在事务的一个真正的子事务. 潜套事务开始执行时, 
 * 它将取得一个 savepoint. 如果这个嵌套事务失败, 我们将回滚到此 savepoint. 潜套事务是外部事务的一部分, 只有外部事务结束后它才会被提交. 

 */
   // @Transactional(propagation = Propagation.NESTED)

    /***
     * 表示当前方法不能在事务中运行,如果在事务中运行，将会抛出异常
     * @param teacher
     */
    @Transactional(propagation = Propagation.NEVER)
    public void update(Teacher teacher){
        int i = teacherDao.update(teacher);
        System.out.println(i+"***");
        i=i/0;
    }

}
