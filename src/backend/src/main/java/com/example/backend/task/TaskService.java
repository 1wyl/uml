package com.example.backend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(Integer userId) {
        return taskMapper.findByUserId(userId);
    }

    @Transactional
    public void addTask(Task task) {
        try {
            //System.out.println("here it is");
            taskMapper.insertTask(task);
            //System.out.println("here it is 2");
        } catch (Exception e) {
            System.out.println("插入任务时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateTask(Task task){
        try {
            taskMapper.updateTask(task);
        } catch (Exception e) {
            System.out.println("插入任务时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
    @Transactional
    public void deleteTask(Task task) {
        try {
            taskMapper.deleteTask(task);

        } catch (Exception e) {
            System.out.println("删除任务时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
    public Task findTaskById(Long taskId) {
        return taskMapper.findTaskById(taskId);
    }
}
