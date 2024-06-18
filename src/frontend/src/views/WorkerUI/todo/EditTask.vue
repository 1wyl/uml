<template>
  <div class="edit-task">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>编辑任务</span>
      </div>
      <el-form :model="taskForm" label-width="100px">
        <el-form-item label="任务名称">
          <el-input v-model="taskForm.name"></el-input>
        </el-form-item>
        <el-form-item label="截止时间">
          <el-input v-model="taskForm.ddl"></el-input>
        </el-form-item>
        <el-form-item label="完成状态">
          <el-switch v-model="taskForm.completed"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveTask">保存</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { edittask } from '@/api/task';
import { fetchtask } from '@/api/task';
export default {
  data() {
    return {
      taskForm: {
        id: null,
        ddl:'',
        name: '',
        completed: false
      }
    };
  },
  created() {
    this.initializeTask();
  },
  methods: {
    initializeTask() {
      const taskId = this.$route.query.taskId;
      console.log(taskId)
      if (taskId) {
        this.fetchTask(taskId);
      }
    },
    fetchTask(taskId) {
      fetchtask(taskId)
          .then(response => {
            this.taskForm = response;
          })
          .catch(error => {
            this.$message.error('加载任务信息失败');
          });
    },
    saveTask() {
      edittask(this.taskForm)
          .then(() => {
            this.$message.success('任务已更新');
             this.$router.push('/todo/list'); 
          })
          .catch(error => {
            this.$message.error('任务更新失败');
            console.error('Error:', error);
          });
    },
    cancel() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.edit-task {
  display: flex;
  justify-content: center; 
}
.box-card {
  width: 400px;
}
</style>
