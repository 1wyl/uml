<template>
  <el-card class="user-card" shadow="hover">
    <div slot="header" class="clearfix">
      <span>编辑个人信息</span>
    </div>
    <el-form :model="userForm" label-position="left" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="userForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="userForm.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker v-model="userForm.birthDate" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="个人描述">
        <el-input v-model="userForm.description"></el-input>
      </el-form-item>
    </el-form>
    <div class="button-group">
      <el-button type="primary" @click="saveForm">Save</el-button>
      <el-button @click="cancelForm">Cancel</el-button>
    </div>
  </el-card>
</template>

<script>
import {getInfo} from '@/api/profile';
import {save} from '@/api/profile';
export default {
  data() {
    return {
      userForm: {
        name: '',
        gender: '',
        birthDate: '',
        description:''
      }
    };
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      getInfo()
      .then(response => {
        const data = response.data;
        this.userForm = {
          name: data.name,
          gender: data.gender,
          birthDate: data.birthDate,
          description:data.description
        };
      })
      .catch(error => {
        console.error('信息获取失败：', error);
      });
    },
    saveForm() {
      save(this.userForm)
      .then(response => {
        if(response.code === 200){
          this.$message.success("信息修改成功")
          this.$router.push('/profile')
        }
        else{
          this.$message.error("信息修改失败")
          this.$router.push('/profile')
        }
      })
      .catch(error => {
        console.error('网络异常', error);
      });
    },
    cancelForm() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.user-card {
  max-width: 600px;
  margin: 0 auto;
}
.button-group {
  margin-top: 20px;
  text-align: right;
}
</style>
