<template>
    <div class="header">
      <div class="left-section">
        <i :class="[isCollapsed ? 'el-icon-s-unfold' : 'el-icon-s-fold']" class="collapse-icon" @click="toggleCollapse"></i>
        <img src="../../assets/logo.png" alt="Logo" class="logo" />
        <span class="title">智慧养老社区</span>
      </div>
      <div class="right-section">
        <el-dropdown>
          <span class="el-dropdown-link">
            <i class="el-icon-user"></i>
            <span>Username</span>
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click="handleMenuClick('/profile/info')">Profile</el-dropdown-item>
            <el-dropdown-item @click="logout">Logout</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </template>
  
  <script>
  import { removeToken } from "@/utils/auth";
  
  export default {
    name: "Header",
    props: {
      isCollapsed: {
        type: Boolean,
        required: true
      }
    },
    methods: {
      toggleCollapse() {
        this.$emit('toggle-collapse');
      },
      handleMenuClick(path) {
        this.$router.push(path);
      },
      logout() {
        this.$confirm("确定注销并退出系统吗？", "提示").then(() => {
          removeToken();
          this.$router.push({ path: "/" }); // Redirect to login page
        }).catch(() => {});
      }
    }
  };
  </script>
  
  <style lang="scss" scoped>
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    height: 60px;
    background-color: #1a202c;
    color: #cbd5e0;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    z-index: 1000;
    position: fixed;
    width: 100%;
    top: 0;
    left: 0;
  }
  
  .left-section {
    display: flex;
    align-items: center;
  }
  
  .collapse-icon {
    font-size: 20px;
    cursor: pointer;
    margin-right: 20px;
    color: #cbd5e0;
  }
  
  .collapse-icon:hover {
    color: #68d391;
  }
  
  .logo {
    height: 40px;
    margin-right: 10px;
  }
  
  .title {
    font-size: 18px;
    font-weight: bold;
  }
  
  .right-section {
    display: flex;
    align-items: center;
  }
  
  .el-dropdown-link {
    display: flex;
    align-items: center;
    cursor: pointer;
    color: #cbd5e0;
  }
  
  .el-dropdown-link i {
    margin-right: 5px;
  }
  
  .el-dropdown-link:hover {
    color: #68d391;
  }
  </style>
  