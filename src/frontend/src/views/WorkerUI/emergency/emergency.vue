<template>
    <div class="event-list">
      <el-table :data="events" border>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="时间" prop="time"></el-table-column>
        <el-table-column label="地址" prop="address"></el-table-column>
        <el-table-column label="位置" prop="location"></el-table-column>
        <el-table-column label="描述" prop="description"></el-table-column>
        <el-table-column label="电话" prop="phone"></el-table-column>
        <el-table-column label="是否解决" prop="isSolved">
          <template slot-scope="scope">
            <span v-if="scope.row.isSolved !== null">{{ scope.row.isSolved ? '是' : '否' }}</span>
            <span v-else>否</span>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </template>
  
  <script>
  import { fetchemer } from '@/api/emer';
  
  export default {
    data() {
      return {
        events: [],
        currentPage: 1,
        pageSize: 10,
        total: 0
      };
    },
    created() {
      this.fetchEvents();
    },
    methods: {
      fetchEvents() {
        fetchemer()
          .then(response => {
            this.events = response.data;
            this.total = response.data.length;
          })
          .catch(error => {
            console.error('Error fetching events:', error);
          });
      },
      handleCurrentChange(page) {
        this.currentPage = page;
      }
    }
  };
  </script>
  
  <style scoped>
  .event-list {
    padding: 20px;
  }
  </style>
  