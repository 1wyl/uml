<template>
  <div :class="['sider', { collapsed: isCollapsed }]">
    <el-menu
      :collapse="isCollapsed"
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      background-color="#1a202c"
      text-color="#cbd5e0"
      active-text-color="#68d391"
    >
      <el-menu-item
        v-for="(item, index) in menuList"
        :key="index"
        :index="item.path"
        @click="handleMenuClick(item.path)"
      >
        <i class="el-icon-menu"></i>
        <span slot="title">{{ item.menu }}</span>
      </el-menu-item>
      
      <el-menu-item index="/logout" @click="logout">
        <i class="el-icon-switch-button"></i>
        <span slot="title">退出</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { removeToken } from "@/utils/auth";
import { getUserMenu } from "@/api/menu"; // 根据实际路径修改

export default {
  name: "Sider",
  props: {
    isCollapsed: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      menuList: []
    };
  },
  mounted() {
    this.fetchMenu();
  },
  methods: {
    async fetchMenu() {
      try {
        const response = await getUserMenu(); // 获取菜单列表
        this.menuList = response.data;
      } catch (error) {
        console.error("Failed to fetch menu:", error);
      }
    },
    handleMenuClick(path) {
      if (this.$route.path !== path) {
        this.$router.push(path);
      }
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
.sider {
  width: 220px;
  height: 100%;
  background-color: #1a202c;
  position: fixed;
  left: 0;
  top: 5%;
  overflow-x: hidden;
  transition: width 0.3s;
  z-index: 1000;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.sider.collapsed {
  width: 60px;
}

.el-menu-vertical-demo {
  background-color: transparent !important;
  border: none !important;
  margin-top: 20px;
  transition: all 0.3s;
  width: auto;
}

.el-menu-vertical-demo .el-menu-item {
  height: 50px;
  line-height: 50px;
}

.el-menu-vertical-demo .el-menu-item:hover {
  background-color: #2d3748 !important;
}

.el-menu-vertical-demo .el-menu-item .el-icon {
  margin-right: 10px;
}

.el-menu-vertical-demo .el-submenu__title .el-icon-arrow-down {
  float: right;
}
</style>
