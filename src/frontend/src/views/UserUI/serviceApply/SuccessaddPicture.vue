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
      :on-exceed="handleExceed"
    >
      <i class="el-icon-plus"></i>
      <div slot="tip" class="el-upload__tip" v-if="showTip">
        请上传
        <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
        <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
        的文件
      </div>
    </el-upload>

    <el-form ref="imageForm" :model="imageForm" label-width="100px" style="margin-top: 20px;">
      <el-form-item label="描述">
        <el-input v-model="description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">确认上传</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';
import { getAccessToken } from "@/utils/auth";

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
      showTip: true
    };
  },
  methods: {
    submitForm() {
      const formData = new FormData();
      this.fileList.forEach(file => {
        formData.append('files', file.raw);
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
        // Clear file list and description after successful upload
        this.fileList = [];
        this.description = '';
      })
      .catch(error => {
        this.$message.error("Failed to upload images");
        console.error('Upload failed', error);
      });
    },
    handleBeforeUpload(file) {
      const isValidType = this.fileType.includes(file.type.split('/')[1].toLowerCase());
      const isValidSize = file.size / 1024 / 1024 < this.fileSize;

      if (!isValidType) {
        this.$message.error(`文件格式不正确, 请上传 ${this.fileType.join("/")} 格式的文件!`);
        return false;
      }

      if (!isValidSize) {
        this.$message.error(`上传文件大小不能超过 ${this.fileSize} MB!`);
        return false;
      }

      this.fileList.push({ name: file.name, url: URL.createObjectURL(file), raw: file });
      return false; // Prevent automatic upload
    },
    handleRemove(file) {
      this.fileList = this.fileList.filter(item => item.name !== file.name);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`最多只能上传 ${this.limit} 个文件！`);
    },
    goBack() {
      this.$router.go(-1);
    }
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
