<template>
  <div class="add-image-page">
    <h2>上传图片</h2>
    <ImageUploader @update = "getPics"/>

    <el-form ref="imageForm" :model="imageForm" label-width="100px" style="margin-top: 20px;">
      <el-form-item label="描述">
        <el-input v-model=description></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">确认上传</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import ImageUploader from '@/components/imageUploader/index.vue';
import { getAccessToken } from '@/utils/auth';
export default {
  components: {
    ImageUploader
  },
  data() {
    return {
    fileList:[],
    description:'',
    headers: {
    Authorization: 'Bearer ' + getAccessToken()
      }
    }
 },

  methods: {
    getPic(files){
      this.fileList=files
    },
    submitForm() {
  const formData = new FormData();
  this.fileList.forEach(file => {
    formData.append('files', file);
  });
  formData.append('description', this.description);

  axios.post('/api/source/uploadImage', formData, {
    headers: {
      'Authorization': 'Bearer ' + localStorage.getAccessToken(),
      'Content-Type': 'multipart/form-data'
    }
  })
  .then(response => {
    this.$message.success("图片上传成功");
    console.log('上传成功', response);
  }).catch(error => {
    this.$message.error("图片上传失败");
    console.error('上传失败', error);
  });
},

    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.add-image-page {
  padding: 20px;
}
.upload-demo {
  margin-bottom: 20px;
}
</style>