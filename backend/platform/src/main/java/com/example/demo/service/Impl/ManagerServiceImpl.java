package com.example.demo.service.Impl;

import com.example.demo.dao.*;
import com.example.demo.model.entity.*;
import com.example.demo.model.jsonRequest.*;
import com.example.demo.model.ov.Result;
import com.example.demo.service.ManagerService;
import com.example.demo.tools.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private ManagersMapper managersMapper;

    @Resource
    private ViewsMapper viewsMapper;

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private AlarmMapper alarmMapper;

    @Resource
    private Unlock_ApplyMapper unlock_applyMapper;

    @Resource
    private CommentsMapper commentsMapper;


    private boolean checkManager(String id){
        ManagersExample managersExample=new ManagersExample();
        managersExample.createCriteria().andIdEqualTo(id);
        List<Managers> managersList=new LinkedList<>();
        managersList=managersMapper.selectByExample(managersExample);
        if(managersList.isEmpty()==false){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public Result getUserInfo(String id) {
        //  验证管理员身份
        if(checkManager(id)==false){
            return ResultTool.error("管理员身份不合法");
        }
        UsersExample usersExample=new UsersExample();
        usersExample.createCriteria().andIdIsNotNull();
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        List<getUserInfoByManager> infoList=new LinkedList<>();
        if(usersList.isEmpty()==true){
            return ResultTool.error("用户不存在");
        }
        for (Users users:usersList){
            getUserInfoByManager info=new getUserInfoByManager();
            info.setUserId(users.getId());
            info.setUserName(users.getName());
            info.setPassword(users.getPassword());
            info.setStatus(users.getBanstate().toString());
            if(users.getBanstate()==0){
                info.setBanType("未封禁");
                info.setBanReason("");
                info.setDate("");
            }else {
                info.setBanReason(users.getBanreason());
                info.setDate(users.getBanstart().toString());
                int state=0;
                state=users.getBantype();
                if(state==0){
                    info.setBanType("未封禁");
                }else if(state==1){
                    info.setBanType("互助系统封禁");
                }else if(state==2){
                    info.setBanType("论坛封禁");
                }else {
                    info.setBanType("都封禁");
                }
            }
            infoList.add(info);
        }
        return ResultTool.success(infoList);
    }

    @Override
    public Result freeUsers(freeUserByManager freeUserByManager) {
        //  验证管理员身份
        if(checkManager(freeUserByManager.getManagerId())==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  查看用户是否存在
        Users users=usersMapper.selectByPrimaryKey(freeUserByManager.getId());
        if(users==null){
            return ResultTool.error("用户不存在");
        }
        Users record=new Users();
        record.setId(freeUserByManager.getId());
        record.setBanstate(0);
        record.setBanreason(null);
        record.setBanstart(null);
        record.setBanend(null);
        record.setBantype(0);
        usersMapper.updateByPrimaryKeySelective(record);
        return ResultTool.success();
    }

    //  获取标签内容
    private String getTagContent(int id){
        Tags tag=tagsMapper.selectByPrimaryKey(id);
        return tag.getContent();
    }

    @Override
    public Result getAllViews(String managerId) {
        //  验证管理员身份
        if(checkManager(managerId)==false){
            return ResultTool.error("管理员身份不合法");
        }
        ViewsExample viewsExample=new ViewsExample();
        viewsExample.createCriteria().andIdIsNotNull();
        List<Views> viewsList=viewsMapper.selectByExample(viewsExample);
        List<getViewsByManager> infoList=new LinkedList<>();
        for(Views views:viewsList){
            getViewsByManager info=new getViewsByManager();
            info.setId(views.getId().toString());
            info.setTitle(views.getTitle());
            info.setType(getTagContent(views.getTags()));
            info.setAddresser(views.getPuller());
            info.setDate(views.getTime().toString());
            infoList.add(info);
        }
        return ResultTool.success(infoList);
    }

    @Override
    public Result deleteViewByManager(deleteViewByManager deleteViewByManager) {
        //  验证管理员身份
        if(checkManager(deleteViewByManager.getId())==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  获取int型
        int v_id;
        try {
            v_id=Integer.parseInt(deleteViewByManager.getId());
        }catch (Exception e){
            return ResultTool.error("帖子id格式错误");
        }
        //  查看帖子是否存在
        Views check=viewsMapper.selectByPrimaryKey(v_id);
        if(check==null){
            return ResultTool.error("帖子不存在");
        }
        viewsMapper.deleteByPrimaryKey(v_id);
        return ResultTool.success();
    }

    @Override
    public Result getAlarmInfo(String managerId) {
        //  验证管理员身份
        if(checkManager(managerId)==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  获取举报信息
        AlarmExample alarmExample=new AlarmExample();
        alarmExample.createCriteria().andIdIsNotNull();
        List<Alarm> alarmList=alarmMapper.selectByExample(alarmExample);
        List<getAlarmInfo> infoList=new LinkedList<>();
        for (Alarm alarm:alarmList){
            getAlarmInfo info=new getAlarmInfo();
            info.setId(alarm.getId().toString());
            info.setReporterId(alarm.getAlarmingUser());
            info.setBeReportedId(alarm.getAlarmedUser());
            info.setDate(alarm.getTime().toString());
            if(alarm.getViewType()==1){
                info.setType("帖子举报");
            }else if(alarm.getViewType()==2){
                info.setType("评论举报");
            }else {
                info.setType("互助系统举报");
            }
            info.setReason(alarm.getReason());
            info.setContent(alarm.getReason());
            infoList.add(info);
        }
        return ResultTool.success(infoList);
    }

    @Override
    public Result getApplyInfo(String managerId) {
        //  验证管理员身份
        if(checkManager(managerId)==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  获取申诉信息
        Unlock_ApplyExample unlock_applyExample=new Unlock_ApplyExample();
        unlock_applyExample.createCriteria().andIdIsNotNull();
        List<Unlock_Apply> applyList=new LinkedList<>();
        applyList=unlock_applyMapper.selectByExample(unlock_applyExample);
        List<getApplyInfo> infoList=new LinkedList<>();
        for(Unlock_Apply apply:applyList){
            getApplyInfo info=new getApplyInfo();
            info.setTitle(apply.getTitle());
            info.setReport(apply.getApplyer());
            info.setDate(apply.getTime().toString());
            int t=apply.getType();
            if(t==1){
                info.setType("论坛申诉");
            }else if(t==2){
                info.setType("评论申诉");
            }else{
                info.setType("互助系统申诉");
            }
            info.setReason(apply.getContent());
            infoList.add(info);
        }
        return ResultTool.success(infoList);
    }

    @Override
    public Result modifyPassword(modifyPasswordByManager modifyPasswordByManager) {
        //  验证管理员身份
        if(checkManager(modifyPasswordByManager.getManagerId())==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  用户身份验证
        Users users=usersMapper.selectByPrimaryKey(modifyPasswordByManager.getUserId());
        if(users==null){
            return ResultTool.error("该用户不存在");
        }
        //  修改密码
        Users record=new Users();
        record.setId(modifyPasswordByManager.getUserId());
        record.setPassword(modifyPasswordByManager.getPassword());
        usersMapper.updateByPrimaryKeySelective(record);
        return ResultTool.success();
    }

    @Override
    public Result deleteComments(deleteViewByManager deleteViewByManager) {
        //  验证管理员身份
        if(checkManager(deleteViewByManager.getManagerId())==false){
            return ResultTool.error("管理员身份不合法");
        }
        //  获取int型
        int v_id;
        try {
            v_id=Integer.parseInt(deleteViewByManager.getId());
        }catch (Exception e){
            return ResultTool.error("帖子id格式错误");
        }
        //  查看评论是否存在
        Comments comments=commentsMapper.selectByPrimaryKey(v_id);
        if(comments==null){
            return ResultTool.error("该评论不存在");
        }
        commentsMapper.deleteByPrimaryKey(v_id);
        return ResultTool.success();
    }

    @Override
    public Result banUser(banUserByManager banUserByManager) {
        //  验证管理员身份
        if(checkManager(banUserByManager.getManageId())==false){
            return ResultTool.error("管理员身份不合法");
        }

        //  用户身份验证
        Users u=usersMapper.selectByPrimaryKey(banUserByManager.getId());
        if(u==null){
            return ResultTool.error("该用户不存在");
        }
        //  修改用户表
        Users users=new Users();
        users.setId(banUserByManager.getId());
        users.setBanstate(1);
        if(banUserByManager.equals("论坛封禁")){
            users.setBantype(2);
        }else if(banUserByManager.equals("任务封禁")){
            users.setBantype(1);
        }else{
            users.setBantype(3);
        }
        Timestamp now=new Timestamp(System.currentTimeMillis());//获取当前时间
        users.setBanstart(now);
        String end=banUserByManager.getBanEndDate()+" 00:00:00";
        users.setBanend(Timestamp.valueOf(end));
        usersMapper.updateByPrimaryKeySelective(users);

        //  修改alarm表
        Alarm alarm=new Alarm();
        alarm.setAlarmedUser(banUserByManager.getId());
        alarm.setAlarmingUser(banUserByManager.getManageId());
        alarm.setAlarmType(users.getBantype());
        alarm.setTime(Timestamp.valueOf(end));
        alarm.setReason(banUserByManager.getBanReason());
        alarmMapper.updateByPrimaryKeySelective(alarm);
        return ResultTool.success();
    }
}
