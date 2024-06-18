<template>
  <div>
      <div class="side-bar">
          <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
              <el-menu-item index="/profile/info" @click="$router.push('/profile')">
                  <i class="el-icon-house"></i>
                  <span slot="title">个人主页</span>
              </el-menu-item>
              <el-menu-item index="/relative/info" @click="$router.push('/relative/info')" >
                  <i class="el-icon-menu"></i>
                  <span slot="title">亲人信息</span>
              </el-menu-item>
              <el-menu-item index="/source/picture" @click="$router.push('/source/picture')">
                  <i class="el-icon-setting"></i>
                  <span slot="title">资源展示</span>
              </el-menu-item>
              <el-menu-item index="/ai/chat" @click="$router.push('/ai/chat')">
                  <i class="el-icon-setting"></i>
                  <span slot="title">与AI聊天</span>
              </el-menu-item>
              <el-menu-item index="/health" @click="$router.push('/health')">
                  <i class="el-icon-setting"></i>
                  <span slot="title">养生知识</span>
              </el-menu-item>
              <el-menu-item index="/todo/list" @click="$router.push('/todo/list')">
                  <i class="el-icon-setting"></i>
                  <span slot="title">语音记事本</span>
              </el-menu-item>
              <el-menu-item index="/logout" @click="logout">
                  <i class="el-icon-setting"></i>
                  <span slot="title">退出</span>
              </el-menu-item>
          </el-menu>

          <div class="main-content" >
            <!-- important !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
              <router-view/>   
            <!-- important !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
          </div>
      </div>
  </div>
</template>
  
 <script>
  import { removeToken } from '@/utils/auth'
  
  export default {
    name: "MyComponent",
    methods: {
      logout() {
        this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
          removeToken()
          this.$router.push({ path: '/' }) // 重定向到登录页面
        }).catch(() => {});
      },
    },
  };
  </script>
  
  
  <style lang="scss" scoped>
  .el-menu-vertical-demo{
    position: fixed;
    z-index: 1;
    width: 10%;
    // flex:1;
  }
  
  .main-content {
  flex: 9;
  padding: 20px;
  width: 89%;
  float: right;
  // margin-left: auto;
  }

  .side-bar {
  // display: flex;

  height: 100vh;
  // position: fixed;
  }
  
  </style>
  