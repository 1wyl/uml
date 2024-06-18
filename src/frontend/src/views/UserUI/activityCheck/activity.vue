<template>
    <div class="task-list">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <!-- <el-button type="primary" @click="showInfo" style="float: left;">个人信息</el-button> -->
          <span>活动列表</span>
          <!-- <el-button type="primary" @click="showAddTaskModal" style="float: right;">添加活动</el-button> -->
        </div>
  
        <el-table :data="activities" style="width: 100%">
          <el-table-column prop="name" label="活动名称" width="500" ></el-table-column>
          <el-table-column prop="location" label="活动地点" width="500" ></el-table-column>
          <el-table-column prop="startTime" label="开始时间" width="500"></el-table-column>
        </el-table>
  
      </el-card>
    </div>
  </template>
  
  <script>
import { fetchActivities, deleteActivity } from '@/api/activity';

  export default {
    data() {
      return {
        activities: []
      };
    },
    created() {
      this.fetchTasks();
    },
    methods: {
      fetchTasks() {
        fetchActivities()
            .then(response => {
              this.activities = response.data;
            })
            .catch(error => {
              this.$message.error('获取任务列表失败');
              //this.$router.push('/');
            });
      },
      showAddTaskModal() {
        // 打开一个模态框或其他界面用于添加新任务
        this.$router.push('/todo/add'); // 假设你有一个路由用于添加任务
      },
      editTask(task) {
        // 导航到编辑任务页面，使用 query 参数传递 taskId
        // console.log(task)
        this.$router.push({
          path: '/todo/edit',
          query: { taskId: task.id }
        });
      },
      deleteTask(task) {
        // const token = localStorage.getItem('ACCESS_TOKEN');
        // axios.post('/api/tasks/delete', task, {
        //   headers: {
        //     'Authorization': 'Bearer '+token
        //   }
        // })
        deletetask(task)
            .then(response => {
              this.$message.success('任务删除成功');
              this.fetchTasks();
            })
            .catch(error => {
              this.$message.error('任务删除失败');
            });
  
      }
    }
  };
  </script>
  