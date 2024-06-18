   <template>
    <div class="container">
      <div class="add-task">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>添加新任务</span>
          </div>
          <el-form :model="taskForm" @submit.native.prevent="addTask" label-width="100px">
            <el-form-item label="任务名称">
              <el-input v-model="taskForm.name" placeholder="请输入任务名称"></el-input>
            </el-form-item>
            <el-form-item label="截止时间">
              <el-input v-model="taskForm.ddl" placeholder="请输入截止时间"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addTask">添加任务</el-button>
              <!-- <el-button type="text" @click="cancel">取消</el-button> -->
              <el-button type="primary" @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
  </template>
  
  <script>
  import { addtask } from '@/api/task';
  export default {
    data() {
      return {
        taskForm: {
          name: '',
          ddl:'',
          completed: 0 // 默认新任务为未完成状态
        }
      };
    },
    methods: {
      addTask() {
        addtask(this.taskForm)
          .then(() => {
            this.$message.success('任务已添加');
            this.taskForm.name = ''; // 清空表单
            this.$router.push('/todo/list'); // 假设你有一个路由用于添加任务
          })
          .catch(error => {
            this.$message.error('添加任务失败');
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
  