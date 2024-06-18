<!-- <template>
  <div class="register-container">
    <el-divider class="blue-title">新用户注册</el-divider>
    <el-form ref="userForm" :model="userForm" label-width="100px" class="register-form">
      <el-form-item label="用户名" prop="username" required>
        <el-input v-model="userForm.username" @blur="validateUsername"></el-input>
        <span v-if="usernameError" class="error-message">{{ usernameError }}</span>
        <span v-else class="tip-message">用户名长度为3～10个字符，可包含字母、数字、下划线</span>
        <span v-if="isUsernameValid" class="success-icon">✔️</span>
      </el-form-item>
      <el-form-item label="密码" prop="password" required>
        <el-input type="password" v-model="userForm.password" @blur="validatePassword"></el-input>
        <span v-if="passwordError" class="error-message">{{ passwordError }}</span>
        <span v-else class="tip-message">密码长度为6～15个字符，可包含字母、数字、下划线和！、@、#特殊字符</span>
        <span v-if="isPasswordValid" class="success-icon">✔️</span>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="confirmPassword" @blur="validateConfirmPassword"></el-input>
        <span v-if="confirmPasswordError" class="error-message">{{ confirmPasswordError }}</span>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="userForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="userForm.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="注册角色" prop="role" required>
        <el-radio-group v-model="userForm.role">
          <el-radio label="1">老人用户</el-radio>
          <el-radio label="2">工作人员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker v-model="userForm.birthDate" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>
      <el-form-item class="register-buttons">
        <el-button type="primary" @click="submitForm">注册</el-button>
        <el-button @click="cancel">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { register } from '@/api/login.js'

export default {
  data() {
    return {
      userForm: {
        username: '',
        password: '',
        name: '',
        gender: '',
        role: null,
        birthDate: '',
      },
      confirmPassword: '',
      usernameError: '',
      passwordError: '',
      confirmPasswordError: '',
      isUsernameValid: false,
      isPasswordValid: false
    };
  },
  methods: {
    validateUsername() {
      if (this.userForm.username.length < 3 || this.userForm.username.length > 10) {
        this.usernameError = '用户名不符合规范';
        this.isUsernameValid = false;
      } else if (!/^[a-zA-Z0-9_]+$/.test(this.userForm.username)) {
        this.usernameError = '用户名不符合规范';
        this.isUsernameValid = false;
      } else {
        this.usernameError = '';
        this.isUsernameValid = true;
      }
    },
    validatePassword() {
      if (this.userForm.password.length < 6 || this.userForm.password.length > 15) {
        this.passwordError = '密码不符合规范';
        this.isPasswordValid = false;
      } else if (!/^[a-zA-Z0-9!@#]+$/.test(this.userForm.password)) {
        this.passwordError = '密码不符合规范';
        this.isPasswordValid = false;
      } else {
        this.passwordError = '';
        this.isPasswordValid = true;
      }
    },
    validateConfirmPassword() {
      if (this.confirmPassword !== this.userForm.password) {
        this.confirmPasswordError = '两次输入的密码不一致';
      } else {
        this.confirmPasswordError = '';
      }
    },
    submitForm() {
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          register(this.userForm)
            .then(response => {
              this.$message.success('注册成功');
              this.$router.push('/login');
            })
            .catch(error => {
              this.$message.error('注册失败，用户名重复或后端未启动');
            });
        } else {
          this.$message.error('请正确填写表单');
          return false;
        }
      });
    },
    cancel() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: url('@/assets/image5.jpeg') no-repeat center center;
  background-size: cover;
}

.top-image {
  width: 100px; /* 调整图片的宽度 */
  height: auto;
  margin-bottom: 20px;
}

.blue-title {
  color: #409EFF;
  font-size: 18px;
  margin-bottom: 20px;
}

.register-form {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 800px;
}

.register-buttons {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 20px;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
}

.tip-message {
  color: grey;
  font-size: 12px;
  margin-top: 5px;
}

.success-icon {
  color: green;
  margin-left: 10px;
}
</style>
 -->

 <template>
  <div class="register-container">
    <el-divider class="blue-title">新用户注册</el-divider>
    <el-form ref="userForm" :model="userForm" :rules="rules" label-width="100px" class="register-form">
      <el-form-item label="用户名" prop="username" required>
        <el-input v-model="userForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" required>
        <el-input type="password" v-model="userForm.password"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name" required>
        <el-input v-model="userForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender" required>
        <el-radio-group v-model="userForm.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄" prop="age" required>
        <el-input-number v-model="userForm.age" :min="0" :max="120"></el-input-number>
      </el-form-item>
      <el-form-item label="注册角色" prop="role" required>
        <el-radio-group v-model="userForm.role">
          <el-radio label="1">老人用户</el-radio>
          <el-radio label="2">工作人员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="register-buttons">
        <el-button type="primary" @click="submitForm">注册</el-button>
        <el-button @click="cancel">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { register } from '@/api/login.js'

export default {
  data() {
    return {
      userForm: {
        username: '',
        password: '',
        name: '',
        gender: '',
        age: null,
        role: null,
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
        age: [{ required: true, type: 'number', message: '请输入年龄', trigger: 'blur' }],
        role: [{ required: true, message: '请选择注册角色', trigger: 'change' }]
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          register(this.userForm)
            .then(response => {
              this.$message.success('注册成功');
              this.$router.push('/login');
            })
            .catch(error => {
              this.$message.error('注册失败，用户名重复或后端未启动');
            });
        } else {
          this.$message.error('请正确填写表单');
          return false;
        }
      });
    },
    cancel() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: url('@/assets/image5.jpeg') no-repeat center center;
  background-size: cover;
}

.top-image {
  width: 100px; /* 调整图片的宽度 */
  height: auto;
  margin-bottom: 20px;
}

.blue-title {
  color: #409EFF;
  font-size: 18px;
  margin-bottom: 20px;
}

.register-form {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 800px;
}

.register-buttons {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 20px;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
}

.tip-message {
  color: grey;
  font-size: 12px;
  margin-top: 5px;
}

.success-icon {
  color: green;
  margin-left: 10px;
}
</style>
