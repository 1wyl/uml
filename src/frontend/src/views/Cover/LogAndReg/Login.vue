<template>
  <div class="login-container">
    <div class="login-box">
      <h2 class="login-title">智慧养老系统</h2>
      <el-form ref="loginForm" :model="loginForm" label-width="100px" class="login-form">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item class="login-buttons">
          <el-button type="primary" @click="submitForm">登录</el-button>
          <el-button type="primary" @click="registerForm">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from '@/api/login.js'

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    submitForm() {
      if (!this.loginForm.username || !this.loginForm.password) {
        this.$message.error('请输入用户名和密码');
        return;
      }
      login(this.loginForm)
        .then(response => {
          if (response.success === true) {
            localStorage.setItem('ACCESS_TOKEN', response.token);
            this.$message.success('登录成功');
            if (response.message === 1)
              this.$router.push('/profile');
            else if (response.message === 2)
              this.$router.push('/worker/profile');
            else if (response.message === 3)
              this.$router.push('/admin/profile');
          } else {
            this.$message.error('用户名或密码错误');
          }
        })

        .catch(error => {
          this.$message.error('网络出现问题');
        });
    },
    registerForm() {
      this.$router.push('/register');
    }
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: url('@/assets/image4.jpeg') no-repeat center center;
  background-size: cover;
}

.login-box {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.login-title {
  color: #333;
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  width: 100%;
}

.login-form .el-form-item {
  width: 100%;
  margin-left: -30px;
}

.login-buttons {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-left: -80px;
}

.login-buttons .el-button {
  margin: 0 35px;
}
</style>