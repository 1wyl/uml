<template>
  <div class="container">
    <div class="add-task">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>添加活动</span>
        </div>
        <el-form :model="taskForm" @submit.native.prevent="addTask" label-width="100px">
          <el-form-item label="活动名称">
            <el-input v-model="taskForm.name" placeholder="请输入活动名称"></el-input>
          </el-form-item>
          <el-form-item label="活动地点">
            <el-input v-model="taskForm.location" placeholder="请输入活动地点"></el-input>
          </el-form-item>
          <el-form-item label="开始时间">
            <!-- <el-date-picker
              v-model="taskForm.startTime"
              type="datetime"
              :picker-options="pickerOptions"
              placeholder="选择日期时间">
            </el-date-picker> -->
            <el-date-picker
      v-model="taskForm.startTime"
      type="datetime"
      :picker-options="pickerOptions"
      placeholder="选择日期时间">
    </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addTask">添加活动</el-button>
            <el-button type="primary" @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { addActivity } from '@/api/activity';
export default {
  data() {
    return {
      taskForm: {
        name: '',
        location:'',
        startTime: '',
      },
      pickerOptions: {
        // 设置时间选择器的格式
        selectableRange: '00:00:00 - 23:59:59'
      }
    };
  },
  mounted() {
    this.customTimePicker();
  },
  methods: {
    customTimePicker() {
      const picker = this.$refs.datePicker;
      if (picker) {
        const timePanel = picker.pickerPanel.$refs.timeSpinner;
        if (timePanel) {
          // 自定义滚动逻辑，使小时和分钟的滚动是循环的
          timePanel.scrollToOption = function (type, index) {
            const el = this.$refs[type + 'List'];
            if (el) {
              const max = type === 'hour' ? 23 : 59;
              const min = 0;
              if (index > max) {
                index = min;
              } else if (index < min) {
                index = max;
              }
              el.scrollTop = index * 32;
            }
          };
        }
      }
    },
    addTask() {
      // 将时间格式转换为字符串，以便传递给后端
      this.taskForm.startTime = this.taskForm.startTime ? this.formatDateTime(this.taskForm.startTime) : null;
      addActivity(this.taskForm)
        .then(() => {
          this.$message.success('任务已添加');
          this.taskForm.name = ''; // 清空表单
          this.taskForm.startTime = ''; // 清空时间选择器
          this.$router.go(-1); // 假设你有一个路由用于添加任务
        })
        .catch(error => {
          this.$message.error('添加任务失败');
          console.error('Error:', error);
        });
    },
    cancel() {
      this.$router.go(-1);
    },
    // 格式化日期时间
    formatDateTime(dateTime) {
      const year = dateTime.getFullYear();
      const month = ('0' + (dateTime.getMonth() + 1)).slice(-2);
      const day = ('0' + dateTime.getDate()).slice(-2);
      const hours = ('0' + dateTime.getHours()).slice(-2);
      const minutes = ('0' + dateTime.getMinutes()).slice(-2);
      const seconds = ('0' + dateTime.getSeconds()).slice(-2);
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
}

.add-task {
  margin: 80px;
}

.box-card {
  width: 500px;
}
</style>
