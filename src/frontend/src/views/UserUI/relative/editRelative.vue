<template>
    <div class="edit-relative">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>编辑亲人信息</span>
        </div>
        <el-form :model="relativeForm" label-width="100px">
          <el-form-item label="姓名">
            <el-input v-model="relativeForm.name"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model.number="relativeForm.age"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="relativeForm.gender">
              <el-radio label="男">男</el-radio>
              <el-radio label="女">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="关系">
            <el-input v-model="relativeForm.relation"></el-input>
          </el-form-item>
          <el-form-item label="职业">
            <el-input v-model="relativeForm.job"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveRelative">保存</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
import { getrelative } from '@/api/relative';
import { saverelative } from '@/api/relative';
  export default {
    data() {
      return {
        relativeForm: {
          rid: null,
          id: null,
          name: '',
          age: null,
          gender: '',
          relation: '',
          job: ''
        }
      };
    },
    created() {
      this.initializeRelative();
    },
    methods: {
      initializeRelative() {
        const rid = this.$route.query.rid;
        if (rid) {
          this.fetchRelative(rid);
        }
      },
      fetchRelative(rid) {
        getrelative(rid)
          .then(response => {
            this.relativeForm = response;
          })
          .catch(error => {
            console.error('Error fetching relative:', error);
            // 处理错误
          });
      },
      saveRelative() {
        saverelative(this.relativeForm)
          .then(response => {
            this.$message.success('亲人信息已更新');
            this.$router.go(-1);
          })
          .catch(error => {
            console.error('Error saving relative:', error);
            this.$message.error('保存亲人信息失败');
          });
      },
      cancel() {
        this.$router.go(-1);
      }
    }
  };
  </script>
  
  <style scoped>
  .edit-relative {
    display: flex;
    justify-content: center; 
  }
  .box-card {
    width: 400px;
  }
  </style>
  