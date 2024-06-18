<template>
  <div class="activity-list">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>活动管理</span>
        <el-button type="primary" @click="showAddActivityModal" style="float: right;">添加活动</el-button>
      </div>

      <el-table :data="activities" style="width: 100%">
        <el-table-column prop="name" label="活动名称" width="300"></el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="300" :formatter="formatDate"></el-table-column>
        <!-- <el-table-column prop="uploadTime" label="上传时间" width="300"></el-table-column> -->
        <!-- <el-table-column label="修改操作" width="300">
          <template slot-scope="scope">
            <el-button size="mini" @click="editActivity(scope.row)">修改</el-button>
          </template>
        </el-table-column> -->
        <el-table-column prop="location" label="活动地点" width="300"></el-table-column>
        <el-table-column label="删除操作" width="300">
          <template slot-scope="scope">
            <el-button size="mini" @click="deleteActivity(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>
  </div>
</template>

<script>
import { fetchActivities, deleteActivity } from '@/api/activity';

export default {
  data() {
    return {
      activities: []
    };
  },
  created() {
    this.fetchActivities();
  },
  methods: {
    fetchActivities() {
      fetchActivities()
        .then(response => {
          this.activities = response.data;
        })
        .catch(error => {
          this.$message.error('获取活动列表失败');
        });
    },
    showAddActivityModal() {
      // 打开一个模态框或其他界面用于添加新任务
      this.$router.push('/worker/activity/add'); // 假设你有一个路由用于添加活动
    },
    editActivity(activity) {
      // 导航到编辑活动页面，使用 query 参数传递 activityId
      this.$router.push({
        path: '/activity/edit',
        query: { activityId: activity.aid }
      });
    },
    deleteActivity(activity) {
      deleteActivity(activity)
        .then(response => {
          this.$message.success('活动删除成功');
          this.fetchActivities();
        })
        .catch(error => {
          this.$message.error('活动删除失败');
        });
    },
    formatDate(row, column) {
      // 使用 Vue 的过滤器来格式化日期
      if (!row.startTime) return ''; // 处理空日期情况
      const date = new Date(row.startTime);
      return date.toLocaleString(); // 格式化日期时间
    }
  }
};
</script>
