<template>
    <div>
      <el-upload
        ref="upload"
        multiple
        list-type="picture-card"
        :before-upload="handleBeforeUpload"
        :limit="limit"
        :file-list="fileList"
        @remove="handleRemove"
        slot="tip" 
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <div class="el-upload__tip" v-if="showTip"> <!-- 删除 slot="tip" -->
  请上传
  <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
  <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
  的文件
</div>

    <el-form ref="imageForm" :model="imageForm" label-width="100px" style="margin-top: 20px;">
      <el-form-item label="描述">
        <el-input v-model="description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">确认上传</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
      <!-- <el-input
        v-model="description"
        placeholder="Description"
        class="description-input"
      ></el-input>
      <el-button type="primary" @click="submitForm" class="send-button">
        Send
      </el-button> -->
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { getAccessToken } from "@/utils/auth";
  import { updateImage } from '@/api/source';
  export default {
    props: {
    limit: {
      type: Number,
      default: 5,
    },
    fileSize: {
      type: Number,
      default: 10,
    },
    fileType: {
      type: Array,
      default: () => ["png", "jpg", "jpeg"],
    },
  },
    data() {
      return {
        fileList: [],
        description: '',
      };
    },
    methods: {
        submitForm() {
    const formData = new FormData();
    this.fileList.forEach(file => {
      formData.append('files', file);
    });
    formData.append('description', this.description);

    axios.post('/api/source/uploadImage', formData, {
      headers: {
        'Authorization': 'Bearer ' + getAccessToken(),
        'Content-Type': 'multipart/form-data'
      }
    })
        .then(response => {
          this.$message.success("Images uploaded successfully");
          console.log('Upload successful', response);
          // Clear description after successful upload
          this.description = '';
        }).catch(error => {
          this.$message.error("Failed to upload images");
          console.error('Upload failed', error);
        });
      },
      handleBeforeUpload(file) {
        // Perform file type and size validation if needed
        this.fileList.push(file);
        return false; // Prevent automatic upload
      },
      handleRemove(file, fileList) {
        // Handle file removal from the fileList
        const index = fileList.indexOf(file);
        if (index !== -1) {
          fileList.splice(index, 1);
        }
      },

    }
  };
  </script>
  
  <style scoped>
  .description-input {
    margin-top: 10px;
  }
  .send-button {
    margin-top: 10px;
  }
  </style>
  