<template>
  <div>
    <el-card class="user-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span class="card-header">个人信息</span>
        <el-button type="text" class="edit-button" icon="el-icon-edit" @click="editInfo">编辑个人信息</el-button>
      </div>
      <div class="info-section">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="姓名">{{ userInfo.name }}</el-descriptions-item>
          <el-descriptions-item label="年龄">{{ userInfo.age }}</el-descriptions-item>
          <el-descriptions-item label="性别">{{ userInfo.gender }}</el-descriptions-item>
          <el-descriptions-item label="个人描述">{{ userInfo.description }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
  </div>
</template>

<script>
import {getInfo} from '@/api/profile';
export default {
  data() {
    return {
      userInfo: {
        name: '',
        age: null,
        gender: '',
        description: ''
      }
    };
  },
  mounted() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      getInfo()
        .then(response => {
          const data = response.data;
          this.userInfo = {
            name: data.name,
            age: this.calculateAge(data.birthDate),
            gender: data.gender,
            description: data.description
          };
        })
        .catch(error => {
          console.error('信息获取失败：', error);
        });
    },
    calculateAge(birthDate) {
      const birth = new Date(birthDate);
      const today = new Date();
      let age = today.getFullYear() - birth.getFullYear();
      const monthDiff = today.getMonth() - birth.getMonth();
      if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
        age--;
      }
      return age;
    },
    editInfo() {
      this.$router.push('/profile/editProfile');
    }
  }
};
</script>

<style scoped>
.user-card {
  max-width: 600px;
  margin: 0 auto;
}
.card-header {
  font-size: 18px;
}
.edit-button {
  float: right;
}
.info-section {
  margin-top: 20px;
}
.button-section {
  margin-top: 20px;
  text-align: center;
}
</style>
