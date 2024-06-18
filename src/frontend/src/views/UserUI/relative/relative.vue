<template>
  <div class="relative-page">
    <h2>亲人信息</h2>
    <div class="relative-cards">
      <el-card v-for="relative in relatives" :key="relative.rid" class="relative-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>{{ relative.relation }}</span>
          <el-button type="text" class="edit-button" icon="el-icon-edit" @click="editRelative(relative)">编辑</el-button>
        </div>
        <div class="relative-info">
          <p><strong>姓名：</strong>{{ relative.name }}</p>
          <p><strong>年龄：</strong>{{ relative.age }}</p>
          <p><strong>性别：</strong>{{ relative.gender }}</p>
          <p><strong>关系：</strong>{{ relative.relation }}</p>
          <p><strong>职业：</strong>{{ relative.job }}</p>
        </div>
      </el-card>
    </div>
    <div class="button-group">
      <el-button type="primary" @click="addRelative">添加亲人信息</el-button>
    </div>
  </div>
</template>

<script>
import { relative } from '@/api/relative';
export default {
  data() {
    return {
      relatives: []
    };
  },
  mounted() {
    this.fetchRelativeInfo();
  },
  methods: {
    fetchRelativeInfo() {
      relative()
        .then(response => {
          this.relatives = response;
        })
        .catch(error => {
          console.error('获取亲人信息失败:', error);
        });
    },
    addRelative() {
      this.$router.push('/relative/addRelative');
      console.log("添加亲人信息");
    },
    editRelative(relative) {
      this.$router.push({ path: '/relative/editRelative', query: { rid: relative.rid } });
      console.log("编辑亲人信息", relative);
    }
  }
};
</script>

<style scoped>
.relative-page {
  padding: 20px;
  background-color: #f5f5f5;
  min-height: calc(100vh - 60px); /* Adjust for header height */
  overflow-y: auto; /* Enable vertical scrolling */
}

h2 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 20px;
}

.relative-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.relative-card {
  width: calc(100% / 2 - 20px); /* Two cards per row with a gap */
  margin-bottom: 20px;
  background-color: #fff;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.relative-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.relative-card .el-card__header {
  background-color: #3498db;
  color: white;
  border-radius: 10px 10px 0 0;
  padding: 10px;
}

.edit-button {
  float: right;
  color: white;
}

.relative-info {
  padding: 20px;
  font-size: 14px;
}

.button-group {
  position: fixed; /* Fixed positioning */
  bottom: 20px; /* 20px from the bottom */
  right: 20px; /* 20px from the right */
  z-index: 1000; /* Ensure the button group is on top */
}
</style>
