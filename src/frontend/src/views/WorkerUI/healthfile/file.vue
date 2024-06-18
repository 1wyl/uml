<template>
    <div class="user-list-container">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>用户列表</span>
        </div>
        <el-table :data="users" style="width: 100%">
          <el-table-column prop="username" label="用户名" width="300"></el-table-column>
          <el-table-column prop="name" label="姓名" width="300"></el-table-column>
          <el-table-column prop="age" label="年龄" width="300"></el-table-column>
          <el-table-column prop="gender" label="性别" width="300"></el-table-column>
          <el-table-column label="健康档案" width="100">
            <template slot-scope="scope">
              <el-button
                type="primary"
                shape="circle"
                icon="el-icon-document"
                @click="viewHealthRecord(scope.row.uid)">
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </template>
  
  <script>
  import { getUsers } from '@/api/user';
  
  export default {
    data() {
      return {
        users: []
      };
    },
    created() {
      this.fetchUsers();
    },
    methods: {
      fetchUsers() {
        getUsers().then(response => {
          this.users = response.data;
        }).catch(error => {
          this.$message.error('无法获取用户列表');
        });
      },
      viewHealthRecord(uid) {
        this.$router.push({ path: '/worker/healthfile', query: { uid } });
      }
    }
  };
  </script>
  
  <style scoped>
  .user-list-container {
    padding: 20px;
  }
  </style>
  