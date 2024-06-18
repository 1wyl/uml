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
          <!-- <el-descriptions-item label="出生日期">{{ userInfo.birthDate }}</el-descriptions-item> -->
          <el-descriptions-item label="地址">{{ userInfo.address }}</el-descriptions-item>
          <el-descriptions-item label="电话">{{ userInfo.phone }}</el-descriptions-item>
          <el-descriptions-item label="个人描述">{{ userInfo.description }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getInfo } from "@/api/profile";

export default {
  data() {
    return {
      userInfo: {
        name: "",
        age: null,
        gender: "",
        birthDate: "",
        address: "",
        phone: "",
        description: ""
      },
      editDialogVisible: false,
      editForm: {
        name: "",
        age: null,
        gender: "",
        birthDate: "",
        address: "",
        phone: "",
        description: ""
      },
      editRules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        age: [
          { required: true, message: "请输入年龄", trigger: "blur" }
          // { type: "number", message: "年龄必须为数字值" }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        birthDate: [{ required: true, message: "请选择出生日期", trigger: "change" }],
        address: [{ required: true, message: "请输入地址", trigger: "blur" }],
        phone: [{ required: true, message: "请输入电话", trigger: "blur" }],
        description: [{ required: true, message: "请输入个人描述", trigger: "blur" }]
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
            age: data.age,
            gender: data.gender,
            birthDate: data.birthDate,
            address: data.address,
            phone: data.phone,
            description: data.description
          };
        })
        .catch(error => {
          console.error("信息获取失败：", error);
        });
    },
    editInfo() {
      this.$router.push('/profile/editProfile');
    },
    saveEdit() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          save(this.editForm)
            .then(() => {
              this.$message({
                type: "success",
                message: "个人信息已更新"
              });
              this.userInfo = { ...this.editForm };
              this.editDialogVisible = false;
            })
            .catch(error => {
              console.error("个人信息更新失败：", error);
              this.$message.error("个人信息更新失败");
            });
        }
      });
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
