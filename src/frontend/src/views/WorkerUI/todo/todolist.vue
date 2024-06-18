<template>
  <div class="task-list">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户请求列表</span>
      </div>

      <el-table :data="paginatedRequests" style="width: 100%">
        <el-table-column prop="name" label="姓名" width="150"></el-table-column>
        <el-table-column prop="address" label="地址" width="200"></el-table-column>
        <el-table-column prop="requestTime" label="请求时间" width="180"></el-table-column>
        <el-table-column prop="phone" label="电话" width="150"></el-table-column>
        <el-table-column prop="serviceType" label="服务类型" width="100"></el-table-column>
        <el-table-column prop="productName" label="产品名称" width="150"></el-table-column>
        <el-table-column label="完成状态" width="120">
          <template slot-scope="scope">
            <el-tag 
              :type="scope.row.completionStatus ? 'success' : 'info'" 
              @click="toggleCompletion(scope.row)"
              style="cursor: pointer;"
            >
              {{ scope.row.completionStatus ? '已完成' : '未完成' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        background
        layout="prev, pager, next"
        :total="requests.length"
        :page-size="pageSize"
        @current-change="handleCurrentChange"
      ></el-pagination>

    </el-card>
  </div>
</template>

<!-- <template>
  <div class="task-list">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户请求列表</span>
      </div>

      <el-table :data="paginatedRequests" style="width: 100%">
        <el-table-column prop="name" label="姓名" width="150"></el-table-column>
        <el-table-column prop="address" label="地址" width="200"></el-table-column>
        <el-table-column prop="requestTime" label="请求时间" width="180"></el-table-column>
        <el-table-column prop="phone" label="电话" width="150"></el-table-column>
        <el-table-column prop="serviceType" label="服务类型" width="100"></el-table-column>
        <el-table-column prop="productName" label="产品名称" width="150"></el-table-column>
        <el-table-column label="完成状态" width="120">
          <template slot-scope="scope">
            <el-tag 
              :type="scope.row.completionStatus ? 'success' : 'info'" 
              @click="toggleCompletion(scope.row)"
              style="cursor: pointer;"
            >
              {{ scope.row.completionStatus ? '已完成' : '未完成' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        background
        layout="prev, pager, next"
        :total="requests.length"
        :page-size="pageSize"
        @current-change="handleCurrentChange"
      ></el-pagination>

    </el-card>
  </div>
</template> -->


<script>
import { fetchtasks } from '@/api/task'; // Adjust the import paths according to your project structure
import {toggle} from '@/api/task';

// export default {
//   data() {
//     return {
//       requests: [],
//       currentPage: 1,
//       pageSize: 10, // 每页显示的条数
//     };
//   },
//   created() {
//     this.fetchRequests();
//   },
//   computed: {
//     paginatedRequests() {
//       const start = (this.currentPage - 1) * this.pageSize;
//       const end = this.currentPage * this.pageSize;
//       return this.requests.slice(start, end);
//     }
//   },
//   methods: {
//     fetchRequests() {
//       fetchtasks()
//         .then(response => {
//           this.requests = response;
//         })
//         .catch(error => {
//           this.$message.error('获取请求列表失败');
//         });
//     },
//     toggleCompletion(request) {
//       toggle(request.completionStatus, request.rid)
//         .then(() => {
//           request.completionStatus = !request.completionStatus;
//           this.$message.success(request.completionStatus ? '请求已完成' : '请求状态已更新为未完成');
//         })
//         .catch(() => {
//           this.$message.error('更新请求状态失败');
//         });
//     },
//     handleCurrentChange(page) {
//       this.currentPage = page;
//     }
//   }
// };
export default {
  data() {
    return {
      requests: [],
      currentPage: 1,
      pageSize: 10, // 每页显示的条数
    };
  },
  created() {
    this.fetchRequests();
  },
  computed: {
    sortedRequests() {
      return this.requests.sort((a, b) => a.completionStatus - b.completionStatus);
    },
    paginatedRequests() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = this.currentPage * this.pageSize;
      return this.sortedRequests.slice(start, end);
    }
  },
  methods: {
    fetchRequests() {
      fetchtasks()
        .then(response => {
          this.requests = response;
        })
        .catch(error => {
          this.$message.error('获取请求列表失败');
        });
    },
    toggleCompletion(request) {
      toggle(request.completionStatus, request.rid)
        .then(() => {
          request.completionStatus = !request.completionStatus;
          this.$message.success(request.completionStatus ? '请求已完成' : '请求状态已更新为未完成');
        })
        .catch(() => {
          this.$message.error('更新请求状态失败');
        });
    },
    handleCurrentChange(page) {
      this.currentPage = page;
    }
  }
};

</script>
