<template>
  <div class="layout">
    <Sider :isCollapsed="isCollapsed" />
    <div :class="['main', { 'collapsed': isCollapsed }]">
      <Header :isCollapsed="isCollapsed" @toggle-collapse="handleToggleCollapse" />
      <div class="header-placeholder"></div>
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './Components/header.vue';
import Sider from './Components/sider.vue';

export default {
  components: {
    Header,
    Sider
  },
  data() {
    return {
      isCollapsed: false
    };
  },
  methods: {
    handleToggleCollapse() {
      this.isCollapsed = !this.isCollapsed;
    }
  }
};
</script>

<style scoped>
.layout {
  display: flex;
  height: 100vh;
  background-color: #f0f2f5;
}

.main {
  display: flex;
  flex-direction: column;
  flex: 1;
  transition: margin-left 0.3s, background-color 0.3s;
  margin-left: 220px; /* 侧边栏全宽状态下的左边距 */
}

.main.collapsed {
  margin-left: 60px; /* 侧边栏收缩状态下的左边距 */
}

.header-placeholder {
  height: 60px; /* header的高度 */
}

.content {
  flex: 1;
  padding: 20px;
  background-color: #ffffff;
  overflow: auto;
  height: calc(100vh - 60px); /* 调整以适应header的高度 */
  box-sizing: border-box;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s, background-color 0.3s;
}

.content:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.content::-webkit-scrollbar {
  width: 8px;
}

.content::-webkit-scrollbar-thumb {
  background-color: #c1c1c1;
  border-radius: 4px;
}

.content::-webkit-scrollbar-track {
  background-color: #f5f5f5;
}
</style>
